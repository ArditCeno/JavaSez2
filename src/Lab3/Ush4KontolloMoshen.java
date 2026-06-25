import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class Ush4KontolloMoshen extends JFrame {
    private JTextField ageField;
    private JButton checkButton;
    private JLabel resultIcon;

    public Ush4KontolloMoshen() {
        setTitle("Kontroll Moshe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        setSize(350, 140);

        ageField = new JTextField(10);
        ageField.setFont(new Font("Arial", Font.PLAIN, 16));
        ageField.setHorizontalAlignment(JTextField.CENTER);

        // Filtri për të lejuar vetëm numra
        ((AbstractDocument) ageField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string != null && string.matches("\\d*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
                if (string != null && string.matches("\\d*")) {
                    super.replace(fb, offset, length, string, attr);
                }
            }
        });

        checkButton = new JButton("Kontrollo");
        resultIcon = new JLabel();
        resultIcon.setPreferredSize(new Dimension(30, 30));

        checkButton.addActionListener(e -> checkAge());
        ageField.addActionListener(e -> checkAge());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Mosha:"));
        inputPanel.add(ageField);
        inputPanel.add(checkButton);
        inputPanel.add(resultIcon);

        add(inputPanel);
        setLocationRelativeTo(null);
    }

    private void checkAge() {
        String text = ageField.getText();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ju lutem shkruani moshën!", "Gabim", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int age = Integer.parseInt(text);

            if (age < 1 || age > 112) {
                JOptionPane.showMessageDialog(this,
                        "Mosha duhet të jetë nga 1 deri në 112.\nNjeriu më i vjetër në botë ka qenë 112 vjeç.",
                        "Gabim", JOptionPane.ERROR_MESSAGE);
                resultIcon.setIcon(null);
            } else if (age < 18) {
                JOptionPane.showMessageDialog(this,
                        "Personi është i mitur.",
                        "Rezultati", JOptionPane.INFORMATION_MESSAGE);
                resultIcon.setIcon(UIManager.getIcon("OptionPane.warningIcon"));
            } else {
                JOptionPane.showMessageDialog(this,
                        "Personi është i rritur.",
                        "Rezultati", JOptionPane.INFORMATION_MESSAGE);
                resultIcon.setIcon(UIManager.getIcon("OptionPane.questionIcon"));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Shkruani një numër të vlefshëm!", "Gabim", JOptionPane.ERROR_MESSAGE);
            resultIcon.setIcon(null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush4KontolloMoshen().setVisible(true));
    }
}