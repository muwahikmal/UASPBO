package mainframe;

import mobilframe.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MobilService {

    public void createMobil(String merk, int tahun, int kuantitas, double harga) {
        String query = "INSERT INTO data_mobil (merk, tahun, kuantitas, harga) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, merk);
            stmt.setInt(2, tahun);
            stmt.setInt(3, kuantitas);
            stmt.setDouble(4, harga);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Mobil> readMobil() {
        List<Mobil> mobilList = new ArrayList<>();
        String query = "SELECT * FROM data_mobil";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                mobilList.add(new Mobil(
                        rs.getInt("idmobil"),
                        rs.getString("merk"),
                        rs.getInt("tahun"),
                        rs.getInt("kuantitas"),
                        rs.getDouble("harga")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mobilList;
    }

    public void updateMobil(int idMobil, String merk, int tahun, int kuantitas, double harga) {
        String query = "UPDATE data_mobil SET merk = ?, tahun = ?, kuantitas = ?, harga = ? WHERE idmobil = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, merk);
            stmt.setInt(2, tahun);
            stmt.setInt(3, kuantitas);
            stmt.setDouble(4, harga);
            stmt.setInt(5, idMobil);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMobil(int idMobil) {
        String query = "DELETE FROM data_mobil WHERE idmobil = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idMobil);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
