package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import database.DatabaseUtils;

public class BorrowTicketManagerModel {
    // Chèn dữ liệu từ database vào bảng
    public void insertDataIntoTable(DefaultTableModel defaultTableModel) throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        String query = "SELECT * FROM BorrowTicket";

        int rowCount = defaultTableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            ((DefaultTableModel) defaultTableModel).removeRow(i);
        }

        ResultSet result = connection.createStatement().executeQuery(query);
        while (result.next()) {
            String borrowTicketCode = result.getString(1);
            String reader = result.getString(2);
            String bookCode = result.getString(3);
            String status = result.getString(4);
            String borrowDate = new SimpleDateFormat("dd/MM/yyyy").format(result.getDate(5));
            String returnDate = new SimpleDateFormat("dd/MM/yyyy").format(result.getDate(6));
            ((DefaultTableModel) defaultTableModel)
                    .addRow(new Object[] { borrowTicketCode, reader, bookCode, status, borrowDate, returnDate });
        }
    }

    // Xử lý mượn sách
    public boolean borrowBook(String borrowTicketCode, String reader, String bookCode, String status, String borrowDate,
            String returnDate) throws Exception {
        Connection connection = DatabaseUtils.getConnection();

        String checkStatusQuery = "SELECT Status FROM Book WHERE Book_code = ?";
        PreparedStatement checkStatusStatement = connection.prepareStatement(checkStatusQuery);
        checkStatusStatement.setString(1, bookCode);
        ResultSet statusResult = checkStatusStatement.executeQuery();

        if (statusResult.next()) {
            String bookStatus = statusResult.getString("Status");

            if (!bookStatus.equals("0")) {
                JOptionPane.showMessageDialog(null, "Mượn không thành công, Sách đã được mượn !!!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Mượn không thành công, Không tìm thấy mã sách này !!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String query = "INSERT INTO BorrowTicket (Borrow_ticket_code, Reader, Book_code, Status, Borrow_date, Return_date) VALUES (?, ?, ?, ?, ?, ?)";

        LocalDate currentDate = LocalDate.now();

        if (status.equals("")) {
            status = "1";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (borrowDate.equals("")) {
            borrowDate = currentDate.format(formatter);
            if (returnDate.equals("")) {
                LocalDate parsedBorrowDate = LocalDate.parse(borrowDate, formatter);
                LocalDate calculatedReturnDate = parsedBorrowDate.plusDays(7);
                returnDate = calculatedReturnDate.format(formatter);
            }
        } else {
            LocalDate dateBorrow = LocalDate.parse(borrowDate, formatter);
            if (returnDate.equals("")) {
                LocalDate parsedBorrowDate = LocalDate.parse(borrowDate, formatter);
                LocalDate calculatedReturnDate = parsedBorrowDate.plusDays(7);
                returnDate = calculatedReturnDate.format(formatter);
            } else {
                LocalDate dateReturn = LocalDate.parse(returnDate, formatter);
                returnDate = dateReturn.format(formatter);
            }

            returnDate = dateBorrow.format(formatter);
        }

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, borrowTicketCode);
        statement.setString(2, reader);
        statement.setString(3, bookCode);
        statement.setString(4, status);
        statement.setString(5, borrowDate);
        statement.setString(6, returnDate);

        int rowsAffected = statement.executeUpdate();

        if (status.equals("0")) {
            String updateQuery = "UPDATE Book SET Status = '0' WHERE Book_code = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, bookCode);
            updateStatement.executeUpdate();
        } else {
            String updateQuery = "UPDATE Book SET Status = '1' WHERE Book_code = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, bookCode);
            updateStatement.executeUpdate();
        }
        return rowsAffected > 0;

    }

    // Xử lý xóa phiếu mượn
    public boolean deleteBorrowTicket(String borrowTicketCode) throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        String selectQuery = "SELECT Status FROM BorrowTicket WHERE Borrow_ticket_code = ?";
        String deleteQuery = "DELETE FROM BorrowTicket WHERE Borrow_ticket_code = ?";

        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        selectStatement.setString(1, borrowTicketCode);
        ResultSet resultSet = selectStatement.executeQuery();

        if (resultSet.next()) {
            String status = resultSet.getString("Status");

            if (status.equals("0")) {
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setString(1, borrowTicketCode);
                int rowsAffected = deleteStatement.executeUpdate();
                return rowsAffected > 0;
            } else {
                JOptionPane.showMessageDialog(null, "Xóa không thành công, phiếu mượn chưa được trả !!!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Xóa không thành công, phiếu mượn không tồn tại !!!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    // Xử lý sửa phiếu mượn
    public boolean editBorrowTicket(String borrowTicketCode, String reader, String bookCode, String status,
            String borrowDate,
            String returnDate)
            throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        String query = "UPDATE BorrowTicket SET Reader = ?, Book_code = ?, Status = ?, Borrow_date = ?, Return_date = ? WHERE Borrow_ticket_code = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, reader);
        statement.setString(2, bookCode);
        statement.setString(3, status);
        statement.setString(4, borrowDate);
        statement.setString(5, returnDate);
        statement.setString(6, borrowTicketCode);

        int rowsAffected = statement.executeUpdate();

        if (status.equals("0")) {
            String updateQuery = "UPDATE Book SET Status = '0' WHERE Book_code = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, bookCode);
            updateStatement.executeUpdate();
        } else {
            String updateQuery = "UPDATE Book SET Status = '1' WHERE Book_code = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, bookCode);
            updateStatement.executeUpdate();
        }
        return rowsAffected > 0;
    }

    // Sắp xếp tăng dần theo mã phiếu
    public void sortAscById(DefaultTableModel tableModel) throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        String query = "SELECT * FROM BorrowTicket ORDER BY Borrow_ticket_code ASC";

        ResultSet result = connection.createStatement().executeQuery(query);

        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        while (result.next()) {
            String borrowTicketCode = result.getString(1);
            String reader = result.getString(2);
            String bookCode = result.getString(3);
            String status = result.getString(4);
            String borrowDate = new SimpleDateFormat("dd/MM/yyyy").format(result.getDate(5));
            String returnDate = new SimpleDateFormat("dd/MM/yyyy").format(result.getDate(6));
            tableModel.addRow(new Object[] { borrowTicketCode, reader, bookCode, status, borrowDate, returnDate });
        }
    }

    // Sắp xếp giảm dần theo mã phiếu
    public void sortDescById(DefaultTableModel tableModel) throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        String query = "SELECT * FROM BorrowTicket ORDER BY Borrow_ticket_code DESC";

        ResultSet result = connection.createStatement().executeQuery(query);

        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        while (result.next()) {
            String borrowTicketCode = result.getString(1);
            String reader = result.getString(2);
            String bookCode = result.getString(3);
            String status = result.getString(4);
            String borrowDate = new SimpleDateFormat("dd/MM/yyyy").format(result.getDate(5));
            String returnDate = new SimpleDateFormat("dd/MM/yyyy").format(result.getDate(6));
            tableModel.addRow(new Object[] { borrowTicketCode, reader, bookCode, status, borrowDate, returnDate });
        }
    }

    // Tìm phiếu mượn theo mã phiếu mượn
    public boolean findBorrowTicket(DefaultTableModel defaultTableModel, String borrowTicketCode) throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        String query = "SELECT * FROM BorrowTicket WHERE Borrow_ticket_code = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, borrowTicketCode);

        ResultSet result = statement.executeQuery();

        int rowCount = defaultTableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            ((DefaultTableModel) defaultTableModel).removeRow(i);
        }

        if (result.next()) {
            String borrowTicketCodeString = result.getString(1);
            String reader = result.getString(2);
            String bookCode = result.getString(3);
            String status = result.getString(4);
            String borrowDate = new SimpleDateFormat("dd/MM/yyyy").format(result.getDate(5));
            String returnDate = new SimpleDateFormat("dd/MM/yyyy").format(result.getDate(6));
            defaultTableModel.addRow(new Object[] {
                    borrowTicketCodeString, reader, bookCode, status, borrowDate, returnDate });
            return true;
        } else {
            return false;
        }
    }
}
