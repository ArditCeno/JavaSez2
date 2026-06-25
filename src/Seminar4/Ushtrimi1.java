package seminar4;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;

public class Ushtrimi1 extends JFrame {

    private JTextArea textArea;

    public Ushtrimi1() {
        setTitle("Konvertimi i Tekstit");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        textArea = new JTextArea("Shkruani tekstin");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JButton btnLower = new JButton("To Lower Case");
        JButton btnUpper = new JButton("To Upper Case");

        JPanel panelButtons = new JPanel();
        panelButtons.add(btnLower);
        panelButtons.add(btnUpper);

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);

        btnLower.addActionListener(e -> {
            String text = textArea.getText();
            textArea.setText(text.toLowerCase());
        });

        btnUpper.addActionListener(e -> {
            String text = textArea.getText();
            textArea.setText(text.toUpperCase());
        });

        setVisible(true);
    }
}
