package Seminar3;

import javax.swing.*;
import java.awt.*;

public class Ush4Alfabeti extends JFrame {
    private JLabel clickedLabel;

    public Ush4Alfabeti() {
        setTitle("Alfabeti");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        clickedLabel = new JLabel("Klikoni nje shkronje", SwingConstants.CENTER);
        clickedLabel.setFont(new Font("Arial", Font.BOLD, 18));
        clickedLabel.setPreferredSize(new Dimension(0, 40));

        JPanel gridPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        for (char c = 'A'; c <= 'Z'; c++) {
            JButton button = new JButton(String.valueOf(c));

            char finalC = c;
            button.addActionListener(e -> clickedLabel.setText("Keni klikuar " + finalC));

            gridPanel.add(button);
        }

        add(gridPanel, BorderLayout.CENTER);
        add(clickedLabel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush4Alfabeti());
    }
}