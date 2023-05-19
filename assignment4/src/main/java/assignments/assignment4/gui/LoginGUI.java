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
        // Label input ID (dan setting constraints GridBagLayout)
        idLabel = new JLabel("Masukkan ID anda: ");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 9;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 9;
        c.weighty = 0.25;
        mainPanel.add(idLabel, c);

        // Text field input ID
        idTextField = new JTextField(20);
        c.gridy = 1;
        mainPanel.add(idTextField, c);
        
        // Label input password
        passwordLabel = new JLabel("Masukkan password anda: ");
        c.gridy = 2;
        mainPanel.add(passwordLabel, c);

        // Text field input password
        passwordField = new JPasswordField();
        c.gridy = 3;
        mainPanel.add(passwordField, c);

        // Tombol untuk login
        loginButton = new JButton("Login");
        c.gridy = 4;
        c.gridx = 4;
        c.gridwidth = 1;
        c.fill = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, c);

        // Tombol untuk back
        backButton = new JButton("Kembali");
        c.gridy = 5;
        mainPanel.add(backButton, c);

        // ActionListener untuk tombol back, memanggil handleBack
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
        });

        // ActionListener untuk tombol login, memanggil handleLogin
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
        // Mengosongkan semua field
        resetFields();
        // Mengambil instance MainFrame lalu navigasi ke HomeGUI
        MainFrame frame = MainFrame.getInstance();
        frame.navigateTo(HomeGUI.KEY);
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        // Mengambil instance MainFrame
        MainFrame frame = MainFrame.getInstance();
        // Mengambil text yang diinput user
        String id = idTextField.getText();
        String password = String.valueOf(passwordField.getPassword());
        // Melakukan login di MainFrame
        boolean success = frame.login(id, password);
        // Memunculkan dialog box apabila login gagal
        if (!success) {
            JOptionPane.showMessageDialog(this, "ID atau password invalid.", "Invalid ID or password", JOptionPane.ERROR_MESSAGE);
        }
        // Mengosongkan kembali fields apabila berhasil
        resetFields();
    }

    /**
     * Method untuk mengosongkan kembali semua field
     */
    private void resetFields() {
        idTextField.setText("");
        passwordField.setText("");
    }
}
