package Seminar8.Ush3GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PronaGUI extends JFrame {
    private Prona prona;
    private JTextField emriKlientitField;
    private JTextField ofertaField;
    private JTextField emriPronesField;
    private JButton krijoPronenBtn;
    private JButton shtoKlientinBtn;
    private JButton shfaqOfertenBtn;
    private JTable tabelaKlienteve;
    private DefaultTableModel modelTabela;
    private JLabel statusLabel;

    public PronaGUI() {
        setTitle("Menaxhimi i Prones dhe Klienteve");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 500);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        prona = null;

        emriPronesField = new JTextField(20);
        emriKlientitField = new JTextField(20);
        ofertaField = new JTextField(20);

        krijoPronenBtn = new JButton("Krijo Pronen");
        shtoKlientinBtn = new JButton("Shto Klient");
        shtoKlientinBtn.setEnabled(false);
        shfaqOfertenBtn = new JButton("Oferta Maksimale");
        shfaqOfertenBtn.setEnabled(false);

        modelTabela = new DefaultTableModel(new Object[]{"#", "Emri i Klientit", "Oferta (€)"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaKlienteve = new JTable(modelTabela);
        tabelaKlienteve.setRowHeight(25);
        tabelaKlienteve.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabelaKlienteve.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabelaKlienteve.getColumnModel().getColumn(2).setPreferredWidth(100);

        statusLabel = new JLabel("Se pari krijoni pronen.");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        krijoPronenBtn.addActionListener(e -> krijoPronenAction());
        shtoKlientinBtn.addActionListener(e -> shtoKlientinAction());
        shfaqOfertenBtn.addActionListener(e -> ofertaMaksAction());
    }

    private void layoutComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel krijoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        krijoPanel.setBorder(BorderFactory.createTitledBorder("Hapi 1: Krijo Pronen"));
        krijoPanel.add(new JLabel("Emri i Prones:"));
        krijoPanel.add(emriPronesField);
        krijoPanel.add(krijoPronenBtn);

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Hapi 2: Shto Klient"));

        JPanel fieldsPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        fieldsPanel.add(new JLabel("Emri i Klientit:"));
        fieldsPanel.add(emriKlientitField);
        fieldsPanel.add(new JLabel("Oferta (€):"));
        fieldsPanel.add(ofertaField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.add(shtoKlientinBtn);
        buttonPanel.add(shfaqOfertenBtn);

        inputPanel.add(fieldsPanel, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(tabelaKlienteve);
        scrollPane.setPreferredSize(new Dimension(500, 200));

        mainPanel.add(krijoPanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        mainPanel.add(statusLabel, BorderLayout.PAGE_END);

        add(mainPanel);
    }

    private void krijoPronenAction() {
        String emriPrones = emriPronesField.getText().trim();
        if (emriPrones.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Shkruani emrin e prones!", "Gabim", JOptionPane.ERROR_MESSAGE);
            return;
        }
        prona = new Prona(emriPrones);
        statusLabel.setText("Prona \"" + emriPrones + "\" u krijua. Tani shtoni kliente.");
        krijoPronenBtn.setEnabled(false);
        emriPronesField.setEnabled(false);
        shtoKlientinBtn.setEnabled(true);
        shfaqOfertenBtn.setEnabled(true);
    }

    private void shtoKlientinAction() {
        String emri = emriKlientitField.getText().trim();
        String ofertaStr = ofertaField.getText().trim();

        if (emri.isEmpty() || ofertaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Plotesoni te gjitha fushat!", "Gabim", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double oferta;
        try {
            oferta = Double.parseDouble(ofertaStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Oferta duhet te jete numer!", "Gabim", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Klient klient = new Klient(emri, oferta);
        prona.shtoKlient(klient);

        int rreshti = modelTabela.getRowCount() + 1;
        modelTabela.addRow(new Object[]{rreshti, klient.getEmri(), String.format("%.2f", klient.getOferta())});

        emriKlientitField.setText("");
        ofertaField.setText("");
        emriKlientitField.requestFocus();
    }

    private void ofertaMaksAction() {
        if (prona == null || prona.numeroKliente() == 0) {
            JOptionPane.showMessageDialog(this, "Nuk ka kliente ende!", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Klient maxKlient = prona.ofertaMaks();
        JOptionPane.showMessageDialog(
                this,
                "Oferta me e larte:\n" + maxKlient,
                "Rezultati",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PronaGUI().setVisible(true));
    }
}
