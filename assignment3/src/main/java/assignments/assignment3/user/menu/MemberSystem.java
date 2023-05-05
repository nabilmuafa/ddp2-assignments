package assignments.assignment3.user.menu;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
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
        // Switch case to call helper methods based on user input
        switch (choice){
            case 1 -> this.handleGenerateNota();
            case 2 -> this.printNota();
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
        // Extends memberList array
        Member[] memberListNew = new Member[memberList.length+1];
        for (int i=0; i<memberList.length; i++){
            memberListNew[i] = memberList[i];
        }
        memberList = memberListNew;
        // Assigns new member to last index
        memberList[memberList.length-1] = member;
    }

    // Helper methods

    /**
     * Method that creates a new nota for user
     */
    protected void handleGenerateNota(){
        System.out.println("Masukkan paket laundry:");
        NotaGenerator.showPaket();
        // Assuming paket & berat must be valid, no need to validate
        String paket = in.nextLine();
        System.out.println("Masukkan berat cucian anda [Kg]:");
        String beratString = in.nextLine();
        int berat = Integer.parseInt(beratString);
        if (berat < 2){
            // Rounds berat to 2 if it is less than 2
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            berat = 2;
        }
        // Creates new Nota object
        Nota nota = new Nota(loginMember, berat, paket, fmt.format(cal.getTime()));
        // Asks user for setrika
        System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?\nHanya tambah 1000 / kg :0");
        System.out.print("[ketik x untuk tidak mau]: ");
        String setrika = in.nextLine();
        // Asks user for antar
        System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!\nCuma 2000 / 4kg, kemudian 500 / kg");
        System.out.print("[ketik x untuk tidak mau]: ");
        String antar = in.nextLine();
        // Calls createService by checking if user input is x
        nota.createService(!setrika.equals("x"), !antar.equals("x"));
        // Adds nota to member's notaList and NotaManager's notaList
        loginMember.addNota(nota);
        NotaManager.addNota(nota);
        System.out.println("Nota berhasil dibuat!");
    }

    // Prints members' nota
    protected void printNota(){
        for (Nota nota : loginMember.getNotaList()){
            System.out.println(nota);
        }
    }
}