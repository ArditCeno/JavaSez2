package Seminar6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ush3ParkimGui extends JFrame {
    private JTextField txtTarga;
    private JComboBox<Integer> cmbKati;
    private JComboBox<Integer> cmbVendi;
    private JPanel[] panelat;
    private String[][] parkingu;
    private int vendetPerKat;
    private int kate = 3;
    private JLabel[][] labelat;
    private JLabel lblStatusi;

    public Ush3ParkimGui(int n) {
        this.vendetPerKat = n / kate;
        this.parkingu = new String[kate][vendetPerKat];
        this.labelat = new JLabel[kate][vendetPerKat];

        for (int k = 0; k < kate; k++) {
            for (int i = 0; i < vendetPerKat; i++) {
                parkingu[k][i] = "";
            }
        }

        setTitle("Sistemi i Menaxhimit te Parkingut");
        setSize(600, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panelTop = new JPanel(new FlowLayout());
        panelTop.add(new JLabel("Targa:"));
        txtTarga = new JTextField(12);
        panelTop.add(txtTarga);

        panelTop.add(new JLabel("Kati:"));
        cmbKati = new JComboBox<>();
        for (int k = 1; k <= kate; k++) cmbKati.addItem(k);
        panelTop.add(cmbKati);

        panelTop.add(new JLabel("Vendi:"));
        cmbVendi = new JComboBox<>();
        for (int i = 1; i <= vendetPerKat; i++) cmbVendi.addItem(i);
        panelTop.add(cmbVendi);

        JButton btnHyrje = new JButton("Hyrje");
        JButton btnDalje = new JButton("Dalje");
        JButton btnPastro = new JButton("Pastro");

        panelTop.add(btnHyrje);
        panelTop.add(btnDalje);
        panelTop.add(btnPastro);

        add(panelTop, BorderLayout.NORTH);

        JTabbedPane tabedPane = new JTabbedPane();
        panelat = new JPanel[kate];

        for (int k = 0; k < kate; k++) {
            panelat[k] = new JPanel(new GridLayout(2, vendetPerKat / 2 + vendetPerKat % 2, 8, 8));
            panelat[k].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panelat[k].setBackground(Color.DARK_GRAY);

            for (int i = 0; i < vendetPerKat; i++) {
                JPanel slot = krijoSlot(k, i);
                panelat[k].add(slot);
            }

            tabedPane.addTab("Kati " + (k + 1), panelat[k]);
        }

        add(tabedPane, BorderLayout.CENTER);

        JPanel panelBottom = new JPanel(new FlowLayout());
        lblStatusi = new JLabel("Vendet e lira: " + llogaretVLira() + " / " + (kate * vendetPerKat));
        panelBottom.add(lblStatusi);
        add(panelBottom, BorderLayout.SOUTH);

        btnHyrje.addActionListener(e -> shtoMakine());
        btnDalje.addActionListener(e -> hiqMakine());
        btnPastro.addActionListener(e -> txtTarga.setText(""));

        tabedPane.addChangeListener(e -> {
            int selectedKat = tabedPane.getSelectedIndex();
            cmbKati.setSelectedIndex(selectedKat);
        });

        setVisible(true);
    }

    private JPanel krijoSlot(int kat, int index) {
        JPanel slot = new JPanel(new BorderLayout());
        slot.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        slot.setPreferredSize(new Dimension(150, 80));

        JLabel nrVendi = new JLabel("V-" + (index + 1), SwingConstants.CENTER);
        nrVendi.setForeground(Color.WHITE);
        nrVendi.setFont(new Font("Arial", Font.BOLD, 10));

        JLabel targaLabel = new JLabel("", SwingConstants.CENTER);
        targaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        targaLabel.setForeground(Color.WHITE);

        labelat[kat][index] = targaLabel;

        slot.add(nrVendi, BorderLayout.NORTH);
        slot.add(targaLabel, BorderLayout.CENTER);

        updateSlot(slot, kat, index);

        return slot;
    }

    private void updateSlot(JPanel slot, int kat, int index) {
        if (parkingu[kat][index].isEmpty()) {
            slot.setBackground(new Color(34, 139, 34));
            labelat[kat][index].setText("BOSH");
            labelat[kat][index].setForeground(Color.WHITE);
        } else {
            slot.setBackground(new Color(178, 34, 34));
            labelat[kat][index].setText(parkingu[kat][index]);
            labelat[kat][index].setForeground(Color.WHITE);
        }
    }

    private void shtoMakine() {
        String targa = txtTarga.getText().trim();
        if (targa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ju lutem vendosni nje targ");
            return;
        }

        int kati = (int) cmbKati.getSelectedItem();
        int vendi = (int) cmbVendi.getSelectedItem();

        int row = kati - 1;
        int col = vendi - 1;

        if (!parkingu[row][col].isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ky vend eshte i zene!");
            return;
        }

        parkingu[row][col] = targa;
        updatePanelParking();
        txtTarga.setText("");
        JOptionPane.showMessageDialog(this, "Makina u parkua: Kati " + kati + ", Vendi " + vendi);
    }

    private void hiqMakine() {
        String targa = txtTarga.getText().trim();
        if (targa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ju lutem vendosni nje targ");
            return;
        }

        for (int k = 0; k < kate; k++) {
            for (int i = 0; i < vendetPerKat; i++) {
                if (parkingu[k][i].equals(targa)) {
                    parkingu[k][i] = "";
                    updatePanelParking();
                    txtTarga.setText("");
                    JOptionPane.showMessageDialog(this, "Makina u largua nga kati " + (k + 1) + ", vendi " + (i + 1));
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Kjo makine nuk ndodhet ne parking");
    }

    private void updatePanelParking() {
        for (int k = 0; k < kate; k++) {
            int index = 0;
            for (Component c : panelat[k].getComponents()) {
                if (c instanceof JPanel) {
                    updateSlot((JPanel) c, k, index);
                    index++;
                }
            }
        }
        lblStatusi.setText("Vendet e lira: " + llogaretVLira() + " / " + (kate * vendetPerKat));
    }

    private int llogaretVLira() {
        int count = 0;
        for (int k = 0; k < kate; k++) {
            for (int i = 0; i < vendetPerKat; i++) {
                if (parkingu[k][i].isEmpty()) count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Vendosni numrin e vendeve (shumezues i 3):");
        try {
            int n = Integer.parseInt(input);
            if (n > 0 && n % 3 == 0) {
                new Ush3ParkimGui(n).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Numri duhet te jete shumezues i 3.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vler e pavlefshme. Programi do te mbyllet.");
        }
    }
}
