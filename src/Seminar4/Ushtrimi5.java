package seminar4;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.util.Random;

public class Ushtrimi5 extends JFrame {

    private int numriSekret;
    private int numriPjekjeve;
    private JTextField textField;
    private JLabel labelFeedback;
    private JLabel labelPjekjeve;

    public Ushtrimi5() {
        setTitle(" Gjej Numrin");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Random random = new Random();
        numriSekret = random.nextInt(100) + 1;
        numriPjekjeve = 0;

        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));

        panel.add(new JLabel("Jepni nje numer nga 1 deri ne 100:"));

        textField = new JTextField();
        panel.add(textField);

        JButton btnProvo = new JButton("Provo");
        panel.add(btnProvo);

        labelFeedback = new JLabel("Provoni te gjeni numrin!");
        labelFeedback.setHorizontalAlignment(JLabel.CENTER);
        panel.add(labelFeedback);

        labelPjekjeve = new JLabel("Numri i pjekjeve: 0");
        labelPjekjeve.setHorizontalAlignment(JLabel.CENTER);
        panel.add(labelPjekjeve);

        JButton btnLojeRe = new JButton("Loje e re");
        panel.add(btnLojeRe);

        add(panel);

        btnProvo.addActionListener(e -> provo());

        btnLojeRe.addActionListener(e -> {
            numriSekret = random.nextInt(100) + 1;
            numriPjekjeve = 0;
            textField.setText("");
            labelFeedback.setText("Provoni te gjeni numrin!");
            labelPjekjeve.setText("Numri i pjekjeve: 0");
        });

        setVisible(true);
    }

    private void provo() {
        String input = textField.getText().trim();

        try {
            int numri = Integer.parseInt(input);

            if (numri < 1 || numri > 100) {
                JOptionPane.showMessageDialog(this, "Ju lutem vendosni nje numer nga 1 deri ne 100!", "Gabim", JOptionPane.ERROR_MESSAGE);
                return;
            }

            numriPjekjeve++;
            labelPjekjeve.setText("Numri i pjekjeve: " + numriPjekjeve);

            if (numri < numriSekret) {
                labelFeedback.setText("Shume i vogel! Provoni perseri.");
            } else if (numri > numriSekret) {
                labelFeedback.setText("Shume i madh! Provoni perseri.");
            } else {
                JOptionPane.showMessageDialog(this, "Urime! E gjetet numrin " + numriSekret + " ne " + numriPjekjeve + " pjekje!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                labelFeedback.setText("Bedava hahah "); //akulloret me fitim
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ju lutem vendosni nje numer te vlefshem ", "Gabim", JOptionPane.ERROR_MESSAGE);
        }
    }
}
