package PerseritjeArdit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LlogaritNP2 extends JFrame {

    private JTextField txtData;
    private JTextField txtGjinia;
    private JTextField txtRezultati;

    public LlogaritNP2() {
        setTitle("Gjeneron NP");
        setSize(420, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblData = new JLabel("Ditelindja DD.MM.YYYY");
        txtData = new JTextField();

        JLabel lblGjinia = new JLabel("Gjinia M ose F");
        txtGjinia = new JTextField();

        JLabel lblRezultati = new JLabel("ID");
        txtRezultati = new JTextField();
        txtRezultati.setEditable(false);
        txtRezultati.setFont(new Font("Arial", Font.BOLD, 14));

        JButton btnGjenero = new JButton("Gjenero ID");

        btnGjenero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gjeneroNumrinPersonal();
            }
        });

        panel.add(lblData);
        panel.add(txtData);
        panel.add(lblGjinia);
        panel.add(txtGjinia);
        panel.add(lblRezultati);
        panel.add(txtRezultati);
        panel.add(new JLabel(""));
        panel.add(btnGjenero);

        add(panel);
    }

    private String gjejShkronjenEVitit(int viti) {
        char shkronja = (char) ('I' + ((viti / 10) - 198)); // e kam bere me kod ASCI duke mare si baz 198 qe eshte I
        return String.valueOf(shkronja);
    }

    private void gjeneroNumrinPersonal() {
        try {
            String data = txtData.getText().trim();
            String gjinia = txtGjinia.getText().trim().toUpperCase();

            if (!gjinia.equals("M") && !gjinia.equals("F")) {
                JOptionPane.showMessageDialog(this, "Gjinia duhet te jete vetem 'M' ose 'F'!", "Kujdes", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String[] pjeset = data.split("\\.");
            if (pjeset.length != 3) {
                JOptionPane.showMessageDialog(this, "Perdorni formatin e sakte me pika: DD.MM.YYYY", "Kujdes", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String dita = pjeset[0];
            String muaji = pjeset[1];
            String vitiFull = pjeset[2];

            if (dita.length() != 2 || muaji.length() != 2 || vitiFull.length() != 4) {
                JOptionPane.showMessageDialog(this, "Data duhet te kete formatin: 2 shifra per diten/muajin dhe 4 per vitin!", "Kujdes", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int vitiInt = Integer.parseInt(vitiFull);
            int muajiInt = Integer.parseInt(muaji);

            String shkronjaFillimi = gjejShkronjenEVitit(vitiInt);

            String vitiID = vitiFull.substring(2);

            if (gjinia.equals("F")) {
                muajiInt += 50;
            }
            String muajiID = String.format("%02d", muajiInt);

            String fundi = "001A";


            String nrPersonal = shkronjaFillimi + vitiID + muajiID + dita + fundi;
            txtRezultati.setText(nrPersonal);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ju lutem fusni vetem numra tek data (psh. 12.03.2006)!", "Gabim Formatimi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ndodhi nje gabim: " + ex.getMessage(), "Gabim", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LlogaritNP2().setVisible(true);
        });
    }
}