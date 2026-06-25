package Lab4;

import javax.swing.*;
import java.awt.*;

public class Ush2Editor extends JFrame {

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 400;

    private JTextArea textArea;

    public Ush2Editor() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Text Editor");
        createComponents();
        setVisible(true);
    }

    public void createComponents() {
        JMenuBar menuBar = new JMenuBar();
        JMenu editMenu = new JMenu("Edit");

        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(e -> textArea.setText(""));

        JMenuItem reverseItem = new JMenuItem("Reverse");
        reverseItem.addActionListener(e -> {
            String text = textArea.getText();
            String reversed = new StringBuilder(text).reverse().toString();
            textArea.setText(reversed);
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        editMenu.add(clearItem);
        editMenu.add(reverseItem);
        editMenu.addSeparator();
        editMenu.add(exitItem);
        menuBar.add(editMenu);
        setJMenuBar(menuBar);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush2Editor());
    }
}
