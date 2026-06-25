package PerseritjeArdit;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LlogaritjeNpRadioButton extends JFrame {

    private static final Color DARK_BG = new Color(30, 30, 35);
    private static final Color DARK_PANEL = new Color(45, 45, 50);
    private static final Color BUTTON_NORMAL = new Color(60, 60, 70);
    private static final Color BUTTON_HOVER = new Color(80, 80, 90);
    private static final Color BUTTON_CLICK = new Color(50, 50, 60);
    private static final Color ACCENT_COLOR = new Color(0, 150, 255);
    private static final Color SUCCESS_COLOR = new Color(0, 200, 100);
    private static final Color ERROR_COLOR = new Color(255, 70, 70);
    private static final Color TEXT_COLOR = new Color(240, 240, 240);
    private static final Color PLACEHOLDER_COLOR = new Color(150, 150, 150);

    private JTextField txtData;
    private JRadioButton rbMashkull;
    private JRadioButton rbFemer;
    private JTextField txtRezultati;

    public LlogaritjeNpRadioButton() {
        setTitle("Gjeneron NP");
        setSize(420, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(DARK_BG);

        Font fontiKryesor = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontiTitujve = new Font("Segoe UI", Font.BOLD, 14);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(DARK_BG);

        JLabel lblData = new JLabel("Ditelindja DD.MM.YYYY:");
        lblData.setFont(fontiTitujve);
        lblData.setForeground(TEXT_COLOR);

        txtData = formatiFushesTekstit(fontiKryesor);

        JLabel lblGjinia = new JLabel("Gjinia:");
        lblGjinia.setFont(fontiTitujve);
        lblGjinia.setForeground(TEXT_COLOR);

        rbMashkull = new JRadioButton("Mashkull");
        rbFemer = new JRadioButton("Femer");

        rbMashkull.setFont(fontiKryesor);
        rbMashkull.setForeground(TEXT_COLOR);
        rbMashkull.setBackground(DARK_BG);
        rbMashkull.setFocusPainted(false);
        rbMashkull.setSelected(true);

        rbFemer.setFont(fontiKryesor);
        rbFemer.setForeground(TEXT_COLOR);
        rbFemer.setBackground(DARK_BG);
        rbFemer.setFocusPainted(false);

        ButtonGroup grupGjinia = new ButtonGroup();
        grupGjinia.add(rbMashkull);
        grupGjinia.add(rbFemer);

        JPanel panelGjinia = new JPanel(new GridLayout(1, 2));
        panelGjinia.setBackground(DARK_BG);
        panelGjinia.add(rbMashkull);
        panelGjinia.add(rbFemer);

        JLabel lblRezultati = new JLabel("ID:");
        lblRezultati.setFont(fontiTitujve);
        lblRezultati.setForeground(TEXT_COLOR);

        txtRezultati = formatiFushesTekstit(new Font("Segoe UI", Font.BOLD, 16));
        txtRezultati.setEditable(false);
        txtRezultati.setForeground(SUCCESS_COLOR);
        txtRezultati.setHorizontalAlignment(JTextField.CENTER);

        JButton btnGjenero = new JButton("Gjenero ID");
        btnGjenero.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGjenero.setBackground(BUTTON_NORMAL);
        btnGjenero.setForeground(TEXT_COLOR);
        btnGjenero.setFocusPainted(false);
        btnGjenero.setBorder(new LineBorder(ACCENT_COLOR, 1));
        btnGjenero.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnGjenero.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnGjenero.setBackground(BUTTON_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnGjenero.setBackground(BUTTON_NORMAL);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                btnGjenero.setBackground(BUTTON_CLICK);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                btnGjenero.setBackground(BUTTON_HOVER);
            }
        });

        btnGjenero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gjeneroNumrinPersonal();
            }
        });

        panel.add(lblData);
        panel.add(txtData);
        panel.add(lblGjinia);
        panel.add(panelGjinia);
        panel.add(lblRezultati);
        panel.add(txtRezultati);
        panel.add(new JLabel(""));
        panel.add(btnGjenero);

        add(panel);
    }

    private JTextField formatiFushesTekstit(Font font) {
        JTextField txt = new JTextField();
        txt.setFont(font);
        txt.setBackground(DARK_PANEL);
        txt.setForeground(TEXT_COLOR);
        txt.setCaretColor(TEXT_COLOR);
        txt.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(PLACEHOLDER_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return txt;
    }

    private String gjejShkronjenEVitit(int viti) {
        char shkronja = (char) ('I' + ((viti / 10) - 198));
        return String.valueOf(shkronja);
    }

    private void gjeneroNumrinPersonal() {
        try {
            String data = txtData.getText().trim();
            txtRezultati.setForeground(SUCCESS_COLOR);

            String gjinia = "";
            if (rbMashkull.isSelected()) {
                gjinia = "M";
            } else if (rbFemer.isSelected()) {
                gjinia = "F";
            } else {
                tregoGabim("Ju lutem zgjidhni gjinine!");
                return;
            }

            String[] pjeset = data.split("\\.");
            if (pjeset.length != 3) {
                tregoGabim("Perdorni formatin e sakte me pika: DD.MM.YYYY");
                return;
            }

            String dita = pjeset[0];
            String muaji = pjeset[1];
            String vitiFull = pjeset[2];

            if (dita.length() != 2 || muaji.length() != 2 || vitiFull.length() != 4) {
                tregoGabim("Data duhet te kete formatin: 2 shifra per diten/muajin dhe 4 per vitin!");
                return;
            }

            int vitiInt = Integer.parseInt(vitiFull);
            int muajiInt = Integer.parseInt(muaji);

            String shkronjaFillimi = gjejShkronjenEVitit(vitiInt);
            String vitiID = vitiFull.substring(3);

            if (gjinia.equals("F")) {
                muajiInt += 50;
            }
            String muajiID = String.format("%02d", muajiInt);
            String fundi = "001A";

            String nrPersonal = shkronjaFillimi + vitiID + muajiID + dita + fundi;
            txtRezultati.setText(nrPersonal);

        } catch (NumberFormatException ex) {
            tregoGabim("Ju lutem fusni vetem numra tek data (psh. 12.03.2006)!");
        } catch (Exception ex) {
            tregoGabim("Ndodhi nje gabim: " + ex.getMessage());
        }
    }

    private void tregoGabim(String mesazhi) {
        txtRezultati.setForeground(ERROR_COLOR);
        txtRezultati.setText("Gabim!");
        JOptionPane.showMessageDialog(this, mesazhi, "Kujdes", JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LlogaritjeNpRadioButton().setVisible(true);
        });
    }
}