package mainframe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelangganService {

    public void createPelanggan(String nama, String nik, String noTelp, String alamat) {
        String query = "INSERT INTO data_pelanggan (nama, nik, notelp, alamat) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setString(2, nik);
            stmt.setString(3, noTelp);
            stmt.setString(4, alamat);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pelanggan> readPelanggan() {
        List<Pelanggan> pelangganList = new ArrayList<>();
        String query = "SELECT * FROM data_pelanggan";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                pelangganList.add(new Pelanggan(
                        rs.getInt("idpelanggan"),  // Pastikan kolom ini sesuai dengan nama di tabel database
                        rs.getString("nama"),
                        rs.getString("nik"),
                        rs.getString("notelp"),
                        rs.getString("alamat")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pelangganList;
    }

    public void updatePelanggan(int idPelanggan, String nama, String nik, String noTelp, String alamat) {
        String query = "UPDATE data_pelanggan SET nama = ?, nik = ?, notelp = ?, alamat = ? WHERE idpelanggan = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setString(2, nik);
            stmt.setString(3, noTelp);
            stmt.setString(4, alamat);
            stmt.setInt(5, idPelanggan);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePelanggan(int idPelanggan) {
        String query = "DELETE FROM data_pelanggan WHERE idpelanggan = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idPelanggan);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
