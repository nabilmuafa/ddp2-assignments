package assignments.assignment4.gui;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static assignments.assignment3.nota.NotaManager.toNextDay;

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private GridBagConstraints c = new GridBagConstraints();
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    public HomeGUI(){
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
        // Welcome label, di bagian atas BorderLayout
        titleLabel = new JLabel("Selamat datang di CuciCuci System!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Label tanggal, di bagian bawah BorderLayout
        dateLabel = new JLabel(String.format("Hari ini: %s", NotaManager.fmt.format(NotaManager.cal.getTime())), SwingConstants.CENTER);
        add(dateLabel, BorderLayout.SOUTH);

        // Constraints untuk tombol ((mengambil dari AbstractMemberGUI agar tampilan
        // tiap card mirip-mirip posisinya, hehe))
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1.0;
        c.weighty = 0.5;
        c.insets = new Insets(5, 5, 5, 5);

        // Objek tombol login, register, dan next day
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        toNextDayButton = new JButton("Next Day");

        // Menambahkan tombol-tombol dengan constraints ke panel utama
        mainPanel.add(loginButton, c);
        mainPanel.add(registerButton, c);
        mainPanel.add(toNextDayButton, c);

        // ActionListener untuk register, memanggil handleToRegister
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleToRegister();
            }
        });

        // ActionListener untuk login, memanggil handleToLogin
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleToLogin();
            }
        });

        // ActionListener untuk next day, memanggil handleNextDay
        toNextDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNextDay();
            }
        });
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private void handleToRegister() {
        // Mengambil instance mainFrame lalu navigasi ke RegisterGUI
        MainFrame frame = MainFrame.getInstance();
        frame.navigateTo(RegisterGUI.KEY);
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleToLogin() {
        // Mengambil instance mainFrame lalu navigasi ke LoginGUI
        MainFrame frame = MainFrame.getInstance();
        frame.navigateTo(LoginGUI.KEY);
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        // Memanggil toNextDay dari NotaManager
        toNextDay();
        // Mengubah label tanggal dan memunculkan dialog box
        dateLabel.setText(String.format("Hari ini: %s", NotaManager.fmt.format(NotaManager.cal.getTime())));
        JOptionPane.showMessageDialog(this, "Kamu tidur hari ini... zzz...", "Hari Baru, Semangat Baru", JOptionPane.INFORMATION_MESSAGE);
    }
}
