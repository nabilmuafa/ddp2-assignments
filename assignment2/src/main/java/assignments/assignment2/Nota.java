package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {
    private int idNota;
    private String paket;
    private Member member;
    private int berat;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private boolean isReady;
    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        this.idNota = idNota++;
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        switch (paket.toLowerCase()){
            case "reguler"  -> this.sisaHariPengerjaan = 3;
            case "fast"     -> this.sisaHariPengerjaan = 2;
            case "express"  -> this.sisaHariPengerjaan = 1;
        }
        this.isReady = false;
    }
    public boolean getStatus(){
        return this.isReady;
    }
    public int getSisaHariPengerjaan(){
        return this.sisaHariPengerjaan;
    }
    public void updateIsReady(){
        this.isReady = !this.isReady;
    }
    public void updateSisaHariPengerjaan(){
        if (this.sisaHariPengerjaan > 0){
            this.sisaHariPengerjaan--;
            if (this.sisaHariPengerjaan == 0){
                this.updateIsReady();
            }
        }
    }
    public String getStatusMsg(){
        if (!this.isReady){
            return "Status:      \t: Belum bisa diambil :(";
        }
        else{
            return "Status:      \t: Sudah dapat diambil!";
        }
    }
}
