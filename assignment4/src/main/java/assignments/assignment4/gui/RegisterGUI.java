package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private GridBagConstraints c;
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        nameLabel = new JLabel("Masukkan nama anda: ");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 9;
        mainPanel.add(nameLabel, c);

        nameTextField = new JTextField();
        c.gridy = 1;
        mainPanel.add(nameTextField, c);

        phoneLabel = new JLabel("Masukkan nomor handphone anda: ");
        c.gridy = 2;
        mainPanel.add(phoneLabel, c);

        phoneTextField = new JTextField();
        c.gridy = 3;
        mainPanel.add(phoneTextField, c);
        
        passwordLabel = new JLabel("Masukkan password anda: ");
        c.gridy = 4;
        mainPanel.add(passwordLabel, c);

        passwordField = new JPasswordField();
        c.gridy = 5;
        mainPanel.add(passwordField, c);

        registerButton = new JButton("Register");
        c.gridy = 6;
        c.gridx = 4;
        c.gridwidth = 1;
        mainPanel.add(registerButton, c);

        backButton = new JButton("Kembali");
        c.gridy = 7;
        mainPanel.add(backButton, c);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame frame = MainFrame.getInstance();
        frame.navigateTo(HomeGUI.KEY);
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        String nama = nameTextField.getText();
        String noHp = phoneTextField.getText();
        String password = String.valueOf(passwordField.getPassword());
        Member member = loginManager.register(nama, noHp, password);
        if (member != null){
            JOptionPane.showMessageDialog(mainPanel, String.format("User dengan ID %s sudah ada!", member.getId()), "Registration Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
