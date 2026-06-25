import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Ush1ShfaqFshi extends JFrame {
    private JLabel label;
    private JButton btnToggle;

    public Ush1ShfaqFshi() {
        setTitle("Fshih & Shfaq");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        label = new JLabel("Label");
        label.setFont(new Font("Arial", Font.PLAIN, 24));

        btnToggle = new JButton("Fshih");
        btnToggle.setPreferredSize(new Dimension(100, 35));
        btnToggle.addActionListener(e -> {
            boolean isVisible = label.isVisible();
            label.setVisible(!isVisible);
            btnToggle.setText(isVisible ? "Shfaq" : "Fshih");
        });

        // Korigjuar: WHEN_FOCUSED (ishte WFHEN_FOCUSED)
        InputMap im = btnToggle.getInputMap(JComponent.WHEN_FOCUSED);
        im.put(KeyStroke.getKeyStroke("SPACE"), "toggle");

        btnToggle.getActionMap().put("toggle", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnToggle.doClick();
            }
        });

        add(label);
        add(btnToggle);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush1ShfaqFshi().setVisible(true));
    }
}