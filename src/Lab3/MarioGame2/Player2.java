package Lab3.MarioGame2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

class Player2 {
    public enum State {NORMAL, BIG, FIRE, STAR}

    public enum Form {SMALL, BIG}

    private int x, y;
    private int width = 32;
    private int height = 32;
    private int speed = 5;
    private int jumpForce = -14;
    private int velocityY = 0;
    private int gravity = 1;
    private int maxFallSpeed = 15;

    private boolean onGround = false;
    private boolean canDoubleJump = false;
    private boolean facingRight = true;
    private int jumpsRemaining = 2;
    private boolean invincible = false;
    private boolean attacking = false;

    private int lives = 3;
    private int invincibleTimer = 0;
    private int fireballCooldown = 0;

    private int animationFrame = 0;
    private int animationTimer = 0;

    private State state = State.NORMAL;
    private Form form = Form.SMALL;
    private int starTimer = 0;
    private boolean isDead = false;

    private int coinCount = 0;
    private int maxX = 2400;

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public Player2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveLeft() {
        if (isDead) return;
        x -= speed;
        facingRight = false;
        animate();
    }

    public void moveRight() {
        if (isDead) return;
        if (x < maxX - width) {
            x += speed;
        }
        facingRight = true;
        animate();
    }

    public void stop() {
        animationFrame = 0;
        animationTimer = 0;
    }

    public void jump() {
        if (isDead) return;
        if (onGround) {
            velocityY = jumpForce;
            onGround = false;
            canDoubleJump = true;
        } else if (canDoubleJump) {
            velocityY = jumpForce;
            canDoubleJump = false;
        }
    }

    public void attack() {
        if (isDead || state != State.FIRE) return;
        attacking = true;
        new Timer(200, e -> {
            attacking = false;
            ((Timer) e.getSource()).stop();
        }).start();
    }

    public void bounce() {
        if (isDead) return;
        velocityY = jumpForce / 2;
    }

    public void update() {
        if (isDead) return;

        velocityY += gravity;
        if (velocityY > maxFallSpeed) velocityY = maxFallSpeed;
        y += velocityY;

        if (invincible) {
            invincibleTimer--;
            if (invincibleTimer <= 0) {
                invincible = false;
            }
        }

        if (fireballCooldown > 0) fireballCooldown--;

        if (state == State.STAR) {
            starTimer--;
            if (starTimer <= 0) {
                state = State.NORMAL;
            }
        }

        onGround = false;
    }

