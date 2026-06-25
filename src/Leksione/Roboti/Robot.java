package Leksione.Roboti;

import javax.swing.*;
import java.awt.*;

public class Robot extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawRect(250, 100, 100, 80);
        g.drawRect(235, 120, 15, 30);
        g.drawRect(350, 120, 15, 30);

        g.drawOval(270, 120, 15, 15);
        g.drawOval(315, 120, 15, 15);
        int[] xHunda = {300, 290, 310};
        int[] yHunda = {140, 160, 160};
        g.drawPolygon(xHunda, yHunda, 3);
        g.drawRect(285, 165, 30, 8);


        int[] xKapelja = {250, 300, 350};
        int[] yKapelja = {100, 50, 100};
        g.drawPolygon(xKapelja, yKapelja, 3);
        g.drawString("★", 295, 85);

        g.drawRect(200, 180, 200, 200);

        g.drawOval(275, 200, 50, 50);
        g.drawOval(275, 260, 50, 50);
        g.drawOval(275, 320, 50, 50);

        int[] xT1 = {230, 260, 260}; int[] yT1 = {300, 275, 325};
        g.drawPolygon(xT1, yT1, 3); // Majtas
        int[] xT2 = {370, 340, 340}; int[] yT2 = {300, 275, 325};
        g.drawPolygon(xT2, yT2, 3);

        g.drawRect(140, 180, 60, 40);
        g.drawRect(140, 220, 40, 150);
        g.drawOval(135, 370, 50, 50);

        g.drawRect(400, 180, 60, 40);
        g.drawRect(420, 220, 40, 150);
        g.drawOval(415, 370, 50, 50);

        g.drawRect(240, 380, 40, 180);
        g.drawRect(320, 380, 40, 180);
        g.drawRect(210, 560, 70, 30);
        g.drawRect(320, 560, 70, 30);

        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Roboti", 180, 630);
        g.setColor(Color.BLACK);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Roboti Gjeometrik");
        Robot panel = new Robot();

        frame.add(panel);
        frame.setSize(650, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}