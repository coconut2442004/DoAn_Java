package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import database.DatabaseUtils;

public class BookManagerModel {
    // Chèn dữ liệu từ database vào bảng
    public void insertDataIntoTable(DefaultTableModel defaultTableModel) throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        String query = "SELECT * FROM Book";

        int rowCount = defaultTableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            ((DefaultTableModel) defaultTableModel).removeRow(i);
        }

        ResultSet result = connection.createStatement().executeQuery(query);
        while (result.next()) {
            String bookCode = result.getString(1);
            String bookTitle = result.getString(2);
            String author = result.getString(3);
            String status = result.getString(4);
            ((DefaultTableModel) defaultTableModel)
                    .addRow(new Object[] { bookCode, bookTitle, author, status });
        }
    }

    // Sắp xếp tăng dần theo mã sách
    public void sortAscById(DefaultTableModel tableModel) throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        String query = "SELECT * FROM Book ORDER BY Book.Book_code ASC";

        ResultSet result = connection.createStatement().executeQuery(query);

        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        while (result.next()) {
            String bookCode = result.getString(1);
            String bookTitle = result.getString(2);
            String author = result.getString(3);
            String borrowTicketCode = result.getString(4);
            tableModel.addRow(new Object[] { bookCode, bookTitle, author, borrowTicketCode });
        }
    }

    // Sắp xếp tăng dần theo tên sách
    public void sortAscByTitle(DefaultTableModel tableModel) throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        String query = "SELECT * FROM Book ORDER BY Book.Book_title ASC";

        ResultSet result = connection.createStatement().executeQuery(query);

        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        while (result.next()) {
            String bookCode = result.getString(1);
            String bookTitle = result.getString(2);
            String author = result.getString(3);
            String borrowTicketCode = result.getString(4);
            tableModel.addRow(new Object[] { bookCode, bookTitle, author, borrowTicketCode });
        }
    }

    // Sắp xếp giảm dần theo mã sách
    public void sortDescById(DefaultTableModel tableModel) throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        String query = "SELECT * FROM Book ORDER BY Book.Book_code DESC";

        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        ResultSet result = connection.createStatement().executeQuery(query);
        while (result.next()) {
            String bookCode = result.getString(1);
            String bookTitle = result.getString(2);
            String author = result.getString(3);
            String borrowTicketCode = result.getString(4);
            tableModel.addRow(new Object[] { bookCode, bookTitle, author, borrowTicketCode });
        }
    }

    // Sắp xếp giảm dần theo tên sách
    public void sortDescByTitle(DefaultTableModel tableModel) throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        String query = "SELECT * FROM Book ORDER BY Book.Book_title DESC";

        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        ResultSet result = connection.createStatement().executeQuery(query);
        while (result.next()) {
            String bookCode = result.getString(1);
            String bookTitle = result.getString(2);
            String author = result.getString(3);
            String borrowTicketCode = result.getString(4);
            tableModel.addRow(new Object[] { bookCode, bookTitle, author, borrowTicketCode });
        }
    }

    // Xử lý thêm sách
    public boolean addBook(String bookCode, String bookTitle, String author, String status) throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        String checkQuery = "SELECT COUNT(*) FROM Book WHERE Book_code = ?";
        String query = "INSERT INTO Book (Book_code, Book_title, Author, Status) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(checkQuery);
        statement = connection.prepareStatement(checkQuery);
        statement.setString(1, bookCode);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);

        if (count > 0) {
            JOptionPane.showMessageDialog(null, "Mã sách đã tồn tại !!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            statement = connection.prepareStatement(query);
            statement.setString(1, bookCode);
            statement.setString(2, bookTitle);
            statement.setString(3, author);
            statement.setString(4, status);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Xử lý xóa sách
    public boolean deleteBook(String bookCode) throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        String query = "DELETE FROM Book WHERE Book_code = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, bookCode);

        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;
    }

    // Xử lý sửa sách
    public boolean editBook(String bookCode, String bookTitle, String author, String status) throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        String checkQuery = "SELECT COUNT(*) FROM Book WHERE Book_code = ?";
        String query = "UPDATE Book SET Book_title = ?, Author = ?, Status = ? WHERE Book_code = ?";

        PreparedStatement statement = connection.prepareStatement(checkQuery);
        statement = connection.prepareStatement(checkQuery);
        statement.setString(1, bookCode);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);

        if (count > 0) {
            statement = connection.prepareStatement(query);
            statement.setString(1, bookTitle);
            statement.setString(2, author);
            statement.setString(3, status);
            statement.setString(4, bookCode);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } else {
            JOptionPane.showMessageDialog(null, "Mã sách không tồn tại !!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Tìm sách theo mã sách
    public boolean findBook(DefaultTableModel defaultTableModel, String bookCode) throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        String query = "SELECT * FROM Book WHERE Book_code = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, bookCode);

        ResultSet result = statement.executeQuery();

        int rowCount = defaultTableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            ((DefaultTableModel) defaultTableModel).removeRow(i);
        }

        if (result.next()) {
            String bookCodeString = result.getString(1);
            String bookTitle = result.getString(2);
            String author = result.getString(3);
            String status = result.getString(4);
            ((DefaultTableModel) defaultTableModel)
                    .addRow(new Object[] { bookCodeString, bookTitle, author, status });
            return true;
        } else {
            return false;
        }
    }
}