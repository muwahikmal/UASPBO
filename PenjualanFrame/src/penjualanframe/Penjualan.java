package mainframe;

public class Penjualan {
    private int idPenjualan;
    private int idPelanggan;
    private int idMobil;
    private double totalBiaya;

    public Penjualan(int idPenjualan, int idPelanggan, int idMobil, double totalBiaya) {
        this.idPenjualan = idPenjualan;
        this.idPelanggan = idPelanggan;
        this.idMobil = idMobil;
        this.totalBiaya = totalBiaya;
    }

    public int getIdPenjualan() {
        return idPenjualan;
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public int getIdMobil() {
        return idMobil;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }
}
