//package Seminar5;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class Ush1Rrathet extends JFrame {
//    private JTextField tfN, tfR, tfD;
//    private JButton btnDraw;
//    private JPanel drawingPanel;
//    private int n;
//    private double r, d;
//
//    public Ush1Rrathet() {
//        setTitle("Rrathet e Brendashkruar");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(800, 700);
//        setLayout(new BorderLayout());
//
//        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
//        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        inputPanel.add(new JLabel("Numri i rratheve (n)"));
//        tfN = new JTextField();
//        inputPanel.add(tfN);
//
//        inputPanel.add(new JLabel("Rrezja e pare (r):"));
//        tfR = new JTextField();
//        inputPanel.add(tfR);
//
//        inputPanel.add(new JLabel("Distanca ndarese (d):"));
//        tfD = new JTextField();
//        inputPanel.add(tfD);
//
//        btnDraw = new JButton("Vizato");
//        inputPanel.add(new JLabel());
//        inputPanel.add(btnDraw);
//
//        add(inputPanel, BorderLayout.NORTH);
//
//        drawingPanel = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                Graphics2D g2d = (Graphics2D) g;
//                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//                int centerX = getWidth() / 2;
//                int centerY = getHeight() / 2;
//
//                for (int i = 0; i < n; i++) {
//                    double currentR = r - (i * d);
//                    if (currentR > 0) {
//                        g2d.drawOval(centerX - (int) currentR, centerY - (int) currentR,
//                                    (int) (currentR * 2), (int) (currentR * 2));
//                    }
//                }
//            }
//        };
//        drawingPanel.setBackground(Color.WHITE);
//        add(drawingPanel, BorderLayout.CENTER);
//
//        btnDraw.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    n = Integer.parseInt(tfN.getText());
//                    r = Double.parseDouble(tfR.getText());
//                    d = Double.parseDouble(tfD.getText());
//
//                    if (n > 0 && r > 0 && d >= 0) {
//                        drawingPanel.repaint();
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Vlera duhet te jete +");
//                    }
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(null, "Spranohet");
//                }
//            }
//        });
//
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new Ush1Rrathet();
//            }
//        });
//    }
//}
