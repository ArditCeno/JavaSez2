//LEKSION7

package Leksione.Prill14;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Shembull1 extends JFrame implements MouseListener {
private JLabel lbl;
public Shembull1() {
setTitle("Eventet e Mouse");
setSize(400, 300);
setDefaultCloseOperation(EXIT_ON_CLOSE);
setLayout(new FlowLayout());
lbl = new JLabel("Provo mouse-in mbi dritare");
add(lbl);
addMouseListener(this);
setVisible(true);
}
@Override
public void mouseClicked(MouseEvent e) {
lbl.setText("Klikim ne pozicionin: (" + e.getX() + ", " + e.getY() + ")");
}

@Override
public void mousePressed(MouseEvent e) {
lbl.setText("Mouse Pressed");
}
@Override
public void mouseReleased(MouseEvent e) {
lbl.setText("Mouse Released");
}
@Override
public void mouseEntered(MouseEvent e) {
lbl.setText("Mouse Entered");
}
@Override
public void mouseExited(MouseEvent e) {
lbl.setText("Mouse Exited");
}
public static void main(String[] args) {
SwingUtilities.invokeLater(() -> new Shembull1());
}
}
