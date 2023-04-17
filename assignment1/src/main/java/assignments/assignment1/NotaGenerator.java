package assignments.assignment1;

import java.util.Scanner;
import java.lang.Character;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Helper method untuk menentukan apakah sebuah string
     * numerik (bisa diubah ke int) 
     */
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
     * Method untuk memeriksa apakah sebuah string numerik dan positif
     */
    public static boolean isPosNumeric(String str){
        // Memeriksa apakah numerik dengan isNumeric
        if (isNumeric(str)){
            long berat = Long.parseLong(str);
            // Jika berat <= 0, maka tidak positif
            if (berat <= 0){
                return false;
            }
        }
        else {
            return false;
        }
        return true;
    }
    /**
     * Method untuk validasi input paket
     * 
     * @param paket : paket yang dimasukkan
     * @return String berupa paket setelah validasi
     */
    public static String inputPaket(String paket){
        // Ignore case untuk memeriksa
        String paketIgnoreCase = paket.toLowerCase();
        // Jika paket yang diinput selain reguler, fast, atau express
        while (!paketIgnoreCase.equals("reguler") &&
            !paketIgnoreCase.equals("fast") &&
            !paketIgnoreCase.equals("express")){
                // cetak paket yang ada jika input "?"
                if (paket.equals("?")){
                    showPaket();
                }
                else{
                    System.out.println("Paket " + paket + " tidak diketahui.");
                    System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                }
                System.out.println("Masukkan paket laundry:");
                paket = input.nextLine();
                paketIgnoreCase = paket.toLowerCase();
        }
        return paket;
    }
    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        String running = "1";
        // While loop dengan sentinel value 0
        while (!running.equals("0")){
            printMenu();
            System.out.print("Pilihan : ");
            running = input.nextLine();
            // Validasi input pilihan opsi
            if (!running.equals("1") && !running.equals("2") && !running.equals("0")){
                System.out.println("================================");
                System.out.println("Pilihan tidak dikenali. Silahkan coba kembali.");
            }
            // Jika user exit
            else if (running.equals("0")){
                System.out.println("================================");
                System.out.println("Terima kasih telah menggunakan Nota Generator!");
            }
            else{
                System.out.println("================================");
                System.out.println("Masukkan nama anda:");
                String nama = input.nextLine();
                System.out.println("Masukkan nomor handphone anda:");
                // Meminta sekaligus validasi input nomor HP agar hanya digit yang dimasukkan
                String nomorHP = input.nextLine();
                while (!isNumeric(nomorHP)){
                    System.out.println("Nomor HP hanya menerima digit");
                    nomorHP = input.nextLine();
                }
                // Jika user memilih pilihan 1
                if (running.equals("1")){
                    // Memanggil generateID, disimpan ke string idLaundry
                    String idLaundry = generateId(nama, nomorHP);
                    System.out.println("ID Anda : " + idLaundry);
                }
                // Jika user memilih pilihan 2
                else{
                    System.out.println("Masukkan tanggal terima:");
                    String tanggalTerima = input.nextLine();
                    System.out.println("Masukkan paket laundry:");
                    String paket = input.nextLine();
                    // Validasi input paket
                    paket = inputPaket(paket);
                    // Input sekaligus validasi berat cucian agar yang dimasukkan hanya digit
                    System.out.println("Masukkan berat cucian anda [Kg]:");
                    String beratCucian = input.nextLine();
                    while (!isPosNumeric(beratCucian)){
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        beratCucian = input.nextLine();
                    }
                    // Mengubah berat cucian menjadi integer
                    int berat = Integer.parseInt(beratCucian);
                    // Membulatkan berat cucian menjadi 2 kg jika kurang dari 2 kg
                    if (berat < 2){
                        System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                        berat = 2;
                    }
                    // Mencetak nota laundry dengan memanggil generateNota
                    System.out.println("Nota Laundry");
                    String nota = generateNota(generateId(nama, nomorHP), paket, berat, tanggalTerima);
                    System.out.println(nota);
                }
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
    public static void showPaket() {
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
        // Memisahkan nama berdasarkan spasi, mengambil kata pertama saja
        String[] namaPerKata = nama.split(" ");
        String namaKataPertama = namaPerKata[0].toUpperCase();
        // Membuat bagian pertama dari ID
        String idLaundry = namaKataPertama + "-" + nomorHP;
        // Perhitungan checksum
        int checkSum = 0;
        for (int i=0; i<idLaundry.length(); i++){
            // Mengambil karakter yang sedang di-iterasi
            char karakterId = idLaundry.charAt(i);
            // Jika ASCII value karakter tsb ada di rentang 48 hingga 57,
            // artinya adalah angka sehingga cukup ambil numeric valuenya
            if (karakterId >= '0' && karakterId <= '9'){
                checkSum += Character.getNumericValue(karakterId);
            }
            // Jika ASCII value karakter tsb ada di rentang 65 hingga 90,
            // artinya adalah huruf kapital, ambil
            else if (karakterId >= 'A' && karakterId <= 'Z'){
                checkSum += (int)(karakterId - 'A') + 1;
            }
            // Selain itu, tambahkan 7
            else{
                checkSum += 7;
            }
        }
        // Simpan string checksum dengan modulo 100 agar yang diambil 2 digit terakhir
        String checkSumString = String.format("%02d", checkSum % 100);
        // Tambahkan ke id laundry
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
        // Memanggil generateNotaDisc dengan nilai diskon false
        return generateNotaDisc(id, paket, berat, tanggalTerima, false);
    }

    /**
     * Method untuk generateNota dengan mengconsider diskon (untuk TP2)
     */
    public static String generateNotaDisc(String id, String paket, int berat, String tanggalTerima, boolean disc){
        String nota = "";
        // Mencetak ID, paket, harga
        nota += "ID    : " + id + "\nPaket : " + paket + "\nHarga :\n";
        int hargaPerPaket = 0;
        int hari = 0;
        // Switch case untuk harga dan durasi paket
        switch (paket.toLowerCase()){
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
        // Mengoutput harga total
        int hargaTotal = berat * hargaPerPaket;
        nota += Integer.toString(berat) + " kg x " + Integer.toString(hargaPerPaket) + " = " + Integer.toString(hargaTotal);
        if (disc) {
            nota += " = " + hargaTotal/2 + " (Discount member 50%!!!)";
        }
        nota += "\nTanggal Terima  : " + tanggalTerima + "\n";
        // Formatting tanggal selesai dan menambahkan ke nota
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate tanggalTerimaDate = LocalDate.parse(tanggalTerima, formatter);
        String tanggalSelesai = formatter.format(tanggalTerimaDate.plusDays(hari));
        nota += "Tanggal Selesai : " + tanggalSelesai;
        return nota;
    }
}
