package Leksione.prill14; //Leksion7

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Shembull5 extends JPanel implements KeyListener {

    private int x = 100, y = 100;
    private String message = "Kliko ose perdor tastieren";

    public Shembull5() {
        setFocusable(true);

        addKeyListener(this);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                message = "Mouse clicked at (" + x + ", " + y + ")";
                repaint();
                requestFocusInWindow();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillOval(x - 20, y - 20, 40, 40);

        g.setColor(Color.BLACK);
        g.drawString(message, 20, 20);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) x -= 10;
        if (key == KeyEvent.VK_RIGHT) x += 10;
        if (key == KeyEvent.VK_UP) y -= 10;
        if (key == KeyEvent.VK_DOWN) y += 10;

        message = "Key pressed: " + KeyEvent.getKeyText(key);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mouse + Tastiere");
        Shembull5 panel = new Shembull5();

        frame.add(panel);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        panel.requestFocusInWindow();
    }
}
