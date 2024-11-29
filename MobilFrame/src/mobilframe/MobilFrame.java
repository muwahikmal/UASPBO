import mainframe.Mobil;
import mainframe.MobilService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MobilFrame extends JFrame {
    private JTextField merkField, tahunField, kuantitasField, hargaField;
    private JTable table;
    private DefaultTableModel tableModel;
    private MobilService mobilService;

    public MobilFrame() {
        mobilService = new MobilService();

        setTitle("Mobil CRUD Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel untuk input
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // 4 baris, 2 kolom
        inputPanel.add(new JLabel("Merk:"));
        merkField = new JTextField();
        inputPanel.add(merkField);

        inputPanel.add(new JLabel("Tahun:"));
        tahunField = new JTextField();
        inputPanel.add(tahunField);

        inputPanel.add(new JLabel("Kuantitas:"));
        kuantitasField = new JTextField();
        inputPanel.add(kuantitasField);

        inputPanel.add(new JLabel("Harga:"));
        hargaField = new JTextField();
        inputPanel.add(hargaField);

        // Panel untuk tombol aksi
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton createButton = new JButton("Create");
        createButton.addActionListener(this::handleCreate);
        buttonPanel.add(createButton);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(this::handleUpdate);
        buttonPanel.add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this::handleDelete);
        buttonPanel.add(deleteButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshTable());
        buttonPanel.add(refreshButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        tableModel = new DefaultTableModel(new String[]{"ID Mobil", "Merk", "Tahun", "Kuantitas", "Harga"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        refreshTable();
    }

    private void handleCreate(ActionEvent e) {
        mobilService.createMobil(merkField.getText(), Integer.parseInt(tahunField.getText()),
                Integer.parseInt(kuantitasField.getText()), Double.parseDouble(hargaField.getText()));
        refreshTable();
        clearFields();
    }

    private void handleUpdate(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int idMobil = (int) tableModel.getValueAt(selectedRow, 0);
            mobilService.updateMobil(idMobil, merkField.getText(), Integer.parseInt(tahunField.getText()),
                    Integer.parseInt(kuantitasField.getText()), Double.parseDouble(hargaField.getText()));
            refreshTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Select a row to update.");
        }
    }

    private void handleDelete(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int idMobil = (int) tableModel.getValueAt(selectedRow, 0);
            mobilService.deleteMobil(idMobil);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Select a row to delete.");
        }
    }

    private void refreshTable() {
        List<Mobil> mobilList = mobilService.readMobil();
        tableModel.setRowCount(0);
        for (Mobil mobil : mobilList) {
            tableModel.addRow(new Object[]{mobil.getIdMobil(), mobil.getMerk(), mobil.getTahun(),
                    mobil.getKuantitas(), mobil.getHarga()});
        }
    }

    private void clearFields() {
        merkField.setText("");
        tahunField.setText("");
        kuantitasField.setText("");
        hargaField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MobilFrame().setVisible(true));
    }
}
