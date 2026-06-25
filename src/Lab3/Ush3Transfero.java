import javax.swing.*;
import javax.swing.event.DocumentEvent; // Importi i nevojshëm
import javax.swing.event.DocumentListener; // Importi i nevojshëm
import java.awt.*;

public class Ush3Transfero extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JButton transferButton;
    private JButton clearButton;
    private JLabel lengthLabel;

    public Ush3Transfero() {
        setTitle("Transfero");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        setSize(450, 200);

        textField1 = new JTextField(25);
        textField1.setFont(new Font("Arial", Font.PLAIN, 14));

        textField2 = new JTextField(25);
        textField2.setEditable(false);
        textField2.setFont(new Font("Arial", Font.PLAIN, 14));
        textField2.setBackground(new Color(240, 240, 240));

        transferButton = new JButton("Transfero");
        clearButton = new JButton("Pastro");
        lengthLabel = new JLabel("0 karaktere");

        textField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLength();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLength();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLength();
            }

            private void updateLength() {
                SwingUtilities.invokeLater(() -> {
                    int length = textField1.getText().length();
                    lengthLabel.setText(length + " karaktere");
                });
            }
        });

        transferButton.addActionListener(e -> {
            String text = textField1.getText();
            textField2.setText(text);
            textField1.setText("");
            textField1.requestFocus();
        });

        clearButton.addActionListener(e -> {
            textField1.setText("");
            textField2.setText("");
            textField1.requestFocus();
        });

        add(new JLabel("Texti i shkruar:"));
        add(textField1);
        add(transferButton);
        add(clearButton);
        add(new JLabel("Texti i dërguar:"));
        add(textField2);
        add(lengthLabel);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush3Transfero().setVisible(true));
    }
}