package Seminar5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ush3Makina extends JFrame {
    private int carXOffset = 0;
    private final int speed = 5;

    public Ush3Makina() {
        setTitle("Makina Sportive në Lëvizje");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout());

        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Qielli
                GradientPaint sky = new GradientPaint(0, 0, new Color(135, 206, 250), 0, 300, new Color(255, 255, 200));
                g2d.setPaint(sky);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // Toka/Rruga
                g2d.setColor(new Color(100, 150, 100));
                g2d.fillRect(0, 340, getWidth(), 100);

                g2d.setColor(Color.WHITE);
                g2d.drawLine(0, 345, getWidth(), 345);


                int carX = 80 + carXOffset;
                int carY = 180;
                int carW = 640;
                int carH = 120;

                GradientPaint bodyGrad = new GradientPaint(carX, carY, new Color(200, 20, 20), carX, carY + carH, new Color(120, 10, 10));
                g2d.setPaint(bodyGrad);
                int[] bodyX = {carX, carX + 40, carX + 180, carX + 220, carX + carW - 40, carX + carW, carX + carW, carX};
                int[] bodyY = {carY + 60, carY, carY, carY + 50, carY + 50, carY, carY + carH, carY + carH};
                g2d.fillPolygon(bodyX, bodyY, 8);

                g2d.setColor(new Color(200, 230, 255));
                int[] win1X = {carX + 190, carX + 200, carX + 310, carX + 320, carX + 190};
                int[] win1Y = {carY + 5, carY - 35, carY - 35, carY + 5, carY + 5};
                g2d.fillPolygon(win1X, win1Y, 5);

                int[] win2X = {carX + 330, carX + 340, carX + 500, carX + 510, carX + 330};
                int[] win2Y = {carY + 5, carY - 35, carY - 35, carY + 5, carY + 5};
                g2d.fillPolygon(win2X, win2Y, 5);

                g2d.setColor(Color.YELLOW);
                g2d.fillRoundRect(carX + 565, carY + 55, 50, 35, 5, 5);

                int wheelY = carY + carH + 5;
                int wheelR = 45;
                drawWheel(g2d, carX + 160, wheelY, wheelR);
                drawWheel(g2d, carX + 500, wheelY, wheelR);
            }

            private void drawWheel(Graphics2D g2d, int x, int y, int r) {
                g2d.setColor(Color.BLACK);
                g2d.fillOval(x - r, y - r, r * 2, r * 2);
                g2d.setColor(Color.GRAY);
                g2d.fillOval(x - r + 10, y - r + 10, (r - 10) * 2, (r - 10) * 2);
                g2d.setColor(Color.BLACK);
                g2d.fillOval(x - 14, y - 14, 28, 28);
            }
        };

        add(drawingPanel, BorderLayout.CENTER);

        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carXOffset += speed;

                if (carXOffset > getWidth() + 100) {
                    carXOffset = -700;
                }

                repaint();
            }
        });

        timer.start();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush3Makina());
    }
}