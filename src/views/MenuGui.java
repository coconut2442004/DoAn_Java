package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuGui extends JFrame {
    private JPanel optionPanel = new JPanel();
    private JLabel menuTitle = new JLabel("Nhập lựa chọn của bạn");
    private JButton buttonLinkToBookManager = new JButton("Quản lý sách");
    private JButton buttonLinkToBorrowTicketManager = new JButton("Quản lý phiếu mượn");
    private JButton logButton = new JButton("Đăng xuất");

    public JButton getLogButton() {
        return this.logButton;
    }

    public void setLogButton(JButton logButton) {
        this.logButton = logButton;
    }

    public JPanel getOpntioPanel() {
        return this.optionPanel;
    }

    public void setOpntioPanel(JPanel optionPanel) {
        this.optionPanel = optionPanel;
    }

    public JLabel getMenuTitle() {
        return this.menuTitle;
    }

    public void setMenuTitle(JLabel menuTitle) {
        this.menuTitle = menuTitle;
    }

    public JButton getButtonLinkToBookManager() {
        return this.buttonLinkToBookManager;
    }

    public void setButtonLinkToBookManager(JButton buttonLinkToBookManager) {
        this.buttonLinkToBookManager = buttonLinkToBookManager;
    }

    public JButton getButtonLinkToBorrowTicketManager() {
        return this.buttonLinkToBorrowTicketManager;
    }

    public void setButtonLinkToBorrowTicketManager(JButton buttonLinkToBorrowTicketManager) {
        this.buttonLinkToBorrowTicketManager = buttonLinkToBorrowTicketManager;
    }

    public MenuGui() {
        int width = 1560;
        int height = 836;
        this.setTitle("Menu");
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        Color buttonBg = Color.decode("#6ab04c");
        Color labeColor = Color.decode("#6ab04c");
        Color buttonText = Color.WHITE;
        Font titleFont = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        int buttonWdith = 240;
        int buttonHeight = 40;

        // Tiêu đề của menu
        menuTitle.setForeground(labeColor);
        menuTitle.setFont(titleFont);

        // Các nút liên kết đến trình quản lý sách và vé mượn
        buttonLinkToBookManager.setFont(buttonFont);
        buttonLinkToBookManager.setBackground(buttonBg);
        buttonLinkToBookManager.setForeground(buttonText);
        buttonLinkToBookManager.setBorderPainted(false);
        buttonLinkToBorrowTicketManager.setFont(buttonFont);
        buttonLinkToBorrowTicketManager.setBackground(buttonBg);
        buttonLinkToBorrowTicketManager.setForeground(buttonText);
        buttonLinkToBorrowTicketManager.setBorderPainted(false);

        // Nút đăng nhập
        logButton.setFont(buttonFont);
        logButton.setBackground(buttonBg);
        logButton.setForeground(buttonText);
        logButton.setBorderPainted(false);

        // Vị trí và kích thước các phần tử trên giao diện
        menuTitle.setBounds((width - "Nhập lựa chọn của bạn".length() * 11) / 2, 275,
                "Nhập lựa chọn của bạn".length() * 11, 40);
        buttonLinkToBookManager.setBounds((width - buttonWdith) / 2, buttonHeight + 275, buttonWdith, buttonHeight);
        buttonLinkToBorrowTicketManager.setBounds((width - buttonWdith) / 2, buttonHeight * 2 + 20 + 275, buttonWdith,
                buttonHeight);
        logButton.setBounds((width - buttonWdith) / 2, buttonHeight * 3 + 40 + 275, buttonWdith, buttonHeight);

        // Đặt layout của panel là null và thêm các phần tử vào panel
        optionPanel.setLayout(null);
        optionPanel.add(menuTitle);
        optionPanel.add(buttonLinkToBookManager);
        optionPanel.add(buttonLinkToBorrowTicketManager);
        optionPanel.add(logButton);

        // Thêm panel vào frame
        this.add(optionPanel, BorderLayout.CENTER);
        this.setVisible(false);
    }
}
