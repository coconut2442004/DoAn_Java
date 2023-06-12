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

public class BookManagementGui extends JFrame {
    private TableModel tableModel = new DefaultTableModel();
    private JTable table = new JTable(tableModel);

    private JTextField bookCodeField = new JTextField(10);
    private JTextField bookTitleField = new JTextField(10);
    private JTextField authorField = new JTextField(10);
    private JTextField statusField = new JTextField(10);

    private JLabel bookCodeLabel = new JLabel("Mã sách:");
    private JLabel bookTitleLabel = new JLabel("Tên sách:");
    private JLabel authorLabel = new JLabel("Tác giả:");
    private JLabel statusLabel = new JLabel("Trạng thái:");

    private JButton addButton = new JButton("Thêm");
    private JButton deleteButton = new JButton("Xóa");
    private JButton editButton = new JButton("Sửa");
    private JButton newButton = new JButton("Mới");
    private JButton findButton = new JButton("Tìm");
    private JButton backButton = new JButton();
    private JComboBox<String> comboBox = new JComboBox<>();

    public JButton getFindButton() {
        return this.findButton;
    }

    public void setFindButton(JButton findButton) {
        this.findButton = findButton;
    }

    public JButton getbackButton() {
        return this.backButton;
    }

    public void setbackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public JButton getNewButton() {
        return this.newButton;
    }

    public void setNewButton(JButton newButton) {
        this.newButton = newButton;
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

    public JTextField getBookCodeField() {
        return this.bookCodeField;
    }

    public void setBookCodeField(JTextField bookCodeField) {
        this.bookCodeField = bookCodeField;
    }

    public JTextField getBookTitleField() {
        return this.bookTitleField;
    }

    public void setBookTitleField(JTextField bookTitleField) {
        this.bookTitleField = bookTitleField;
    }

    public JTextField getAuthorField() {
        return this.authorField;
    }

    public void setAuthorField(JTextField authorField) {
        this.authorField = authorField;
    }

    public JTextField getStatusField() {
        return this.statusField;
    }

    public void setStatusField(JTextField statusField) {
        this.statusField = statusField;
    }

    public JLabel getBookCodeLabel() {
        return this.bookCodeLabel;
    }

    public void setBookCodeLabel(JLabel bookCodeLabel) {
        this.bookCodeLabel = bookCodeLabel;
    }

    public JLabel getBookTitleLabel() {
        return this.bookTitleLabel;
    }

    public void setBookTitleLabel(JLabel bookTitleLabel) {
        this.bookTitleLabel = bookTitleLabel;
    }

    public JLabel getAuthorLabel() {
        return this.authorLabel;
    }

    public void setAuthorLabel(JLabel authorLabel) {
        this.authorLabel = authorLabel;
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

    public JComboBox<String> getComboBox() {
        return this.comboBox;
    }

    public void setComboBox(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }

    public BookManagementGui() {
        Color buttonColor = Color.WHITE;
        Color buttonBg = Color.decode("#6ab04c");
        Color lightBg = Color.decode("#badc58");
        Color background = Color.decode("#f6e58d");
        ImageIcon iconLogout = new ImageIcon("images/back.png");
        Image originalImage = iconLogout.getImage();

        Image resizedImage = originalImage.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Thiết lập các thuộc tính cho frame
        this.setTitle("Quản lý sách");
        this.setSize(1600, 860);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Khởi tạo Insets cho các thành phần
        Insets padding = new Insets(10, 10, 10, 10);

        // Thêm các cột cho table
        ((DefaultTableModel) tableModel).addColumn("Mã sách");
        ((DefaultTableModel) tableModel).addColumn("Tên sách");
        ((DefaultTableModel) tableModel).addColumn("Tác giả");
        ((DefaultTableModel) tableModel).addColumn("Trạng thái");

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
        panel.setBorder(BorderFactory.createEmptyBorder(padding.top, padding.left, padding.bottom, padding.right));
        panel.setBackground(lightBg);
        JPanel panelSort = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSort.setBackground(lightBg);
        panelSort.setBorder(BorderFactory.createEmptyBorder(padding.top, padding.left, padding.bottom, padding.right));

        // Tạo danh sách các lựa chọn cho JComboBox
        String[] options = { "Sắp xếp tăng dần theo mã sách", "Sắp xếp giảm dần theo mã sách",
                "Sắp xếp tăng dần theo tên sách", "Sắp xếp giảm dần theo tên sách" };
        comboBox = new JComboBox<>(options);

        // Thiết lập font chữ cho các nhãn và các trường nhập liệu
        bookCodeLabel.setFont(bookCodeLabel.getFont().deriveFont(16f));
        bookTitleLabel.setFont(bookTitleLabel.getFont().deriveFont(16f));
        authorLabel.setFont(authorLabel.getFont().deriveFont(16f));
        statusLabel.setFont(statusLabel.getFont().deriveFont(16f));

        bookCodeField.setFont(bookCodeField.getFont().deriveFont(16f));
        bookCodeField.setBorder(null);
        bookTitleField.setFont(bookTitleField.getFont().deriveFont(16f));
        bookTitleField.setBorder(null);
        authorField.setFont(authorField.getFont().deriveFont(16f));
        authorField.setBorder(null);
        statusField.setFont(statusField.getFont().deriveFont(16f));
        statusField.setBorder(null);

        // Thiết lập font chữ cho các nút
        addButton.setFont(bookCodeField.getFont().deriveFont(16f));
        editButton.setFont(bookTitleField.getFont().deriveFont(16f));
        deleteButton.setFont(authorField.getFont().deriveFont(16f));
        newButton.setFont(authorField.getFont().deriveFont(16f));
        findButton.setFont(authorField.getFont().deriveFont(16f));

        // Thiết lập màu sắc và hiệu ứng cho các nút
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

        // Thêm các thành phần vào panel
        panel.add(bookCodeLabel);
        panel.add(bookCodeField);
        panel.add(bookTitleLabel);
        panel.add(bookTitleField);
        panel.add(authorLabel);
        panel.add(authorField);
        panel.add(statusLabel);
        panel.add(statusField);
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(editButton);
        panel.add(newButton);
        panel.add(findButton);
        panelSort.add(backButton);
        panelSort.add(Box.createHorizontalGlue());
        panelSort.add(comboBox);

        // Thêm panel vào frame
        this.add(panel, BorderLayout.SOUTH);
        this.add(panelSort, BorderLayout.NORTH);
        this.setVisible(false);
    }
}
