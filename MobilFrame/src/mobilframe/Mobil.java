package mainframe;

public class Mobil {
    private int idMobil;
    private String merk;
    private int tahun;
    private int kuantitas;
    private double harga;

    public Mobil(int idMobil, String merk, int tahun, int kuantitas, double harga) {
        this.idMobil = idMobil;
        this.merk = merk;
        this.tahun = tahun;
        this.kuantitas = kuantitas;
        this.harga = harga;
    }

    public int getIdMobil() {
        return idMobil;
    }

    public String getMerk() {
        return merk;
    }

    public int getTahun() {
        return tahun;
    }

    public int getKuantitas() {
        return kuantitas;
    }

    public double getHarga() {
        return harga;
    }
}
