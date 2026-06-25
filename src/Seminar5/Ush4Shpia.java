package Seminar5;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class Ush4Shpia extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(255, 222, 173)); // Ngjyrë kremi
        g2d.fillRect(200, 200, 300, 200);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(200, 200, 300, 200);

        g2d.setColor(new Color(165, 42, 42)); // Ngjyrë kafe e errët
        Path2D.Double catia = new Path2D.Double();
        catia.moveTo(200, 200);  // Pika majtas
        catia.lineTo(350, 100);  // Kulmi i çatisë
        catia.lineTo(500, 200);  // Pika djathtas
        catia.closePath();
        g2d.fill(catia);
        g2d.setColor(Color.BLACK);
        g2d.draw(catia);

        g2d.setColor(new Color(139, 69, 19)); // Kafe druri
        g2d.fillRect(320, 300, 60, 100);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(320, 300, 60, 100);

        g2d.setColor(Color.YELLOW);
        g2d.fillOval(365, 350, 8, 8);

        g2d.setColor(new Color(173, 216, 230)); // Blu e hapur (qelq)
        g2d.fillRect(230, 240, 60, 60);
        g2d.fillRect(410, 240, 60, 60);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(230, 240, 60, 60);
        g2d.drawRect(410, 240, 60, 60);

        g2d.drawLine(260, 240, 260, 300); // Vertikale majtas
        g2d.drawLine(230, 270, 290, 270); // Horizontale majtas
        g2d.drawLine(440, 240, 440, 300); // Vertikale djathtas
        g2d.drawLine(410, 270, 470, 270); // Horizontale djathtas

        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(410, 120, 30, 50);
        g2d.drawRect(410, 120, 30, 50);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Programi për Vizatimin e Shtëpisë");
        Ush4Shpia paneli = new Ush4Shpia();

        frame.add(paneli);
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}