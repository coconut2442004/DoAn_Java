package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class BorrowTicketManagementGui extends JFrame {
    private TableModel tableModel = new DefaultTableModel();
    private JTable table = new JTable(tableModel);

    private JTextField borrowTicketCodeField = new JTextField(10);
    private JTextField readerField = new JTextField(10);
    private JTextField bookCodeField = new JTextField(10);
    private JTextField statusField = new JTextField(10);
    private JTextField borrowDateField = new JTextField(10);
    private JTextField returnDateField = new JTextField(10);

    private JLabel borrowTicketCodeLabel = new JLabel("Mã phiếu:");
    private JLabel readerLabel = new JLabel("Tên đọc giả:");
    private JLabel bookCodeLabel = new JLabel("Mã sách:");
    private JLabel statusLabel = new JLabel("Trạng thái:");
    private JLabel borrowDateLabel = new JLabel("Ngày mượn:");
    private JLabel returnDateLabel = new JLabel("Hạn trả:");

    private JButton addButton = new JButton("Thêm");
    private JButton deleteButton = new JButton("Xóa");
    private JButton editButton = new JButton("Sửa");
    private JButton newButton = new JButton("Mới");
    private JButton findButton = new JButton("Tìm");
    private JComboBox<String> comboBox = new JComboBox<>();
    private JButton backButton = new JButton();

    public JButton getFindButton() {
        return this.findButton;
    }

    public void setFindButton(JButton findButton) {
        this.findButton = findButton;
    }

    public JLabel getBorrowDateLabel() {
        return this.borrowDateLabel;
    }

    public void setBorrowDateLabel(JLabel borrowDateLabel) {
        this.borrowDateLabel = borrowDateLabel;
    }

    public JLabel getReturnDateLabel() {
        return this.returnDateLabel;
    }

    public void setReturnDateLabel(JLabel returnDateLabel) {
        this.returnDateLabel = returnDateLabel;
    }

    public JTextField getBorrowDateField() {
        return this.borrowDateField;
    }

    public void setBorrowDateField(JTextField borrowDateField) {
        this.borrowDateField = borrowDateField;
    }

    public JTextField getReturnDateField() {
        return this.returnDateField;
    }

    public void setReturnDateField(JTextField returnDateField) {
        this.returnDateField = returnDateField;
    }

    public TableModel getTableModel() {
        return this.tableModel;
    }

    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    public JTable getTable() {
        return this.table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JTextField getBorrowTicketCodeField() {
        return this.borrowTicketCodeField;
    }

    public void setBorrowTicketCodeField(JTextField borrowTicketCodeField) {
        this.borrowTicketCodeField = borrowTicketCodeField;
    }

    public JTextField getReaderField() {
        return this.readerField;
    }

    public void setReaderField(JTextField readerField) {
        this.readerField = readerField;
    }

    public JTextField getBookCodeField() {
        return this.bookCodeField;
    }

    public void setBookCodeField(JTextField bookCodeField) {
        this.bookCodeField = bookCodeField;
    }

    public JTextField getStatusField() {
        return this.statusField;
    }

    public void setStatusField(JTextField statusField) {
        this.statusField = statusField;
    }

    public JLabel getBorrowTicketCodeLabel() {
        return this.borrowTicketCodeLabel;
    }

    public void setBorrowTicketCodeLabel(JLabel borrowTicketCodeLabel) {
        this.borrowTicketCodeLabel = borrowTicketCodeLabel;
    }

    public JLabel getReaderLabel() {
        return this.readerLabel;
    }

    public void setReaderLabel(JLabel readerLabel) {
        this.readerLabel = readerLabel;
    }

    public JLabel getBookCodeLabel() {
        return this.bookCodeLabel;
    }

    public void setBookCodeLabel(JLabel bookCodeLabel) {
        this.bookCodeLabel = bookCodeLabel;
    }

    public JLabel getStatusLabel() {
        return this.statusLabel;
    }

    public void setStatusLabel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
    }

    public JButton getAddButton() {
        return this.addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JButton getDeleteButton() {
        return this.deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JButton getEditButton() {
        return this.editButton;
    }

    public void setEditButton(JButton editButton) {
        this.editButton = editButton;
    }

    public JButton getNewButton() {
        return this.newButton;
    }

    public void setNewButton(JButton newButton) {
        this.newButton = newButton;
    }

    public JComboBox<String> getComboBox() {
        return this.comboBox;
    }

    public void setComboBox(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }

    public JButton getBackButton() {
        return this.backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public BorrowTicketManagementGui() {
        Color buttonColor = Color.WHITE;
        Color buttonBg = Color.decode("#6ab04c");
        Color lightBg = Color.decode("#badc58");
        Color background = Color.decode("#f6e58d");
        ImageIcon iconLogout = new ImageIcon("images/back.png");
        Image originalImage = iconLogout.getImage();

        Image resizedImage = originalImage.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Thiết lập các thuộc tính cho frame
        this.setTitle("Quản lý phiếu mượn");
        this.setSize(1600, 860);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Khởi tạo các Insets cho các thành phần
        Insets paddingBottom = new Insets(20, 10, 40, 10);
        Insets paddingTop = new Insets(10, 10, 10, 10);

        // Thêm các cột cho table
        ((DefaultTableModel) tableModel).addColumn("Mã phiếu");
        ((DefaultTableModel) tableModel).addColumn("Tên độc giả");
        ((DefaultTableModel) tableModel).addColumn("Mã sách");
        ((DefaultTableModel) tableModel).addColumn("Trạng thái");
        ((DefaultTableModel) tableModel).addColumn("Ngày mượn");
        ((DefaultTableModel) tableModel).addColumn("Hạn trả");

        // Thiết lập các thuộc tính cho table
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 15));

        // Thêm JScrollPane chứa table vào frame
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(background);
        this.add(scrollPane, BorderLayout.CENTER);

        // Thiết lập font chữ cho header của table
        JTableHeader header = table.getTableHeader();
        Font headerFont = header.getFont().deriveFont(Font.BOLD, 16f);
        header.setFont(headerFont);

        // Khởi tạo các panel và các thành phần giao diện
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(BorderFactory.createEmptyBorder(paddingBottom.top, paddingBottom.left, paddingBottom.bottom,
                paddingBottom.right));
        panel.setBackground(lightBg);
        JPanel panelSort = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSort.setBackground(lightBg);
        panelSort.setBorder(
                BorderFactory.createEmptyBorder(paddingTop.top, paddingTop.left, paddingTop.bottom, paddingTop.right));

        String[] options = { "Sắp xếp tăng dần theo mã phiếu", "Sắp xếp giảm dần theo mã phiếu" };
        comboBox = new JComboBox<>(options);

        // Thiết lập font chữ cho các nhãn và các trường nhập liệu
        borrowTicketCodeLabel.setFont(borrowTicketCodeLabel.getFont().deriveFont(16f));
        readerLabel.setFont(readerLabel.getFont().deriveFont(16f));
        bookCodeLabel.setFont(bookCodeLabel.getFont().deriveFont(16f));
        statusLabel.setFont(statusLabel.getFont().deriveFont(16f));
        borrowDateLabel.setFont(statusLabel.getFont().deriveFont(16f));
        returnDateLabel.setFont(statusLabel.getFont().deriveFont(16f));

        borrowTicketCodeField.setFont(borrowTicketCodeField.getFont().deriveFont(16f));
        borrowTicketCodeField.setBorder(null);
        readerField.setFont(readerField.getFont().deriveFont(16f));
        readerField.setBorder(null);
        bookCodeField.setFont(bookCodeField.getFont().deriveFont(16f));
        bookCodeField.setBorder(null);
        statusField.setFont(statusField.getFont().deriveFont(16f));
        statusField.setBorder(null);
        borrowDateField.setFont(statusField.getFont().deriveFont(16f));
        borrowDateField.setBorder(null);
        returnDateField.setFont(statusField.getFont().deriveFont(16f));
        returnDateField.setBorder(null);

        addButton.setFont(borrowTicketCodeField.getFont().deriveFont(16f));
        editButton.setFont(readerField.getFont().deriveFont(16f));
        deleteButton.setFont(bookCodeField.getFont().deriveFont(16f));
        newButton.setFont(bookCodeField.getFont().deriveFont(16f));
        findButton.setFont(bookCodeField.getFont().deriveFont(16f));

        addButton.setForeground(buttonColor);
        addButton.setBackground(buttonBg);
        addButton.setBorderPainted(false);
        deleteButton.setForeground(buttonColor);
        deleteButton.setBackground(buttonBg);
        deleteButton.setBorderPainted(false);
        editButton.setForeground(buttonColor);
        editButton.setBackground(buttonBg);
        editButton.setBorderPainted(false);
        newButton.setForeground(buttonColor);
        newButton.setBackground(buttonBg);
        newButton.setBorderPainted(false);
        backButton.setIcon(resizedIcon);
        backButton.setBackground(lightBg);
        backButton.setPreferredSize(new Dimension(resizedIcon.getIconWidth(), resizedIcon.getIconHeight()));
        backButton.setBorderPainted(false);
        findButton.setForeground(buttonColor);
        findButton.setBackground(buttonBg);
        findButton.setBorderPainted(false);

        // Khởi tạo các panel chứa các thành phần tương ứng
        JPanel panelField = new JPanel();
        JPanel panelButton = new JPanel();

        panelField.setBackground(lightBg);
        panelButton.setBackground(lightBg);

        panelField.add(borrowTicketCodeLabel);
        panelField.add(borrowTicketCodeField);
        panelField.add(readerLabel);
        panelField.add(readerField);
        panelField.add(bookCodeLabel);
        panelField.add(bookCodeField);
        panelField.add(statusLabel);
        panelField.add(statusField);
        panelField.add(borrowDateLabel);
        panelField.add(borrowDateField);
        panelField.add(returnDateLabel);
        panelField.add(returnDateField);

        panelButton.add(addButton);
        panelButton.add(deleteButton);
        panelButton.add(editButton);
        panelButton.add(newButton);
        panelButton.add(findButton);
        panelSort.add(backButton);
        panelSort.add(Box.createHorizontalGlue());
        panelSort.add(comboBox);
        panel.add(panelField, BorderLayout.SOUTH);
        panel.add(panelButton, BorderLayout.NORTH);

        // Thêm các panel vào frame
        this.add(panel, BorderLayout.SOUTH);
        this.add(panelSort, BorderLayout.NORTH);
        this.setVisible(false);
    }
}
