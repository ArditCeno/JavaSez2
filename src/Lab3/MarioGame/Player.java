package Lab3.MarioGame;

import java.awt.*;
import javax.swing.*;

public class Player {
    private int x, y;
    private int width = 32;
    private int height = 48;
    private int speed = 5;
    private int jumpForce = -14;
    private int velocityY = 0;
    private int gravity = 1;

    private boolean onGround = false;
    private boolean facingRight = true;
    private int jumpsRemaining = 2;
    private boolean isBig = false;
    private boolean invincible = false;
    private boolean attacking = false;

    private int lives = 3;
    private int invincibleTimer = 0;
    private int fireballCooldown = 0;

    private int animationFrame = 0;
    private int animationTimer = 0;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveLeft() {
        x -= speed;
        facingRight = false;
        animate();
    }

    public void moveRight() {
        x += speed;
        facingRight = true;
        animate();
    }

    public void stop() {
        animationFrame = 0;
    }

    public void jump() {
        if (jumpsRemaining > 0) {
            velocityY = jumpForce;
            jumpsRemaining--;
            onGround = false;
        }
    }

    public void attack() {
        attacking = true;
        new Timer(200, e -> {
            attacking = false;
            ((Timer) e.getSource()).stop();
        }).start();
    }

    public void bounce() {
        velocityY = jumpForce / 2;
    }

    public void update() {
        velocityY += gravity;
        y += velocityY;

        if (velocityY > 15) velocityY = 15;

        if (invincible) {
            invincibleTimer--;
            if (invincibleTimer <= 0) {
                invincible = false;
            }
        }

        if (fireballCooldown > 0) fireballCooldown--;

        onGround = false;
    }

    public void collideWith(Platform platform) {
        Rectangle playerBounds = getBounds();
        Rectangle platformBounds = platform.getBounds();

        if (playerBounds.intersects(platformBounds)) {
            if (velocityY > 0 && y + height - velocityY <= platform.y + 5) {
                y = platform.y - height;
                velocityY = 0;
                onGround = true;
                jumpsRemaining = 2;
            } else if (velocityY < 0 && y - velocityY >= platform.y + platform.height - 5) {
                y = platform.y + platform.height;
                velocityY = 0;
            } else if (x + width - speed <= platform.x) {
                x = platform.x - width;
            } else if (x + speed >= platform.x + platform.width) {
                x = platform.x + platform.width;
            }
        }
    }

    private void animate() {
        animationTimer++;
        if (animationTimer > 8) {
            animationFrame = (animationFrame + 1) % 4;
            animationTimer = 0;
        }
    }

    public void draw(Graphics2D g2d) {
        if (invincible && animationFrame % 2 == 0) return;

        g2d = (Graphics2D) g2d.create();

        if (!facingRight) {
            g2d.translate(x + width, y);
            g2d.scale(-1, 1);
            g2d.translate(-x, -y);
        }

        Color skinColor = new Color(255, 200, 150);
        Color hatColor = new Color(200, 50, 50);
        Color shirtColor = isBig ? new Color(50, 100, 200) : new Color(50, 100, 255);
        Color pantsColor = new Color(100, 50, 20);
        Color shoeColor = new Color(100, 60, 30);

        g2d.setColor(hatColor);
        g2d.fillRect(x + 4, y, 24, 8);
        g2d.fillRect(x, y + 6, 32, 6);

        g2d.setColor(skinColor);
        g2d.fillRect(x + 8, y + 12, 16, 12);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(x + 10, y + 16, 4, 4);
        g2d.fillRect(x + 18, y + 16, 4, 4);

        g2d.setColor(shirtColor);
        g2d.fillRect(x + 4, y + 24, 24, 14);

        if (attacking) {
            g2d.setColor(Color.YELLOW);
            g2d.fillOval(x + 28, y + 28, 12, 8);
        }

        int legOffset = onGround ? (animationFrame % 2) * 4 : 2;

        g2d.setColor(pantsColor);
        g2d.fillRect(x + 6, y + 38, 8, 10 - legOffset);
        g2d.fillRect(x + 18, y + 38, 8, 10 - (4 - legOffset));

        g2d.setColor(shoeColor);
        g2d.fillRect(x + 4, y + height - 6 + (legOffset > 0 ? -2 : 0), 12, 6);
        g2d.fillRect(x + 16, y + height - 6 + (legOffset > 0 ? 2 : 0), 12, 6);

        g2d.dispose();
    }

    public void hit() {
        if (!invincible) {
            lives--;
            invincible = true;
            invincibleTimer = 120;
        }
    }

    public void respawn() {
        x = 100;
        y = 300;
        velocityY = 0;
        invincible = true;
        invincibleTimer = 120;
    }

    public boolean isFalling() {
        return velocityY > 0;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public boolean canShoot() {
        return fireballCooldown <= 0;
    }

    public void resetFireballCooldown() {
        fireballCooldown = 30;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public Rectangle getBounds() {
        return new Rectangle(x + 4, y, width - 8, height);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getLives() { return lives; }
}
