import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ush2NdryshoTxt extends JFrame {
    private JTextField textField;
    private JLabel resultLabel;
    private JButton btnMadhe;
    private JButton btnVogel;
    private JButton btnClear;

    public Ush2NdryshoTxt() {
        setTitle("Konverto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        setSize(400, 150);

        textField = new JTextField(20);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        resultLabel = new JLabel("Rezultati do t\u00eb shfaqet k\u00ebtu");
        resultLabel.setPreferredSize(new Dimension(300, 30));
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        btnMadhe = new JButton("TE MADHE");
        btnVogel = new JButton("te vogla");
        btnClear = new JButton("Pastro");

        btnMadhe.addActionListener(e -> {
            String text = textField.getText();
            if (!text.isEmpty()) {
                resultLabel.setText(text.toUpperCase());
            }
        });

        btnVogel.addActionListener(e -> {
            String text = textField.getText();
            if (!text.isEmpty()) {
                resultLabel.setText(text.toLowerCase());
            }
        });

        btnClear.addActionListener(e -> {
            textField.setText("");
            resultLabel.setText("Rezultati do t\u00eb shfaqet k\u00ebtu");
            textField.requestFocus();
        });

        add(new JLabel("Shkruani tekstin:"));
        add(textField);
        add(btnMadhe);
        add(btnVogel);
        add(btnClear);
        add(resultLabel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush2NdryshoTxt().setVisible(true));
    }
}
