package views;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

public class LoginGui extends JFrame {
    private JButton loginButton = new JButton("Đăng nhập");
    private JLabel titlePage = new JLabel("Đăng nhập");
    private JTextField textFieldUsername = new JTextField();
    private JPasswordField textFieldPassword = new JPasswordField();
    private JLabel labelUsername = new JLabel("Tài khoản");
    private JLabel labelPassword = new JLabel("Mật khẩu");
    private JLabel labelNotification = new JLabel("");

    public JLabel getLabelNotification() {
        return this.labelNotification;
    }

    public void setLabelNotification(JLabel labelNotification) {
        this.labelNotification = labelNotification;
    }

    public JButton getLoginButton() {
        return this.loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public JLabel getTitlePage() {
        return this.titlePage;
    }

    public void setTitlePage(JLabel titlePage) {
        this.titlePage = titlePage;
    }

    public JTextField getTextFieldUsername() {
        return this.textFieldUsername;
    }

    public void setTextFieldUsername(JTextField textFieldUsername) {
        this.textFieldUsername = textFieldUsername;
    }

    public JPasswordField getTextFieldPassword() {
        return this.textFieldPassword;
    }

    public void setTextFieldPassword(JPasswordField textFieldPassword) {
        this.textFieldPassword = textFieldPassword;
    }

    public JLabel getLabelUsername() {
        return this.labelUsername;
    }

    public void setLabelUsername(JLabel labelUsername) {
        this.labelUsername = labelUsername;
    }

    public JLabel getLabelPassword() {
        return this.labelPassword;
    }

    public void setLabelPassword(JLabel labelPassword) {
        this.labelPassword = labelPassword;
    }

    public LoginGui() {
        int width = 600;
        int height = 400;
        int padding = 20;
        int labelWidth = 120;
        int buttonWdith = 200;
        int buttonHeight = 40;
        int textFieldHeight = 40;
        int textFieldWidth = width - labelWidth - padding * 3;

        this.setSize(width, height);
        this.setTitle("Trang đăng nhập");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        // Tiêu đề trang
        titlePage.setBounds(250, 40, 160, 20);

        // Nhãn "Tài khoản"
        labelUsername.setBounds(40, 80, 80, textFieldHeight);

        // Ô nhập liệu "Tài khoản"
        textFieldUsername.setBounds(labelWidth + padding, 80, textFieldWidth, textFieldHeight);

        // Nhãn "Mật khẩu"
        labelPassword.setBounds(40, 140, 120, textFieldHeight);

        // Ô nhập liệu "Mật khẩu"
        textFieldPassword.setBounds(labelWidth + padding, 140, textFieldWidth, textFieldHeight);

        // Nút "Đăng nhập"
        loginButton.setBounds((width - buttonWdith) / 2, 200, buttonWdith, buttonHeight);

        // Nhãn thông báo lỗi
        labelNotification.setBounds((width - 360) / 2, 260,
                "Tài khoản hoặc mật khẩu không chính xác!!!".length() * 16, 20);

        // Thay đổi màu sắc và font chữ
        Color textColor = Color.decode("#6ab04c");
        Color inputColor = Color.BLACK;
        Color buttonBg = Color.decode("#6ab04c");
        Color labeColor = Color.decode("#6ab04c");
        Color buttonText = Color.WHITE;
        Color notiColor = Color.decode("#eb4d4b");
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        Font titleFont = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Border paddingInput = new EmptyBorder(20, 20, 20, 20);

        // Thiết lập màu sắc và font chữ cho các thành phần giao diện
        titlePage.setForeground(labeColor);
        titlePage.setFont(titleFont);
        labelUsername.setForeground(textColor);
        labelUsername.setFont(labelFont);
        textFieldUsername.setFont(labelFont);
        textFieldUsername.setForeground(inputColor);
        textFieldUsername.setBorder(BorderFactory.createCompoundBorder(textFieldUsername.getBorder(), paddingInput));
        textFieldUsername.setBorder(null);
        labelPassword.setForeground(textColor);
        labelPassword.setFont(labelFont);
        textFieldPassword.setFont(labelFont);
        textFieldPassword.setForeground(inputColor);
        textFieldPassword.setBorder(BorderFactory.createCompoundBorder(textFieldPassword.getBorder(), paddingInput));
        textFieldPassword.setBorder(null);
        loginButton.setFont(buttonFont);
        loginButton.setBackground(buttonBg);
        loginButton.setForeground(buttonText);
        loginButton.setBorderPainted(false);
        labelNotification.setFont(labelFont);
        labelNotification.setForeground(notiColor);

        // Thêm các thành phần giao diện vào frame
        add(titlePage);
        add(labelUsername);
        add(textFieldUsername);
        add(labelPassword);
        add(textFieldPassword);
        add(loginButton);
        add(labelNotification);

        setVisible(false);
    }
}
