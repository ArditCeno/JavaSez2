package Seminar5;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ush5Vija extends JPanel {
    private int vijatEVizatuara = 0;
    private Random random = new Random();

    public Ush5Vija() {
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (vijatEVizatuara == 0) {
            super.paintComponent(g);
        }

        int x1 = random.nextInt(getWidth());
        int y1 = random.nextInt(getHeight());
        int x2 = random.nextInt(getWidth());
        int y2 = random.nextInt(getHeight());

        Color ngjyraRandom = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        g.setColor(ngjyraRandom);
        g.drawLine(x1, y1, x2, y2);

        vijatEVizatuara++;
        if (vijatEVizatuara >= 100) {
            vijatEVizatuara = 0;
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simulimi i Screen Saver");
        Ush5Vija saver = new Ush5Vija();

        frame.add(saver);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}