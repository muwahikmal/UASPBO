import mainframe.Pelanggan;
import mainframe.PelangganService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PelangganFrame extends JFrame {
    private JTextField namaField, nikField, notelpField, alamatField;
    private JTable table;
    private DefaultTableModel tableModel;
    private PelangganService pelangganService;

    public PelangganFrame() {
        pelangganService = new PelangganService();

        setTitle("Pelanggan Management");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Nama:"));
        namaField = new JTextField();
        inputPanel.add(namaField);

        inputPanel.add(new JLabel("NIK:"));
        nikField = new JTextField();
        inputPanel.add(nikField);

        inputPanel.add(new JLabel("No. Telp:"));
        notelpField = new JTextField();
        inputPanel.add(notelpField);

        inputPanel.add(new JLabel("Alamat:"));
        alamatField = new JTextField();
        inputPanel.add(alamatField);

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

        tableModel = new DefaultTableModel(new String[]{"ID Pelanggan", "Nama", "NIK", "No. Telp", "Alamat"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        refreshTable();
    }

    private void handleCreate(ActionEvent e) {
        pelangganService.createPelanggan(namaField.getText(), nikField.getText(), notelpField.getText(), alamatField.getText());
        refreshTable();
        clearFields();
    }

    private void handleUpdate(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int idpelanggan = (int) tableModel.getValueAt(selectedRow, 0);
            pelangganService.updatePelanggan(idpelanggan, namaField.getText(), nikField.getText(), notelpField.getText(), alamatField.getText());
            refreshTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang akan diupdate.");
        }
    }

    private void handleDelete(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int idpelanggan = (int) tableModel.getValueAt(selectedRow, 0);
            pelangganService.deletePelanggan(idpelanggan);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang akan dihapus.");
        }
    }

    private void refreshTable() {
        List<Pelanggan> pelangganList = pelangganService.readPelanggan();
        tableModel.setRowCount(0);
        for (Pelanggan pelanggan : pelangganList) {
            tableModel.addRow(new Object[]{
                    pelanggan.getIdPelanggan(),
                    pelanggan.getNama(),
                    pelanggan.getNik(),
                    pelanggan.getNoTelp(),
                    pelanggan.getAlamat()
            });
        }
    }

    private void clearFields() {
        namaField.setText("");
        nikField.setText("");
        notelpField.setText("");
        alamatField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PelangganFrame().setVisible(true));
    }
}
