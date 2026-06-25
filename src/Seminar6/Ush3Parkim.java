package Seminar6;

import javax.swing.*;
import java.awt.*;

public class Ush3Parkim extends JFrame {
    private JTextField txtTarga;
    private JTextArea areaVizualizimi;
    private String[] parkingu;
    private int n;

    public Ush3Parkim(int n) {
        this.n = n;
        this.parkingu = new String[n];

        for (int i = 0; i < n; i++) {
            parkingu[i] = "-------";
        }

        setTitle("Sistemi i Menaxhimit te Parkingut");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Targa e makines"));
        txtTarga = new JTextField(15);
        add(txtTarga);

        JButton btnHyrje = new JButton("Hyrje");
        JButton btnDalje = new JButton("Dalje");
        JButton btnShfaq = new JButton("Shfaq Parkingun");
        JButton btnPastro = new JButton("Pastro");

        add(btnHyrje);
        add(btnDalje);
        add(btnShfaq);
        add(btnPastro);

        areaVizualizimi = new JTextArea(15, 30);
        areaVizualizimi.setEditable(false);
        add(new JScrollPane(areaVizualizimi));

        btnHyrje.addActionListener(e -> shtoMakine());
        btnDalje.addActionListener(e -> hiqMakine());
        btnShfaq.addActionListener(e -> rifreskoDisplay());
        btnPastro.addActionListener(e -> txtTarga.setText(""));

        rifreskoDisplay();
    }

    private void shtoMakine() {
        String targa = txtTarga.getText().trim();
        if (targa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ju lutem vendosni nje targ");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (parkingu[i].equals("-------")) {
                parkingu[i] = targa;
                rifreskoDisplay();
                txtTarga.setText("");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Nuk ka vende te lira");
    }

    private void hiqMakine() {
        String targa = txtTarga.getText().trim();
        boolean uGjet = false;

        for (int i = 0; i < n; i++) {
            if (parkingu[i].equals(targa)) {
                parkingu[i] = "-------";
                uGjet = true;
                break;
            }
        }

        if (uGjet) {
            rifreskoDisplay();
            txtTarga.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Kjo makine nuk ndodhet ne parking");
        }
    }

    private void rifreskoDisplay() {
        areaVizualizimi.setText("Gjendja e Parkingut:\n");
        for (int i = 0; i < n; i++) {
            areaVizualizimi.append("Vendi " + (i + 1) + ": " + parkingu[i] + "\n");
        }
    }

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Vendosni numrin e vendeve");
        try {
            int n = Integer.parseInt(input);
            new Ush3Parkim(n).setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vler e pavlefshme. Programi do te mbyllet.");
        }
    }
}