import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ush2Rrethi extends JPanel {
    private int x = -100;
    private int y = -100;
    private final int RADIUSI = 60;

    public Ush2Rrethi() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                x = e.getX();
                y = e.getY();

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.YELLOW);
        g.fillOval(x - RADIUSI/2, y - RADIUSI/2, RADIUSI, RADIUSI);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Vizato rreth");
        Ush2Rrethi panel = new Ush2Rrethi();

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}