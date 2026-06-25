package Seminar5;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Ush2MickyMouse extends JFrame {

    public Ush2MickyMouse() {
        setTitle("Mickey Mouse - Golden Ratio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);
        setResizable(false);

        add(new MickeyPanel());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class MickeyPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Lëshojmë antialiasing për vija super të lëmuara
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int cx = getWidth() / 2;
            int cy = getHeight() / 2 + 30;

            // --- 1. VESHËT (Pozicionuar në këndin 45 gradë) ---
            g2d.setColor(Color.BLACK);
            int earD = 120; // Diametri i veshit
            // Veshi i majtë
            g2d.fillOval(cx - 165, cy - 175, earD, earD);
            // Veshi i djathtë
            g2d.fillOval(cx + 55, cy - 175, earD, earD);

            // --- 2. KOKA (Rrethi i zi bazë) ---
            int headD = 210;
            g2d.fillOval(cx - headD/2, cy - headD/2, headD, headD);

            // --- 3. MASKA E FYTYRËS (Forma e zemrës) ---
            g2d.setColor(new Color(255, 229, 204)); // Ngjyrë lëkure e saktë

            // Balli (dy rrathë që bashkohen në mes)
            g2d.fillOval(cx - 85, cy - 55, 90, 110); // Majtas
            g2d.fillOval(cx - 5, cy - 55, 90, 110);  // Djathtas

            // Faqet dhe Mjekra (një rreth më i madh poshtë)
            g2d.fillOval(cx - 80, cy + 5, 160, 100);

            // --- 4. SYTË (Ovalë vertikalë) ---
            g2d.setColor(Color.WHITE);
            int eyeW = 35;
            int eyeH = 65;
            g2d.fillOval(cx - 38, cy - 15, eyeW, eyeH); // Syri majtas
            g2d.fillOval(cx + 3, cy - 15, eyeW, eyeH);  // Syri djathtas

            // Bebet e syve (të ngjitura pas vijës së poshtme)
            g2d.setColor(Color.BLACK);
            g2d.fillOval(cx - 24, cy + 25, 14, 25);
            g2d.fillOval(cx + 10, cy + 25, 14, 25);

            // --- 5. HUNDA (Ovale horizontale mbi gojën) ---
            g2d.fillOval(cx - 25, cy + 50, 50, 28);

            // Shkëlqimi mbi hundë (një pikë e vogël e bardhë që e bën "3D")
            g2d.setColor(new Color(255, 255, 255, 150));
            g2d.fillOval(cx - 10, cy + 68, 12, 6);

            // --- 6. GOJA (Buzëqeshja karakteristike) ---
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            // Vija e madhe e buzëqeshjes
            g2d.drawArc(cx - 80, cy + 35, 160, 80, 200, 140);

            // Gropëzat anash (vijat e shkurtra të faqeve)
            g2d.drawArc(cx - 85, cy + 65, 25, 20, 50, 160);
            g2d.drawArc(cx + 60, cy + 65, 25, 20, 130, 160);

            // --- 7. BRENDIA E GOJËS (Nëse dëshiron gojën hapur) ---
            // Mund ta lësh kështu për një buzëqeshje të thjeshtë,
            // ose të shtosh harkun e gjuhës si më poshtë:
            g2d.setColor(new Color(255, 102, 102)); // Ngjyrë rozë/kuqe
            g2d.fillArc(cx - 25, cy + 95, 50, 25, 0, -180);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Ush2MickyMouse::new);
    }
}