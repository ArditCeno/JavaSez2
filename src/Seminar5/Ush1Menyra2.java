package Seminar5;
import javax.swing.*;
import java.awt.*;

public class Ush1Menyra2 extends JPanel {

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 150, y = 150;
        for (int r = 10; r <= 140; r += 15) {
            g.drawOval(x - r, y - r, 2 * r, 2 * r);
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Ush1Menyra2");
        f.add(new Ush1Menyra2());
        f.setSize(300, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
