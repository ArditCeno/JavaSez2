package Leksione;
//merni brinjet nga perdoruesi, beni kontrollet nese (a+b)>c, (a+c)>b qe tregon qe eeshte trekendesh. Gjeni perimetrin P=(a+b+c) dhe syprinen S=sqrt p(p-a)(p-b)(p-c) ku p eshte p=(a+b+c)/2=
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Trekendeshi2 extends JFrame {

    private JTextField txtA, txtB, txtC;
    private JLabel lblResult;

    public Trekendeshi2() {
        setTitle("Llogaritjet e trekendeshit");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("Brinja a  "));
        txtA = new JTextField();
        add(txtA);

        add(new JLabel("Brinja b  "));
        txtB = new JTextField();
        add(txtB);

        add(new JLabel("Brinja c  "));
        txtC = new JTextField();
        add(txtC);

        JButton btnPerimetri = new JButton("Perimetri \n P=(a+b+c)");
        JButton btnSyprina = new JButton("Syprina ");

        btnPerimetri.addActionListener((ActionEvent e) -> {
            llogaritPerimetrin();
        });

        btnSyprina.addActionListener((ActionEvent e) -> {
            llogaritSyprinen();
        });

        add(btnPerimetri);
        add(btnSyprina);

        lblResult = new JLabel("", SwingConstants.CENTER);
        lblResult.setFont(new Font("Arial", Font.PLAIN, 14));
        add(new JLabel());
        add(lblResult);

        setVisible(true);
    }

    private boolean trekendeshIVlefshem(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            JOptionPane.showMessageDialog(this, "duhet te jene positive ", "Gabim", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if ((a + b) <= c) {
            JOptionPane.showMessageDialog(this, "Nuk eshte trekendesh \n(a+b) <= c\n" + a + " + " + b + " = " + (a + b) + " <= " + c, "Gabim", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if ((a + c) <= b) {
            JOptionPane.showMessageDialog(this, "Nuk eshte trekendesh \n(a+c) <= b\n" + a + " + " + c + " = " + (a + c) + " <= " + b, "Gabim", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if ((b + c) <= a) {
            JOptionPane.showMessageDialog(this, "Nuk eshte trekendesh \n(b+c) <= a\n" + b + " + " + c + " = " + (b + c) + " <= " + a, "Gabim", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void llogaritPerimetrin() {
        try {
            double a = Double.parseDouble(txtA.getText());
            double b = Double.parseDouble(txtB.getText());
            double c = Double.parseDouble(txtC.getText());

            if (!trekendeshIVlefshem(a, b, c)) return;

            double perimeter = a + b + c;
            lblResult.setText("P = " + a + " + " + b + " + " + c + " = " + perimeter);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ju lutem vendosni numra te vlefshem!", "Gabim", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void llogaritSyprinen() {
        try {
            double a = Double.parseDouble(txtA.getText());
            double b = Double.parseDouble(txtB.getText());
            double c = Double.parseDouble(txtC.getText());

            if (!trekendeshIVlefshem(a, b, c)) return;

            double p = (a + b + c) / 2;
            double syp = Math.sqrt(p * (p - a) * (p - b) * (p - c));
            lblResult.setText("S = " + String.format("%.2f", syp));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ju lutem vendosni numra te vlefshem!", "Gabim", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Trekendeshi2());
    }
}
