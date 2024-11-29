package mainframe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PenjualanService {

    public void createPenjualan(int idPelanggan, int idMobil, double totalBiaya) {
        String query = "INSERT INTO data_penjualan (idpelanggan, idmobil, totalbiaya) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idPelanggan);
            stmt.setInt(2, idMobil);
            stmt.setDouble(3, totalBiaya);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Penjualan> readPenjualan() {
        List<Penjualan> penjualanList = new ArrayList<>();
        String query = "SELECT * FROM data_penjualan";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                penjualanList.add(new Penjualan(
                        rs.getInt("idpenjualan"),
                        rs.getInt("idpelanggan"),
                        rs.getInt("idmobil"),
                        rs.getDouble("totalbiaya")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return penjualanList;
    }

    public void updatePenjualan(int idPenjualan, int idPelanggan, int idMobil, double totalBiaya) {
        String query = "UPDATE data_penjualan SET idpelanggan = ?, idmobil = ?, totalbiaya = ? WHERE idpenjualan = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idPelanggan);
            stmt.setInt(2, idMobil);
            stmt.setDouble(3, totalBiaya);
            stmt.setInt(4, idPenjualan);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePenjualan(int idPenjualan) {
        String query = "DELETE FROM data_penjualan WHERE idpenjualan = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idPenjualan);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
