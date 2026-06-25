package Leksione.prill14;
import javax.swing.*;
import java.awt.event.*;
public class Shembull2 extends JFrame {
private JLabel lbl;
public Shembull2() {
setTitle("Butonat e Mouse");
setSize(400, 300);
setDefaultCloseOperation(EXIT_ON_CLOSE);
lbl = new JLabel("Kliko me mouse", SwingConstants.CENTER);
add(lbl);
addMouseListener(new MouseAdapter() {
@Override
public void mouseClicked(MouseEvent e) {
if (e.getButton() == MouseEvent.BUTTON1) {
lbl.setText("Klik i majte");
} else if (e.getButton() == MouseEvent.BUTTON3) {
lbl.setText("Klik i djathte");
}
}
});
setVisible(true);
}
public static void main(String[] args) {
SwingUtilities.invokeLater(() -> new Leksione.prill14.Shembull2());
}
}
