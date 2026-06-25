package Leksione.prill14;
import javax.swing.*;
import java.awt.event.*;
public class Shembull4 extends JFrame implements KeyListener {
private JLabel lbl;
public Shembull4() {
setTitle("Eventet e Tastieres");
setSize(400, 300);
setDefaultCloseOperation(EXIT_ON_CLOSE);
lbl = new JLabel("Shtyp nje tast", SwingConstants.CENTER);
add(lbl);
addKeyListener(this);
setFocusable(true);
setVisible(true);
}


@Override
public void keyTyped(KeyEvent e) {
lbl.setText("Key Typed: " + e.getKeyChar());
}
@Override
public void keyPressed(KeyEvent e) {
lbl.setText("Key Pressed: " + e.getKeyCode());
}
@Override
public void keyReleased(KeyEvent e) {
lbl.setText("Key Released: " + e.getKeyChar());
}
public static void main(String[] args) {
SwingUtilities.invokeLater(() -> new Shembull4());
}
}
