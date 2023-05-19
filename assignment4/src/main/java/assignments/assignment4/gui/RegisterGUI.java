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
        // Label input nama (dan setting GridBagConstraints)
        nameLabel = new JLabel("Masukkan nama anda: ");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 9;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 9;
        c.weighty = 0.25;
        mainPanel.add(nameLabel, c);

        // Text field input nama
        nameTextField = new JTextField(20);
        c.gridy = 1;
        mainPanel.add(nameTextField, c);

        // Label input nomor HP
        phoneLabel = new JLabel("Masukkan nomor handphone anda: ");
        c.gridy = 2;
        mainPanel.add(phoneLabel, c);

        // Text field input nomor HP
        phoneTextField = new JTextField();
        c.gridy = 3;
        mainPanel.add(phoneTextField, c);
        
        // Label input password
        passwordLabel = new JLabel("Masukkan password anda: ");
        c.gridy = 4;
        mainPanel.add(passwordLabel, c);

        // Password field input password
        passwordField = new JPasswordField();
        c.gridy = 5;
        mainPanel.add(passwordField, c);

        // Tombol register
        registerButton = new JButton("Register");
        c.gridy = 6;
        c.gridx = 4;
        c.gridwidth = 1;
        c.fill = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.CENTER;
        mainPanel.add(registerButton, c);

        // Tombol back
        backButton = new JButton("Kembali");
        c.gridy = 7;
        mainPanel.add(backButton, c);

        // ActionListener tombol back, memanggil handleBack
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
        });

        // ActionListener tombol register, memanggil handleRegister
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
        // Mengosongkan semua fields
        resetFields();
        // Mengambil instance MainFrame, navigasi ke HomeGUI
        MainFrame frame = MainFrame.getInstance();
        frame.navigateTo(HomeGUI.KEY);
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        // Mengambil instance MainFrame
        MainFrame frame = MainFrame.getInstance();
        // Mengambil data-data yang diinput user
        String nama = nameTextField.getText();
        String noHp = phoneTextField.getText();
        String password = String.valueOf(passwordField.getPassword());
        // Validasi apabila ada field yang kosong
        if (nama.equals("") || noHp.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(this, "Semua field diatas harus diisi!", "Empty Field", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Validasi apabila nomor HP tidak numerik
        if (!isNumeric(noHp)) {
            JOptionPane.showMessageDialog(this, "Nomor handphone harus berisi angka!", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
            // Mengosongkan kembali field nomor HP
            phoneTextField.setText("");
            return;
        }
        // Registrasi member melalui loginManager.register()
        Member member = loginManager.register(nama, noHp, password);
        // Jika member == null (registrasi gagal karena sudah ada ID yang sama), munculkan dialog box
        if (member == null){
            JOptionPane.showMessageDialog(this, String.format("User dengan nama %s dan nomor hp %s sudah ada!", nama, noHp), "Registration Failed", JOptionPane.ERROR_MESSAGE);
        }
        // Jika berhasil
        else {
            // Munculkan dialog box dengan JTextField agar ID bisa di copy-paste
            JTextField message = new JTextField(String.format("Berhasil membuat user dengan ID %s", member.getId()));
            message.setEditable(false);
            JOptionPane.showMessageDialog(this, message, "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
        }
        // Mengosongkan semua field
        resetFields();
        // Kembali ke HomeGUI
        frame.navigateTo(HomeGUI.KEY);
    }

    /**
     * Method untuk mengosongkan kembali semua field
     */
    private void resetFields() {
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
    }
}
