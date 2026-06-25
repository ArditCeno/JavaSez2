//package PerseritjeArdit;
////ndertoni nje nderfaqe qe shfaq nje mesazh sa here klikohet nje buton
////ush2 krijo 2 butona u ndryshua pjesa e butonave nga btnKliko ne btnKlikoplus dhe btnKlikominus
////ndryshohet dhe pjesa e mesazzhit dhe gjithashtu dyfishohet  lnlMesazh
//
//
//import javax.swing.*;
//public class JavaLeksion10mars extends JFrame {
//    private JPanel pnlShembull1;//panel
//    private JButton btnKlikoplus;
//    private JButton btnKlikominus;
//    private JLabel lblMesazh;
//
//
//    private static final int FRAME_WIDTH = 300;
//    private static final int FRAME_HEIGHT = 200;
//
//    public JavaLeksion10mars() {
//        setTitle("Shembull 2");
//        setSize(FRAME_WIDTH, FRAME_HEIGHT);
//        shtoKomponente();
//    }
//
//    private void shtoKomponente() {
//        pnlShembull1 = new JPanel();
//        btnKlikoplus = new JButton("Klikoni plus!");
//        lblMesazh = new JLabel("Nderfaqe me nje buton dhe nje label.");
//        btnKlikominus = new JButton("Klikoni minus!");
//        lblMesazh = new JLabel("Nderfaqe me nje buton dhe nje label.");
//
//        //shtojme komponentet ne panel
//        pnlShembull1.add(btnKlikoplus);
//        pnlShembull1.add(btnKlikominus);
//        pnlShembull1.add(lblMesazh);
//        add(pnlShembull1);
//    }
//
//
//    public static void main(String[] args) {
//        JFrame frame = new JavaLeksion10mars();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
//
//        frame.setVisible(true);
//    }
//}