    public void collideWith(Platform2 platform) {
        if (isDead) return;
        
        int playerHeight = (form == Form.BIG) ? height * 2 : height;
        Rectangle playerBounds = getBounds();
        Rectangle platformBounds = platform.getBounds();

        if (playerBounds.intersects(platformBounds)) {
            int playerBottom = y;
            int platformTop = platform.getY();
            
            if (velocityY > 0 && playerBottom >= platformTop - 5 && playerBottom <= platformTop + 20) {
                y = platformTop - playerHeight;
                velocityY = 0;
                onGround = true;
            } else if (velocityY < 0 && y - velocityY >= platform.getY() + platform.getHeight() - 5) {
                y = platform.getY() + platform.getHeight();
                velocityY = 0;
            } else if (x + width / 2 <= platform.getX()) {
                x = platform.getX() - width / 2;
            } else if (x + width / 2 >= platform.getX() + platform.getWidth()) {
                x = platform.getX() + platform.getWidth() - width / 2;
            }
        }
        
        if (y + playerHeight >= platform.getY() && y + playerHeight <= platform.getY() + 10 && velocityY >= 0) {
            y = platform.getY() - playerHeight;
            velocityY = 0;
            onGround = true;
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
        if (isDead) return;
        if (invincible && animationFrame % 2 == 0) return;

        int drawHeight = (form == Form.BIG) ? height * 2 : height;
        int drawY = (form == Form.BIG) ? y - height : y;
        int drawWidth = width;

        Graphics2D g = (Graphics2D) g2d.create();

        if (!facingRight) {
            AffineTransform t = g.getTransform();
            g.translate(x + drawWidth, drawY);
            g.scale(-1, 1);
            g.translate(-x, -drawY);
            g = (Graphics2D) g;
        }

        Color flashColor = (state == State.STAR) ?
                new Color((int) (Math.sin(starTimer * 0.5) * 127 + 127),
                        (int) (Math.sin(starTimer * 0.3 + 2) * 127 + 127),
                        (int) (Math.sin(starTimer * 0.7 + 4) * 127 + 127)) : null;

        Color skinColor = (flashColor != null) ? flashColor : new Color(255, 200, 150);
        Color hatColor = (flashColor != null) ? flashColor : new Color(200, 50, 50);
        Color shirtColor = (state == State.FIRE) ? new Color(255, 80, 50) :
                (state == State.BIG) ? new Color(50, 100, 200) : new Color(50, 100, 255);
        Color pantsColor = new Color(100, 50, 20);
        Color shoeColor = new Color(100, 60, 30);

        g.setColor(hatColor);
        g.fillRect(x + 4, drawY, 24, 8);
        g.fillRect(x, drawY + 6, 32, 6);

        g.setColor(skinColor);
        g.fillRect(x + 8, drawY + 12, 16, 12);

        g.setColor(Color.BLACK);
        g.fillRect(x + 10, drawY + 16, 4, 4);
        g.fillRect(x + 18, drawY + 16, 4, 4);

        g.setColor(shirtColor);
        g.fillRect(x + 4, drawY + 24, 24, 14);

        if (state == State.FIRE && attacking) {
            g.setColor(Color.YELLOW);
            g.fillOval(x + 28, drawY + 26, 14, 10);
            g.setColor(Color.ORANGE);
            g.fillOval(x + 30, drawY + 28, 8, 6);
        }

        int legOffset = onGround ? (animationFrame % 2) * 4 : 2;

        g.setColor(pantsColor);
        g.fillRect(x + 6, drawY + 38, 8, 10 - legOffset);
        g.fillRect(x + 18, drawY + 38, 8, 10 - (4 - legOffset));

        g.setColor(shoeColor);
        g.fillRect(x + 4, drawY + drawHeight - 6 + (legOffset > 0 ? -2 : 0), 12, 6);
        g.fillRect(x + 16, drawY + drawHeight - 6 + (legOffset > 0 ? 2 : 0), 12, 6);

        if (form == Form.BIG) {
            g.setColor(skinColor);
            g.fillRect(x + 8, drawY + 56, 16, 12);
            g.setColor(shirtColor);
            g.fillRect(x + 4, drawY + 48, 24, 14);
        }

        g.dispose();
    }

    public void hit() {
        if (isDead || invincible) return;

        if (state == State.STAR) {
            return;
        }

        if (state == State.FIRE) {
            state = State.BIG;
            return;
        }

        if (state == State.BIG) {
            state = State.NORMAL;
            form = Form.SMALL;
            return;
        }

        lives--;
        if (lives <= 0) {
            isDead = true;
        } else {
            invincible = true;
            invincibleTimer = 120;
        }
    }

    public void respawn() {
        x = 100;
        y = 200;
        velocityY = 0;
        invincible = true;
        invincibleTimer = 120;
    }

    public void collectPowerUp(PowerUp powerUp) {
        switch (powerUp.getType()) {
            case MUSHROOM:
                state = State.BIG;
                form = Form.BIG;
                jumpForce = -12;
                break;
            case FIRE_FLOWER:
                state = State.FIRE;
                break;
            case STAR:
                state = State.STAR;
                starTimer = 600;
                break;
            case ONE_UP:
                lives++;
                break;
        }
    }

    public void collectCoin() {
        coinCount++;
    }

    public boolean isFalling() {
        return velocityY > 0;
    }

    public boolean isAttacking() {
        return attacking && state == State.FIRE;
    }

    public boolean canShoot() {
        return state == State.FIRE || state == State.NORMAL || state == State.BIG;
    }

    public void resetFireballCooldown() {
        fireballCooldown = 30;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public Rectangle getBounds() {
        int h = (form == Form.BIG) ? height * 2 : height;
        int dy = (form == Form.BIG) ? y - height : y;
        return new Rectangle(x + 4, dy, width - 8, h);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return (form == Form.BIG) ? y - height : y;
    }

    public int getDrawY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return (form == Form.BIG) ? height * 2 : height;
    }

    public int getLives() {
        return lives;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public boolean canTakeDamage() {
        return !invincible && state != State.STAR;
    }
}
