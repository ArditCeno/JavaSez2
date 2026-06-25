package Seminar7;
import javax.swing.*;
import java.awt.event.*;

public class Ush3Menu extends JFrame {

    public Ush3Menu() {
        setTitle("Menu ");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("Menu1");
        JMenu menu2 = new JMenu("Menu2");
        JMenu menu3 = new JMenu("Menu3");
        JMenu menu4 = new JMenu("Dil");

        JMenuItem exitItem = new JMenuItem("Exit (Shtyp D)");

        exitItem.setMnemonic(KeyEvent.VK_D);

        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0));

        exitItem.addActionListener(e -> System.exit(0));

        menu4.add(exitItem);
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menuBar.add(menu4);

        setJMenuBar(menuBar);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush3Menu());
    }
}