package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import java.util.ArrayList;
import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> notaList = new ArrayList<Nota>();
    private static ArrayList<Member> memberList = new ArrayList<Member>();

    public static void main(String[] args) {
        boolean isRunning = true;
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
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void handleGenerateUser() {
        System.out.println("Masukan nama Anda: ");
        String nama = input.nextLine();
        System.out.println("Masukkan nomor handphone Anda: ");
        String noHp = input.nextLine();
        while(!isNumeric(noHp)){
            System.out.println("Field nomor hp hanya menerima digit.");
            noHp = input.nextLine();
        }
        Member member = new Member(nama, noHp);
        for (Member i: memberList){
            if (i.getId().equals(member.getId())){
                System.out.println("Member dengan nama " + nama + " dan nomor hp " + noHp + " sudah ada!");
                return;
            }
        }
        memberList.add(member);
        System.out.println("Berhasil membuat member dengan ID " + member.getId() + "!");
    }

    private static void handleGenerateNota() {
        System.out.println("Masukkan ID member: ");
        String idMember = input.nextLine();
        boolean isAMember = false;
        Member currentMember = null;
        for (Member member: memberList){
            if (member.getId().equals(idMember)){
                currentMember = member;
                member.addBonusCounter();
                isAMember = true;
                break;
            }
        }
        if (isAMember){
            System.out.println("Masukkan paket laundry:");
            String paket = input.nextLine();
            paket = inputPaket(paket);
            System.out.println("Masukkan berat cucian anda: ");
            String berat = input.nextLine();
            while (!isPosNumeric(berat)){
                System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                berat = input.nextLine();
            }
            int beratInt = Integer.parseInt(berat);
            if (beratInt < 2){
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                beratInt = 2;
            }
            Nota nota = new Nota(currentMember, paket, beratInt, fmt.format(cal.getTime()));
            notaList.add(nota);
            System.out.println("Berhasil menambahkan nota!");
            System.out.println("[ID Nota = " + nota.getIdNota() + "]");
            System.out.println(nota.getNota());
            System.out.println(nota.getStatusMsg());
        }
        else {
            System.out.println("Member dengan ID " + idMember + " tidak ditemukan!");
        }

    }

    private static void handleListNota() {
        System.out.println("Terdaftar " + notaList.size() + " nota dalam sistem.");
        for (Nota nota: notaList){
            System.out.print("- [" + nota.getIdNota() + "] ");
            System.out.println(nota.getStatusMsg());
        }
    }

    private static void handleListUser() {
        System.out.println("Terdaftar " + memberList.size() + " member dalam sistem.");
        for (Member member: memberList){
            System.out.println("- " + member.getId() + " : " + member.getNama());
        }
    }

    private static void handleAmbilCucian() {
        // TODO: handle ambil cucian
    }

    private static void handleNextDay() {
        // TODO: handle ganti hari
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
