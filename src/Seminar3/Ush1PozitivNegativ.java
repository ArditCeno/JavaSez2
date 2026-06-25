package Seminar3;
/*Krijoni një aplikacion në JAVA që merr nga përdoruesi në një textField një numër. Në
klik të një butoni tregoni nëse numri është pozitiv apo negativ.*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ush1PozitivNegativ extends JFrame {
    private JTextField textField;
    private JLabel resultLabel;

    public Ush1PozitivNegativ() {
        setTitle("Kontrollo numrin");
        setSize(350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Jepni nje nr");
        textField = new JTextField(15);
        JButton button = new JButton("Kontrollo");
        resultLabel = new JLabel("");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double numri = Double.parseDouble(textField.getText());
                    if (numri > 0) {
                        resultLabel.setText("Numri " + numri + " eshte poz");
                    } else if (numri < 0) {
                        resultLabel.setText("Numri " + numri + " eshte neg");
                    } else {
                        resultLabel.setText("Numri eshte ZERO (as positiv");
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Vetem nr jo shkronja");
                }
            }
        });

        add(label);
        add(textField);
        add(button);
        add(resultLabel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush1PozitivNegativ());
    }
}
