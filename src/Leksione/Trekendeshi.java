//package Leksione;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class Trekendeshi extends JFrame implements ActionListener {
//
//    private JTextField txtA, txtB, txtC;
//    private JLabel lblResult;
//
//    public Trekendeshi() {
//        setTitle("Llogaritjet e trekendeshit");
//        setSize(400, 350);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new GridLayout(6, 2, 5, 5));
//
//        add(new JLabel("Brinja a  "));
//        txtA = new JTextField();
//        add(txtA);
//
//        add(new JLabel("Brinja b  "));
//        txtB = new JTextField();
//        add(txtB);
//
//        add(new JLabel("Brinja c  "));
//        txtC = new JTextField();
//        add(txtC);
//
//        JButton btnPerimetri = new JButton("Perimetri");
//        btnPerimetri.addActionListener(this);
//        add(btnPerimetri);
//
//        JButton btnSyprina = new JButton("Syprina");
//        btnSyprina.addActionListener(this);
//        add(btnSyprina);
//
//        lblResult = new JLabel("", SwingConstants.CENTER);
//        lblResult.setFont(new Font("Arial", Font.PLAIN, 14));
//        add(new JLabel());
//        add(lblResult);
//
//        setVisible(true);
//    }
//
//    private boolean trekendeshIVlefshem(double a, double b, double c) {
//        if (a <= 0 || b <= 0 || c <= 0) {
//            JOptionPane.showMessageDialog(this, "Te gjitha brinjet duhet te jene positive ", "Gabim", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        if ((a + b) <= c) {
//            JOptionPane.showMessageDialog(this, "Nuk eshte trekendesh \n(a+b) <= c\n" + a + " + " + b + " = " + (a + b) + " <= " + c, "Gabim", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        if ((a + c) <= b) {
//            JOptionPane.showMessageDialog(this, "Nuk eshte trekendesh \n(a+c) <= b\n" + a + " + " + c + " = " + (a + c) + " <= " + b, "Gabim", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        if ((b + c) <= a) {
//            JOptionPane.showMessageDialog(this, "Nuk eshte trekendesh \n(b+c) <= a\n" + b + " + " + c + " = " + (b + c) + " <= " + a, "Gabim", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        try {
//            double a = Double.parseDouble(txtA.getText());
//            double b = Double.parseDouble(txtB.getText());
//            double c = Double.parseDouble(txtC.getText());
//
//            if (!trekendeshIVlefshem(a, b, c)) return;
//
//            String cmd = e.getActionCommand();
//
//            if (cmd.equals("Perimetri")) {
//                double perimeter = a + b + c;
//                lblResult.setText("P = " + a + " + " + b + " + " + c + " = " + perimeter);
//            } else if (cmd.equals("Syprina")) {
//                double s = (a + b + c) / 2;
//                double syp = Math.sqrt(s * (s - a) * (s - b) * (s - c));
//                lblResult.setText("S = " + String.format("%.2f", syp));
//            }
//        } catch (NumberFormatException ex) {
//            JOptionPane.showMessageDialog(this, "Ju lutem vendosni numra te vlefshem!", "Gabim", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new Trekendeshi());
//    }
//}
