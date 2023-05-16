package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
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
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        GridBagConstraints c = new GridBagConstraints();

        paketLabel = new JLabel("Paket Laundry: ");
        beratLabel = new JLabel("Berat Cucian (kg):");
        paketComboBox = new JComboBox<String>(new String[]{"Express", "Fast", "Reguler"});
        beratTextField = new JTextField();
        showPaketButton = new JButton("Show Paket");
        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000 / kg)");
        antarCheckBox = new JCheckBox("Tambah Antar Service (2000 / 4kg pertama, kemudian 500 / kg)");
        createNotaButton = new JButton("Buat Nota");
        backButton = new JButton("Kembali");

        c.gridx = 0;
        c.gridy = 0;
        c.fill = 4;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 5, 5);
        mainPanel.add(paketLabel, c);

        c.gridy = 1;
        mainPanel.add(beratLabel,c);

        c.gridy = 2;
        mainPanel.add(setrikaCheckBox, c);

        c.gridy = 3;
        mainPanel.add(antarCheckBox, c);

        c.gridy = 0;
        c.gridx = 4;
        c.fill = 1;
        c.weightx = 1;
        mainPanel.add(paketComboBox, c);

        c.gridx = 0;
        c.gridx = 5;
        mainPanel.add(showPaketButton, c);

        c.gridx = 4;
        c.gridy = 1;
        mainPanel.add(beratTextField, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 6;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        mainPanel.add(createNotaButton, c);

        c.gridy = 5;
        mainPanel.add(backButton, c);
        
        add(mainPanel, BorderLayout.CENTER);
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
        // TODO
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // TODO
    }
}
