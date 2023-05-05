package assignments.assignment3.nota;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment1.NotaGenerator;

public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services;
    private long baseHarga;
    private int sisaHariPengerjaan;
    private  int berat;
    private int id;
    private String tanggalMasuk;
    private boolean isDone;
    static public int totalNota;

    /**
     * Constructor for class Nota
     * @param member : member that created the Nota
     * @param berat : weight of laundry
     * @param paket : laundry package
     * @param tanggal : start date of laundry
     */
    public Nota(Member member, int berat, String paket, String tanggal) {
        // assign current totalNota number to id, then increment (for next ID)
        this.id = totalNota++;
        // assigning every parameter to its respective instance variables
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggal;
        // switch case for sisaHariPengerjaan according to paket chosen
        switch (paket.toLowerCase()){
            case "reguler":
                this.sisaHariPengerjaan = 3;
                this.baseHarga = 7000*berat;
                break;
            case "fast":
                this.sisaHariPengerjaan = 2;
                this.baseHarga = 10000*berat;
                break;
            case "express":
                this.sisaHariPengerjaan = 1;
                this.baseHarga = 12000*berat;
        }
        // Creating CuciService by default to add to service
        this.services = new LaundryService[1];
        this.services[0] = new CuciService();

        // Inital value of isDone is always false
        this.isDone = false;
    }

    /**
     * Method to add another service based on user input
     * @param wantSetrika : Adds SetrikaService if true
     * @param wantAntar : Adds AntarService if true
     */
    public void createService(boolean wantSetrika, boolean wantAntar){
        if (wantSetrika) {
            // Creating SetrikaService object, adds to services
            SetrikaService setrika = new SetrikaService();
            this.addService(setrika);
        }
        if (wantAntar) {
            // Creating AntarService object, adds to services
            AntarService antar = new AntarService();
            this.addService(antar);
        }
    }

    /**
     * Method to add a service to the services array
     * @param service
     */
    public void addService(LaundryService service){
        // Extends the services array
        LaundryService[] newServices = new LaundryService[services.length+1];
        for (int i=0; i<services.length; i++){
            newServices[i] = services[i];
        }
        services = newServices;
        // Assigns the new element to the last index
        services[services.length-1] = service;
    }

    /**
     * Method that works on Nota based on its working status
     * @return
     */
    public String kerjakan(){
        if (!this.isDone){
            String message = "";
            // Iterates through the services
            for (LaundryService service: services) {
                // doWork will be called if an unfinished service is found
                if (!service.isDone()){
                    message = service.doWork();
                    break;
                }
            }
            // Updates the nota finish status after doWork() called
            this.isDone = this.updateStatus();
            // Returns the working status after doWork()
            return String.format("Nota %d : %s", this.id, message);
        }
        // Returns nota status by default if nota is done
        return this.getNotaStatus();
    }

    // Decrements sisaHariPengerjaan if Nota is not done
    public void toNextDay() {
        if (!this.isDone){
            this.sisaHariPengerjaan--;
        }
    }

    /**
     * Method that calculates the final price after considering services and late compensation
     * @return
     */
    public long calculateHarga(){
        long harga = baseHarga;
        for (LaundryService service: services){
            // Calls getHarga of every service, adds it to final price
            harga += service.getHarga(berat);
        }
        /*
         * If sisaHariPengerjaan reaches negative, it means that the Nota
         * is late, because it's not done even after the due date
         */
        if (this.sisaHariPengerjaan < 0){
            harga += 2000*this.sisaHariPengerjaan;
        }
        // Final price wont reach less than 0
        if (harga < 0) harga = 0;
        return harga;
    }

    /**
     * Returns the status of the nota.
     * @return
     */
    public String getNotaStatus(){
        String done = this.isDone ? "Sudah" : "Belum";
        return String.format("Nota %d : %s selesai.", this.id, done);
    }

    /**
     * Updates the done status of the nota by iterating through the services
     * and checking the isDone
     * @return
     */
    public boolean updateStatus() {
        for (LaundryService service: services){
            if (!service.isDone()){
                return false;
            }
        }
        return true;
    }

    /**
     * Prints the Nota with added messages
     */
    @Override
    public String toString(){
        String nota = String.format("[ID Nota = %d]\n", this.id);
        // Calls generateNota from NotaGenerato (TP 1)
        nota += NotaGenerator.generateNota(
            this.member.getId(), this.paket, this.berat, this.tanggalMasuk);
        // Replaces the start & finish dates (just to pass Gradle test)
        nota = nota.replaceAll("Tanggal Terima", "tanggal terima");
        nota = nota.replaceAll("Tanggal Selesai", "tanggal selesai");
        // Prints the services this Nota uses
        nota += "\n--- SERVICE LIST ---\n";
        for (LaundryService service: services){
            nota += String.format("-%s @ Rp.%d\n", service.getServiceName(), service.getHarga(this.berat));
        }
        nota += String.format("Harga Akhir: %d", this.calculateHarga());
        // Adds the message if Nota is late
        if (this.isLate()){
            nota += String.format(" Ada kompensasi keterlambatan %d * 2000 hari", -1*this.sisaHariPengerjaan);
        }
        return nota;
    }

    // Method that returns True if nota is late, that is if sisaHariPengerjaan touches negative
    public boolean isLate(){
        return this.sisaHariPengerjaan < 0;
    }
    // Dibawah ini adalah getter

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
    public boolean isDone() {
        return isDone;
    }

    public LaundryService[] getServices(){
        return services;
    }
}
