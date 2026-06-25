package seminar4;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Ushtrimi2 extends JFrame {

    private JLabel label;
    private JMenuBar menuBar;

    public Ushtrimi2() {
        setTitle("Ndrysho Pamjen");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        krijoMenuBar();

        label = new JLabel("Tekst default");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton btnNdrysho = new JButton("Ndrysho Tekstin");

        setLayout(new java.awt.GridLayout(2, 1));
        add(label);
        add(btnNdrysho);

        btnNdrysho.addActionListener(e -> {
            label.setText("Teksti u ndryshua");
        });

        setVisible(true);
    }

    private void krijoMenuBar() {
        menuBar = new JMenuBar();

        JMenu menuNdryshoPamjen = new JMenu("Ndrysho Pamjen");

        JMenuItem itemMetal = new JMenuItem("Metal");
        JMenuItem itemSystem = new JMenuItem("System");
        JMenuItem itemFlatLaf = new JMenuItem("FlatLaf");

        menuNdryshoPamjen.add(itemMetal);
        menuNdryshoPamjen.add(itemSystem);
        menuNdryshoPamjen.add(itemFlatLaf);

        menuBar.add(menuNdryshoPamjen);

        setJMenuBar(menuBar);

        itemMetal.addActionListener(e -> ndryshoPamjen("javax.swing.plaf.metal.MetalLookAndFeel"));
        itemSystem.addActionListener(e -> ndryshoPamjen(UIManager.getSystemLookAndFeelClassName()));
        itemFlatLaf.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
                SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception ex) {
                System.out.println("FlatLaf nuk eshte i instaluar");
            }
        });
    }

    private void ndryshoPamjen(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
