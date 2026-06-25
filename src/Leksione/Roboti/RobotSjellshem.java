package Leksione.Roboti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RobotSjellshem extends JPanel implements ActionListener {

    private int xPozicioni = -200;
    private double koha = 0;

    public RobotSjellshem() {
        // Timer-i qe kontrollon shpejtesine e animacionit
        Timer timer = new Timer(20, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        xPozicioni += 2; // Shpejtesia e levizjes horizontale
        koha += 0.15;    // Shpejtesia e lekundjes (ecjes)

        // Nese roboti del nga dritarja, kthehet ne fillim
        if (xPozicioni > getWidth()) {
            xPozicioni = -400;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Zbutja e vijave (Anti-aliasing)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // --- LOGJIKA E ECJES ---
        // Hop: trupi ulet e ngrihet pak
        int hop = (int) (Math.abs(Math.sin(koha)) * 10);
        // Hapi: per levizjen e kembeve dhe kraheve
        int hapi = (int) (Math.sin(koha) * 15);

        // --- KOKA ---
        int yK = 50 + hop; // Koka ndjek trupin
        g2.setPaint(new GradientPaint(xPozicioni + 250, yK, Color.PINK, xPozicioni + 350, yK + 100, new Color(255, 102, 178)));
        g2.fillRect(xPozicioni + 250, yK, 100, 100);
        g2.setColor(Color.BLACK);
        g2.drawRect(xPozicioni + 250, yK, 100, 100);

        // Sytë
        g2.setColor(Color.CYAN);
        g2.fillOval(xPozicioni + 270, yK + 30, 20, 20);
        g2.fillOval(xPozicioni + 310, yK + 30, 20, 20);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(xPozicioni + 280, yK + 70, 40, 10, 5, 5);

        // --- TRUPI ---
        int yT = 150 + hop;
        g2.setPaint(new GradientPaint(xPozicioni + 210, yT, Color.LIGHT_GRAY, xPozicioni + 390, yT + 100, Color.GRAY));
        g2.fillRoundRect(xPozicioni + 210, yT, 180, 100, 20, 20);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(xPozicioni + 210, yT, 180, 100, 20, 20);

        // Butoni ne mes te trupit
        g2.setColor(Color.YELLOW);
        g2.fillOval(xPozicioni + 285, yT + 35, 30, 30);
        g2.setColor(Color.BLACK);
        g2.drawOval(xPozicioni + 285, yT + 35, 30, 30);

        // --- KRAHET (Levizin ne kundershtim me njeri-tjetrin) ---
        g2.setColor(Color.BLACK);
        // Krahu i majte
        g2.fillRect(xPozicioni + 130, yT + 20 + hapi, 80, 40);
        g2.fillRect(xPozicioni + 150, yT + 60 + hapi, 40, 60);

        // Krahu i djathte
        g2.fillRect(xPozicioni + 390, yT + 20 - hapi, 80, 40);
        g2.fillRect(xPozicioni + 410, yT + 60 - hapi, 40, 60);

        // --- KEMBET ---
        // Kemba 1
        int k1 = (hapi > 0) ? hapi : 0;
        g2.fillRect(xPozicioni + 245, yT + 100 - k1, 40, 100);
        g2.setColor(Color.DARK_GRAY);
        g2.fillOval(xPozicioni + 235, yT + 190 - k1, 60, 25);

        // Kemba 2
        g2.setColor(Color.BLACK);
        int k2 = (hapi < 0) ? -hapi : 0;
        g2.fillRect(xPozicioni + 315, yT + 100 - k2, 40, 100);
        g2.setColor(Color.DARK_GRAY);
        g2.fillOval(xPozicioni + 305, yT + 190 - k2, 60, 25);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Robot i sjellshëm në IntelliJ");
        RobotSjellshem panel = new RobotSjellshem();

        f.add(panel);
        f.setSize(900, 600);
        // Vendosim nje ngjyre sfondi per dritaren
        panel.setBackground(new Color(255, 255, 255));

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}