//package PerseritjeArdit;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class JavaLeksionush2 extends JFrame {
//
//    private JPanel pnlShembull1;
//    private JButton btnKlikoplus;
//    private JButton btnKlikominus;
//    private JLabel lblMesazh;
//    private JLabel Rez;
//    private JTextField tfNumer1;
//    private JTextField tfNumer2;
//
//    private static final int FRAME_WIDTH = 400;
//    private static final int FRAME_HEIGHT = 600;
//
//    public JavaLeksionush2() {
//        setTitle("Shembull 2");
//        setSize(FRAME_WIDTH, FRAME_HEIGHT);
//        shtoKomponente();
//    }
//
//    private void shtoKomponente() {
//
//        pnlShembull1 = new JPanel();
//
//        btnKlikoplus = new JButton("Plus");
//        btnKlikominus = new JButton("Minus");
//
//        lblMesazh = new JLabel("Shkruani dy numra:");
//
//        tfNumer1 = new JTextField(10);
//        tfNumer2 = new JTextField(10);
//
//        Rez = new JLabel("Rezultati: ");
//
//        pnlShembull1.add(lblMesazh);
//        pnlShembull1.add(tfNumer1);
//        pnlShembull1.add(tfNumer2);
//        pnlShembull1.add(btnKlikoplus);
//        pnlShembull1.add(btnKlikominus);
//        pnlShembull1.add(Rez);
//
//        add(pnlShembull1);
//
//        btnKlikoplus.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                int nr1 = Integer.parseInt(tfNumer1.getText());
//                int nr2 = Integer.parseInt(tfNumer2.getText());
//                int rezultat = nr1 + nr2;
//                Rez.setText("Rezultati: " + rezultat);
//            }
//        });
//
//        btnKlikominus.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                int nr1 = Integer.parseInt(tfNumer1.getText());
//                int nr2 = Integer.parseInt(tfNumer2.getText());
//                int rezultat = nr1 - nr2;
//                Rez.setText("Rezultati: " + rezultat);
//            }
//        });
//    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JavaLeksionush2();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
//        frame.setVisible(true);
//    }
//}