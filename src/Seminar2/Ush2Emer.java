package Seminar2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ush2Emer {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Programi Im");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JTextField textField = new JTextField(15);
        JButton button = new JButton("Afisho");
        JLabel label = new JLabel("Emri do te shfaqet ketu");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emri = textField.getText();
                label.setText("Pershendetje, " + emri + "!");
            }
        });

        panel.add(textField);
        panel.add(button);
        panel.add(label);

        frame.add(panel);
        frame.setVisible(true);
    }
}