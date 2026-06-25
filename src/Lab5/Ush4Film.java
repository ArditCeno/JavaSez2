package Lab5;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Film {
    private String titulli, regjizori, zhanri;
    private int viti;
    private double vleresimi;

    public Film(String titulli, String regjizori, int viti, String zhanri, double vleresimi) {
        this.titulli = titulli;
        this.regjizori = regjizori;
        this.viti = viti;
        this.zhanri = zhanri;
        this.vleresimi = vleresimi;
    }

    public String getTitulli() { return titulli; }
    public double getVleresimi() { return vleresimi; }

    @Override
    public String toString() {
        return String.format("%-15s | %-15s | %d | %-10s | %.1f/10\n",
                titulli, regjizori, viti, zhanri, vleresimi);
    }
}

public class Ush4Film extends JFrame {
    private ArrayList<Film> listaFilmave = new ArrayList<>();
    private JTextArea areaEkrani;
    private JTextField txtT, txtR, txtV, txtZ, txtVl;

    public Ush4Film() {

        setTitle("USh4");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        JPanel pnlInput = new JPanel(new GridLayout(5, 2, 5, 5));
        pnlInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        pnlInput.add(new JLabel("Titulli i Filmit:"));   txtT = new JTextField(); pnlInput.add(txtT);
        pnlInput.add(new JLabel("Regjizori:"));         txtR = new JTextField(); pnlInput.add(txtR);
        pnlInput.add(new JLabel("Viti i Prodhimit:"));  txtV = new JTextField(); pnlInput.add(txtV);
        pnlInput.add(new JLabel("Zhanri:"));            txtZ = new JTextField(); pnlInput.add(txtZ);
        pnlInput.add(new JLabel("Vleresimi:"));  txtVl = new JTextField(); pnlInput.add(txtVl);

        areaEkrani = new JTextArea();
        areaEkrani.setEditable(false);
        areaEkrani.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaEkrani.setBackground(new Color(245, 245, 245));
        JScrollPane scroll = new JScrollPane(areaEkrani);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnShto = new JButton("Shto Film");
        JButton btnShfaq = new JButton("Shfaq Listën");
        JButton btnKerko = new JButton("Kerko Titull");
        JButton btnHiq = new JButton("Fshi Film");
        JButton btnTop = new JButton("Vleresimi Max");

        pnlButtons.add(btnShto);
        pnlButtons.add(btnShfaq);
        pnlButtons.add(btnKerko);
        pnlButtons.add(btnHiq);
        pnlButtons.add(btnTop);

        add(pnlInput, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);

        listaFilmave.add(new Film("Inception", "C. Nolan", 2010, "Sci-Fi", 8.8));
        listaFilmave.add(new Film("The Godfather", "F. Coppola", 1972, "Crime", 9.2));

        btnShto.addActionListener(e -> {
            try {
                String t = txtT.getText();
                String r = txtR.getText();
                int v = Integer.parseInt(txtV.getText());
                String z = txtZ.getText();
                double vl = Double.parseDouble(txtVl.getText());

                listaFilmave.add(new Film(t, r, v, z, vl));
                JOptionPane.showMessageDialog(this, "Filmi u shtua me sukses!");
                pastroFushat();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Gabim! Viti dhe Vleresimi duhet të jene numra.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnShfaq.addActionListener(e -> {
            areaEkrani.setText("TITULLI         | REGJIZORI       | VITI | ZHANRI     | RATING\n");
            areaEkrani.append("------------------------------------------------------------\n");
            if (listaFilmave.isEmpty()) areaEkrani.append("Lista është bosh.");
            for (Film f : listaFilmave) areaEkrani.append(f.toString());
        });

        btnKerko.addActionListener(e -> {
            String titulli = JOptionPane.showInputDialog(this, "Shkruani titullin qe kerkoni:");
            if (titulli != null && !titulli.isEmpty()) {
                areaEkrani.setText("--- Rezultati i Kerkimit ---\n");
                boolean gjetur = false;
                for (Film f : listaFilmave) {
                    if (f.getTitulli().equalsIgnoreCase(titulli)) {
                        areaEkrani.append(f.toString());
                        gjetur = true;
                    }
                }
                if (!gjetur) areaEkrani.append("Nuk u gjet asnje film me kete titull.");
            }
        });

        btnHiq.addActionListener(e -> {
            String titulli = JOptionPane.showInputDialog(this, "Titulli qe deshironi te hiqni:");
            if (listaFilmave.removeIf(f -> f.getTitulli().equalsIgnoreCase(titulli))) {
                JOptionPane.showMessageDialog(this, "Filmi u hoq nga lista.");
            } else {
                JOptionPane.showMessageDialog(this, "Filmi nuk u gjet.");
            }
        });

        btnTop.addActionListener(e -> {
            if (listaFilmave.isEmpty()) {
                areaEkrani.setText("Lista eshte bosh!");
                return;
            }
            Film max = listaFilmave.get(0);
            for (Film f : listaFilmave) {
                if (f.getVleresimi() > max.getVleresimi()) max = f;
            }
            areaEkrani.setText("--- Filmi me vlerësimin me te larte ---\n" + max.toString());
        });
    }

    private void pastroFushat() {
        txtT.setText(""); txtR.setText(""); txtV.setText(""); txtZ.setText(""); txtVl.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Ush4Film().setVisible(true);
        });
    }
}