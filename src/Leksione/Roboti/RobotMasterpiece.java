//package Leksione;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.geom.*;
//import java.util.ArrayList;
//import java.util.Random;
//
//public class RobotMasterpiece extends JPanel implements ActionListener {
//
//    private int time = 0;
//    private double walkX = -100;
//    private String state = "walk"; // walk, fly
//    private ArrayList<Particle> particles = new ArrayList<>();
//    private Random rand = new Random();
//
//    public RobotMasterpiece() {
//        Timer timer = new Timer(30, this);
//        timer.start();
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        // 1. Sfondi me Gradient dhe Yje
//        drawCinematicBackground(g2);
//
//        int robotX = (int) walkX;
//        int robotY = 400;
//
//        // 2. Logjika e Fluturimit
//        if (state.equals("fly")) {
//            robotY = 200 + (int) (Math.sin(time * 0.1) * 30);
//            createThrusterParticles(robotX, robotY + 180);
//        }
//
//        // 3. Vizatimi i Robotit tënd Origjinal
//        drawYourRobot(g2, robotX, robotY);
//
//        // 4. Grimcat dhe UI
//        updateAndDrawParticles(g2);
//        drawUIOverlay(g2);
//    }
//
//    private void drawYourRobot(Graphics2D g2, int x, int y) {
//        double bob = Math.sin(time * 0.2) * 5; // Lëkundja e trupit
//        double swing = Math.sin(time * 0.15) * 25; // Lëvizja e gjymtyrëve
//
//        // --- KOKA (Dizajni yt fiks) ---
//        int headY = y - 100 + (int)bob;
//        g2.setColor(Color.WHITE);
//        g2.fillOval(x - 40, headY, 80, 85);
//        g2.setColor(Color.BLACK);
//        g2.setStroke(new BasicStroke(2));
//        g2.drawOval(x - 40, headY, 80, 85);
//
//        // Veshët dhe Sytë Neon
//        g2.drawArc(x - 50, headY + 15, 20, 50, 90, 180);
//        g2.drawArc(x + 30, headY + 15, 20, 50, 270, 180);
//        drawNeonEyes(g2, x, headY + 35);
//
//        // --- TORSO (Me Yllin dhe C3) ---
//        Path2D torso = new Path2D.Double();
//        int torsoY = y - 15 + (int)bob;
//        torso.moveTo(x - 70, torsoY);
//        torso.curveTo(x - 90, torsoY + 20, x - 80, torsoY + 100, x, torsoY + 120);
//        torso.curveTo(x + 80, torsoY + 100, x + 90, torsoY + 20, x + 70, torsoY);
//        torso.closePath();
//
//        g2.setColor(new Color(240, 240, 245));
//        g2.fill(torso);
//        g2.setColor(Color.BLACK);
//        g2.draw(torso);
//
//        // Detajet: C3 dhe Ylli i Kuq
//        g2.drawRect(x - 50, torsoY + 25, 30, 30);
//        g2.drawString("C3", x - 42, torsoY + 45);
//        g2.setColor(Color.RED);
//        g2.fillOval(x + 20, torsoY + 25, 20, 20); // Vendndodhja e zemrës
//        g2.setColor(Color.WHITE);
//        g2.drawString("★", x + 24, torsoY + 40);
//
//        // --- KËMBËT (Animacioni) ---
//        g2.setColor(Color.BLACK);
//        if (state.equals("walk")) {
//            // Këmba 1
//            g2.drawLine(x - 30, torsoY + 110, x - 40 + (int)swing, y + 180);
//            // Këmba 2
//            g2.drawLine(x + 30, torsoY + 110, x + 40 - (int)swing, y + 180);
//        } else {
//            // Pozicioni i fluturimit
//            g2.drawLine(x - 25, torsoY + 110, x - 20, y + 170);
//            g2.drawLine(x + 25, torsoY + 110, x + 20, y + 170);
//        }
//    }
//
//    private void drawNeonEyes(Graphics2D g2, int x, int y) {
//        float pulse = (float)(Math.sin(time * 0.2) + 1) / 2;
//        g2.setPaint(new RadialGradientPaint(x - 15, y, 10, new float[]{0f, 1f}, new Color[]{Color.CYAN, new Color(0,0,0,0)}));
//        g2.fillOval(x - 22, y - 7, 15, 15);
//        g2.setPaint(new RadialGradientPaint(x + 15, y, 10, new float[]{0f, 1f}, new Color[]{Color.CYAN, new Color(0,0,0,0)}));
//        g2.fillOval(x + 7, y - 7, 15, 15);
//    }
//
//    private void drawCinematicBackground(Graphics2D g2) {
//        GradientPaint sky = new GradientPaint(0, 0, new Color(10, 15, 30), 0, getHeight(), new Color(40, 50, 90));
//        g2.setPaint(sky);
//        g2.fillRect(0, 0, getWidth(), getHeight());
//
//        g2.setColor(new Color(255, 255, 255, 150));
//        for(int i=0; i<30; i++) {
//            int sx = (i * 123) % getWidth();
//            int sy = (i * 77) % 400;
//            g2.fillOval(sx, sy, 2, 2);
//        }
//    }
//
//    private void createThrusterParticles(int x, int y) {
//        for(int i=0; i<5; i++) {
//            particles.add(new Particle(x - 20, y, new Color(255, 100, 0)));
//            particles.add(new Particle(x + 20, y, new Color(255, 200, 50)));
//        }
//    }
//
//    private void updateAndDrawParticles(Graphics2D g2) {
//        for (int i = particles.size() - 1; i >= 0; i--) {
//            Particle p = particles.get(i);
//            p.update();
//            p.draw(g2);
//            if (p.life <= 0) particles.remove(i);
//        }
//    }
//
//    private void drawUIOverlay(Graphics2D g2) {
//        g2.setColor(Color.CYAN);
//        g2.drawRect(20, 20, 150, 40);
//        g2.drawString("STATUS: " + state.toUpperCase(), 30, 45);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        time++;
//        if (state.equals("walk")) {
//            walkX += 3;
//            if (walkX > 350) state = "fly";
//        } else if (state.equals("fly")) {
//            walkX += 5;
//            if (walkX > 900) {
//                walkX = -150;
//                state = "walk";
//            }
//        }
//        repaint();
//    }
//
//    class Particle {
//        double x, y, vx, vy;
//        int life = 15;
//        Color col;
//        Particle(int x, int y, Color c) {
//            this.x = x; this.y = y;
//            this.vx = (rand.nextDouble() - 0.5) * 3;
//            this.vy = rand.nextDouble() * 4 + 2;
//            this.col = c;
//        }
//        void update() { x += vx; y += vy; life--; }
//        void draw(Graphics2D g2) {
//            g2.setColor(new Color(col.getRed(), col.getGreen(), col.getBlue(), life * 15));
//            g2.fillOval((int)x, (int)y, life/2, life/2);
//        }
//    }
//
//    public static void main(String[] args) {
//        JFrame f = new JFrame("Robot Masterpiece V2");
//        f.add(new RobotMasterpiece());
//        f.setSize(800, 700);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setLocationRelativeTo(null);
//        f.setVisible(true);
//    }
//}