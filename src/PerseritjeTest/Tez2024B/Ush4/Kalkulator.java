package PerseritjeTest.Tez2024B.Ush4;
/*Ndërtoni një aplikacion grafik që ndërton një makinë të thjeshtë llogaritëse.
Ndërfaqja ka dy fusha teksti ku përdoruesi mund të fusë dy numra, katër butona, të cilët përdoruesi
mund t'i klikojë për të shtuar, zbritur, shumëzuar apo pjesëtuar dy numrat dhe një JLabel për të paraqitur rezultatin e operacionit.
Një ndërfaqje e mundshme e aplikacionit është paraqitur në figurë.
 */
import javax.swing.*;
import java.awt.*;

public class Kalkulator extends JFrame {

    JTextField x = new JTextField(10);
    JTextField y = new JTextField(10);
    JLabel rez = new JLabel();

    Kalkulator() {
        JButton b1 = new JButton("+");
        JButton b2 = new JButton("-");
        JButton b3 = new JButton("*");
        JButton b4 = new JButton("/");

        setLayout(new FlowLayout());
        add(x);
        add(y);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(rez);

        b1.addActionListener(e -> calc('+'));
        b2.addActionListener(e -> calc('-'));
        b3.addActionListener(e -> calc('*'));
        b4.addActionListener(e -> calc('/'));

        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void calc(char op) {
        try {
            double a = Double.parseDouble(x.getText());
            double b = Double.parseDouble(y.getText());
            double r;

            switch (op) {
                case '+': r = a + b; break;
                case '-': r = a - b; break;
                case '*': r = a * b; break;
                case '/':
                    if (b == 0) {
                        rez.setText("Nuk pjestohet me 0");
                        return;
                    }
                    r = a / b;
                    break;
                default:
                    rez.setText("Operacion i panjohur");
                    return;
            }

            rez.setText(String.valueOf(r));
        } catch (NumberFormatException e) {
            rez.setText("Vlera invalide");
        }
    }

    public static void main(String[] args) {
        new Kalkulator();
    }
}
