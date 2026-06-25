package Seminar2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ush4Calculator extends JFrame {

    private JTextField textField1;
    private JTextField textField2;
    private double shumaTotale = 0.0;

    public Ush4Calculator() {
        setTitle("Calculator");
        setSize(350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2, 10, 10));
        setLocationRelativeTo(null);

        JLabel label1 = new JLabel(" Ver vleren");
        textField1 = new JTextField();

        JLabel label2 = new JLabel(" SHuma");
        textField2 = new JTextField("0.0");
        textField2.setEditable(false);

        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double VleraRe = Double.parseDouble(textField1.getText());

                    shumaTotale += VleraRe;

                    textField2.setText(String.valueOf(shumaTotale));

                    textField1.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Ver vler te vlefshme",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(label1);
        add(textField1);
        add(label2);
        add(textField2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ush4Calculator().setVisible(true);
            }
        });
    }
}