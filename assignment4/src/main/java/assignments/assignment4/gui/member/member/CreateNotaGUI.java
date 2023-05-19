package assignments.assignment4.gui.member.member;

import static assignments.assignment1.NotaGenerator.isPosNumeric;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        super(new BorderLayout());
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        // Set up main panel, Feel free to make any changes
        initGUI();
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // Panel utama dengan GridBagLayout)
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // GridBagConstraints untuk batas-batas GridBag
        GridBagConstraints c = new GridBagConstraints();

        // Instansiasi tombol-tombol dan label-label
        paketLabel = new JLabel("Paket Laundry: ");
        beratLabel = new JLabel("Berat Cucian (kg):");
        paketComboBox = new JComboBox<String>(new String[]{"Express", "Fast", "Reguler"});
        beratTextField = new JTextField();
        showPaketButton = new JButton("Show Paket");
        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000 / kg)");
        antarCheckBox = new JCheckBox("Tambah Antar Service (2000 / 4kg pertama, kemudian 500 / kg)");
        createNotaButton = new JButton("Buat Nota");
        backButton = new JButton("Kembali");

        // Add label paket ke panel
        c.gridx = 0;
        c.gridy = 0;
        c.fill = 4;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 5, 5);
        mainPanel.add(paketLabel, c);

        // Add label berat ke panel
        c.gridy = 1;
        mainPanel.add(beratLabel,c);

        // Add checkbox setrika ke panel
        c.gridy = 2;
        mainPanel.add(setrikaCheckBox, c);

        // Add checkbox antar ke panel
        c.gridy = 3;
        mainPanel.add(antarCheckBox, c);

        // Add dropdown menu paket ke panel
        c.gridy = 0;
        c.gridx = 4;
        c.fill = 1;
        c.weightx = 1;
        mainPanel.add(paketComboBox, c);

        // Add tombol show paket ke panel
        c.gridx = 5;
        mainPanel.add(showPaketButton, c);

        // Add text field untuk berat ke panel
        c.gridx = 4;
        c.gridy = 1;
        mainPanel.add(beratTextField, c);

        // Add tombol buat nota ke panel
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 6;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        mainPanel.add(createNotaButton, c);

        // Add tombol back ke pael
        c.gridy = 5;
        mainPanel.add(backButton, c);
        
        // Menambahkan panel ke frame
        add(mainPanel, BorderLayout.CENTER);

        // ActionListener untuk tombol show paket, memanggil showPaket()
        showPaketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPaket();
            }
        });

        // ActionListener untuk tombol back, memanggil handleBack()
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
        });

        // ActionListener untuk tombol buat nota, memanggil createNota()
        createNotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNota();
            }
        });

    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        Member member = memberSystemGUI.getLoggedInMember();
        String beratString = beratTextField.getText();
        // Validasi field berat (harus >0 dan tidak kosong)
        if (!isPosNumeric(beratString) || beratString.equals("")) {
            // Memunculkan dialog box, lalu reset field dan return
            JOptionPane.showMessageDialog(this, "Berat cucian harus berisi angka!", "Berat Invalid", JOptionPane.ERROR_MESSAGE);
            beratTextField.setText("");
            return;
        }
        int berat = Integer.parseInt(beratString);
        // Validasi berat >= 2
        if (berat < 2) {
            // Memunculkan dialog box, set berat ke 2
            JOptionPane.showMessageDialog(this, "Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg", "info", JOptionPane.INFORMATION_MESSAGE);
            berat = 2;
        }
        // Mengambil paket yang dipilih di dropdown, lalu buat objek nota dan tambah service
        String paket = paketComboBox.getSelectedItem().toString();
        Nota nota = new Nota(member, berat, paket, fmt.format(cal.getTime()));
        nota.createService(setrikaCheckBox.isSelected(), antarCheckBox.isSelected());
        // Add nota ke list milik member dan list NotaManager
        member.addNota(nota);
        NotaManager.addNota(nota);
        // Munculkan dialog box dan reset semua field ke default
        JOptionPane.showMessageDialog(this, "Nota berhasil dibuat!", "Success", JOptionPane.INFORMATION_MESSAGE);
        resetFields();
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // Reset semua field ke default
        resetFields();
        // Mengambil instance mainFrame, lalu navigasi ke MemberSystemGUI
        MainFrame frame = MainFrame.getInstance();
        frame.navigateTo(MemberSystemGUI.KEY);
    }

    /**
     * Method untuk mengosongkan kembali semua field, set semuanya ke default.
     */
    private void resetFields() {
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
        beratTextField.setText("");
        paketComboBox.setSelectedIndex(0);
    }
}
