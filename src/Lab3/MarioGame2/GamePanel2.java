package Lab3.MarioGame2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

class GamePanel2 extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;
    private static final int GROUND_Y = 420;

    private Timer gameLoop;
    private Player2 player;
    private ArrayList<Platform2> platforms;
    private ArrayList<Enemy2> enemies;
    private ArrayList<Coin2> coins;
    private ArrayList<Fireball2> fireballs;
    private ArrayList<PowerUp> powerUps;

    private int score = 0;
    private int cameraX = 0;
    private GameState gameState = GameState.START;
    private int currentLevel = 1;

    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean spacePressed = false;
    private boolean xPressed = false;

    private int levelWidth = 2400;
    private int checkpointX = 0;
    private boolean hasCheckpoint = false;

    private Random random = new Random();

    public GamePanel2() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(107, 140, 255));
        setFocusable(true);

        initGame();
        setupControls();

        gameLoop = new Timer(16, e -> {
            update();
            repaint();
        });
        gameLoop.start();
    }

    private void initGame() {
        player = new Player2(100, 200);
        player.setMaxX(levelWidth);
        platforms = new ArrayList<>();
        enemies = new ArrayList<>();
        coins = new ArrayList<>();
        fireballs = new ArrayList<>();
        powerUps = new ArrayList<>();
        score = 0;
        cameraX = 0;
        hasCheckpoint = false;
        checkpointX = 0;

        createLevel(currentLevel);
    }

    private void createLevel(int level) {
        platforms.clear();
        enemies.clear();
        coins.clear();
        powerUps.clear();

        platforms.add(new Platform2(0, GROUND_Y, levelWidth, 80, Platform2.Type.GROUND));

        if (level == 1) {
            createWorld1();
        } else if (level == 2) {
            createWorld2();
        } else {
            createWorld3();
        }

        for (int i = 0; i < 5 + level * 2; i++) {
            int x = 200 + random.nextInt(levelWidth - 400);
            int y = GROUND_Y - 40 - random.nextInt(100);
            enemies.add(new Enemy2(x, y, Enemy2.Type.GOOMBA, 80));
        }

        if (level >= 2) {
            for (int i = 0; i < 3; i++) {
                int x = 400 + random.nextInt(levelWidth - 600);
                enemies.add(new Enemy2(x, GROUND_Y - 32, Enemy2.Type.KOOPA, 100));
            }
        }

        if (level >= 3) {
            enemies.add(new Enemy2(levelWidth - 200, GROUND_Y - 64, Enemy2.Type.BOWSER, 50));
            for (int i = 0; i < 3; i++) {
                int x = 300 + random.nextInt(levelWidth - 400);
                enemies.add(new Enemy2(x, GROUND_Y - 32, Enemy2.Type.FLY_KOOPA, 150));
            }
        }
    }

    private void createWorld1() {
        platforms.add(new Platform2(200, 320, 100, 20, Platform2.Type.QUESTION));
        platforms.add(new Platform2(400, 280, 100, 20, Platform2.Type.BRICK));
        platforms.add(new Platform2(600, 320, 150, 20, Platform2.Type.QUESTION));
        platforms.add(new Platform2(850, 250, 100, 20, Platform2.Type.BRICK));
        Platform2 p1 = new Platform2(1050, 320, 200, 20, Platform2.Type.MOVING, 100, false);
        p1.startMoving();
        platforms.add(p1);
        platforms.add(new Platform2(1350, 280, 100, 20, Platform2.Type.QUESTION));
        platforms.add(new Platform2(1550, 220, 150, 20, Platform2.Type.BRICK));
        Platform2 p2 = new Platform2(1800, 280, 100, 20, Platform2.Type.MOVING, 80, true);
        p2.startMoving();
        platforms.add(p2);
        platforms.add(new Platform2(2000, 320, 200, 20, Platform2.Type.QUESTION));
        platforms.add(new Platform2(2200, 250, 100, 20, Platform2.Type.BRICK));

        for (int i = 0; i < 20; i++) {
            int x = 250 + i * 120 + random.nextInt(30);
            int y = 200 + random.nextInt(150);
            coins.add(new Coin2(x, y));
        }

        powerUps.add(new PowerUp(210, 300, PowerUp.Type.MUSHROOM));
        powerUps.add(new PowerUp(610, 300, PowerUp.Type.FIRE_FLOWER));
        powerUps.add(new PowerUp(1410, 260, PowerUp.Type.STAR));
    }

    private void createWorld2() {
        platforms.add(new Platform2(150, 340, 80, 20, Platform2.Type.QUESTION));
        platforms.add(new Platform2(300, 300, 120, 20, Platform2.Type.BRICK));
        Platform2 p3 = new Platform2(500, 260, 100, 20, Platform2.Type.MOVING, 150, false);
        p3.startMoving();
        platforms.add(p3);
        platforms.add(new Platform2(750, 320, 100, 20, Platform2.Type.BRICK));
        platforms.add(new Platform2(900, 250, 80, 20, Platform2.Type.QUESTION));
        Platform2 p4 = new Platform2(1050, 320, 150, 20, Platform2.Type.MOVING, 100, true);
        p4.startMoving();
        platforms.add(p4);
        platforms.add(new Platform2(1300, 280, 100, 20, Platform2.Type.BRICK));
        platforms.add(new Platform2(1500, 220, 120, 20, Platform2.Type.QUESTION));
        platforms.add(new Platform2(1750, 300, 100, 20, Platform2.Type.BRICK));
        Platform2 p5 = new Platform2(1950, 250, 150, 20, Platform2.Type.MOVING, 120, false);
        p5.startMoving();
        platforms.add(p5);

        for (int i = 0; i < 25; i++) {
            int x = 200 + i * 100 + random.nextInt(40);
            int y = 180 + random.nextInt(160);
            coins.add(new Coin2(x, y));
        }

        powerUps.add(new PowerUp(160, 320, PowerUp.Type.STAR));
        powerUps.add(new PowerUp(510, 240, PowerUp.Type.MUSHROOM));
        powerUps.add(new PowerUp(910, 230, PowerUp.Type.ONE_UP));
        powerUps.add(new PowerUp(1510, 200, PowerUp.Type.FIRE_FLOWER));
    }

    private void createWorld3() {
        platforms.add(new Platform2(100, 350, 80, 20, Platform2.Type.QUESTION));
        platforms.add(new Platform2(250, 300, 100, 20, Platform2.Type.BRICK));
        Platform2 p6 = new Platform2(400, 250, 80, 20, Platform2.Type.MOVING, 100, true);
        p6.startMoving();
        platforms.add(p6);
        platforms.add(new Platform2(550, 320, 100, 20, Platform2.Type.QUESTION));
        platforms.add(new Platform2(700, 280, 120, 20, Platform2.Type.BRICK));
        Platform2 p7 = new Platform2(900, 220, 80, 20, Platform2.Type.MOVING, 150, false);
        p7.startMoving();
        platforms.add(p7);
        platforms.add(new Platform2(1100, 300, 100, 20, Platform2.Type.BRICK));
        platforms.add(new Platform2(1300, 250, 80, 20, Platform2.Type.QUESTION));
        Platform2 p8 = new Platform2(1500, 320, 120, 20, Platform2.Type.MOVING, 80, true);
        p8.startMoving();
        platforms.add(p8);
        platforms.add(new Platform2(1750, 280, 100, 20, Platform2.Type.BRICK));
        Platform2 p9 = new Platform2(1950, 220, 150, 20, Platform2.Type.MOVING, 100, false);
        p9.startMoving();
        platforms.add(p9);

        for (int i = 0; i < 30; i++) {
            int x = 150 + i * 80 + random.nextInt(30);
            int y = 160 + random.nextInt(180);
            coins.add(new Coin2(x, y));
        }

        powerUps.add(new PowerUp(110, 330, PowerUp.Type.FIRE_FLOWER));
        powerUps.add(new PowerUp(410, 230, PowerUp.Type.STAR));
        powerUps.add(new PowerUp(710, 260, PowerUp.Type.MUSHROOM));
        powerUps.add(new PowerUp(1310, 230, PowerUp.Type.ONE_UP));
    }

    private void setupControls() {
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        leftPressed = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        rightPressed = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        if (!spacePressed) {
                            player.jump();
                            spacePressed = true;
                        }
                        break;
                    case KeyEvent.VK_X:
                        if (!xPressed) {
                            shootFireball();
                            xPressed = true;
                        }
                        break;
                    case KeyEvent.VK_ENTER:
                        if (gameState == GameState.START || gameState == GameState.GAMEOVER || gameState == GameState.WIN) {
                            restartGame();
                        }
                        break;
                    case KeyEvent.VK_ESCAPE:
                        if (gameState == GameState.PLAYING) {
                            gameState = GameState.START;
                        }
                        break;
                    case KeyEvent.VK_P:
                        if (gameState == GameState.PLAYING) {
                            gameState = GameState.PAUSED;
                        } else if (gameState == GameState.PAUSED) {
                            gameState = GameState.PLAYING;
                        }
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        leftPressed = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        rightPressed = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        spacePressed = false;
                        break;
                    case KeyEvent.VK_X:
                        xPressed = false;
                        break;
                }
            }
        });
    }

    private void shootFireball() {
        if (player.canShoot()) {
            int px = player.getX() + (player.isFacingRight() ? 30 : -10);
            int py = player.getY() + 15;
            fireballs.add(new Fireball2(px, py, player.isFacingRight()));
            player.resetFireballCooldown();
        }
    }

    private void restartGame() {
        currentLevel = 1;
        initGame();
        gameState = GameState.PLAYING;
    }

    private void nextLevel() {
        currentLevel++;
        if (currentLevel > 3) {
            gameState = GameState.WIN;
        } else {
            player.respawn();
            cameraX = 0;
            createLevel(currentLevel);
            score += 1000;
        }
    }

    private void update() {
        if (gameState != GameState.PLAYING) return;

        if (leftPressed) player.moveLeft();
        if (rightPressed) player.moveRight();
        if (!leftPressed && !rightPressed) player.stop();

        player.update();

        for (Platform2 platform : platforms) {
            platform.update();
            player.collideWith(platform);
        }

        for (Iterator<Enemy2> it = enemies.iterator(); it.hasNext(); ) {
            Enemy2 enemy = it.next();
            enemy.update();

            if (player.getBounds().intersects(enemy.getBounds())) {
                if (player.isFalling() && player.getY() + player.getHeight() < enemy.getY() + enemy.getHeight() / 2) {
                    enemy.stomp();
                    player.bounce();
                    score += 100;
                } else if (player.isAttacking()) {
                    enemy.defeat();
                    score += 200;
                    if (enemy.getType() == Enemy2.Type.BOWSER) {
                        nextLevel();
                    }
                    it.remove();
                } else if (player.canTakeDamage()) {
                    player.hit();
                    if (player.getLives() <= 0) {
                        gameState = GameState.GAMEOVER;
                    }
                }
            }

            for (Iterator<Fireball2> fbIt = fireballs.iterator(); fbIt.hasNext(); ) {
                Fireball2 fb = fbIt.next();
                if (fb.getBounds().intersects(enemy.getBounds())) {
                    enemy.defeat();
                    score += 200;
                    fb.setActive(false);
                    fbIt.remove();
                }
            }
        }

        for (Iterator<Coin2> it = coins.iterator(); it.hasNext(); ) {
            Coin2 coin = it.next();
            coin.update();
            if (player.getBounds().intersects(coin.getBounds())) {
                coin.collect();
                score += 50;
                player.collectCoin();
                it.remove();
            }
        }

        for (Iterator<PowerUp> it = powerUps.iterator(); it.hasNext(); ) {
            PowerUp powerUp = it.next();
            powerUp.update();
            if (player.getBounds().intersects(powerUp.getBounds())) {
                player.collectPowerUp(powerUp);
                powerUp.collect();
                score += 500;
                it.remove();
            }
            for (Platform2 platform : platforms) {
                if (powerUp.getBounds().intersects(platform.getBounds()) &&
                        powerUp.getVelocityY() >= 0 &&
                        powerUp.getY() + powerUp.getSize() - powerUp.getVelocityY() <= platform.getY() + 5) {
                    powerUp.land(platform.getY() - powerUp.getSize());
                }
            }
        }

        for (Iterator<Fireball2> it = fireballs.iterator(); it.hasNext(); ) {
            Fireball2 fb = it.next();
            fb.update();

            for (Platform2 platform : platforms) {
                if (fb.getBounds().intersects(platform.getBounds()) && platform.getType() != Platform2.Type.GROUND) {
                    fb.setHitGround(true);
                }
            }

            if (!fb.isActive() || fb.getX() < cameraX - 50 || fb.getX() > cameraX + WIDTH + 50) {
                it.remove();
            }
        }

        for (Platform2 platform : platforms) {
            if (platform.getType() == Platform2.Type.QUESTION &&
                    player.getBounds().intersects(new Rectangle(platform.getX(), platform.getY() - 10, platform.getWidth(), 20))) {
                platform.activate();
                spawnQuestionBlockReward(platform);
            }
        }

        cameraX = Math.max(0, Math.min(player.getX() - WIDTH / 3, levelWidth - WIDTH));

        if (!hasCheckpoint && player.getX() > levelWidth / 2) {
            hasCheckpoint = true;
            checkpointX = levelWidth / 2;
        }

        if (player.getDrawY() > HEIGHT + 100) {
            player.hit();
            if (player.getLives() <= 0) {
                gameState = GameState.GAMEOVER;
            } else {
                player.respawn();
                if (hasCheckpoint) {
                    cameraX = checkpointX - WIDTH / 3;
                } else {
                    cameraX = 0;
                }
            }
        }

        if (player.getX() >= levelWidth - 50) {
            nextLevel();
        }
    }

    private void spawnQuestionBlockReward(Platform2 platform) {
        int x = platform.getX();
        int y = platform.getY() - 32;

        int rand = random.nextInt(100);
        if (rand < 40) {
            powerUps.add(new PowerUp(x, y, PowerUp.Type.MUSHROOM));
        } else if (rand < 60) {
            coins.add(new Coin2(x + 10, y + 10, 5));
        } else if (rand < 70) {
            powerUps.add(new PowerUp(x, y, PowerUp.Type.STAR));
        } else if (rand < 75) {
            powerUps.add(new PowerUp(x, y, PowerUp.Type.ONE_UP));
        } else {
            for (int i = 0; i < 3; i++) {
                coins.add(new Coin2(x + i * 16, y + 10));
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawBackground(g2d);

        g2d.translate(-cameraX, 0);

        for (Platform2 platform : platforms) {
            platform.draw(g2d);
        }

        for (Coin2 coin : coins) {
            coin.draw(g2d);
        }

        for (PowerUp powerUp : powerUps) {
            powerUp.draw(g2d);
        }

        for (Enemy2 enemy : enemies) {
            enemy.draw(g2d);
        }

        for (Fireball2 fb : fireballs) {
            fb.draw(g2d);
        }

        player.draw(g2d);

        g2d.translate(cameraX, 0);

        drawHUD(g2d);
        drawGameState(g2d);
    }

    private void drawBackground(Graphics2D g2d) {
        Color skyColor;
        if (currentLevel == 1) {
            skyColor = new Color(107, 140, 255);
        } else if (currentLevel == 2) {
            skyColor = new Color(100, 180, 150);
        } else {
            skyColor = new Color(50, 50, 80);
        }
        g2d.setColor(skyColor);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        if (currentLevel == 1) {
            g2d.setColor(new Color(180, 200, 255));
            for (int i = 0; i < 20; i++) {
                int x = (i * 100 - cameraX / 3) % (WIDTH + 200) - 100;
                int y = 50 + (i % 3) * 40;
                g2d.fillOval(x, y, 30, 20);
            }
        } else if (currentLevel == 2) {
            g2d.setColor(new Color(100, 200, 100));
            for (int i = 0; i < 15; i++) {
                int x = (i * 150 - cameraX / 4) % (WIDTH + 300) - 150;
                int y = 100 + (i % 4) * 60;
                g2d.fillOval(x, y, 80, 120);
            }
        } else {
            g2d.setColor(new Color(80, 80, 120));
            for (int i = 0; i < 30; i++) {
                int x = (i * 80 - cameraX / 2) % (WIDTH + 200) - 100;
                int y = 40 + (i % 5) * 50;
                g2d.fillOval(x, y, 15, 15);
            }
        }

        if (player.getDrawY() > 300) {
            g2d.setColor(new Color(139, 90, 43, 100));
            g2d.fillRect(0, 0, WIDTH, 50);
        }
    }

    private void drawHUD(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(5, 5, 180, 30);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Score: " + score, 15, 27);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(WIDTH - 160, 5, 155, 30);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Lives: " + player.getLives(), WIDTH - 150, 27);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(WIDTH / 2 - 60, 5, 120, 30);
        g2d.setColor(Color.YELLOW);
        g2d.drawString("Coins: " + player.getCoinCount(), WIDTH / 2 - 50, 27);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(5, 40, 80, 25);
        g2d.setColor(new Color(255, 200, 100));
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString("Level: " + currentLevel, 10, 57);
    }

    private void drawGameState(Graphics2D g2d) {
        if (gameState == GameState.START) {
            drawOverlay(g2d, "MARIO GAME 2.0", getStartMenuLines());
        } else if (gameState == GameState.GAMEOVER) {
            drawOverlay(g2d, "GAME OVER", new String[]{"Final Score: " + score, "Press ENTER to Restart"});
        } else if (gameState == GameState.WIN) {
            drawOverlay(g2d, "YOU WIN!", new String[]{"Final Score: " + score, "Press ENTER to Play Again"});
        } else if (gameState == GameState.PAUSED) {
            drawOverlay(g2d, "PAUSED", new String[]{"Press P to Resume"});
        }
    }

    private void drawOverlay(Graphics2D g2d, String title, String[] lines) {
        g2d.setColor(new Color(0, 0, 0, 180));
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 48));
        g2d.drawString(title, WIDTH / 2 - 150, HEIGHT / 2 - 50);

        g2d.setFont(new Font("Arial", Font.PLAIN, 18));
        int yOffset = 20;
        for (String line : lines) {
            int x = WIDTH / 2 - g2d.getFontMetrics().stringWidth(line) / 2;
            g2d.drawString(line, x, HEIGHT / 2 + yOffset);
            yOffset += 30;
        }
    }

    private String[] getStartMenuLines() {
        return new String[]{
                "Arrow Keys - Move",
                "Space - Jump",
                "X - Shoot Fireball (Fire Mode)",
                "P - Pause",
                "",
                "Press ENTER to Start"
        };
    }

    private enum GameState {
        START, PLAYING, PAUSED, GAMEOVER, WIN
    }
}
