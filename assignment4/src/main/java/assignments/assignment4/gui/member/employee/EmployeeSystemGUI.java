package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        JButton cuci = new JButton("It's nyuci time");
        JButton lihatNota = new JButton("Display list nota");
        return new JButton[]{
            cuci, lihatNota
        };
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void displayNota() {
        // Memunculkan dialog box "Belum ada nota" jika list nota masih kosong (length == 0)
        if (NotaManager.notaList.length == 0){
            JOptionPane.showMessageDialog(this, "Belum ada nota", "List Nota", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Menggunakan syntax HTML pada JLabel agar bisa newline
        String notaStatus = "<html>";
        for (Nota nota : NotaManager.notaList) {
            // Menambahkan status setiap nota ke notaStatus, lalu newline
            notaStatus += nota.getNotaStatus() + "<br/>";
        }
        notaStatus += "</html>";
        // Membuat JLabel dari notaStatus
        JLabel statusList = new JLabel(notaStatus);
        // Memunculkan dialog box berisi status nota
        JOptionPane.showMessageDialog(this, statusList, "Detail Nota", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        JOptionPane.showMessageDialog(this, String.format("Stand back! %s beginning to nyuci!", loggedInMember.getNama()), "Nyuci Time", JOptionPane.INFORMATION_MESSAGE);
        // Memunculkan dialog box lalu return jika notaList masih kosong
        if (NotaManager.notaList.length == 0) {
            JOptionPane.showMessageDialog(this, "Belum ada cucian yang bisa dikerjakan!", "Nyuci Results", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Menggunakan syntax HTML agar bisa newline
        String results = "<html>";
        for (Nota nota : NotaManager.notaList){
            // Menambahkan hasil pengerjaan tiap nota ke results, lalu newline
            results += nota.kerjakan() + "<br/>";
        }
        results += "</html>";
        // Membuat JLabel dengan tulisan results
        JLabel resultLabel = new JLabel(results);
        // Memunculkan dialog box berisi hasil pengerjaan
        JOptionPane.showMessageDialog(this, resultLabel, "Nyuci Results", JOptionPane.INFORMATION_MESSAGE);
    }
}
