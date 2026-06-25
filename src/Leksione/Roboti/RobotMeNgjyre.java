package Leksione.Roboti;

import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

public class RobotMeNgjyre extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    private int time = 0;
    private int walkX = -100;
    private int walkPhase = 0;
    private int flyPhase = 0;
    private int wavePhase = 0;
    private int dancePhase = 0;
    private int titlePhase = 0;
    private int explosionParticles = 0;
    private int celebrationTimer = 0;
    private int powerLevel = 100;
    private int energyShield = 0;
    private int mouseX = 325, mouseY = 350;
    private float cameraShake = 0;

    private String state = "walk";
    private String prevState = "walk";

    private boolean isDragging = false;
    private boolean showParticles = true;
    private boolean musicEnabled = true;
    private boolean debugMode = false;
    private boolean slowMotion = false;
    private boolean invincibility = false;
    private boolean ultraMode = false;
    private boolean showCombo = false;
    private int comboCount = 0;
    private long lastClickTime = 0;

    private BufferedImage buffer;
    private Graphics2D bufferG2d;
    private ParticleSystem particles;
    private TrailSystem trails;
    private StarField stars;
    private LightningSystem lightning;
    private ConfettiSystem confetti;
    private PowerBar powerBar;
    private RobotBuddy buddy;
    private MissionObjective mission;
    private JPanel controlPanel;
    private JButton walkBtn, waveBtn, flyBtn, danceBtn, ultraBtn, slowMoBtn;
    private JButton musicBtn, particlesBtn, shieldBtn, soundBtn;
    private JLabel stateLabel;

    private static final Color CYAN = new Color(0, 206, 209);
    private static final Color DARK_METAL = new Color(70, 70, 80);
    private static final Color ORANGE = new Color(255, 140, 0);
    private static final Color GLOW_BLUE = new Color(0, 191, 255);
    private static final Color GLOW_GREEN = new Color(50, 255, 50);
    private static final Color RED_ACCENT = new Color(220, 20, 60);
    private static final Color SILVER = new Color(180, 180, 190);
    private static final Color LIGHT_METAL = new Color(150, 150, 160);
    private static final Color GOLD = new Color(255, 215, 0);
    private static final Color PURPLE = new Color(138, 43, 226);
    private static final Color PINK = new Color(255, 20, 147);
    private static final Color RAINBOW[] = {
        new Color(255,0,0), new Color(255,127,0), new Color(255,255,0),
        new Color(0,255,0), new Color(0,0,255), new Color(75,0,130), new Color(148,0,211)
    };

    private int fps = 0, frameCount = 0;
    private long lastFpsTime = System.currentTimeMillis();

    public RobotMeNgjyre() {
        setPreferredSize(new Dimension(1400, 900));
        setBackground(Color.BLACK);
        addMouseListener(this);
        addMouseMotionListener(this);

        particles = new ParticleSystem();
        trails = new TrailSystem();
        stars = new StarField(300);
        lightning = new LightningSystem();
        confetti = new ConfettiSystem();
        powerBar = new PowerBar();
        buddy = new RobotBuddy();
        mission = new MissionObjective();

        buffer = new BufferedImage(1400, 900, BufferedImage.TYPE_INT_ARGB);
        bufferG2d = buffer.createGraphics();

        javax.swing.Timer timer = new javax.swing.Timer(16, this);
        timer.start();

        playStartupSound();
    }

    private void playStartupSound() {
        new Thread(() -> {
            try {
                if (musicEnabled) {
                    float[] notes = {523.25f, 659.25f, 783.99f, 1046.50f};
                    for (int i = 0; i < notes.length; i++) {
                        playTone(notes[i], 0.2f);
                        Thread.sleep(200);
                    }
                }
            } catch (Exception e) {}
        }).start();
    }

    private void playTone(float freq, float duration) {
        try {
            AudioFormat af = new AudioFormat(44100, 16, 1, true, false);
            byte[] buf = new byte[(int)(44100 * duration)];
            for (int i = 0; i < buf.length; i++) {
                double angle = 2.0 * Math.PI * freq * i / 44100;
                buf[i] = (byte)(Math.sin(angle) * 127);
            }
            SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
            sdl.open(af);
            sdl.start();
            sdl.write(buf, 0, buf.length);
            sdl.drain();
            sdl.close();
        } catch (Exception e) {}
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        if (slowMotion) {
            bufferG2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        }

        applyCameraShake(bufferG2d);

        drawUltimateBackground(bufferG2d);
        stars.draw(bufferG2d);

        if (state.equals("fly")) {
            drawSpaceClouds(bufferG2d);
        }

        drawEnvironment(bufferG2d);
        trails.draw(bufferG2d);

        int baseX = 150 + walkX;
        int baseY = 500;

        if (state.equals("walk")) {
            drawRobotMasterpiece(bufferG2d, baseX, baseY, 0, false, 0);
            if (showParticles) particles.addDustParticles(baseX, baseY + 180, walkPhase);
        } else if (state.equals("wave")) {
            drawRobotMasterpiece(bufferG2d, baseX, baseY, wavePhase, true, 0);
            if (showParticles && time % 10 == 0) particles.addSparkles(baseX + 100, baseY - 50);
        } else if (state.equals("fly")) {
            int flyY = 350 + (int)(Math.sin(flyPhase * 0.05) * 100);
            int flyX = (int)(700 + Math.sin(flyPhase * 0.02) * 200);
            drawRobotMasterpiece(bufferG2d, flyX, flyY, 0, false, flyPhase);
            drawAdvancedJets(bufferG2d, flyX, flyY);
            if (showParticles) {
                particles.addJetParticles(flyX, flyY + 100, flyPhase);
                if (time % 5 == 0) particles.addStarTrail(flyX, flyY);
            }
        } else if (state.equals("dance")) {
            drawRobotMasterpiece(bufferG2d, 700, 500, dancePhase, false, 0);
            drawDanceEffects(bufferG2d);
            if (showParticles && time % 3 == 0) particles.addDiscoParticles(700, 400, dancePhase);
        }

        buddy.update(baseX, baseY);
        buddy.draw(bufferG2d);

        if (showParticles) {
            particles.draw(bufferG2d);
            lightning.draw(bufferG2d);
            confetti.draw(bufferG2d);
        }

        if (energyShield > 0) {
            drawEnergyShield(bufferG2d, 700, 450);
        }

        drawUltimateHUD(bufferG2d);
        drawMissionUI(bufferG2d);
        powerBar.draw(bufferG2d);

        if (ultraMode) {
            drawUltraEffects(bufferG2d);
        }

        if (cameraShake > 0) {
            g2d.drawImage(buffer, (int)(Math.random() * cameraShake - cameraShake/2),
                         (int)(Math.random() * cameraShake - cameraShake/2), null);
        } else {
            g2d.drawImage(buffer, 0, 0, null);
        }

        if (debugMode) {
            drawDebugInfo(g2d);
        }

        calculateFPS();
    }

    private void applyCameraShake(Graphics2D g2d) {
        if (cameraShake > 0) {
            g2d.translate(Math.random() * cameraShake - cameraShake/2,
                         Math.random() * cameraShake - cameraShake/2);
        }
    }

    private void drawUltimateBackground(Graphics2D g2d) {
        GradientPaint bg;
        if (state.equals("fly")) {
            bg = new GradientPaint(0, 0, new Color(5, 5, 30),
                                 0, 900, new Color(20, 5, 40));
        } else if (state.equals("dance")) {
            bg = new GradientPaint(0, 0, RAINBOW[(time/20) % 7].darker(),
                                 0, 900, RAINBOW[(time/20 + 3) % 7].darker());
        } else {
            bg = new GradientPaint(0, 0, new Color(135, 206, 250),
                                 0, 600, new Color(255, 250, 205));
        }
        g2d.setPaint(bg);
        g2d.fillRect(0, 0, 1400, 900);

        if (state.equals("fly")) {
            for (int i = 0; i < 50; i++) {
                int x = (int)((time * 2 + i * 100) % 1500) - 50;
                int y = (i * 37) % 900;
                g2d.setColor(new Color(255, 255, 255, 100));
                g2d.fillOval(x, y, 2, 2);
            }
        }
    }

    private void drawSpaceClouds(Graphics2D g2d) {
        for (int i = 0; i < 5; i++) {
            int cx = (int)((time * 0.5 + i * 300) % 1600) - 100;
            int cy = 100 + i * 150;
            float alpha = 0.1f + (float)Math.sin(time * 0.02 + i) * 0.05f;
            RadialGradientPaint cloud = new RadialGradientPaint(
                cx, cy, 80,
                new float[]{0f, 1f},
                new Color[]{new Color(200, 200, 255, (int)(alpha * 255)),
                           new Color(100, 100, 200, 0)}
            );
            g2d.setPaint(cloud);
            g2d.fillOval(cx - 80, cy - 40, 160, 80);
        }
    }

    private void drawEnvironment(Graphics2D g2d) {
        if (state.equals("fly")) return;

        for (int i = 0; i < 20; i++) {
            int layer = i % 5;
            int baseY = 650 + layer * 20;
            int offset = (time / (layer + 1)) % 100;

            Color c = new Color(34 + layer * 20, 139 + layer * 10, 34 + layer * 15);
            if (state.equals("dance")) {
                c = RAINBOW[(i + time/10) % 7];
            }

            g2d.setColor(c);
            for (int x = -offset; x < 1400; x += 50 + layer * 10) {
                int h = 30 + (int)(Math.sin(x * 0.1) * 20);
                g2d.fillRect(x, baseY - h, 30 + layer * 5, h);
            }
        }

        if (state.equals("walk")) {
            g2d.setColor(new Color(139, 90, 43));
            g2d.fillRect(0, 700, 1400, 200);

            for (int x = 0; x < 1400; x += 40) {
                int pattern = (x + walkX) % 80;
                g2d.setColor(pattern < 40 ? new Color(139, 90, 43) : new Color(120, 80, 35));
                g2d.fillRect(x, 700, 40, 10);
            }
        }
    }

    private void drawAdvancedJets(Graphics2D g2d, int robotX, int robotY) {
        for (int side = -1; side <= 1; side += 2) {
            int jetX = robotX + side * 50;
            int jetY = robotY + 100;

            for (int layer = 0; layer < 5; layer++) {
                int flicker = (int)(Math.random() * 15);
                int height = 80 + layer * 30 + flicker;
                float alpha = 1.0f - layer * 0.18f;

                Color outer = new Color(255, 100, 0, (int)(alpha * 100));
                Color inner = new Color(255, 255, 150, (int)(alpha * 255));

                GradientPaint jetGrad = new GradientPaint(
                    jetX, jetY, inner,
                    jetX, jetY + height, outer
                );
                g2d.setPaint(jetGrad);

                int[] xPoints = {jetX - 15 - layer * 3, jetX + 15 + layer * 3, jetX};
                int[] yPoints = {jetY, jetY, jetY + height};
                g2d.fillPolygon(xPoints, yPoints, 3);
            }

            RadialGradientPaint glow = new RadialGradientPaint(
                jetX, jetY, 40,
                new float[]{0f, 0.5f, 1f},
                new Color[]{Color.WHITE, new Color(255, 200, 100, 150), new Color(255, 100, 0, 0)}
            );
            g2d.setPaint(glow);
            g2d.fillOval(jetX - 30, jetY - 10, 60, 30);
        }
    }

    private void drawDanceEffects(Graphics2D g2d) {
        for (int i = 0; i < 8; i++) {
            double angle = (time * 0.1 + i * Math.PI / 4) % (Math.PI * 2);
            int x = 700 + (int)(Math.cos(angle) * 150);
            int y = 450 + (int)(Math.sin(angle) * 100);

            Color c = RAINBOW[(i + time/5) % 7];
            g2d.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), 100));
            g2d.fillOval(x - 20, y - 20, 40, 40);

            g2d.setColor(c);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawOval(x - 25, y - 25, 50, 50);
        }

        if (time % 15 == 0) {
            playTone(400 + (time % 500), 0.1f);
        }
    }

    private void drawEnergyShield(Graphics2D g2d, int x, int y) {
        int radius = 150;
        float alpha = energyShield / 100f;

        RadialGradientPaint shield = new RadialGradientPaint(
            x, y, radius,
            new float[]{0f, 0.7f, 1f},
            new Color[]{new Color(100, 200, 255, (int)(alpha * 100)),
                       new Color(50, 150, 255, (int)(alpha * 150)),
                       new Color(0, 100, 255, 0)}
        );
        g2d.setPaint(shield);
        g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);

        g2d.setColor(new Color(200, 230, 255, (int)(alpha * 200)));
        g2d.setStroke(new BasicStroke(3));
        for (int i = 0; i < 3; i++) {
            float hexAngle = (float)(time * 0.02 + i * Math.PI * 2 / 3);
            int hx = x + (int)(Math.cos(hexAngle) * radius * 0.9f);
            int hy = y + (int)(Math.sin(hexAngle) * radius * 0.9f);
            g2d.drawOval(hx - 30, hy - 30, 60, 60);
        }
    }

    private void drawUltimateHUD(Graphics2D g2d) {
        g2d.setFont(new Font("Consolas", Font.BOLD, 18));

        String title = ultraMode ? "MK-2025 ULTRA ROBOT v10.0" : "MK-2025 ROBOT";
        if (state.equals("dance")) title = "POGONISHTE MODE ACTIVATED";
        if (state.equals("fly")) title = "FLIGHT MODE ENGAGED";

        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRoundRect(490, 10, 420, 50, 15, 15);

        GradientPaint titleGrad = new GradientPaint(500, 20, CYAN, 900, 50, GLOW_BLUE);
        g2d.setPaint(titleGrad);
        g2d.drawString(title, 500, 45);

        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        g2d.setColor(Color.WHITE);
        g2d.drawString("State: " + state.toUpperCase(), 20, 30);
        g2d.drawString("Power: " + powerLevel + "%", 20, 50);
        g2d.drawString("FPS: " + fps, 20, 70);

        if (showCombo && comboCount > 1) {
            g2d.setFont(new Font("Impact", Font.BOLD, 48));
            g2d.setColor(new Color(255, 215, 0, 200));
            g2d.drawString(comboCount + "x COMBO!", 600, 200);
        }

        if (slowMotion) {
            g2d.setFont(new Font("Arial", Font.BOLD, 24));
            g2d.setColor(Color.CYAN);
            g2d.drawString("SLOW MOTION", 600, 150);
        }

        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.setColor(new Color(255, 255, 255, 150));
        g2d.drawString("Or use: Click=Dance, Right-Click=Fly, Double-Click=Ultra | Use Control Panel for full control", 350, 880);
    }

    private void drawMissionUI(Graphics2D g2d) {
        mission.update(state, time);
        mission.draw(g2d);
    }

    private void drawUltraEffects(Graphics2D g2d) {
        for (int i = 0; i < 10; i++) {
            double angle = time * 0.05 + i * Math.PI / 5;
            int x = 700 + (int)(Math.cos(angle) * 250);
            int y = 450 + (int)(Math.sin(angle) * 150);

            Color c = RAINBOW[i % 7];
            g2d.setStroke(new BasicStroke(4));
            g2d.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), 100));
            g2d.drawLine(700, 450, x, y);

            g2d.fillOval(x - 8, y - 8, 16, 16);
        }

        if (time % 30 == 0) {
            playTone(800 + (int)(Math.random() * 400), 0.15f);
        }
    }

    private void drawDebugInfo(Graphics2D g2d) {
        g2d.setFont(new Font("Monospaced", Font.PLAIN, 12));
        g2d.setColor(Color.GREEN);
        int y = 100;
        g2d.drawString("DEBUG MODE", 20, y += 15);
        g2d.drawString("Time: " + time, 20, y += 15);
        g2d.drawString("WalkX: " + walkX, 20, y += 15);
        g2d.drawString("Particles: " + particles.size(), 20, y += 15);
        g2d.drawString("Mouse: " + mouseX + ", " + mouseY, 20, y += 15);
    }

    private void calculateFPS() {
        frameCount++;
        long now = System.currentTimeMillis();
        if (now - lastFpsTime >= 1000) {
            fps = frameCount;
            frameCount = 0;
            lastFpsTime = now;
        }
    }

    private void drawRobotMasterpiece(Graphics2D g2d, int x, int y, int phase, boolean waving, int flyTime) {
        trails.addTrail(x, y - 80);

        int legSwing = (int)(Math.sin(phase * 0.3) * 25);
        int bodyBob = (int)(Math.abs(Math.sin(phase * 0.3)) * 6);
        int armSwing = (int)(Math.sin(phase * 0.3) * 20);
        float breathe = (float)(Math.sin(time * 0.05) * 2);

        if (state.equals("dance")) {
            legSwing = (int)(Math.sin(phase * 0.5) * 35);
            armSwing = (int)(Math.cos(phase * 0.4) * 40);
            bodyBob = (int)(Math.abs(Math.sin(phase * 0.5)) * 15);
        }

        if (ultraMode) {
            bodyBob += (int)(Math.sin(time * 0.3) * 5);
        }

        drawLegMasterpiece(g2d, x - 35, y - 40, -legSwing, false);
        drawLegMasterpiece(g2d, x + 35, y - 40, legSwing, true);

        if (state.equals("dance")) {
            drawNeckMasterpiece(g2d, x + (int)(Math.sin(phase * 0.3) * 5), y - 100 - bodyBob);
        } else {
            drawNeckMasterpiece(g2d, x, y - 100 - bodyBob);
        }

        if (state.equals("dance")) {
            drawHeadMasterpiece(g2d, x, y - 170 - bodyBob, true);
        } else {
            drawHeadMasterpiece(g2d, x, y - 170 - bodyBob, false);
        }
        drawAntennaMasterpiece(g2d, x, y - 230 - bodyBob);

        drawBodyMasterpiece(g2d, x, y - 90 - bodyBob + (int)breathe);

        drawArmMasterpiece(g2d, x - 85, y - 80 - bodyBob, armSwing, false);

        if (waving) {
            drawArmMasterpiece(g2d, x + 85, y - 80 - bodyBob, wavePhase, true);
        } else {
            drawArmMasterpiece(g2d, x + 85, y - 80 - bodyBob, -armSwing, false);
        }

        if (ultraMode && time % 10 < 5) {
            drawGlowOutline(g2d, x, y - 120, 180, 280);
        }
    }

    private void drawGlowOutline(Graphics2D g2d, int x, int y, int w, int h) {
        for (int i = 5; i > 0; i--) {
            g2d.setStroke(new BasicStroke(i * 2));
            g2d.setColor(new Color(255, 255, 0, 30));
            g2d.drawRoundRect(x - w/2 - i*3, y - h/2 - i*3, w + i*6, h + i*6, 30, 30);
        }
    }

    private void drawNeckMasterpiece(Graphics2D g2d, int x, int y) {
        GradientPaint neckGrad = new GradientPaint(
            x - 20, y, new Color(60, 60, 70),
            x + 20, y, new Color(140, 140, 155)
        );
        g2d.setPaint(neckGrad);
        g2d.fillRoundRect(x - 20, y, 40, 35, 8, 8);

        g2d.setColor(DARK_METAL);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(x - 20, y, 40, 35, 8, 8);

        for (int i = 0; i < 3; i++) {
            g2d.setColor(CYAN);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(x - 15 + i * 15, y + 10, x - 15 + i * 15, y + 25);
        }

        g2d.setColor(new Color(100, 100, 120, 100));
        g2d.fillRoundRect(x - 25, y + 5, 10, 10, 3, 3);
        g2d.fillRoundRect(x + 15, y + 5, 10, 10, 3, 3);
    }

    private void drawBodyMasterpiece(Graphics2D g2d, int x, int y) {
        GradientPaint bodyGrad = new GradientPaint(
            x - 70, y - 20, new Color(50, 50, 60),
            x + 70, y + 140, new Color(120, 120, 140)
        );
        g2d.setPaint(bodyGrad);

        RoundRectangle2D body = new RoundRectangle2D.Float(x - 65, y - 20, 130, 145, 20, 20);
        g2d.fill(body);

        if (ultraMode) {
            g2d.setColor(new Color(255, 255, 0, 50));
            g2d.fill(body);
        }

        g2d.setColor(new Color(40, 40, 50));
        g2d.setStroke(new BasicStroke(4));
        g2d.draw(body);

        g2d.setColor(CYAN);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(x - 55, y + 5, x + 55, y + 5);

        GradientPaint chestGrad = new GradientPaint(
            x - 50, y + 20, new Color(30, 30, 40),
            x + 50, y + 80, new Color(60, 60, 75)
        );
        g2d.setPaint(chestGrad);
        g2d.fillRoundRect(x - 50, y + 20, 100, 55, 10, 10);

        g2d.setColor(GLOW_BLUE);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(x - 48, y + 22, 96, 51, 8, 8);

        String version = ultraMode ? "ALBANIAN" : "BOBI";
        g2d.setColor(new Color(0, 206, 209, 200));
        g2d.setFont(new Font("Consolas", Font.BOLD, 14));
        g2d.drawString(version, x - 25, y + 55);

        for (int i = 0; i < 4; i++) {
            boolean on = (time / 10 + i) % 2 == 0;
            Color ledColor = on ? (i % 2 == 0 ? GLOW_GREEN : RED_ACCENT) : new Color(50, 50, 50);
            g2d.setColor(ledColor);
            g2d.fillOval(x - 30 + i * 18, y + 80, 12, 12);
            if (on) {
                g2d.setColor(new Color(ledColor.getRed(), ledColor.getGreen(), ledColor.getBlue(), 100));
                g2d.fillOval(x - 35 + i * 18, y + 75, 22, 22);
            }
        }

        g2d.setColor(ORANGE);
        g2d.fillRoundRect(x - 25, y + 100, 50, 15, 5, 5);
        g2d.setColor(Color.YELLOW);
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < 5; i++) {
            g2d.drawLine(x - 20 + i * 10, y + 100, x - 20 + i * 10, y + 115);
        }
    }

    private void drawHeadMasterpiece(Graphics2D g2d, int x, int y, boolean disco) {
        GradientPaint headGrad = new GradientPaint(
            x - 50, y - 45, SILVER,
            x + 50, y + 55, LIGHT_METAL
        );
        g2d.setPaint(headGrad);

        RoundRectangle2D head = new RoundRectangle2D.Float(x - 50, y - 45, 100, 90, 15, 15);
        g2d.fill(head);

        g2d.setColor(DARK_METAL);
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(head);

        if (disco) {
            Color headColor = RAINBOW[(time / 3) % 7];
            g2d.setColor(new Color(headColor.getRed(), headColor.getGreen(), headColor.getBlue(), 80));
            g2d.fill(head);
        }

        for (int i = 0; i < 3; i++) {
            g2d.setColor(ultraMode ? GOLD : CYAN);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(x - 40 + i * 40, y - 40, x - 40 + i * 40, y - 35);
        }

        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(x - 35, y - 15, 30, 28, 6, 6);
        g2d.fillRoundRect(x + 5, y - 15, 30, 28, 6, 6);

        Color eyeColor = disco ? RAINBOW[(time / 5) % 7] : (ultraMode ? GOLD : GLOW_BLUE);
        g2d.setColor(eyeColor);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(x - 33, y - 13, 26, 24, 5, 5);
        g2d.drawRoundRect(x + 7, y - 13, 26, 24, 5, 5);

        int pupilOffset = (int)(Math.sin(time * 0.1) * 3);
        if (ultraMode) {
            pupilOffset = (int)(Math.sin(time * 0.2) * 5);
        }

        RadialGradientPaint eyeGlow = new RadialGradientPaint(
            x - 20 + pupilOffset, y - 2, 15,
            new float[]{0f, 1f},
            new Color[]{Color.WHITE, eyeColor}
        );
        g2d.setPaint(eyeGlow);
        g2d.fillOval(x - 28, y - 8, 16, 16);
        g2d.fillOval(x + 12, y - 8, 16, 16);

        g2d.setColor(Color.WHITE);
        g2d.fillOval(x - 25 + pupilOffset, y - 5, 5, 5);
        g2d.fillOval(x + 15 + pupilOffset, y - 5, 5, 5);

        g2d.setColor(ORANGE);
        int[] noseX = {x, x - 8, x + 8};
        int[] noseY = {y + 18, y + 32, y + 32};
        g2d.fillPolygon(noseX, noseY, 3);

        int mouthY = y + 38;
        int mouthWidth = (int)(20 + Math.sin(time * 0.1) * 5);

        if (state.equals("wave")) {
            g2d.setColor(GLOW_GREEN);
            g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.drawArc(x - mouthWidth, mouthY, mouthWidth * 2, 20, 20, 140);
        } else if (disco) {
            g2d.setColor(RAINBOW[(time / 4) % 7]);
            g2d.setStroke(new BasicStroke(4));
            g2d.drawArc(x - mouthWidth, mouthY - 5, mouthWidth * 2, 25, 30, 120);
        } else {
            g2d.setColor(new Color(60, 60, 70));
            g2d.fillRoundRect(x - mouthWidth, mouthY, mouthWidth * 2, 8, 4, 4);
            g2d.setColor(RED_ACCENT);
            g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.drawArc(x - mouthWidth + 2, mouthY - 2, mouthWidth * 2 - 4, 12, 30, 120);
        }

        g2d.setColor(LIGHT_METAL);
        g2d.fillRect(x - 55, y - 5, 8, 25);
        g2d.fillRect(x + 47, y - 5, 8, 25);

        Color earGlow = ultraMode ? GOLD : CYAN;
        g2d.setColor(earGlow);
        g2d.setStroke(new BasicStroke(2));
        g2d.fillOval(x - 53, y - 8, 12, 12);
        g2d.fillOval(x + 41, y - 8, 12, 12);

        if (ultraMode) {
            g2d.setColor(new Color(255, 215, 0, 150));
            g2d.fillOval(x - 58, y - 13, 22, 22);
            g2d.fillOval(x + 36, y - 13, 22, 22);
        }
    }

    private void drawAntennaMasterpiece(Graphics2D g2d, int x, int y) {
        float wave = (float)(Math.sin(time * 0.15) * 5);

        g2d.setColor(DARK_METAL);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(x, y + 25, x + (int)wave, y - 10);

        int pulse = (int)((Math.sin(time * 0.2) + 1) * 127);
        Color antennaColor = ultraMode ?
            RAINBOW[(time / 10) % 7] : new Color(255, pulse, 0);

        for (int i = 3; i > 0; i--) {
            g2d.setColor(new Color(antennaColor.getRed(), antennaColor.getGreen(),
                                  antennaColor.getBlue(), 50 * i));
            g2d.fillOval(x + (int)wave - 5 - i*5, y - 20 - i*5, 14 + i*10, 14 + i*10);
        }

        g2d.setColor(antennaColor);
        g2d.fillOval(x + (int)wave - 7, y - 20, 14, 14);

        g2d.setColor(Color.YELLOW);
        g2d.setFont(new Font("Wingdings", Font.BOLD, 16));
        g2d.drawString("★", x + (int)wave - 5, y - 11);
    }

    private void drawArmMasterpiece(Graphics2D g2d, int x, int y, int swing, boolean waving) {
        int dir = x < 700 ? -1 : 1;

        GradientPaint armGrad = new GradientPaint(
            x - 15, y - 20, new Color(80, 80, 95),
            x + dir * 45, y, new Color(140, 140, 160)
        );
        g2d.setPaint(armGrad);
        g2d.fillRoundRect(x - 15, y - 20, 35, 60, 8, 8);

        g2d.setColor(DARK_METAL);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(x - 15, y - 20, 35, 60, 8, 8);

        g2d.setColor(ORANGE);
        g2d.fillRoundRect(x - 10, y + 35, 25, 15, 4, 4);

        int elbowX = x + swing / 2;
        int elbowY = y + 50;

        if (waving) {
            int waveAngle = (int)(Math.sin(wavePhase * 0.4) * 45);
            int armDir = x < 700 ? 1 : -1;

            g2d.fillRoundRect(x + armDir * 8, y + 30, 28, 70, 6, 6);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawRoundRect(x + armDir * 8, y + 30, 28, 70, 6, 6);

            int handX = x + armDir * 20;
            int handY = y + 90;

            GradientPaint handGrad = new GradientPaint(
                handX - 20, handY - 15, new Color(60, 60, 75),
                handX + 20, handY + 15, new Color(130, 130, 150)
            );
            g2d.setPaint(handGrad);
            Ellipse2D hand = new Ellipse2D.Float(handX - 22, handY - 15, 44, 35);
            g2d.fill(hand);

            g2d.setColor(CYAN);
            g2d.setStroke(new BasicStroke(3));
            g2d.draw(hand);

            Color fingerColor = RAINBOW[(time / 8) % 7];
            g2d.setColor(fingerColor);
            g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            for (int i = 0; i < 5; i++) {
                int fx = handX - 18 + i * 9;
                int fy = handY + 5;
                int waveOffset = (int)(Math.sin(wavePhase * 0.4 + i * 0.6) * waveAngle / 2);
                g2d.drawLine(fx, fy, fx + waveOffset, fy - 20);
            }

            g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
            Color hiColor = RAINBOW[(time / 5) % 7];
            g2d.setColor(new Color(hiColor.getRed(), hiColor.getGreen(), hiColor.getBlue(), 220));
            g2d.drawString("HI!", handX - 18, handY - 30);

            if (time % 20 == 0) {
                particles.addSparkles(handX, handY - 20);
            }

        } else {
            g2d.fillRoundRect(elbowX - 12, elbowY, 26, 85, 6, 6);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawRoundRect(elbowX - 12, elbowY, 26, 85, 6, 6);

            GradientPaint handGrad = new GradientPaint(
                elbowX - 18, elbowY + 80, new Color(60, 60, 75),
                elbowX + 18, elbowY + 100, new Color(130, 130, 150)
            );
            g2d.setPaint(handGrad);
            Ellipse2D hand = new Ellipse2D.Float(elbowX - 20, elbowY + 78, 40, 32);
            g2d.fill(hand);

            g2d.setColor(CYAN);
            g2d.setStroke(new BasicStroke(3));
            g2d.draw(hand);

            g2d.setColor(GLOW_BLUE);
            g2d.setStroke(new BasicStroke(3));
            int fingerY = elbowY + 100;
            for (int i = 0; i < 4; i++) {
                g2d.drawLine(elbowX - 14 + i * 9, fingerY + 8, elbowX - 14 + i * 9, fingerY + 22);
            }

            g2d.setColor(new Color(0, 206, 209, 120));
            int palmX = x < 700 ? elbowX + 10 : elbowX - 22;
            g2d.fillOval(palmX, elbowY + 85, 15, 15);
        }

        g2d.setColor(DARK_METAL);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(x, y + 15, x, y + 50);
    }

    private void drawLegMasterpiece(Graphics2D g2d, int x, int y, int swing, boolean isRight) {
        GradientPaint legGrad = new GradientPaint(
            x - 22, y, new Color(70, 70, 85),
            x + 22, y, new Color(130, 130, 150)
        );
        g2d.setPaint(legGrad);
        g2d.fillRoundRect(x - 22, y, 44, 95, 8, 8);

        g2d.setColor(DARK_METAL);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(x - 22, y, 44, 95, 8, 8);

        g2d.setColor(new Color(60, 60, 75));
        g2d.fillRoundRect(x - 18, y + 25, 36, 18, 5, 5);

        g2d.setColor(CYAN);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(x - 14, y + 34, x + 14, y + 34);

        int kneeX = x + swing / 2;
        int kneeY = y + 90;

        g2d.setPaint(legGrad);
        g2d.fillRoundRect(kneeX - 20, kneeY, 40, 70, 6, 6);
        g2d.setColor(DARK_METAL);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(kneeX - 20, kneeY, 40, 70, 6, 6);

        g2d.setColor(ORANGE);
        g2d.fillRoundRect(kneeX - 16, kneeY + 25, 32, 12, 4, 4);

        GradientPaint footGrad = new GradientPaint(
            kneeX - 40, kneeY + 65, new Color(50, 50, 65),
            kneeX + 40, kneeY + 80, new Color(110, 110, 130)
        );
        g2d.setPaint(footGrad);
        g2d.fillRoundRect(kneeX - 40, kneeY + 65, 80, 28, 8, 8);

        g2d.setColor(ultraMode ? GOLD : CYAN);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(kneeX - 40, kneeY + 65, 80, 28, 8, 8);

        Color ledColor = ultraMode ? GOLD : GLOW_BLUE;
        g2d.setColor(ledColor);
        g2d.fillOval(kneeX - 35, kneeY + 72, 10, 10);
        g2d.fillOval(kneeX + 25, kneeY + 72, 10, 10);

        if (ultraMode) {
            g2d.setColor(new Color(255, 215, 0, 100));
            g2d.fillOval(kneeX - 40, kneeY + 67, 20, 20);
            g2d.fillOval(kneeX + 20, kneeY + 67, 20, 20);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time++;

        int speed = slowMotion ? 4 : 1;

        if (state.equals("walk")) {
            walkPhase += speed;
            walkX += speed * 2;
            if (walkX > 800) {
                state = "wave";
                wavePhase = 0;
            }
        } else if (state.equals("wave")) {
            wavePhase += speed;
            if (wavePhase > 100) {
                state = "fly";
                flyPhase = 0;
            }
        } else if (state.equals("fly")) {
            flyPhase += speed;
            if (flyPhase > 1500) {
                state = "dance";
                dancePhase = 0;
            }
        } else if (state.equals("dance")) {
            dancePhase += speed;
            if (dancePhase > 300) {
                state = "walk";
                walkX = -100;
                walkPhase = 0;
            }
        }

        particles.update();
        trails.update();
        stars.update();
        lightning.update();
        confetti.update();
        buddy.update(time);

        if (powerLevel < 100 && time % 60 == 0) {
            powerLevel = Math.min(100, powerLevel + 1);
        }

        if (energyShield > 0) {
            energyShield = Math.max(0, energyShield - 1);
        }

        if (cameraShake > 0) {
            cameraShake *= 0.9f;
        }

        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        long now = System.currentTimeMillis();
        if (now - lastClickTime < 300) {
            ultraMode = !ultraMode;
            playTone(ultraMode ? 880 : 440, 0.3f);
            if (ultraMode) {
                for (int i = 0; i < 20; i++) {
                    confetti.addConfetti(700, 450);
                }
            }
        } else {
            state = "dance";
            dancePhase = 0;
            playTone(600 + (time % 200), 0.2f);
        }
        lastClickTime = now;
        comboCount++;
        showCombo = true;

        if (e.getY() < 100) {
            debugMode = !debugMode;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isDragging = true;
        if (e.getButton() == MouseEvent.BUTTON3) {
            prevState = state;
            state = "fly";
            flyPhase = 0;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isDragging = false;
        if (e.getButton() == MouseEvent.BUTTON3) {
            state = prevState;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    class Particle {
        float x, y, vx, vy, life, maxLife;
        Color color;
        float size;

        Particle(float x, float y, float vx, float vy, Color color, float life, float size) {
            this.x = x; this.y = y; this.vx = vx; this.vy = vy;
            this.color = color; this.life = life; this.maxLife = life; this.size = size;
        }

        void update() {
            x += vx;
            y += vy;
            vy += 0.1f;
            life--;
        }

        void draw(Graphics2D g2d) {
            float alpha = life / maxLife;
            g2d.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)(alpha * 255)));
            g2d.fillOval((int)x, (int)y, (int)size, (int)size);
        }
    }

    class ParticleSystem {
        List<Particle> particles = new CopyOnWriteArrayList<>();

        void addDustParticles(int x, int y, int phase) {
            if (time % 5 == 0) {
                particles.add(new Particle(
                    x + (float)(Math.random() - 0.5) * 60,
                    y + 180,
                    (float)(Math.random() - 0.5) * 2,
                    (float)(-Math.random() * 2 - 1),
                    new Color(139, 90, 43),
                    30, 5 + (float)Math.random() * 5
                ));
            }
        }

        void addSparkles(int x, int y) {
            for (int i = 0; i < 5; i++) {
                particles.add(new Particle(
                    x + (float)(Math.random() - 0.5) * 40,
                    y + (float)(Math.random() - 0.5) * 40,
                    (float)(Math.random() - 0.5) * 4,
                    (float)(Math.random() - 0.5) * 4,
                    CYAN,
                    40, 3 + (float)Math.random() * 3
                ));
            }
        }

        void addJetParticles(int x, int y, int phase) {
            for (int side = -1; side <= 1; side += 2) {
                particles.add(new Particle(
                    x + side * 50 + (float)(Math.random() - 0.5) * 20,
                    y + 100,
                    (float)(Math.random() - 0.5) * 3,
                    (float)(Math.random() * 5 + 3),
                    new Color(255, 150 + (int)(Math.random() * 105), 0),
                    50, 4 + (float)Math.random() * 6
                ));
            }
        }

        void addStarTrail(int x, int y) {
            particles.add(new Particle(
                x + (float)(Math.random() - 0.5) * 100,
                y + (float)(Math.random() - 0.5) * 50,
                (float)(Math.random() - 0.5) * 1,
                (float)(Math.random() * 2),
                Color.WHITE,
                60, 2 + (float)Math.random() * 2
            ));
        }

        void addDiscoParticles(int x, int y, int phase) {
            Color c = RAINBOW[(time / 5) % 7];
            particles.add(new Particle(
                x + (float)(Math.random() - 0.5) * 300,
                y + (float)(Math.random() - 0.5) * 200,
                (float)(Math.random() - 0.5) * 6,
                (float)(Math.random() - 0.5) * 6,
                c, 50, 4 + (float)Math.random() * 4
            ));
        }

        void update() {
            for (Particle p : particles) {
                p.update();
            }
            particles.removeIf(p -> p.life <= 0);
            if (particles.size() > 2000) {
                particles = new ArrayList<>(particles.subList(0, 2000));
            }
        }

        void draw(Graphics2D g2d) {
            for (Particle p : particles) {
                p.draw(g2d);
            }
        }

        int size() { return particles.size(); }
    }

    class TrailSystem {
        List<Point> trails = new CopyOnWriteArrayList<>();

        void addTrail(int x, int y) {
            if (time % 3 == 0) {
                trails.add(new Point(x, y));
                if (trails.size() > 500) trails.remove(0);
            }
        }

        void update() {
            // Trails persist
        }

        void draw(Graphics2D g2d) {
            for (int i = 0; i < trails.size(); i++) {
                Point p = trails.get(i);
                float alpha = (float)i / trails.size();
                g2d.setColor(new Color(0, 206, 209, (int)(alpha * 100)));
                g2d.fillOval(p.x - 3, p.y - 3, 6, 6);
            }
        }
    }

    class Star {
        float x, y, size, brightness;

        Star(float x, float y, float size) {
            this.x = x; this.y = y; this.size = size;
            this.brightness = (float)Math.random();
        }

        void update() {
            brightness = 0.5f + (float)(Math.sin(time * 0.05 + x * 0.01) * 0.5);
        }

        void draw(Graphics2D g2d) {
            g2d.setColor(new Color(255, 255, 255, (int)(brightness * 255)));
            g2d.fillOval((int)x, (int)y, (int)size, (int)size);
        }
    }

    class StarField {
        List<Star> stars = new ArrayList<>();

        StarField(int count) {
            for (int i = 0; i < count; i++) {
                stars.add(new Star(
                    (float)Math.random() * 1400,
                    (float)Math.random() * 900,
                    1 + (float)Math.random() * 2
                ));
            }
        }

        void update() {
            for (Star s : stars) s.update();
        }

        void draw(Graphics2D g2d) {
            for (Star s : stars) s.draw(g2d);
        }
    }

    class LightningSystem {
        List<Lightning> bolts = new ArrayList<>();

        void addLightning(int x1, int y1, int x2, int y2) {
            bolts.add(new Lightning(x1, y1, x2, y2));
        }

        void update() {
            if (time % 120 == 0 && state.equals("fly")) {
                addLightning((int)(Math.random() * 1400), 0, 700, 450);
            }
            bolts.removeIf(b -> {
                b.life--;
                return b.life <= 0;
            });
        }

        void draw(Graphics2D g2d) {
            for (Lightning b : bolts) b.draw(g2d);
        }
    }

    class Lightning {
        int x1, y1, x2, y2, life;
        List<Point> points = new ArrayList<>();

        Lightning(int x1, int y1, int x2, int y2) {
            this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2;
            this.life = 10;
            generatePath();
        }

        void generatePath() {
            points.add(new Point(x1, y1));
            int segments = 8;
            for (int i = 1; i < segments; i++) {
                float t = (float)i / segments;
                int x = (int)(x1 + (x2 - x1) * t + (Math.random() - 0.5) * 60);
                int y = (int)(y1 + (y2 - y1) * t);
                points.add(new Point(x, y));
            }
            points.add(new Point(x2, y2));
        }

        void draw(Graphics2D g2d) {
            g2d.setColor(new Color(200, 200, 255, life * 25));
            g2d.setStroke(new BasicStroke(3));
            for (int i = 0; i < points.size() - 1; i++) {
                g2d.drawLine(points.get(i).x, points.get(i).y,
                            points.get(i + 1).x, points.get(i + 1).y);
            }
        }
    }

    class ConfettiSystem {
        List<Confetti> confetti = new ArrayList<>();

        void addConfetti(int x, int y) {
            for (int i = 0; i < 10; i++) {
                confetti.add(new Confetti(
                    x + (float)(Math.random() - 0.5) * 100,
                    y + (float)(Math.random() - 0.5) * 50,
                    RAINBOW[(int)(Math.random() * 7)]
                ));
            }
        }

        void update() {
            for (Confetti c : confetti) c.update();
            confetti.removeIf(c -> c.y > 900);
        }

        void draw(Graphics2D g2d) {
            for (Confetti c : confetti) c.draw(g2d);
        }
    }

    class Confetti {
        float x, y, vx, vy, rotation, rotSpeed;
        Color color;

        Confetti(float x, float y, Color color) {
            this.x = x; this.y = y; this.color = color;
            this.vx = (float)(Math.random() - 0.5) * 8;
            this.vy = (float)(-Math.random() * 8 - 4);
            this.rotation = (float)(Math.random() * Math.PI * 2);
            this.rotSpeed = (float)((Math.random() - 0.5) * 0.3);
        }

        void update() {
            x += vx;
            y += vy;
            vy += 0.15f;
            rotation += rotSpeed;
        }

        void draw(Graphics2D g2d) {
            g2d.setColor(color);
            g2d.rotate(rotation, x, y);
            g2d.fillRect((int)x - 5, (int)y - 3, 10, 6);
            g2d.rotate(-rotation, x, y);
        }
    }

    class PowerBar {
        void draw(Graphics2D g2d) {
            int x = 20, y = 820, w = 200, h = 25;

            g2d.setColor(new Color(0, 0, 0, 150));
            g2d.fillRoundRect(x - 5, y - 5, w + 10, h + 10, 8, 8);

            g2d.setColor(new Color(50, 50, 60));
            g2d.fillRoundRect(x, y, w, h, 5, 5);

            Color barColor;
            if (powerLevel > 70) barColor = GLOW_GREEN;
            else if (powerLevel > 30) barColor = ORANGE;
            else barColor = RED_ACCENT;

            GradientPaint barGrad = new GradientPaint(x, y, barColor, x + w, y + h, barColor.brighter());
            g2d.setPaint(barGrad);
            g2d.fillRoundRect(x + 2, y + 2, (int)(w * powerLevel / 100) - 4, h - 4, 4, 4);

            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            g2d.drawString("POWER: " + powerLevel + "%", x + 5, y + 17);
        }
    }

    class RobotBuddy {
        int buddyX = 850, buddyY = 400;
        int wavePhase = 0;
        boolean following = true;

        void update(int robotX, int robotY) {
            if (following) {
                int targetX = robotX + 150;
                int targetY = robotY - 50;
                buddyX += (targetX - buddyX) * 0.05;
                buddyY += (targetY - buddyY) * 0.05;
            }
            wavePhase++;
        }

        void update(int time) {
            buddyX += (int)(Math.sin(time * 0.03) * 2);
            buddyY += (int)(Math.cos(time * 0.04) * 1.5);
        }

        void draw(Graphics2D g2d) {
            float scale = 0.5f;
            int x = buddyX, y = buddyY;

            GradientPaint bodyGrad = new GradientPaint(
                x - 25 * scale, y - 10 * scale, new Color(100, 50, 150),
                x + 25 * scale, y + 50 * scale, new Color(150, 100, 200)
            );
            g2d.setPaint(bodyGrad);
            g2d.fillRoundRect((int)(x - 25 * scale), (int)(y - 10 * scale),
                             (int)(50 * scale), (int)(60 * scale), 10, 10);

            g2d.fillOval((int)(x - 20 * scale), (int)(y - 40 * scale),
                        (int)(40 * scale), (int)(35 * scale));

            g2d.setColor(Color.YELLOW);
            int eyeOffset = (int)(Math.sin(wavePhase * 0.1) * 3);
            g2d.fillOval((int)(x - 12 * scale + eyeOffset), (int)(y - 32 * scale),
                        (int)(8 * scale), (int)(8 * scale));
            g2d.fillOval((int)(x + 4 * scale + eyeOffset), (int)(y - 32 * scale),
                        (int)(8 * scale), (int)(8 * scale));

            int armWave = (int)(Math.sin(wavePhase * 0.2) * 20);
            g2d.setStroke(new BasicStroke((int)(4 * scale)));
            g2d.drawLine((int)(x + 25 * scale), (int)(y + 5 * scale),
                        (int)(x + 45 * scale), (int)(y - 10 * scale + armWave));

            g2d.setColor(PURPLE);
            g2d.setFont(new Font("Arial", Font.BOLD, (int)(10 * scale)));
            g2d.drawString("Buddy", (int)(x - 15), (int)(y - 50 * scale));
        }
    }

    class MissionObjective {
        String[] missions = {
            "Walk forward", "Wave to crowd", "Take flight", "Dance moves!"
        };
        int currentMission = 0;
        boolean completed = false;

        void update(String state, int time) {
            if (state.equals("walk") && currentMission == 0) {
                if (walkX > 300) {
                    currentMission = 1;
                    completed = true;
                    confetti.addConfetti(700, 400);
                }
            } else if (state.equals("wave") && currentMission == 1) {
                if (wavePhase > 50) {
                    currentMission = 2;
                    completed = true;
                    confetti.addConfetti(700, 400);
                }
            } else if (state.equals("fly") && currentMission == 2) {
                if (flyPhase > 500) {
                    currentMission = 3;
                    completed = true;
                    for (int i = 0; i < 100; i++) confetti.addConfetti(700, 400);
                    playTone(1046, 0.5f);
                }
            }
        }

        void draw(Graphics2D g2d) {
            int x = 1150, y = 30;

            g2d.setColor(new Color(0, 0, 0, 150));
            g2d.fillRoundRect(x - 15, y - 15, 240, 120, 10, 10);

            g2d.setColor(completed ? GLOW_GREEN : Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            g2d.drawString("MISSION:", x, y += 15);

            for (int i = 0; i < missions.length; i++) {
                if (i < currentMission) {
                    g2d.setColor(GLOW_GREEN);
                    g2d.drawString("✓ " + missions[i], x, y += 18);
                } else if (i == currentMission) {
                    g2d.setColor(Color.YELLOW);
                    g2d.drawString("→ " + missions[i], x, y += 18);
                } else {
                    g2d.setColor(Color.GRAY);
                    g2d.drawString("  " + missions[i], x, y += 18);
                }
            }

            if (currentMission >= missions.length) {
                g2d.setColor(GOLD);
                g2d.setFont(new Font("Arial", Font.BOLD, 20));
                g2d.drawString("MISSION COMPLETE!", 600, 100);
            }
        }
    }

    private JPanel createControlPanel() {
        controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(new Color(30, 30, 40));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("ARDIT ROBOT CONTROLS");
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 16));
        titleLabel.setForeground(CYAN);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(titleLabel);
        controlPanel.add(Box.createVerticalStrut(10));

        stateLabel = new JLabel("STATE: " + state.toUpperCase());
        stateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        stateLabel.setForeground(Color.WHITE);
        stateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(stateLabel);
        controlPanel.add(Box.createVerticalStrut(15));

        JLabel modeLabel = new JLabel("MODE SELECT");
        modeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        modeLabel.setForeground(Color.GRAY);
        modeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(modeLabel);
        controlPanel.add(Box.createVerticalStrut(5));

        JPanel modePanel = new JPanel(new GridLayout(2, 2, 5, 5));
        modePanel.setOpaque(false);

        walkBtn = createModeButton("WALK", new Color(76, 175, 80), e -> setMode("walk"));
        waveBtn = createModeButton("WAVE", new Color(33, 150, 243), e -> setMode("wave"));
        flyBtn = createModeButton("FLY", new Color(255, 152, 0), e -> setMode("fly"));
        danceBtn = createModeButton("DANCE", new Color(233, 30, 99), e -> setMode("dance"));

        modePanel.add(walkBtn);
        modePanel.add(waveBtn);
        modePanel.add(flyBtn);
        modePanel.add(danceBtn);
        controlPanel.add(modePanel);
        controlPanel.add(Box.createVerticalStrut(15));

        JLabel effectsLabel = new JLabel("EFFECTS");
        effectsLabel.setFont(new Font("Arial", Font.BOLD, 12));
        effectsLabel.setForeground(Color.GRAY);
        effectsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(effectsLabel);
        controlPanel.add(Box.createVerticalStrut(5));

        JPanel effectsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        effectsPanel.setOpaque(false);

        ultraBtn = createToggleButton("ULTRA MODE", GOLD, e -> toggleUltra());
        slowMoBtn = createToggleButton("SLOW MOTION", new Color(156, 39, 176), e -> toggleSlowMotion());
        musicBtn = createToggleButton("MUSIC: ON", new Color(0, 188, 212), e -> toggleMusic());
        particlesBtn = createToggleButton("PARTICLES", new Color(255, 87, 34), e -> toggleParticles());

        effectsPanel.add(ultraBtn);
        effectsPanel.add(slowMoBtn);
        effectsPanel.add(musicBtn);
        effectsPanel.add(particlesBtn);
        controlPanel.add(effectsPanel);
        controlPanel.add(Box.createVerticalStrut(15));

        JLabel powerLabel = new JLabel("POWER: " + powerLevel + "%");
        powerLabel.setFont(new Font("Arial", Font.BOLD, 12));
        powerLabel.setForeground(GLOW_GREEN);
        powerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(powerLabel);
        controlPanel.add(Box.createVerticalStrut(10));

        JPanel powerBarPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, true);

                g2d.setColor(new Color(50, 50, 60));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);

                Color barColor = powerLevel > 70 ? GLOW_GREEN : (powerLevel > 30 ? ORANGE : RED_ACCENT);
                g2d.setColor(barColor);
                g2d.fillRoundRect(2, 2, (int)((getWidth() - 4) * powerLevel / 100.0), getHeight() - 4, 4, 4);
            }
        };
        powerBarPanel.setPreferredSize(new Dimension(150, 20));
        powerBarPanel.setMaximumSize(new Dimension(150, 20));
        powerBarPanel.setOpaque(false);
        controlPanel.add(powerBarPanel);
        controlPanel.add(Box.createVerticalStrut(15));

        JButton resetBtn = new JButton("RESET");
        resetBtn.setFont(new Font("Arial", Font.BOLD, 12));
        resetBtn.setBackground(new Color(100, 100, 110));
        resetBtn.setForeground(Color.WHITE);
        resetBtn.setFocusPainted(false);
        resetBtn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        resetBtn.addActionListener(e -> resetRobot());
        resetBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(resetBtn);

        JLabel hintLabel = new JLabel("Or use: Click=Dance, Right-Click=Fly");
        hintLabel.setFont(new Font("Arial", Font.ITALIC, 10));
        hintLabel.setForeground(Color.GRAY);
        hintLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(Box.createVerticalStrut(10));
        controlPanel.add(hintLabel);

        updateButtonStates();

        return controlPanel;
    }

    private JButton createModeButton(String text, Color color, ActionListener action) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color.darker(), 2),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        btn.setOpaque(true);
        btn.addActionListener(action);
        return btn;
    }

    private JButton createToggleButton(String text, Color color, ActionListener action) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 11));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color.darker(), 2),
            BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
        btn.setOpaque(true);
        btn.addActionListener(action);
        return btn;
    }

    private void setMode(String newState) {
        state = newState;
        if (newState.equals("walk")) {
            walkPhase = 0;
            walkX = -100;
        } else if (newState.equals("wave")) {
            wavePhase = 0;
        } else if (newState.equals("fly")) {
            flyPhase = 0;
        } else if (newState.equals("dance")) {
            dancePhase = 0;
        }
        playTone(500, 0.1f);
        updateButtonStates();
    }

    private void toggleUltra() {
        ultraMode = !ultraMode;
        if (ultraMode) {
            playTone(880, 0.3f);
            for (int i = 0; i < 30; i++) confetti.addConfetti(700, 400);
        } else {
            playTone(440, 0.2f);
        }
        updateButtonStates();
    }

    private void toggleSlowMotion() {
        slowMotion = !slowMotion;
        updateButtonStates();
    }

    private void toggleMusic() {
        musicEnabled = !musicEnabled;
        musicBtn.setText("MUSIC: " + (musicEnabled ? "ON" : "OFF"));
        musicBtn.setBackground(musicEnabled ? new Color(0, 188, 212) : new Color(100, 100, 110));
    }

    private void toggleParticles() {
        showParticles = !showParticles;
        particlesBtn.setText("PARTICLES: " + (showParticles ? "ON" : "OFF"));
        particlesBtn.setBackground(showParticles ? new Color(255, 87, 34) : new Color(100, 100, 110));
    }

    private void resetRobot() {
        state = "walk";
        walkX = -100;
        walkPhase = 0;
        wavePhase = 0;
        flyPhase = 0;
        dancePhase = 0;
        ultraMode = false;
        slowMotion = false;
        powerLevel = 100;
        energyShield = 50;
        cameraShake = 10;
        playTone(300, 0.2f);
        updateButtonStates();
    }

    private void updateButtonStates() {
        walkBtn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(state.equals("walk") ? Color.WHITE : new Color(76, 175, 80).darker(), 3),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        waveBtn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(state.equals("wave") ? Color.WHITE : new Color(33, 150, 243).darker(), 3),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        flyBtn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(state.equals("fly") ? Color.WHITE : new Color(255, 152, 0).darker(), 3),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        danceBtn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(state.equals("dance") ? Color.WHITE : new Color(233, 30, 99).darker(), 3),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));

        ultraBtn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ultraMode ? Color.WHITE : GOLD.darker(), 3),
            BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
        slowMoBtn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(slowMotion ? Color.WHITE : new Color(156, 39, 176).darker(), 3),
            BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));

        if (stateLabel != null) {
            stateLabel.setText("STATE: " + state.toUpperCase());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ARDIT :) ULTRA ROBOT - MASTERPIECE EDITION");

            RobotMeNgjyre robotPanel = new RobotMeNgjyre();
            JPanel controlPanel = robotPanel.createControlPanel();

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(robotPanel, BorderLayout.CENTER);
            mainPanel.add(controlPanel, BorderLayout.EAST);

            frame.add(mainPanel);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(true);
        });
    }
}