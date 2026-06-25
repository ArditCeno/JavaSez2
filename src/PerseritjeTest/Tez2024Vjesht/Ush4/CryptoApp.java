// 4. (20 pikë) Krijoni një aplikacion në JAVA që përmban 2 butona: Enkripto dhe Dekripto.
// Enkriptimi është procesi i shndërrimit të një mesazhi të zakonshëm në një formë të koduar
// që nuk mund të kuptohet lehtësisht nga personat e paautorizuar, ndërsa dekriptimi është
// procesi i kundërt. Përdorni algoritmin e zhvendosjes me x-vlera (shembull: në qoftë se x=3,
// A zëvendësohet me D). Në klikim të secilit buton hapet një JFileChooser dhe zgjidhet skedari
// që do të enkriptohet/dekriptohet.

package PerseritjeTest.Tez2024Vjesht.Ush4;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;

public class CryptoApp extends JFrame {
    private static final int ZHVIENDOSJA = 3;

    public CryptoApp() {
        setTitle("Enkriptimi / Dekriptimi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 150);
        setLocationRelativeTo(null);

        JButton btnEnkripto = new JButton("Enkripto");
        JButton btnDekripto = new JButton("Dekripto");

        btnEnkripto.addActionListener(e -> procesoSkedarin(true));
        btnDekripto.addActionListener(e -> procesoSkedarin(false));

        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 40));
        add(btnEnkripto);
        add(btnDekripto);
    }

    private void procesoSkedarin(boolean enkripto) {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;

        File skedari = fc.getSelectedFile();
        try {
            String permbajtja = Files.readString(skedari.toPath());
            StringBuilder rezultati = new StringBuilder();

            for (char c : permbajtja.toCharArray()) {
                if (Character.isLetter(c)) {
                    char baza = Character.isUpperCase(c) ? 'A' : 'a';
                    int indeksi = (c - baza + (enkripto ? ZHVIENDOSJA : -ZHVIENDOSJA) + 26) % 26;
                    rezultati.append((char) (baza + indeksi));
                } else {
                    rezultati.append(c);
                }
            }

            String emriRi = (enkripto ? "enkriptuar_" : "dekriptuar_") + skedari.getName();
            Path rruga = skedari.toPath().resolveSibling(emriRi);
            Files.writeString(rruga, rezultati.toString());

            JOptionPane.showMessageDialog(this,
                    "Skedari u " + (enkripto ? "enkriptua" : "dekriptua") + " me sukses:\n" + rruga);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gabim: " + ex.getMessage(), "Gabim", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CryptoApp().setVisible(true));
    }
}
