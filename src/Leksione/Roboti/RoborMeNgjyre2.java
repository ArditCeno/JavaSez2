//package Leksione;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//public class RobotMeNgjyre extends JPanel implements ActionListener {
//    private int yKapele = 100;
//    private int drejtimiKapele = -2;
//    private boolean poNgrihet = true;
//    public RobotMeNgjyre() {
//        Timer timer = new Timer(30, this);
//        timer.start();
//    }
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        // Sfondi
//        this.setBackground(new Color(230, 240, 250));
//        // 1. KOKA
//        g.setColor(new Color(192, 192, 192)); // Silver
//        g.fillRect(250, 100, 100, 80);
//        g.setColor(Color.BLACK);
//        g.drawRect(250, 100, 100, 80);
//        // Veshët
//        g.setColor(new Color(105, 105, 105)); // DimGray
//        g.fillRect(235, 120, 15, 30);
//        g.fillRect(350, 120, 15, 30);
//        // Sytë
//        g.setColor(Color.WHITE);
//        g.fillOval(270, 120, 18, 18);
//        g.fillOval(312, 120, 18, 18);
//        g.setColor(new Color(0, 102, 204)); // Blu elektrike
//        g.fillOval(275, 125, 8, 8);
//        g.fillOval(317, 125, 8, 8);
//        // Hunda (Trekëndësh)
//        g.setColor(new Color(255, 140, 0)); // DarkOrange
//        int[] xHunda = {300, 288, 312};
//        int[] yHunda = {140, 162, 162};
//        g.fillPolygon(xHunda, yHunda, 3);
//        // Goja
//        g.setColor(new Color(204, 0, 0));
//        g.fillRoundRect(285, 168, 30, 6, 2, 2);
//        // 2. KAPELJA (Lëvizëse)
//        g.setColor(new Color(198, 10, 10));
//        int[] xKapele = {250, 300, 350};
//        int[] yKapeleTrekendesh = {yKapele, yKapele - 55, yKapele};
//        g.fillPolygon(xKapele, yKapeleTrekendesh, 3);
//        g.setColor(Color.YELLOW);
//        g.setFont(new Font("Segoe UI Symbol", Font.BOLD, 22));
//        g.setColor(Color.YELLOW);
//        g.drawString("★", 290, yKapele - 18);
//        // 3. TRUPI
//        g.setColor(new Color(70, 130, 180)); // SteelBlue
//        g.fillRect(200, 180, 200, 200);
//        g.setColor(Color.BLACK);
//        g.drawRect(200, 180, 200, 200);
//        // Butonat rrethorë
//        g.setColor(Color.BLACK);
//        g.fillOval(275, 200, 50, 50);
//        g.setColor(new Color(0, 0, 0)); // LimeGreen
//        g.fillOval(275, 260, 50, 50);
//        g.setColor(Color.RED);
//        g.fillOval(275, 320, 50, 50);
//        // Trekëndëshat anash butonave
//        g.setColor(Color.PINK);
//        g.fillPolygon(new int[]{230, 265, 265}, new int[]{300, 275, 325}, 3);
//        g.fillPolygon(new int[]{370, 335, 335}, new int[]{300, 275, 325}, 3);
//        // 4. KRAHËT
//        // Krahu i majtë
//        g.setColor(new Color(216, 201, 201));
//        g.fillRect(140, 180, 60, 40);
//        g.fillRect(140, 220, 40, 140);
//        g.setColor(new Color(112, 76, 76));
//        g.fillOval(135, 355, 50, 50);
//        // Krahu i djathtë (Lëviz me kapelen)
//        g.setColor(new Color(189, 186, 186));
//        g.fillRect(400, 180, 60, 40); // Supi
//        g.fillRect(420, yKapele, 40, 180 - yKapele + 40); // Krahu që zgjatet
//        g.setColor(new Color(97, 61, 61));
//        g.fillOval(415, yKapele - 25, 50, 50); // Dora
//        // 5. KËMBËT
//        g.setColor(new Color(36, 159, 36, 255));
//        g.fillRect(240, 380, 40, 170);
//        g.fillRect(320, 380, 40, 170);
//        // Shputat
//        g.setColor(Color.BLACK);
//        g.fillRoundRect(210, 550, 75, 25, 10, 10);
//        g.fillRoundRect(315, 550, 75, 25, 10, 10);
//    }
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (poNgrihet) {
//            yKapele += drejtimiKapele;
//            if (yKapele <= 35) {
//                poNgrihet = false;
//                drejtimiKapele = 2;
//            }
//        } else {
//            yKapele += drejtimiKapele;
//            if (yKapele >= 100) {
//                poNgrihet = true;
//                drejtimiKapele = -2;
//            }
//        }
//        repaint();
//    }
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("RobotMeNgjyre - Animacion");
//        RobotMeNgjyre panel = new RobotMeNgjyre();
//        frame.add(panel);
//        frame.setSize(650, 750);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
//}