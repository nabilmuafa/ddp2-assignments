package assignments.assignment4.gui;

import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private GridBagConstraints c = new GridBagConstraints();
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;

    public LoginGUI() {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
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
        idLabel = new JLabel("Masukkan ID anda: ");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 9;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 9;
        c.weighty = 0.25;
        mainPanel.add(idLabel, c);

        idTextField = new JTextField(20);
        c.gridy = 1;
        mainPanel.add(idTextField, c);
        
        passwordLabel = new JLabel("Masukkan password anda: ");
        c.gridy = 2;
        mainPanel.add(passwordLabel, c);

        passwordField = new JPasswordField();
        c.gridy = 3;
        mainPanel.add(passwordField, c);

        loginButton = new JButton("Login");
        c.gridy = 4;
        c.gridx = 4;
        c.gridwidth = 1;
        c.fill = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, c);

        backButton = new JButton("Kembali");
        c.gridy = 5;
        mainPanel.add(backButton, c);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        resetFields();
        MainFrame frame = MainFrame.getInstance();
        frame.navigateTo(HomeGUI.KEY);
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        MainFrame frame = MainFrame.getInstance();
        String id = idTextField.getText();
        String password = String.valueOf(passwordField.getPassword());
        boolean success = frame.login(id, password);
        if (!success) {
            JOptionPane.showMessageDialog(this, "ID atau password invalid.", "Invalid ID or password", JOptionPane.ERROR_MESSAGE);
        }
        resetFields();
    }

    private void resetFields() {
        idTextField.setText("");
        passwordField.setText("");
    }
}
