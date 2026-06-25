package Seminar7;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ush5Katrori extends JFrame {
    private JComboBox<String> ngjyraCombo;
    private JCheckBox iMbushurCheck;
    private PaneliVizatimit paneli;

    public Ush5Katrori() {
        setTitle("Katroret");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel kontrollPanel = new JPanel();

        String[] ngjyrat = {"E kuqe", "Blu", "E verdhe"};
        ngjyraCombo = new JComboBox<>(ngjyrat);

        iMbushurCheck = new JCheckBox("I ngjyrosur");

        kontrollPanel.add(new JLabel("Zgjidh ngjyren"));
        kontrollPanel.add(ngjyraCombo);
        kontrollPanel.add(iMbushurCheck);

        paneli = new PaneliVizatimit();

        ngjyraCombo.addActionListener(e -> paneli.repaint());

        iMbushurCheck.addItemListener(e -> paneli.repaint());

        add(kontrollPanel, BorderLayout.NORTH);
        add(paneli, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class PaneliVizatimit extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Random rand = new Random();

            String ngjyraEzgjedhur = (String) ngjyraCombo.getSelectedItem();
            switch (ngjyraEzgjedhur) {
                case "E kuqe": g.setColor(Color.RED); break;
                case "Blu": g.setColor(Color.BLUE); break;
                case "E verdhe": g.setColor(Color.YELLOW ); break;
            }

            for (int i = 0; i < 20; i++) {
                int x = rand.nextInt(getWidth() - 50);
                int y = rand.nextInt(getHeight() - 50);
                int brinja = 40;

                if (iMbushurCheck.isSelected()) {
                    g.fillRect(x, y, brinja, brinja);
                } else {
                    g.drawRect(x, y, brinja, brinja);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush5Katrori());
    }
}
