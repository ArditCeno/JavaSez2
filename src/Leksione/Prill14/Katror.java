package Leksione.Prill14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Katror extends JPanel implements KeyListener, ComponentListener {

    private int x, y;
    private boolean initialized = false;
    private String message = "Shtyp ENTER per fund djathtas ose shigjeta per levizje";

    public Katror() {
        setFocusable(true);
        addKeyListener(this);
        addComponentListener(this);
    }

    private void initPosition() {
        if (!initialized) {
            x = getWidth() > 0 ? getWidth() - 25 : 575;
            y = getHeight() > 0 ? getHeight() - 25 : 375;
            initialized = true;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        initPosition();
        g.setColor(Color.YELLOW);
        g.fillRect(x - 25, y - 25, 50, 50);
        g.setColor(Color.BLACK);
        g.drawString(message, 20, 20);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            x = getWidth() - 25;
            y = getHeight() - 25;
            message = "Fund djathtas";
            repaint();
            return;
        }

        if (key == KeyEvent.VK_LEFT) { x -= 10; message = "Majtas"; }
        if (key == KeyEvent.VK_RIGHT) { x += 10; message = "Djathtas"; }
        if (key == KeyEvent.VK_UP) { y -= 10; message = "Lart"; }
        if (key == KeyEvent.VK_DOWN) { y += 10; message = "Poshte"; }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void componentResized(ComponentEvent e) {
        x = getWidth() - 25;
        y = getHeight() - 25;
        repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {}
    @Override
    public void componentShown(ComponentEvent e) {}
    @Override
    public void componentHidden(ComponentEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Katrori i Kuq");
        Katror panel = new Katror();
        frame.add(panel);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel.requestFocusInWindow();
    }
}
