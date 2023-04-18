package assignments.assignment3.user.menu;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

public class MemberSystem extends SystemCLI {
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        switch (choice){
            case 1 -> this.handleGenerateNota();
            case 3 -> logout = true;
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        Member[] memberListNew = new Member[memberList.length+1];
        for (int i=0; i<memberList.length; i++){
            memberListNew[i] = memberList[i];
        }
        memberList = memberListNew;
        memberList[memberList.length-1] = member;
    }

    protected void handleGenerateNota(){
        System.out.println("Masukkan paket laundry:");
        NotaGenerator.showPaket();
        // Dikatakan "input dijamin valid"
        // asumsi paket & berat pasti benar jadi tidak perlu validasi
        String paket = in.nextLine();
        System.out.println("Masukkan berat cucian anda:");
        int berat = in.nextInt();
        if (berat < 2){
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            berat = 2;
        }
        Nota nota = new Nota(loginMember, berat, paket, fmt.format(cal.getTime()));
        System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?\nHanya tambah 1000 / kg :0");
        System.out.print("[ketik x untuk tidak mau]: ");
        String setrika = in.nextLine();
        System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!\nCuma 2000 / 4kg, kemudian 500 / kg");
        System.out.print("[ketik x untuk tidak mau]: ");
        String antar = in.nextLine();
        nota.createService(!setrika.equals("x"), !antar.equals("x"));
        loginMember.addNota(nota);
        System.out.println("Nota berhasil dibuat!");
    }
}