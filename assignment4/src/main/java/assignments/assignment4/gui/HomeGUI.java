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
        titleLabel = new JLabel("Selamat datang di Cuci-Cuci System!");
        dateLabel = new JLabel("Sekarang tanggal gatau");
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        toNextDayButton = new JButton("Next Day");

        c.gridy = 0;
        c.gridx = 0;
        c.weighty = 0.5;
        mainPanel.add(titleLabel, c);

        c.gridy = 1;
        mainPanel.add(loginButton, c);

        c.gridy = 2;
        mainPanel.add(registerButton, c);

        c.gridy = 3;
        mainPanel.add(toNextDayButton, c);

        c.gridy = 4;
        mainPanel.add(dateLabel, c);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleToRegister();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                handleToLogin();
            }
        });
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private void handleToRegister() {
        MainFrame frame = MainFrame.getInstance();
        frame.navigateTo(RegisterGUI.KEY);
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleToLogin() {
        MainFrame frame = MainFrame.getInstance();
        frame.navigateTo(LoginGUI.KEY);
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
    }
}
