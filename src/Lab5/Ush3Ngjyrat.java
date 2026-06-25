package Lab5;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ush3Ngjyrat extends JPanel {
    private JPopupMenu popupMenu;

    public Ush3Ngjyrat() {

        popupMenu = new JPopupMenu();

        JMenuItem redItem = new JMenuItem("Red");
        JMenuItem greenItem = new JMenuItem("Green");
        JMenuItem blueItem = new JMenuItem("Blue");

        redItem.addActionListener(e -> setBackground(Color.RED));
        greenItem.addActionListener(e -> setBackground(Color.GREEN));
        blueItem.addActionListener(e -> setBackground(Color.BLUE));

        popupMenu.add(redItem);
        popupMenu.add(greenItem);
        popupMenu.add(blueItem);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                shfaqMenune(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                shfaqMenune(e);
            }

            private void shfaqMenune(MouseEvent e) {

                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Programi me Popup Menu");
        Ush3Ngjyrat panel = new Ush3Ngjyrat();

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}