package assignments.assignment4.gui;

import static assignments.assignment1.NotaGenerator.isNumeric;
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
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 9;
        c.weighty = 0.25;
        mainPanel.add(nameLabel, c);

        nameTextField = new JTextField(20);
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
        c.fill = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.CENTER;
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
        MainFrame frame = MainFrame.getInstance();
        String nama = nameTextField.getText();
        String noHp = phoneTextField.getText();
        String password = String.valueOf(passwordField.getPassword());
        if (nama.equals("") || noHp.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(this, "Semua field diatas harus diisi!", "Empty Field", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isNumeric(noHp)) {
            JOptionPane.showMessageDialog(this, "Nomor handphone harus berisi angka!", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
            phoneTextField.setText("");
            return;
        }
        Member member = loginManager.register(nama, noHp, password);
        if (member == null){
            JOptionPane.showMessageDialog(this, String.format("User dengan nama %s dan nomor hp %s sudah ada!", nama, noHp), "Registration Failed", JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this, String.format("Berhasil membuat user dengan ID %s", member.getId()));
        }
        frame.navigateTo(HomeGUI.KEY);
    }
}
