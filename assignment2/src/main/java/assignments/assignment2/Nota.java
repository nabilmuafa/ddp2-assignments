package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {
    /**
     * Instance variables:
     * 
     * idCounter            = to count how many IDs are registered,
     *                      at the same time acts as ID for every new Nota created
     * idNota               = current Nota's ID
     * paket                = laundry package chosen
     * member               = "owner" of the current Nota object
     * berat                = laundry weight
     * tanggalMasuk         = beginning date of laundry
     * sisaHariPengerjaan   = day left for laundry to be "ready"
     * isReady              = laundry status, ready (finished) or not yet
     */
    private static int idCounter = 0;
    private int idNota;
    private String paket;
    private Member member;
    private int berat;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private boolean isReady;
    /**
     * Constructor for Nota class
     */
    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        // assign current idCounter number to idNota, then increment (for next ID)
        this.idNota = idCounter++;
        // assigning every parameter to its respective instance variables
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        // switch case for sisaHariPengerjaan according to paket chosen
        switch (paket.toLowerCase()){
            case "reguler"  -> this.sisaHariPengerjaan = 3;
            case "fast"     -> this.sisaHariPengerjaan = 2;
            case "express"  -> this.sisaHariPengerjaan = 1;
        }
        // Inital value of isReady is always false
        this.isReady = false;
    }
    /*
     * Getters
     */
    public int getIdNota(){
        return this.idNota;
    }
    public boolean getStatus(){
        return this.isReady;
    }
    public int getSisaHariPengerjaan(){
        return this.sisaHariPengerjaan;
    }
    /*
     * Method to update isReady to its opposite value
     */
    public void updateIsReady(){
        this.isReady = !this.isReady;
    }
    /*
     * Method to update sisaHariPengerjaan by decrementing by one
     */
    public void updateSisaHariPengerjaan(){
        // Check first, if sisaHariPengerjaan is already 0 theres no need to decrement
        if (this.sisaHariPengerjaan > 0){
            this.sisaHariPengerjaan--;
            // Updates isReady if sisaHariPengerjaan is 0
            if (this.sisaHariPengerjaan == 0){
                this.updateIsReady();
            }
        }
    }
    /*
     * Method to get status message according to isReady status
     */
    public String getStatusMsg(){
        if (!this.isReady){
            return "Status:      \t: Belum bisa diambil :(";
        }
        else{
            return "Status:      \t: Sudah dapat diambil!";
        }
    }
    /*
     * Method to generate Nota by calling generateNotaDisc from NotaGenerator
     */
    public String getNota(){
        return NotaGenerator.generateNotaDisc(
            this.member.getId(), paket, berat, tanggalMasuk, this.member.getDiscount()
            );
    }
}
