package Lab4;

import javax.swing.*;
import java.awt.*;

public class Ush3Formatues extends JFrame {

    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 400;

    private JTextArea textArea;
    private JLabel charCountLabel;

    public Ush3Formatues() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Text Formatter");
        createComponents();
        setVisible(true);
    }

    public void createComponents() {
        JMenuBar menuBar = new JMenuBar();
        JMenu formatMenu = new JMenu("Format");

        JMenuItem upperItem = new JMenuItem("Te medha");
        upperItem.addActionListener(e -> textArea.setText(textArea.getText().toUpperCase()));

        JMenuItem lowerItem = new JMenuItem("Te vogla");
        lowerItem.addActionListener(e -> textArea.setText(textArea.getText().toLowerCase()));

        JMenuItem countItem = new JMenuItem("Numeron Karakteret");
        countItem.addActionListener(e -> {
            int count = textArea.getText().length();
            charCountLabel.setText("Numri i karaktereve: " + count);
        });

        formatMenu.add(upperItem);
        formatMenu.add(lowerItem);
        formatMenu.addSeparator();
        formatMenu.add(countItem);
        menuBar.add(formatMenu);
        setJMenuBar(menuBar);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        charCountLabel = new JLabel("Numri i karaktereve: 0");
        panel.add(charCountLabel, BorderLayout.SOUTH);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush3Formatues());
    }
}
