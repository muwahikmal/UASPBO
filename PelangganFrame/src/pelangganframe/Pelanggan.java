package mainframe;

public class Pelanggan {
    private int idPelanggan;
    private String nama;
    private String nik;
    private String noTelp;
    private String alamat;

    public Pelanggan(int idPelanggan, String nama, String nik, String noTelp, String alamat) {
        this.idPelanggan = idPelanggan;
        this.nama = nama;
        this.nik = nik;
        this.noTelp = noTelp;
        this.alamat = alamat;
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public String getNama() {
        return nama;
    }

    public String getNik() {
        return nik;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public String getAlamat() {
        return alamat;
    }
}
