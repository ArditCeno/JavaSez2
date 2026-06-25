package Seminar5;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Ush3Makina2 extends JFrame {
    // Variabla carX kontrollon lëvizjen fizike të makinës
    private int carX = 800;
    private double carBounce = 0;
    private Timer timer;

    public Ush3Makina2() {
        setTitle("Lambo - Lëvizje nga e Djathta në të Majtë");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setResizable(false);

        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // --- 1. QIELLI (Statik - Sfondi YT) ---
                GradientPaint nightSky = new GradientPaint(0, 0, new Color(10, 10, 50), 0, 300, new Color(40, 20, 90));
                g2d.setPaint(nightSky);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // --- 2. QYTETI YT (Statik) ---
                g2d.setColor(new Color(20, 20, 40));
                g2d.fillRect(50, 150, 80, 200);
                g2d.fillRect(150, 100, 100, 250);
                g2d.fillRect(300, 180, 70, 170);
                g2d.fillRect(450, 120, 90, 230);
                g2d.fillRect(600, 160, 110, 190);

                // Dritaret e ndërtesave
                g2d.setColor(new Color(255, 255, 150, 150));
                g2d.fillRect(170, 120, 15, 15);
                g2d.fillRect(170, 160, 15, 15);
                g2d.fillRect(480, 140, 15, 15);
                g2d.fillRect(480, 180, 15, 15);

                // --- 3. RRUGA (Statike) ---
                g2d.setColor(new Color(30, 30, 30));
                g2d.fillRect(0, 350, getWidth(), 150);
                g2d.setColor(Color.WHITE);
                for (int i = 0; i < getWidth(); i += 100) {
                    g2d.fillRect(i + 25, 420, 50, 5);
                }

                // --- 4. MAKINA JOTE (Që lëviz nga e djathta në të majtë) ---
                int x = carX;
                int y = 280 + (int)(Math.sin(carBounce) * 2);
                drawLambo(g2d, x, y);
            }

            private void drawLambo(Graphics2D g2d, int x, int y) {
                // Trupi (Kodi yt origjinal pa asnjë ndryshim në formë)
                GradientPaint bodyGrad = new GradientPaint(x, y, new Color(255, 50, 0), x, y + 70, new Color(150, 0, 0));
                g2d.setPaint(bodyGrad);

                Path2D.Double body = new Path2D.Double();
                body.moveTo(x, y + 40);
                body.lineTo(x + 80, y + 5);
                body.lineTo(x + 200, y - 25);
                body.lineTo(x + 400, y - 25);
                body.lineTo(x + 520, y + 40);
                body.lineTo(x + 520, y + 75);
                body.lineTo(x, y + 75);
                body.closePath();
                g2d.fill(body);

                // Spoiler
                g2d.setColor(Color.BLACK);
                g2d.fillRect(x + 450, y - 35, 70, 6);

                // Xhamat
                g2d.setPaint(new LinearGradientPaint(x+210, y-20, x+210, y+30, new float[]{0f, 1f}, new Color[]{new Color(50, 150, 255), new Color(10, 30, 80)}));
                g2d.fill(new Rectangle2D.Double(x + 210, y - 18, 180, 45));

                // Dritat
                g2d.setColor(new Color(200, 230, 255));
                g2d.fillOval(x + 15, y + 40, 45, 12);

                // Rrotat
                drawWheel(g2d, x + 120, y + 75);
                drawWheel(g2d, x + 400, y + 75);
            }

            private void drawWheel(Graphics2D g2d, int cx, int cy) {
                g2d.setColor(Color.BLACK);
                g2d.fillOval(cx - 40, cy - 40, 80, 80);
                g2d.setColor(Color.DARK_GRAY);
                g2d.fillOval(cx - 32, cy - 32, 64, 64);
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.setStroke(new BasicStroke(4));
                for(int i = 0; i < 360; i += 60) {
                    // Rrotullimi i rrotave në përputhje me lëvizjen majtas
                    double rad = Math.toRadians(i - (carX * 2));
                    g2d.drawLine(cx, cy, cx + (int)(Math.cos(rad)*28), cy + (int)(Math.sin(rad)*28));
                }
            }
        };

        add(drawingPanel);

        // --- TIMER PËR LËVIZJEN ---
        timer = new Timer(15, e -> {
            carX -= 5;        // Zvogëlojmë X që makina të shkojë majtas
            carBounce += 0.2;

            // Nëse makina zhduket plotësisht në të majtë, e rinisim në të djathtë
            if (carX < -550) {
                carX = 800;
            }

            repaint();
        });
        timer.start();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Ush3Makina2::new);
    }
}