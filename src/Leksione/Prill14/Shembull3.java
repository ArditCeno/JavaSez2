package Leksione.prill14;
import javax.swing.*;
import java.awt.event.*;
public class Shembull3 extends JFrame implements MouseMotionListener {
private JLabel lbl;
public Shembull3() {
setTitle("Levizja e Mouse");
setSize(500, 300);
setDefaultCloseOperation(EXIT_ON_CLOSE);
lbl = new JLabel("Leviz mouse-in...", SwingConstants.CENTER);
add(lbl);
addMouseMotionListener(this);
setVisible(true);
}
@Override
public void mouseMoved(MouseEvent e) {
lbl.setText("Mouse moved: X=" + e.getX() + " Y=" + e.getY());
}
@Override
public void mouseDragged(MouseEvent e) {
lbl.setText("Mouse dragged: X=" + e.getX() + " Y=" + e.getY());
}
public static void main(String[] args) {
SwingUtilities.invokeLater(() -> new Shembull3());
}
}
