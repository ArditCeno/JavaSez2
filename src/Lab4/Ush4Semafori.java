package Lab4;

import javax.swing.*;
import java.awt.*;

public class Ush4Semafori extends JFrame {

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 400;

    public Ush4Semafori() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Semafori");
        add(new SemaforiPanel());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush4Semafori());
    }

    class SemaforiPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.BLACK);
            g.fillRect(50, 20, 80, 300);

            g.setColor(Color.BLACK);
            g.fillRect(85, 300, 10, 250);

            g.setColor(Color.RED);
            g.fillOval(60, 40, 60, 60);

            g.setColor(Color.YELLOW);
            g.fillOval(60, 130, 60, 60);

            g.setColor(Color.GREEN);
            g.fillOval(60, 220, 60, 60);

            g.setColor(Color.BLACK);
            g.fillRect(1150, 20, 80, 300);

            g.setColor(Color.BLACK);
            g.fillRect(1185, 300, 10, 250);

            g.setColor(Color.RED);
            g.fillOval(1160, 40, 60, 60);

            g.setColor(Color.YELLOW);
            g.fillOval(1160, 130, 60, 60);

            g.setColor(Color.GREEN);
            g.fillOval(1160, 220, 60, 60);


        }
    }
}
