package Seminar2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ush3Numeruesi {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Programi me Verifikim");
        frame.setSize(350, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Vlera:");
        JTextField textField = new JTextField("0", 10);
        JButton button = new JButton("Rrit +1");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numri = Integer.parseInt(textField.getText());
                    numri++;
                    textField.setText(String.valueOf(numri));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "GABIM: Ju lutem vendosni vetem numra!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(label);
        frame.add(textField);
        frame.add(button);

        frame.setVisible(true);
    }
}