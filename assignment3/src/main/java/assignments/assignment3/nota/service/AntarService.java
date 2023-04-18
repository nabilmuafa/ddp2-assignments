package assignments.assignment3.nota.service;

public class AntarService implements LaundryService{
    private boolean done = false;
    @Override
    public String doWork() {
        this.done = true;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        return this.done;
    }

    @Override
    public long getHarga(int berat) {
        int harga = 500*berat;
        if (harga < 2000){
            harga = 2000;
        }
        return harga;
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
