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

    public Nota(Member member, int berat, String paket, String tanggal) {
        // assign current idCounter number to idNota, then increment (for next ID)
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
        this.services = new LaundryService[1];
        this.services[0] = new CuciService();

        // Inital value of isDone is always false
        this.isDone = false;
    }

    public void createService(boolean wantSetrika, boolean wantAntar){
        if (wantSetrika) {
            SetrikaService setrika = new SetrikaService();
            this.addService(setrika);
        }
        if (wantAntar) {
            AntarService antar = new AntarService();
            this.addService(antar);
        }
        
    }

    public void addService(LaundryService service){
        LaundryService[] newServices = new LaundryService[services.length+1];
        for (int i=0; i<services.length; i++){
            newServices[i] = services[i];
        }
        services = newServices;
        services[services.length-1] = service;
    }

    public String kerjakan(){
        if (!this.isDone){
            String message = "";
            for (LaundryService service: services) {
                if (!service.isDone()){
                    message = service.doWork();
                    break;
                }
            }
            this.isDone = this.updateStatus();
            return String.format("Nota %d : %s", this.id, message);
        }
        return this.getNotaStatus();
    }

    public void toNextDay() {
        if (!this.isDone){
            this.sisaHariPengerjaan--;
        }
    }

    public long calculateHarga(){
        long harga = baseHarga;
        for (LaundryService service: services){
            harga += service.getHarga(berat);
        }
        if (this.sisaHariPengerjaan < 0){
            harga += 2000*this.sisaHariPengerjaan;
        }
        return harga;
    }

    public String getNotaStatus(){
        String done = this.isDone ? "Sudah" : "Belum";
        return String.format("Nota %d : %s selesai.", this.id, done);
    }

    public boolean updateStatus() {
        for (LaundryService service: services){
            if (!service.isDone()){
                return false;
            }
        }
        return true;
    }
    @Override
    public String toString(){
        String nota = String.format("[ID Nota = %d]\n", this.id);
        nota += NotaGenerator.generateNota(
            this.member.getId(), this.paket, this.berat, this.tanggalMasuk);
        nota = nota.replaceAll("Tanggal Terima", "tanggal terima");
        nota = nota.replaceAll("Tanggal Selesai", "tanggal selesai");
        nota += "\n--- SERVICE LIST ---\n";
        for (LaundryService service: services){
            nota += String.format("-%s @ Rp.%d\n", service.getServiceName(), service.getHarga(this.berat));
        }
        nota += String.format("Harga Akhir: %d", this.calculateHarga());
        if (this.isLate()){
            nota += String.format(" Ada kompensasi keterlambatan %d * 2000 hari", -1*this.sisaHariPengerjaan);
        }
        return nota;
    }

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
