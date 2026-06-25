package Seminar10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ush7Koch extends JFrame {
    private int niveli = 0;

    public Ush7Koch() {
        setTitle("Floku i Debores i Koch");
        setSize(800, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        DrawingPanel panel = new DrawingPanel();

        JButton btnItero = new JButton("Next");
        btnItero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                niveli++;
                btnItero.setText("Next (Niveli: " + niveli + ")");
                panel.repaint();
            }
        });

        add(panel, BorderLayout.CENTER);
        add(btnItero, BorderLayout.SOUTH);
    }

    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.PINK);

            double x1 = 200, y1 = 500;
            double x2 = 600, y2 = 500;
            double x3 = 400, y3 = 500 - Math.sqrt(3) * 200;

            vizatoKurbenKoch(g2, niveli, x1, y1, x2, y2);
            vizatoKurbenKoch(g2, niveli, x2, y2, x3, y3);
            vizatoKurbenKoch(g2, niveli, x3, y3, x1, y1);
        }

        private void vizatoKurbenKoch(Graphics2D g, int n, double x1, double y1, double x5, double y5) {
            if (n == 0) {
                g.drawLine((int) x1, (int) y1, (int) x5, (int) y5);
            } else {
                double deltaX = x5 - x1;
                double deltaY = y5 - y1;

                double x2 = x1 + deltaX / 3;
                double y2 = y1 + deltaY / 3;

                double x3 = (0.5 * (x1 + x5) + Math.sqrt(3) * (y1 - y5) / 6);
                double y3 = (0.5 * (y1 + y5) + Math.sqrt(3) * (x5 - x1) / 6);

                double x4 = x1 + 2 * deltaX / 3;
                double y4 = y1 + 2 * deltaY / 3;

                vizatoKurbenKoch(g, n - 1, x1, y1, x2, y2);
                vizatoKurbenKoch(g, n - 1, x2, y2, x3, y3);
                vizatoKurbenKoch(g, n - 1, x3, y3, x4, y4);
                vizatoKurbenKoch(g, n - 1, x4, y4, x5, y5);
            }
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Ush7Koch().setVisible(true);
        });
    }
}
