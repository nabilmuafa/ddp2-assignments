package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MemberSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "MEMBER";

    public MemberSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }

    @Override
    public String getPageName(){
        return KEY;
    }

    public Member getLoggedInMember(){
        return loggedInMember;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements MemberSystem.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        JButton laundryButton = new JButton("Saya ingin laundry");
        JButton lihatNota = new JButton("Lihat detail nota saya");
        return new JButton[]{
            laundryButton, lihatNota
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
                e -> createNota(),
                e -> showDetailNota(),
        };
    }

    /**
     * Menampilkan detail Nota milik loggedInMember.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void showDetailNota() {

        String kumpulanNota = "";
        if (loggedInMember.getNotaList().length == 0) {
            kumpulanNota = "Kamu belum pernah laundry di CuciCuci. Buat nota cucian pertamamu sekarang!";
        }
        else{
            for (Nota nota : loggedInMember.getNotaList()) {
                kumpulanNota += nota + "\n";
            }
        }
        JTextArea detailNota = new JTextArea(kumpulanNota);
        JScrollPane display = new JScrollPane(detailNota);
        display.setPreferredSize(new Dimension(300, 400));
        JOptionPane.showMessageDialog(this, display, "Detail Nota", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Pergi ke halaman CreateNotaGUI.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void createNota() {
        MainFrame frame = MainFrame.getInstance();
        frame.navigateTo(CreateNotaGUI.KEY);
    }

}
