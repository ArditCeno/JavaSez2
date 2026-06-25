package Seminar13;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Ush2Frekuenca extends JFrame {
    private JTextArea txtShkrimi, txtFrekuencaNumra, txtFrekuencaYje;
    private JButton btnRuaj, btnLexo, btnAnalizo;

    public Ush2Frekuenca() {
        setTitle("Frekuenca e fjaleve");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        txtShkrimi = new JTextArea(10, 40);
        txtFrekuencaNumra = new JTextArea(15, 25);
        txtFrekuencaNumra.setEditable(false);
        txtFrekuencaYje = new JTextArea(15, 25);
        txtFrekuencaYje.setEditable(false);

        btnRuaj = new JButton("Ruaj ne Skedar");
        btnLexo = new JButton("Lexo nga Skedari");
        btnAnalizo = new JButton("Analizo Frekuencen");

        btnRuaj.addActionListener(e -> ruajNeSkedar());
        btnLexo.addActionListener(e -> lexoSkedarin());
        btnAnalizo.addActionListener(e -> analizoFrekuencen());

        JPanel panelButona = new JPanel();
        panelButona.add(btnRuaj);
        panelButona.add(btnLexo);
        panelButona.add(btnAnalizo);

        JPanel panelTeksti = new JPanel(new BorderLayout());
        panelTeksti.setBorder(BorderFactory.createTitledBorder("Shkruaj tekstin"));
        panelTeksti.add(new JScrollPane(txtShkrimi), BorderLayout.CENTER);

        JPanel panelFrekuenca = new JPanel(new GridLayout(1, 2));
        txtFrekuencaNumra.setBorder(BorderFactory.createTitledBorder("Frekuenca (numer)"));
        txtFrekuencaYje.setBorder(BorderFactory.createTitledBorder("Frekuenca (yje)"));
        panelFrekuenca.add(new JScrollPane(txtFrekuencaNumra));
        panelFrekuenca.add(new JScrollPane(txtFrekuencaYje));

        setLayout(new BorderLayout());
        add(panelButona, BorderLayout.NORTH);
        add(panelTeksti, BorderLayout.CENTER);
        add(panelFrekuenca, BorderLayout.SOUTH);
    }

    private void ruajNeSkedar() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("frekuence.txt"))) {
            bw.write(txtShkrimi.getText());
            JOptionPane.showMessageDialog(this, "Teksti u ruajt ne skedarin frekuence.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gabim gjate ruajtjes: " + e.getMessage());
        }
    }

    private void lexoSkedarin() {
        try (BufferedReader br = new BufferedReader(new FileReader("frekuence.txt"))) {
            txtShkrimi.setText("");
            String rresht;
            while ((rresht = br.readLine()) != null) {
                txtShkrimi.append(rresht + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gabim gjate leximit: " + e.getMessage());
        }
    }

    private void analizoFrekuencen() {
        String tekst = txtShkrimi.getText();
        if (tekst.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nuk ka tekst per te analizuar.");
            return;
        }

        String[] fjalet = tekst.toLowerCase().split("[^a-zA-Z\\u00C0-\\u024F]+");
        Map<String, Integer> frekuenca = new LinkedHashMap<>();

        for (String fjale : fjalet) {
            if (!fjale.isEmpty()) {
                frekuenca.put(fjale, frekuenca.getOrDefault(fjale, 0) + 1);
            }
        }

        txtFrekuencaNumra.setText("");
        txtFrekuencaYje.setText("");

        int nr = 1;
        for (Map.Entry<String, Integer> entry : frekuenca.entrySet()) {
            txtFrekuencaNumra.append(nr + ": " + entry.getKey() + " -> " + entry.getValue() + " here\n");

            StringBuilder yje = new StringBuilder();
            for (int i = 0; i < entry.getValue(); i++) {
                yje.append("*");
            }
            txtFrekuencaYje.append(nr + ": " + yje.toString() + "\n");

            nr++;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush2Frekuenca().setVisible(true));
    }
}
