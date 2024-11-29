import mainframe.Penjualan;
import mainframe.PenjualanService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PenjualanFrame extends JFrame {
    private JTextField idPelangganField, idMobilField, totalBiayaField;
    private JTable table;
    private DefaultTableModel tableModel;
    private PenjualanService penjualanService;

    public PenjualanFrame() {
        penjualanService = new PenjualanService();

        setTitle("Penjualan Management");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("ID Pelanggan:"));
        idPelangganField = new JTextField();
        inputPanel.add(idPelangganField);

        inputPanel.add(new JLabel("ID Mobil:"));
        idMobilField = new JTextField();
        inputPanel.add(idMobilField);

        inputPanel.add(new JLabel("Total Biaya:"));
        totalBiayaField = new JTextField();
        inputPanel.add(totalBiayaField);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(this::handleCreate);
        inputPanel.add(createButton);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(this::handleUpdate);
        inputPanel.add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this::handleDelete);
        inputPanel.add(deleteButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshTable());
        inputPanel.add(refreshButton);

        add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID Penjualan", "ID Pelanggan", "ID Mobil", "Total Biaya"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        refreshTable();
    }

    private void handleCreate(ActionEvent e) {
        int idPelanggan = Integer.parseInt(idPelangganField.getText());
        int idMobil = Integer.parseInt(idMobilField.getText());
        double totalBiaya = Double.parseDouble(totalBiayaField.getText());
        penjualanService.createPenjualan(idPelanggan, idMobil, totalBiaya);
        refreshTable();
        clearFields();
    }

    private void handleUpdate(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int idPenjualan = (int) tableModel.getValueAt(selectedRow, 0);
            int idPelanggan = Integer.parseInt(idPelangganField.getText());
            int idMobil = Integer.parseInt(idMobilField.getText());
            double totalBiaya = Double.parseDouble(totalBiayaField.getText());
            penjualanService.updatePenjualan(idPenjualan, idPelanggan, idMobil, totalBiaya);
            refreshTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang akan diupdate.");
        }
    }

    private void handleDelete(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int idPenjualan = (int) tableModel.getValueAt(selectedRow, 0);
            penjualanService.deletePenjualan(idPenjualan);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang akan dihapus.");
        }
    }

    private void refreshTable() {
        List<Penjualan> penjualanList = penjualanService.readPenjualan();
        tableModel.setRowCount(0);
        for (Penjualan penjualan : penjualanList) {
            tableModel.addRow(new Object[]{
                    penjualan.getIdPenjualan(),
                    penjualan.getIdPelanggan(),
                    penjualan.getIdMobil(),
                    penjualan.getTotalBiaya()
            });
        }
    }

    private void clearFields() {
        idPelangganField.setText("");
        idMobilField.setText("");
        totalBiayaField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PenjualanFrame().setVisible(true));
    }
}
