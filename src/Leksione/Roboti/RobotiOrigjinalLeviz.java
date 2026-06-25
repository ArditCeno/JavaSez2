package Leksione.Roboti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RobotiOrigjinalLeviz extends JPanel implements ActionListener {

    private int yKapele = 100;
    private int drejtimiKapele = -2;
    private boolean poNgrihet = true;

    public RobotiOrigjinalLeviz() {
        Timer timer = new Timer(30, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // KOKA
        g.drawRect(250, 100, 100, 80);
        g.drawRect(235, 120, 15, 30);
        g.drawRect(350, 120, 15, 30);
        g.drawOval(270, 120, 15, 15);
        g.drawOval(315, 120, 15, 15);
        int[] xHunda = {300, 290, 310};
        int[] yHunda = {140, 160, 160};
        g.drawPolygon(xHunda, yHunda, 3);
        g.drawRect(285, 165, 30, 8);

        // KAPELJA (Lëviz sipas yKapele)
        int[] xKapele = {250, 300, 350};
        int[] yKapeleTrekendesh = {yKapele, yKapele - 50, yKapele};
        g.drawPolygon(xKapele, yKapeleTrekendesh, 3);
        g.drawString("★", 295, yKapele - 15);

        // TRUPI
        g.drawRect(200, 180, 200, 200);
        g.drawOval(275, 200, 50, 50);
        g.drawOval(275, 260, 50, 50);
        g.drawOval(275, 320, 50, 50);
        int[] xT1 = {230, 260, 260}; int[] yT1 = {300, 275, 325};
        g.drawPolygon(xT1, yT1, 3);
        int[] xT2 = {370, 340, 340}; int[] yT2 = {300, 275, 325};
        g.drawPolygon(xT2, yT2, 3);

        // KRAHU I MAJTË (Qëndron fiks)
        g.drawRect(140, 180, 60, 40);
        g.drawRect(140, 220, 40, 150);
        g.drawOval(135, 370, 50, 50);

        // KRAHU I DJATHTË (Lëviz për të hequr kapelen)
        // Pozicioni i supit mbetet fiks, por krahu dhe dora ndjekin kapelen
        g.drawRect(400, 180, 60, 40); // Supi
        g.drawRect(420, yKapele, 40, 180 - yKapele + 40); // Krahu që zgjatet
        g.drawOval(415, yKapele - 20, 50, 50); // Dora që kap kapelen

        // KËMBËT
        g.drawRect(240, 380, 40, 180);
        g.drawRect(320, 380, 40, 180);
        g.drawRect(210, 560, 70, 30);
        g.drawRect(320, 560, 70, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (poNgrihet) {
            yKapele += drejtimiKapele;
            if (yKapele <= 30) { // Sa lart e ngre dorën
                poNgrihet = false;
                drejtimiKapele = 2;
            }
        } else {
            yKapele += drejtimiKapele;
            if (yKapele >= 100) { // Kur kthehet në kokë
                poNgrihet = true;
                drejtimiKapele = -2;
            }
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Roboti Heq Kapelen me Dore");
        frame.add(new RobotiOrigjinalLeviz());
        frame.setSize(650, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}