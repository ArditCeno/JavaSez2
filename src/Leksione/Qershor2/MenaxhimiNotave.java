package Leksione.Qershor2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class MenaxhimiNotave extends JFrame {

    private JTextField txtEmri, txtNota;
    private JTextArea txtZoneShfaqje;
    private JLabel lblMesatarja;
    private final String EMRI_SKEDARIT = "nota_studenteve.txt";

    public MenaxhimiNotave() {
        setTitle("Notat");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelInput = new JPanel(new GridLayout(2, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelInput.add(new JLabel("Emri "));
        txtEmri = new JTextField();
        panelInput.add(txtEmri);

        panelInput.add(new JLabel("Nota:"));
        txtNota = new JTextField();
        panelInput.add(txtNota);

        add(panelInput, BorderLayout.NORTH);

        txtZoneShfaqje = new JTextArea();
        txtZoneShfaqje.setEditable(false);
        txtZoneShfaqje.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtZoneShfaqje);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista e Studenteve dhe Notave"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelJug = new JPanel(new BorderLayout(5, 5));
        panelJug.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelButona = new JPanel(new GridLayout(1, 2, 10, 0));
        JButton btnRuaj = new JButton("Ruaj Studentin");
        JButton btnLexo = new JButton("Shfaq & Llogarit Mesataren");
        panelButona.add(btnRuaj);
        panelButona.add(btnLexo);

        lblMesatarja = new JLabel("Mesatarja e pergjithshme: 0.00", SwingConstants.CENTER);
        lblMesatarja.setFont(new Font("Arial", Font.BOLD, 14));
        lblMesatarja.setForeground(Color.BLUE);

        panelJug.add(panelButona, BorderLayout.NORTH);
        panelJug.add(lblMesatarja, BorderLayout.SOUTH);

        add(panelJug, BorderLayout.SOUTH);

        btnRuaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ruajTeDhenat();
            }
        });

        btnLexo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lexoDheLlogarit();
            }
        });
    }

    private void ruajTeDhenat() {
        String emri = txtEmri.getText().trim();
        String notaStr = txtNota.getText().trim();

        if (emri.isEmpty() || notaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ju lutem plotesoni te gjitha fushat!", "Gabim", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double nota = Double.parseDouble(notaStr);
            if (nota < 1 || nota > 10) {
                JOptionPane.showMessageDialog(this, "Nota duhet te jete midis 1 dhe 10!", "Gabim", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMRI_SKEDARIT, true))) {
                writer.write(emri + "," + nota);
                writer.newLine();
                JOptionPane.showMessageDialog(this, "Te dhenat u ruajten me sukses!");

                txtEmri.setText("");
                txtNota.setText("");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nota duhet te jete nje numer i vlefshem!", "Gabim", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gabim gjate shkrimit ne skedar!", "Gabim", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void lexoDheLlogarit() {
        File skedari = new File(EMRI_SKEDARIT);

        if (!skedari.exists()) {
            JOptionPane.showMessageDialog(this, "Skedari nu ekziston akoma. Shto nje student te pare!", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        txtZoneShfaqje.setText("");
        txtZoneShfaqje.append(String.format("%-25s %-10s\n", "EMRI", "NOTA"));
        txtZoneShfaqje.append("---------------------------------------\n");

        double shuma = 0;
        int numriStudenteve = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(skedari))) {
            String rreshti;

            while ((rreshti = reader.readLine()) != null) {
                String[] pjeset = rreshti.split(",");
                if (pjeset.length == 2) {
                    String emri = pjeset[0];
                    double nota = Double.parseDouble(pjeset[1]);

                    txtZoneShfaqje.append(String.format("%-25s %-10.2f\n", emri, nota));

                    shuma += nota;
                    numriStudenteve++;
                }
            }

            if (numriStudenteve > 0) {
                double mesatare = shuma / numriStudenteve;
                lblMesatarja.setText(String.format("Mesatarja e pergjithshme: %.2f (Nga %d studente)", mesatare, numriStudenteve));
            } else {
                lblMesatarja.setText("Nuk ka studente te regjistruar.");
            }

        } catch (IOException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Gabim gjate leximit te skedarit!", "Gabim", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenaxhimiNotave().setVisible(true);
            }
        });
    }
}