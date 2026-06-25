/*Ndertoni aplikacion grafik qe rregjistron produktet nga te dhenat e perodruesit.
Pasi perdoruesi vendos te dhenat e produktit nepermjet fushave perkatese te tekstit dhe klikoni ne butonin Regjistro,
programi duhet te shtoje rreshtin me te dhenat e produktit te rregjistruar se bashku me shumen e llogaritur ne nje siperfaqe
teksti si dhe ne nje skedar produktet.txt ne formatin; produkt cmimi sasia.
Programi duhet te shfaqe shumen totale te produkteve te rregjistruara  si dhe shume per cdo produkt.
Nje pamje emundeshme e aplikacionit qe duhet te krijoni paraqitet ne figure
 */
package PerseritjeTest.Tez2025B.Ush4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MenaxhimProduktesh extends JFrame {
    private JTextField txtPershkrimi, txtCmim, txtSasia;
    private JTextArea txtAreaArtikujt;
    private JLabel lblShumaTotale;
    private double shumaTotaleGlobal = 0.0;

    public MenaxhimProduktesh(){
        setTitle("Artikujt");
        setSize(650, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));


        JPanel panelMajte = new JPanel();
        panelMajte.setBorder(BorderFactory.createTitledBorder("Artikujt"));
        panelMajte.setLayout(new GridLayout(4, 2, 5, 10)); // 4 rreshta, 2 kolona

        panelMajte.add(new JLabel("Pershkrimi:"));
        txtPershkrimi = new JTextField();
        panelMajte.add(txtPershkrimi);

        panelMajte.add(new JLabel("Cmimi:"));
        txtCmim = new JTextField();
        panelMajte.add(txtCmim);

        panelMajte.add(new JLabel("Sasia:"));
        txtSasia = new JTextField();
        panelMajte.add(txtSasia);

        JButton btnRegjistro = new JButton("Regjistro");
        panelMajte.add(btnRegjistro);


        JPanel panelDjathte = new JPanel(new BorderLayout());
        panelDjathte.setBorder(BorderFactory.createTitledBorder("Artikujt:"));

        txtAreaArtikujt = new JTextArea();
        txtAreaArtikujt.setEditable(false);

        txtAreaArtikujt.setFont(new Font("Monospaced", Font.PLAIN, 12));


        txtAreaArtikujt.append(String.format("%-15s %-10s %-10s %-10s\n", "Produkti", "Cmimi", "Sasia", "Shuma"));

        JScrollPane scrollPane = new JScrollPane(txtAreaArtikujt);
        panelDjathte.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblShumaTotale = new JLabel("Shuma totale: 0.0");
        panelBottom.add(lblShumaTotale);

        add(panelMajte, BorderLayout.WEST);
        add(panelDjathte, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);

        btnRegjistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regjistroProdukt();
            }
        });
    }

    private void regjistroProdukt() {
        String produkti = txtPershkrimi.getText().trim();
        String cmimiStr = txtCmim.getText().trim();
        String sasiaStr = txtSasia.getText().trim();

        // Validimi nëse ka fusha bosh
        if (produkti.isEmpty() || cmimiStr.isEmpty() || sasiaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ju lutem plotësoni të gjitha fushat!", "Gabim", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double cmimi = Double.parseDouble(cmimiStr);
            int sasia = Integer.parseInt(sasiaStr);

            double shumaProduktit = cmimi * sasia;
            shumaTotaleGlobal += shumaProduktit;

            txtAreaArtikujt.append(String.format("%-15s %-10.1f %-10d %-10.1f\n", produkti, cmimi, sasia, shumaProduktit));

            lblShumaTotale.setText("Shuma totale: " + shumaTotaleGlobal);

            shkruajNeSkedar(produkti, cmimi, sasia);

            txtPershkrimi.setText("");
            txtCmim.setText("");
            txtSasia.setText("");
            txtPershkrimi.requestFocus();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Çmimi dhe sasia duhet të jenë numra validë!", "Gabim Formati", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void shkruajNeSkedar(String produkt, double cmimi, int sasia) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("produktet.txt", true))) {
            writer.write(produkt + " " + cmimi + " " + sasia);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gabim gjatë shkrimit në skedar!", "Gabim", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenaxhimProduktesh().setVisible(true);
            }
        });
    }
}