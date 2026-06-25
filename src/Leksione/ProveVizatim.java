package Leksione;
//ushtrim leksion data 31 mars
import javax.swing.*;
import java.awt.*;
public class ProveVizatim extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(50, 50, 200, 50);// vija lart
        g.drawLine(50, 50, 50, 150);// vija majtas
        g.drawLine(200, 50, 200, 150);// vija djathtas
        g.drawLine(50, 150, 200, 150);//vija posht
        g.drawLine(50, 50, 125, 5); // vija majtas e catis
        g.drawLine(125, 5, 200, 50);// vija djathtas e catis
        g.fillRect(300, 70, 160, 100);
        g.drawRect(300 , 180, 180, 120 );


        g.setColor(Color.PINK);

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Figurat Gjeometrike me Graphics");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 580);
        frame.setLocationRelativeTo(null);
        frame.add(new ProveVizatim());
        frame.setVisible(true);
    }
}
