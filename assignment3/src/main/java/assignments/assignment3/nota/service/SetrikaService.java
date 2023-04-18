package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    private boolean done = false;

    @Override
    public String doWork() {
        this.done = true;
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        return this.done;
    }

    @Override
    public long getHarga(int berat) {
        return 500*berat;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
