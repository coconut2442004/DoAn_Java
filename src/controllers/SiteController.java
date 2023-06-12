package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import models.BookManagerModel;
import models.BorrowTicketManagerModel;
import models.LoginModel;
import utils.DateValidation;
import views.BookManagementGui;
import views.BorrowTicketManagementGui;
import views.LoginGui;
import views.MenuGui;

/**
 * SiteController
 */
public class SiteController {
    private LoginGui loginView;
    private BookManagementGui bookManagementView;
    private BorrowTicketManagementGui borrowTicketManagerView;
    private MenuGui menuView;
    private LoginModel loginModel;
    private BookManagerModel bookManagerModel;
    private BorrowTicketManagerModel borrowTicketManagerModel;

    public SiteController() {
        this.loginView = new LoginGui();
        this.bookManagementView = new BookManagementGui();
        this.loginModel = new LoginModel();
        this.bookManagerModel = new BookManagerModel();
        this.menuView = new MenuGui();
        this.bookManagerModel = new BookManagerModel();
        this.borrowTicketManagerView = new BorrowTicketManagementGui();
        this.borrowTicketManagerModel = new BorrowTicketManagerModel();

        loginView.setVisible(true);
        bookManagementView.setVisible(false);
        menuView.setVisible(false);
        borrowTicketManagerView.setVisible(false);

        // Đăng nhập
        loginView.getLoginButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        // Chuyển đến trang quản lý sách
        menuView.getButtonLinkToBookManager().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Đỗ dữ liệu từ database vào bảng
                menuView.setVisible(false);
                bookManagementView.setVisible(true);
                populateBookTableData();
            }
        });

        // Chuyển đến trang quản lý phhiếu mượn
        menuView.getButtonLinkToBorrowTicketManager().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuView.setVisible(false);
                borrowTicketManagerView.setVisible(true);
                populateBorrowTicketTableData();
            }
        });

        // Thêm sách
        bookManagementView.getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        // Mượn sách
        borrowTicketManagerView.getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrowBook();
            }
        });

        // Xóa sách
        bookManagementView.getDeleteButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });

        // Xóa phiếu mượn
        borrowTicketManagerView.getDeleteButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBorrowTicket();
            }
        });

        // Sửa sách
        bookManagementView.getEditButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editBook();
            }
        });

        // Sửa phiếu mượn
        borrowTicketManagerView.getEditButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editBorrowTicket();
            }
        });

        // Làm mới textField
        bookManagementView.getNewButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearBookFields();
                populateBookTableData();
            }
        });

        // Làm mới textField
        borrowTicketManagerView.getNewButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearBorrowTicketFields();
                populateBorrowTicketTableData();
            }
        });

        // Button Trở lại
        bookManagementView.getbackButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookManagementView.setVisible(false);
                menuView.setVisible(true);
            }
        });

        // Button Trở lại
        borrowTicketManagerView.getBackButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrowTicketManagerView.setVisible(false);
                menuView.setVisible(true);
            }
        });

        // Sắp xếp sách
        bookManagementView.getComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedOption = (String) bookManagementView.getComboBox().getSelectedItem();
                    switch (selectedOption) {
                        case "Sắp xếp tăng dần theo mã sách":
                            try {
                                bookManagerModel
                                        .sortAscById((DefaultTableModel) bookManagementView.getTableModel());
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            break;
                        case "Sắp xếp tăng dần theo tên sách":
                            try {
                                bookManagerModel
                                        .sortAscByTitle((DefaultTableModel) bookManagementView.getTableModel());
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            break;
                        case "Sắp xếp giảm dần theo mã sách":
                            try {
                                bookManagerModel
                                        .sortDescById((DefaultTableModel) bookManagementView.getTableModel());
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            break;
                        case "Sắp xếp giảm dần theo tên sách":
                            try {
                                bookManagerModel
                                        .sortDescByTitle((DefaultTableModel) bookManagementView.getTableModel());
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            break;

                        default:
                            break;
                    }
                }
            }
        });

        // Tìm sách theo mã sách
        bookManagementView.getFindButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bookCode = bookManagementView.getBookCodeField().getText();
                if (bookCode.equals("")) {
                    JOptionPane.showMessageDialog(null, "Không thể để trống ô mã sách !!!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        if (bookManagerModel.findBook((DefaultTableModel) bookManagementView.getTableModel(),
                                bookCode)) {
                        } else {
                            JOptionPane.showMessageDialog(null, "Không thể tìm thấy sách !!!", "Error",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        // Tìm phiếu mượn theo mã phiếu mượn
        borrowTicketManagerView.getFindButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String borrowTicketCode = borrowTicketManagerView.getBorrowTicketCodeField().getText();
                if (borrowTicketCode.equals("")) {
                    JOptionPane.showMessageDialog(null, "Không thể để trống ô mã phiếu !!!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        if (borrowTicketManagerModel.findBorrowTicket(
                                (DefaultTableModel) borrowTicketManagerView.getTableModel(),
                                borrowTicketCode)) {
                        } else {
                            JOptionPane.showMessageDialog(null, "Không thể tìm thấy phiếu mượn !!!", "Error",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        // Sắp xếp phiếu mượn
        borrowTicketManagerView.getComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedOption = (String) borrowTicketManagerView.getComboBox().getSelectedItem();
                    switch (selectedOption) {
                        case "Sắp xếp tăng dần theo mã phiếu":
                            try {
                                borrowTicketManagerModel
                                        .sortAscById((DefaultTableModel) borrowTicketManagerView.getTableModel());
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            break;
                        case "Sắp xếp giảm dần theo mã phiếu":
                            try {
                                borrowTicketManagerModel
                                        .sortDescById((DefaultTableModel) borrowTicketManagerView.getTableModel());
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        // Đăng xuất
        menuView.getLogButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuView.setVisible(false);
                loginView.setVisible(true);
            }
        });
    }

    // Đẩy dữ liệu từ database vào bảng
    private void populateBookTableData() {
        try {
            bookManagerModel.insertDataIntoTable((DefaultTableModel) bookManagementView.getTableModel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateBorrowTicketTableData() {
        try {
            borrowTicketManagerModel.insertDataIntoTable((DefaultTableModel) borrowTicketManagerView.getTableModel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addBook() {
        String bookCode = bookManagementView.getBookCodeField().getText();
        String bookTitle = bookManagementView.getBookTitleField().getText();
        String author = bookManagementView.getAuthorField().getText();
        String status = bookManagementView.getStatusField().getText();

        if (bookCode.isEmpty() || bookTitle.isEmpty() || author.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không được để trống các ô !!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (!status.equals("0") && !status.equals("1")) {
            JOptionPane.showMessageDialog(null, "Trạng thái phải là 0 hoặc 1 !!!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (bookCode.length() > 12) {
            JOptionPane.showMessageDialog(null, "Mã sách phải nhỏ hơn hoặc bằng 12 ký tự !!!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                boolean result = bookManagerModel.addBook(bookCode, bookTitle, author, status);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công !!!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    populateBookTableData();
                    clearBookFields();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Thêm không thành công !!!, Error: " + e, "Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private void borrowBook() {
        String borrowTicketCode = borrowTicketManagerView.getBorrowTicketCodeField().getText();
        String reader = borrowTicketManagerView.getReaderField().getText();
        String bookCode = borrowTicketManagerView.getBookCodeField().getText();
        String status = borrowTicketManagerView.getStatusField().getText();
        String borrowDate = borrowTicketManagerView.getBorrowDateField().getText();
        String returnDate = borrowTicketManagerView.getReturnDateField().getText();
        String dateFormat = "yyyy-MM-dd";
        boolean isValid = DateValidation.validateDate(borrowDate, dateFormat) && DateValidation.validateDate(
                returnDate,
                dateFormat);
        if (bookCode.isEmpty() || reader.isEmpty() || borrowTicketCode.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không được để trống ô (mã phiếu, độc giả, mã sách) !!!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (borrowTicketCode.length() > 20) {
            JOptionPane.showMessageDialog(null, "Mã phiếu phải nhỏ hơn hoặc bằng 20 ký tự !!!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (!borrowDate.isEmpty() && !returnDate.isEmpty() && !isValid) {

            JOptionPane.showMessageDialog(null,
                    "Định dạng ngày tháng không hợp lệ, định dạng đúng (yyyy-MM-dd) !!!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                boolean result = borrowTicketManagerModel.borrowBook(borrowTicketCode, reader, bookCode, status,
                        borrowDate, returnDate);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Mượn thành công !!!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    populateBorrowTicketTableData();
                    clearBorrowTicketFields();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Mượn không thành công !!!, Error: " + e, "Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private void editBorrowTicket() {
        String borrowTicketCode = borrowTicketManagerView.getBorrowTicketCodeField().getText();
        String reader = borrowTicketManagerView.getReaderField().getText();
        String bookCode = borrowTicketManagerView.getBookCodeField().getText();
        String status = borrowTicketManagerView.getStatusField().getText();
        String borrowDate = borrowTicketManagerView.getBorrowDateField().getText();
        String returnDate = borrowTicketManagerView.getReturnDateField().getText();

        String dateFormat = "yyyy-MM-dd";
        boolean isValid = DateValidation.validateDate(borrowDate, dateFormat) && DateValidation.validateDate(
                returnDate,
                dateFormat);

        if (bookCode.isEmpty() || reader.isEmpty() || borrowTicketCode.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không được để trống các ô !!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (borrowTicketCode.length() > 20) {
            JOptionPane.showMessageDialog(null, "Mã phiếu phải nhỏ hơn hoặc bằng 20 ký tự !!!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (!isValid) {
            JOptionPane.showMessageDialog(null, "Định dạng ngày tháng không hợp lệ, định dạng đúng (yyyy-MM-dd) !!!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                boolean result = borrowTicketManagerModel.editBorrowTicket(borrowTicketCode, reader, bookCode, status,
                        borrowDate, returnDate);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công !!!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    populateBorrowTicketTableData();
                    clearBorrowTicketFields();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Cập nhật không thành công !!!, Error: " + e, "Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private void deleteBorrowTicket() {
        String borrowTicketCode = borrowTicketManagerView.getBorrowTicketCodeField().getText();

        if (borrowTicketCode.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không được để trống các ô !!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                boolean result = borrowTicketManagerModel.deleteBorrowTicket(borrowTicketCode);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công !!!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    populateBorrowTicketTableData();
                    clearBorrowTicketFields();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Xóa không thành công !!!, Error: " + e, "Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private void deleteBook() {
        String bookCode = bookManagementView.getBookCodeField().getText();

        if (bookCode.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không được để trống ô mã sách!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                boolean result = bookManagerModel.deleteBook(bookCode);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công !!!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    populateBookTableData();
                    clearBookFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa không thành công !!!, Sách không tồn tại", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Xóa không thành công !!!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private void editBook() {
        String bookCode = bookManagementView.getBookCodeField().getText();
        String bookTitle = bookManagementView.getBookTitleField().getText();
        String author = bookManagementView.getAuthorField().getText();
        String status = bookManagementView.getStatusField().getText();

        if (bookCode.isEmpty() || bookTitle.isEmpty() || author.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không được để trống các ô !!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (bookCode.length() > 12) {
            JOptionPane.showMessageDialog(null, "Mã sách phải nhỏ hơn hoặc bằng 12 ký tự !!!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if (!status.equals("0") && !status.equals("1")) {
                JOptionPane.showMessageDialog(null, "Trạng thái chỉ có thể là 0 hoặc 1 !!!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (bookCode.length() > 12) {
                JOptionPane.showMessageDialog(null, "Mã sách phải nhỏ hơn hoặc bằng 12 ký tự !!!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    boolean result = bookManagerModel.editBook(bookCode, bookTitle, author, status);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Sửa thành công !!!", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        populateBookTableData();
                        clearBookFields();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Sửa không thành công !!!, Error: " + e, "Error",
                            JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }
    }

    private void clearBookFields() {
        bookManagementView.getBookCodeField().setText("");
        bookManagementView.getBookTitleField().setText("");
        bookManagementView.getAuthorField().setText("");
        bookManagementView.getStatusField().setText("");
    }

    private void clearBorrowTicketFields() {
        borrowTicketManagerView.getBorrowTicketCodeField().setText("");
        borrowTicketManagerView.getBookCodeField().setText("");
        borrowTicketManagerView.getReaderField().setText("");
        borrowTicketManagerView.getStatusField().setText("");
        borrowTicketManagerView.getBorrowDateField().setText("");
        borrowTicketManagerView.getReturnDateField().setText("");
    }

    private void handleLogin() {
        String username = loginView.getTextFieldUsername().getText();
        char[] passwordStr = loginView.getTextFieldPassword().getPassword();
        String password = new String(passwordStr);

        System.out.println(username);
        System.out.println(password);

        try {
            if (loginModel.checkLogin(username, password)) {
                loginView.getTextFieldUsername().setText("");
                loginView.getTextFieldPassword().setText("");
                loginView.getLabelNotification().setText("");
                loginView.setVisible(false);
                menuView.setVisible(true);
            } else {
                loginView.getLabelNotification().setText("Tài khoản hoặc mật khẩu không chính xác!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không thể kết nối tới cơ sở dữ liệu !!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();

        }
    }

}