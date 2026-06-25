package seminar4;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Menu extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuUshtrimi1;
    private JMenu menuUshtrimi2;
    private JMenu menuUshtrimi3;
    private JMenu menuUshtrimi4;
    private JMenu menuUshtrimi5;
    private JMenu menuNdryshoPamjen;
    private JMenu menuDil;

    private JMenuItem itemUshtrimi1;
    private JMenuItem itemUshtrimi2;
    private JMenuItem itemUshtrimi3;
    private JMenuItem itemUshtrimi4;
    private JMenuItem itemUshtrimi5;
    private JMenuItem itemMetal;
    private JMenuItem itemSystem;
    private JMenuItem itemFlatLaf;
    private JMenuItem itemDil;

    public Menu() {
        setTitle("Aplikacioni Seminar4");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        krijoMenu();
        shtoNgjarje();

        setVisible(true);
    }

    private void krijoMenu() {
        menuBar = new JMenuBar();

        menuUshtrimi1 = new JMenu("Ushtrimi 1");
        menuUshtrimi2 = new JMenu("Ushtrimi 2");
        menuUshtrimi3 = new JMenu("Ushtrimi 3");
        menuUshtrimi4 = new JMenu("Ushtrimi 4");
        menuUshtrimi5 = new JMenu("Ushtrimi 5");
        menuNdryshoPamjen = new JMenu("Ndrysho Pamjen");
        menuDil = new JMenu("Dil");

        itemUshtrimi1 = new JMenuItem("Hap");
        itemUshtrimi2 = new JMenuItem("Hap");
        itemUshtrimi3 = new JMenuItem("Hap");
        itemUshtrimi4 = new JMenuItem("Hap");
        itemUshtrimi5 = new JMenuItem("Hap");
        itemMetal = new JMenuItem("Metal");
        itemSystem = new JMenuItem("System");
        itemFlatLaf = new JMenuItem("FlatLaf");
        itemDil = new JMenuItem("Mbyll Aplikacionin");

        menuUshtrimi1.add(itemUshtrimi1);
        menuUshtrimi2.add(itemUshtrimi2);
        menuUshtrimi3.add(itemUshtrimi3);
        menuUshtrimi4.add(itemUshtrimi4);
        menuUshtrimi5.add(itemUshtrimi5);

        menuNdryshoPamjen.add(itemMetal);
        menuNdryshoPamjen.add(itemSystem);
        menuNdryshoPamjen.add(itemFlatLaf);

        menuDil.add(itemDil);

        menuBar.add(menuUshtrimi1);
        menuBar.add(menuUshtrimi2);
        menuBar.add(menuUshtrimi3);
        menuBar.add(menuUshtrimi4);
        menuBar.add(menuUshtrimi5);
        menuBar.add(menuNdryshoPamjen);
        menuBar.add(menuDil);

        setJMenuBar(menuBar);
    }

    private void shtoNgjarje() {
        itemUshtrimi1.addActionListener(e -> new seminar4.Ushtrimi1());
        itemUshtrimi2.addActionListener(e -> new seminar4.Ushtrimi2());
        itemUshtrimi3.addActionListener(e -> new seminar4.Ushtrimi3());
        itemUshtrimi4.addActionListener(e -> new seminar4.Ushtrimi4());
        itemUshtrimi5.addActionListener(e -> new seminar4.Ushtrimi5());
        itemDil.addActionListener(e -> System.exit(0));

        itemMetal.addActionListener(e -> ndryshoPamjen("javax.swing.plaf.metal.MetalLookAndFeel"));
        itemSystem.addActionListener(e -> ndryshoPamjen(UIManager.getSystemLookAndFeelClassName()));
        itemFlatLaf.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
                SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception ex) {
                System.out.println("FlatLaf non che... Shtoni librarine.");
            }
        });
    }

    private void ndryshoPamjen(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Menu());
    }
}
