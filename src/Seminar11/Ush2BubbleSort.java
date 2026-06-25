package Seminar11;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ush2BubbleSort  extends JFrame {
        private static final int NUMRI_VLERAVE = 20;
        private static final int PANEL_LARTESIA = 400;
        private static final int PANEL_GJERSIA = 700;
        private static final int VONESA = 100;

        private int[] vargu;
        private PanelVizualizim panelVizualizim;
        private JButton butonRendit;
        private JButton butonGjenero;
        private boolean dukeRenditur;
        public Ush2BubbleSort() {
            setTitle("Vizualizimi i Bubble Sort");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(650, 520);
            setLocationRelativeTo(null);
            setResizable(false);
            vargu = new int[NUMRI_VLERAVE];
            dukeRenditur = false;
            initComponents();
            gjeneroVlerat();
        }
        private void initComponents() {
            setLayout(new BorderLayout());
            panelVizualizim = new PanelVizualizim();
            panelVizualizim.setPreferredSize(new Dimension(PANEL_GJERSIA, PANEL_LARTESIA));
            panelVizualizim.setBackground(Color.WHITE);
            butonRendit = new JButton("Rendit");
            butonRendit.addActionListener(e -> filloLlogjikenERenditjes());
            butonGjenero = new JButton("Gjenero");
            butonGjenero.addActionListener(e -> {
                if (!dukeRenditur) {
                    gjeneroVlerat();
                }
            });
            JPanel panelButonave = new JPanel();
            panelButonave.add(butonGjenero);
            panelButonave.add(butonRendit);
            add(panelVizualizim, BorderLayout.CENTER);
            add(panelButonave, BorderLayout.SOUTH);
        }
        private void gjeneroVlerat() {
            Random rnd = new Random();
            for (int i = 0; i < vargu.length; i++) {
                vargu[i] = rnd.nextInt(300) + 20;
            }
            panelVizualizim.repaint();
        }
        //bubble sort llogjika
        private void filloLlogjikenERenditjes() {
            if (dukeRenditur) return;
            dukeRenditur = true;
            butonRendit.setEnabled(false);
            butonGjenero.setEnabled(false);
            new Thread(() -> {
                for (int i = 0; i < vargu.length - 1; i++) {
                    for (int j = 0; j < vargu.length - 1 - i; j++) {
                        if (vargu[j] > vargu[j + 1]) {
                            int tmp = vargu[j];
                            vargu[j] = vargu[j + 1];
                            vargu[j + 1] = tmp;
                        }
                        SwingUtilities.invokeLater(() -> panelVizualizim.repaint());
                        try {
                            Thread.sleep(VONESA);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                SwingUtilities.invokeLater(() -> {
                    dukeRenditur = false;
                    butonRendit.setEnabled(true);
                    butonGjenero.setEnabled(true);
                });
            }).start();
        }
        private class PanelVizualizim extends JPanel {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int gjeresiShtylle = (getWidth() - 20) / vargu.length;
                int maxVlera = 0;
                for (int v : vargu) {
                    if (v > maxVlera) maxVlera = v;
                }
                for (int i = 0; i < vargu.length; i++) {
                    int lartesiShtylle = (int) ((double) vargu[i] / maxVlera * (getHeight() - 40));
                    int x = 10 + i * gjeresiShtylle;
                    int y = getHeight() - 30 - lartesiShtylle;
                    Color ngjyre = new Color(Math.min(255, 50 + i * 10), 100, Math.max(0, 200 - i * 10));
                    g2d.setColor(ngjyre);
                    g2d.fillRect(x, y, gjeresiShtylle - 4, lartesiShtylle);
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(x, y, gjeresiShtylle - 4, lartesiShtylle);
                    String label = String.valueOf(vargu[i]);
                    FontMetrics fm = g2d.getFontMetrics();
                    int labelX = x + (gjeresiShtylle - 4 - fm.stringWidth(label)) / 2;
                    int labelY = y - 5;
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(label, labelX > 0 ? labelX : x, labelY > 10 ? labelY : 15);
                }
            }
        }
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new Ush2BubbleSort().setVisible(true));
        }
    }