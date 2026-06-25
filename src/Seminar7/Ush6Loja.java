package Seminar7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;

public class Ush6Loja extends JPanel implements ActionListener, KeyListener {
    private final int WIDTH = 600;
    private final int HEIGHT = 400;

    private Timer timer;
    private int basketX = 250;
    private final int basketWidth = 100;
    private final int basketHeight = 100;

    private int score = 0;
    private int lives = 5;
    private boolean isPaused = false;

    private ArrayList<Point> objects = new ArrayList<>();
    private Random rand = new Random();
    private Image basketImage;

    public Ush6Loja() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addKeyListener(this);

        try {
            basketImage = ImageIO.read(new File("koshi.png"));
        } catch (Exception e) {
            try {
                URL url = new URL("https://www.onlinetankstore.co.uk/wp-content/uploads/2024/07/SpaceBuddy-Bin-MixedRec-510x510.png");
                basketImage = ImageIO.read(url);
            } catch (Exception ex) {
                System.out.println("Imazhi nuk u ngarkua.");
            }
        }

        timer = new Timer(20, this);
        timer.start();
    }

    public static JMenuBar krijoMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuUdhëzime = new JMenu("Menu / Kontrollet");

        menuUdhëzime.add(new JMenuItem("N - Ndal lojen (Pause)"));
        menuUdhëzime.add(new JMenuItem("R - Rifillo lojen (Resume)"));
        menuUdhëzime.add(new JMenuItem("S - Stop (Mbyll lojen)"));
        menuUdhëzime.addSeparator();
        menuUdhëzime.add(new JMenuItem("Levizja: Shigjetat Majtas / Djathtas"));

        menuBar.add(menuUdhëzime);
        return menuBar;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (basketImage != null) {
            g.drawImage(basketImage, basketX, HEIGHT - basketHeight - 10, basketWidth, basketHeight, this);
        } else {
            g.setColor(Color.GREEN);
            g.fillRoundRect(basketX, HEIGHT - 50, basketWidth, 40, 15, 15);
        }

        for (Point p : objects) {
            g2d.setPaint(new GradientPaint(p.x, p.y, Color.ORANGE, p.x + 20, p.y + 20, Color.RED));
            g2d.fillOval(p.x, p.y, 25, 25);
            g2d.setColor(Color.DARK_GRAY);
            g2d.drawOval(p.x, p.y, 25, 25);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Piket: " + score, 10, 20);
        g.drawString("Jetet: " + lives, 10, 40);

        if (lives <= 0) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", WIDTH / 2 - 120, HEIGHT / 2);
            timer.stop();
        }

        if (isPaused) {
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            g.drawString("Pauze", WIDTH / 2 - 40, HEIGHT / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isPaused && lives > 0) {
            if (rand.nextInt(100) < 3) {
                objects.add(new Point(rand.nextInt(WIDTH - 30), 0));
            }
            for (int i = 0; i < objects.size(); i++) {
                Point p = objects.get(i);
                p.y += 5;

                Rectangle basketHitbox = new Rectangle(basketX, HEIGHT - basketHeight - 10, basketWidth, 30);
                Rectangle objectHitbox = new Rectangle(p.x, p.y, 25, 25);

                if (basketHitbox.intersects(objectHitbox)) {
                    score++;
                    objects.remove(i);
                    i--;
                } else if (p.y > HEIGHT) {
                    lives--;
                    objects.remove(i);
                    i--;
                }
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT && basketX > 0) basketX -= 30;
        if (key == KeyEvent.VK_RIGHT && basketX < WIDTH - basketWidth) basketX += 30;
        if (key == KeyEvent.VK_N) isPaused = true;
        if (key == KeyEvent.VK_R) isPaused = false;
        if (key == KeyEvent.VK_S) System.exit(0);
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Loja me Menu");
        Ush6Loja gamePanel = new Ush6Loja();

        frame.setJMenuBar(krijoMenu());

        frame.add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}