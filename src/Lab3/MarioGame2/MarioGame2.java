package Lab3.MarioGame2;

import javax.swing.*;

public class MarioGame2 extends JFrame {
    public MarioGame2() {
        setTitle("Mario Game 2.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        GamePanel2 gamePanel = new GamePanel2();
        add(gamePanel);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MarioGame2().setVisible(true));
    }
}

