package assignments.assignment1;

import java.util.Scanner;
import java.lang.Character;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    public static boolean isNumeric(String str){
        char[] chars = str.toCharArray();
        for (int i=0; i<chars.length; i++){
            if (!Character.isDigit(chars[i])){
                return false;
            }
        }
        return true;
    }
    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        String running = "1";
        while (!running.equals("0")){
            printMenu();
            System.out.print("Pilihan : ");
            running = input.nextLine();
            if (!running.equals("1") && !running.equals("2") && !running.equals("0")){
                System.out.println("================================");
                System.out.println("Pilihan tidak dikenali. Silahkan coba kembali.");
            }
            else if (running.equals("1")){
                System.out.println("================================");
                System.out.println("Masukkan nama anda:");
                String nama = input.nextLine();
                System.out.println("Masukkan nomor handphone anda:");
                String nomorHP = input.nextLine();
                while (!isNumeric(nomorHP)){
                    System.out.println("Nomor HP hanya menerima digit");
                    nomorHP = input.nextLine();
                }
                String idLaundry = generateId(nama, nomorHP);
                System.out.println("ID Anda : " + idLaundry);   
            }
            else if (running.equals("2")){
                System.out.println("================================");
                System.out.println("Masukkan nama anda:");
                String nama = input.nextLine();
                System.out.println("Masukkan nomor handphone anda:");
                String nomorHP = input.nextLine();
                while (!isNumeric(nomorHP)){
                    System.out.println("Nomor HP hanya menerima digit");
                    nomorHP = input.nextLine();
                }
                System.out.println("Masukkan tanggal terima:");
                String tanggalTerima = input.nextLine();
                System.out.println("Masukkan paket laundry:");
                String paket = input.nextLine().toLowerCase();
                while (!paket.equals("express") && !paket.equals("fast") && !paket.equals("reguler")){
                        if (paket.equals("?")){
                            showPaket();
                        }
                        else{
                            System.out.println("Paket " + paket + " tidak diketahui.");
                            System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                        }
                        System.out.println("Masukkan paket laundry:");
                        paket = input.nextLine().toLowerCase();
                }
                System.out.println("Masukkan berat cucian anda [Kg]:");
                String beratCucian = input.nextLine();
                while (!isNumeric(beratCucian)){
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    beratCucian = input.nextLine();
                }
                int berat = Integer.parseInt(beratCucian);
                if (berat < 2){
                    System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                    berat = 2;
                }
                System.out.println("Nota Laundry");
                String nota = generateNota(generateId(nama, nomorHP), paket, berat, tanggalTerima);
                System.out.println(nota);
            }
            else{
                System.out.println("================================");
                System.out.println("Terima kasih telah menggunakan Nota Generator!");
            }
        }
    }

    /**
     * Method untuk menampilkan menu di NotaGenerator.
     */
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /**
     * Method untuk menampilkan paket.
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP){
        String[] namaPerKata = nama.split(" ");
        String namaKataPertama = namaPerKata[0].toUpperCase();
        String idLaundry = namaKataPertama + "-" + nomorHP;
        int checkSum = 0;
        for (int i=0; i<idLaundry.length(); i++){
            char karakterId = idLaundry.charAt(i);
            if ((int)karakterId >= 48 && (int)karakterId < 58){
                checkSum += Character.getNumericValue(karakterId);
            }
            else if ((int)karakterId >= 65 && (int)karakterId < 91){
                checkSum += ((int)karakterId - 64);
            }
            else{
                checkSum += 7;
            }
        }
        String checkSumString = String.format("%02d", checkSum);
        idLaundry += ("-" + checkSumString);
        
        return idLaundry;
    }

    /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */

    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        String nota = "";
        nota += "ID    : " + id + "\n";
        nota += "Paket : " + paket + "\n";
        nota += "Harga :\n";
        int hargaPerPaket = 0;
        int hari = 0;
        switch (paket){
            case "reguler":
                hargaPerPaket = 7000;
                hari = 3;
                break;
            case "fast":
                hargaPerPaket = 10000;
                hari = 2;
                break;
            case "express":
                hargaPerPaket = 12000;
                hari = 1;
                break;
        }
        int hargaTotal = berat * hargaPerPaket;
        nota += Integer.toString(berat) + " kg x " + Integer.toString(hargaPerPaket) + " = " + Integer.toString(hargaTotal) + "\n";
        nota += "Tanggal Terima  : " + tanggalTerima + "\n";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate tanggalTerimaDate = LocalDate.parse(tanggalTerima, formatter);
        String tanggalSelesai = formatter.format(tanggalTerimaDate.plusDays(hari));
        nota += "Tanggal Selesai : " + tanggalSelesai;
        return nota;
    }
}
