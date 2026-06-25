package Seminar2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ush2Menyra2 extends JFrame implements ActionListener {

    private JTextField textField;
    private JLabel label;
    private JButton button;

    public Ush2Menyra2() {
        setTitle("Emer panel");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        textField = new JTextField(15);
        button = new JButton("Afisho");
        label = new JLabel("Emri do te shfaqet ketu");

        button.addActionListener(this);

        add(textField);
        add(button);
        add(label);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String emri = textField.getText();
        label.setText("Pershendetje, " + emri + "!");
    }

    public static void main(String[] args) {
        new Ush2Menyra2();
    }
}