package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import java.util.ArrayList;
import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    // Scanner, date formatter, and list
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> notaList = new ArrayList<Nota>();
    private static ArrayList<Member> memberList = new ArrayList<Member>();

    /*
     * Main driver for program
     */
    public static void main(String[] args) {
        boolean isRunning = true;
        // Does a while loop while input from user is not 0
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                // Sentinel value handling
                case "0" -> isRunning = false;
                // Invalid input handling
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    /*
     * Method to generate a new user (Member)
     */
    private static void handleGenerateUser() {
        // Input for name and phone number
        System.out.println("Masukan nama Anda: ");
        String nama = input.nextLine();
        System.out.println("Masukkan nomor handphone Anda: ");
        String noHp = input.nextLine();
        // Digit validation for converting to int
        while(!isNumeric(noHp)){
            System.out.println("Field nomor hp hanya menerima digit.");
            noHp = input.nextLine();
        }
        // Creating new Member object using the credentials given
        Member member = new Member(nama, noHp);
        for (Member i: memberList){
            // Exit if user with the same ID already exists
            if (i.getId().equals(member.getId())){
                System.out.println("Member dengan nama " + nama + " dan nomor hp " + noHp + " sudah ada!");
                return;
            }
        }
        // Otherwise, add member to memberList
        memberList.add(member);
        System.out.println("Berhasil membuat member dengan ID " + member.getId() + "!");
    }

    /*
     * Method to generate a new nota (Nota)
     */
    private static void handleGenerateNota() {
        // Inputting ID
        System.out.println("Masukkan ID member: ");
        String idMember = input.nextLine();
        // ID validation, check if it exists in memberList
        boolean isAMember = false;
        Member currentMember = null;
        for (Member member: memberList){
            // Assign member object to currentMember if member with given ID exists
            if (member.getId().equals(idMember)){
                currentMember = member;
                // Adds bonus counter for every new Nota on the same member
                member.addBonusCounter();
                isAMember = true;
                break;
            }
        }
        if (isAMember){
            // Input for laundry package and weight
            System.out.println("Masukkan paket laundry:");
            String paket = input.nextLine();
            // Calling inputPaket from NotaGenerator to validate paket
            paket = inputPaket(paket);
            System.out.println("Masukkan berat cucian anda: ");
            String berat = input.nextLine();
            // Weight validation using isPosNumeric, has to be a positive integer
            while (!isPosNumeric(berat)){
                System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                berat = input.nextLine();
            }
            // Takes the numeric value of the weight string (after validation)
            int beratInt = Integer.parseInt(berat);
            // Counts it as 2 kg if weight is < 2 kg
            if (beratInt < 2){
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                beratInt = 2;
            }
            // Create a new Nota object from the given informations, add it to notaList
            Nota nota = new Nota(currentMember, paket, beratInt, fmt.format(cal.getTime()));
            notaList.add(nota);
            System.out.println("Berhasil menambahkan nota!");
            // Printing ID, nota, and status using Nota methods
            System.out.println("[ID Nota = " + nota.getIdNota() + "]");
            System.out.println(nota.getNota());
            System.out.println(nota.getStatusMsg());
        }
        // Output if member doesn't exist
        else {
            System.out.println("Member dengan ID " + idMember + " tidak ditemukan!");
        }

    }

    /*
     * Method to print the list of notas currently available and its status
     */
    private static void handleListNota() {
        System.out.println("Terdaftar " + notaList.size() + " nota dalam sistem.");
        // Iterating through notaList, printing ID and status
        for (Nota nota: notaList){
            System.out.print("- [" + nota.getIdNota() + "] ");
            System.out.println(nota.getStatusMsg());
        }
    }
    /*
     * Method to print the list of members registered, their ID and name
     */
    private static void handleListUser() {
        System.out.println("Terdaftar " + memberList.size() + " member dalam sistem.");
        // Iterating through memberList, printing ID and name
        for (Member member: memberList){
            System.out.println("- " + member.getId() + " : " + member.getNama());
        }
    }

    /*
     * Method to handle taking finished laundries
     */
    private static void handleAmbilCucian() {
        // Input for ID nota
        System.out.println("Masukan ID nota yang akan diambil: ");
        String id = input.nextLine();
        // ID validation using isNumeric from NotaGenerator (has to be an integer)
        while(!isNumeric(id)){
            System.out.println("ID Nota berbentuk angka!");
            id = input.nextLine();
        }
        // Converting to integer after validation
        int idNota = Integer.parseInt(id);
        // Iterating through notaList to find nota with corresponding ID
        for (int i=0; i<notaList.size(); i++){
            Nota currentNota = notaList.get(i);
            // If the nota object with the ID was found, do some checking:
            if (currentNota.getIdNota()==idNota){
                // Check the current nota's status before taking
                if (!currentNota.getStatus()){
                    System.out.println("Nota dengan ID " + idNota + " gagal diambil!");
                    return;
                }
                else{
                    System.out.println("Nota dengan ID " + idNota + " berhasil diambil!");
                    // Remove current nota object from the list after successfully taking
                    notaList.remove(i);
                    return;
                }
            }
        }
        // Output if nota with the inputted ID wasn't found
        System.out.println("Nota dengan ID " + idNota + " tidak ditemukan!");
    }

    private static void handleNextDay() {
        System.out.println("Dek Depe tidur hari ini... zzz...");
        // Adds one day to the calendar object
        cal.add(Calendar.DATE, 1);
        // Iterate through notaList, decrement sisaHariPengerjaan by 1
        for (Nota nota: notaList){
            nota.updateSisaHariPengerjaan();
            // If after updating sisaHariPengerjaan status is ready, output message
            if (nota.getStatus()){
                System.out.println("Laundry dengan nota ID " + nota.getIdNota() + " sudah dapat diambil!");
            }
        }
        System.out.println("Selamat pagi dunia!");
        System.out.println("Dek Depe: It's CuciCuci time.");
    }

    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

}
