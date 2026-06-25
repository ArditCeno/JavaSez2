package Lab3.MarioGame2;

import java.awt.*;

class Enemy2 {
    public enum Type {GOOMBA, KOOPA, FLY_KOOPA, BOWSER}

    public enum Direction {LEFT, RIGHT}

    private int x, y;
    private int width = 32;
    private int height = 32;
    private int speed = 2;
    private Direction direction = Direction.LEFT;
    private int animationFrame = 0;
    private int animationTimer = 0;
    private boolean alive = true;
    private boolean stomped = false;
    private int deathTimer = 0;
    private Type type = Type.GOOMBA;

    private int startX;
    private int startY;
    private int patrolRange = 100;
    private boolean isShell = false;
    private boolean shellMoving = false;
    private int shellTimer = 0;

    public Enemy2(int x, int y) {
        this(x, y, Type.GOOMBA, 100);
    }

    public Enemy2(int x, int y, Type type) {
        this(x, y, type, 100);
    }

    public Enemy2(int x, int y, Type type, int patrolRange) {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        this.type = type;
        this.patrolRange = patrolRange;

        if (type == Type.BOWSER) {
            width = 64;
            height = 64;
            speed = 1;
        }
    }

    public void update() {
        if (!alive) {
            deathTimer++;
            return;
        }

        if (stomped && isShell) {
            shellTimer++;
            if (shellTimer > 180 && !shellMoving) {
                isShell = false;
                stomped = false;
                shellMoving = false;
            }
            return;
        }

        if (type == Type.FLY_KOOPA && !stomped) {
            y += speed * (direction == Direction.LEFT ? -1 : 1);
            if (y <= startY - patrolRange || y >= startY + patrolRange) {
                direction = direction == Direction.LEFT ? Direction.RIGHT : Direction.LEFT;
            }
        } else if (!isShell || shellMoving) {
            int moveSpeed = isShell ? speed * 3 : speed;
            x += (direction == Direction.LEFT ? -moveSpeed : moveSpeed);

            if (x <= startX - patrolRange || x >= startX + patrolRange) {
                direction = direction == Direction.LEFT ? Direction.RIGHT : Direction.LEFT;
            }
        }

        animationTimer++;
        if (animationTimer > (isShell ? 5 : 10)) {
            animationFrame = (animationFrame + 1) % 2;
            animationTimer = 0;
        }
    }

    public void draw(Graphics2D g2d) {
        if (!alive && deathTimer > 30) return;

        Graphics2D g = (Graphics2D) g2d.create();

        switch (type) {
            case GOOMBA:
                drawGoomba(g);
                break;
            case KOOPA:
                drawKoopa(g);
                break;
            case FLY_KOOPA:
                drawFlyKoopa(g);
                break;
            case BOWSER:
                drawBowser(g);
                break;
        }

        g.dispose();
    }

    private void drawGoomba(Graphics2D g) {
        if (!alive) {
            g.translate(x + width / 2, y + height);
            g.rotate(Math.PI);
            g.translate(-x - width / 2, -y - height);
        }

        if (stomped) {
            g.translate(x, y + height - 16);
            g.scale(1, 0.5f);
            g.translate(-x, -y - height + 16);
        }

        g.setColor(new Color(139, 90, 43));
        g.fillRect(x + 4, y + 4, 24, 20);

        g.setColor(new Color(205, 133, 63));
        g.fillRect(x + 2, y + 6, 6, 14);
        g.fillRect(x + 24, y + 6, 6, 14);

        g.setColor(Color.BLACK);
        g.fillRect(x + 8, y + 8, 6, 6);
        g.fillRect(x + 18, y + 8, 6, 6);

        g.setColor(Color.WHITE);
        g.fillRect(x + 9, y + 9, 3, 3);
        g.fillRect(x + 19, y + 9, 3, 3);

        g.setColor(Color.BLACK);
        g.fillRect(x + 10, y + 16, 12, 4);

        if (animationFrame == 1 && alive) {
            g.fillRect(x + 2, y + 24, 8, 6);
            g.fillRect(x + 22, y + 24, 8, 6);
        } else {
            g.fillRect(x, y + 22, 10, 6);
            g.fillRect(x + 22, y + 22, 10, 6);
        }
    }

    private void drawKoopa(Graphics2D g) {
        if (isShell) {
            g.setColor(new Color(50, 150, 50));
            g.fillOval(x, y + height / 2, width, height / 2);
            g.setColor(new Color(30, 100, 30));
            g.fillOval(x + 4, y + height / 2 + 4, width - 8, height / 2 - 8);

            if (shellMoving) {
                g.setColor(Color.YELLOW);
                g.fillOval(x + width / 2 - 4, y + height / 2 + 8, 8, 8);
            }
        } else {
            if (stomped) {
                g.translate(x, y + height - 12);
                g.scale(1, 0.4f);
                g.translate(-x, -y - height + 12);
            }

            g.setColor(new Color(50, 150, 50));
            g.fillRect(x + 4, y, 24, 28);

            g.setColor(new Color(200, 50, 50));
            g.fillRect(x + 6, y + 2, 20, 8);

            g.setColor(Color.BLACK);
            g.fillRect(x + 8, y + 12, 4, 8);
            g.fillRect(x + 20, y + 12, 4, 8);

            if (animationFrame == 1 && alive) {
                g.fillRect(x - 4, y + 24, 10, 8);
                g.fillRect(x + 26, y + 24, 10, 8);
            } else {
                g.fillRect(x, y + 20, 8, 8);
                g.fillRect(x + 24, y + 20, 8, 8);
            }
        }
    }

    private void drawFlyKoopa(Graphics2D g) {
        g.setColor(new Color(100, 100, 200));
        g.fillRect(x + 4, y + 8, 24, 20);

        g.setColor(new Color(200, 50, 50));
        g.fillRect(x + 6, y + 10, 20, 6);

        g.setColor(Color.BLACK);
        g.fillRect(x + 8, y + 18, 6, 6);
        g.fillRect(x + 18, y + 18, 6, 6);

        g.setColor(Color.WHITE);
        g.fillRect(x + 9, y + 19, 3, 3);
        g.fillRect(x + 19, y + 19, 3, 3);

        g.setColor(new Color(100, 100, 200));
        int wingY = animationFrame == 0 ? y : y - 8;
        g.fillRect(x - 4, wingY, 8, 16);
        g.fillRect(x + 28, wingY, 8, 16);
    }

    private void drawBowser(Graphics2D g) {
        g.setColor(new Color(50, 150, 50));
        g.fillRect(x + 8, y, 48, 56);

        g.setColor(new Color(200, 80, 50));
        g.fillRect(x + 16, y + 8, 32, 16);

        g.setColor(Color.BLACK);
        g.fillRect(x + 16, y + 16, 8, 8);
        g.fillRect(x + 40, y + 16, 8, 8);

        g.setColor(Color.WHITE);
        g.fillRect(x + 17, y + 17, 4, 4);
        g.fillRect(x + 41, y + 17, 4, 4);

        g.setColor(new Color(150, 50, 30));
        g.fillRect(x + 20, y + 28, 24, 8);
        g.fillRect(x + 26, y + 36, 4, 12);
        g.fillRect(x + 32, y + 36, 4, 12);

        g.setColor(new Color(100, 100, 50));
        int spikeFrame = animationFrame == 0 ? 0 : -4;
        g.fillRect(x + 4, y - 8 + spikeFrame, 8, 12);
        g.fillRect(x + 16, y - 12 + spikeFrame, 8, 16);
        g.fillRect(x + 28, y - 16 + spikeFrame, 8, 20);
        g.fillRect(x + 40, y - 12 + spikeFrame, 8, 16);
        g.fillRect(x + 52, y - 8 + spikeFrame, 8, 12);
    }

    public void stomp() {
        if (type == Type.BOWSER) {
            return;
        }
        if (type == Type.KOOPA || type == Type.FLY_KOOPA) {
            if (!isShell) {
                isShell = true;
                stomped = true;
                shellTimer = 0;
                return;
            } else {
                shellMoving = true;
                return;
            }
        }
        stomped = true;
        alive = false;
    }

    public void defeat() {
        alive = false;
    }

    public Rectangle getBounds() {
        if (stomped && type != Type.KOOPA && type != Type.FLY_KOOPA) {
            return new Rectangle(x, y + height - 8, width, 8);
        }
        return new Rectangle(x + 2, y + 2, width - 4, height - 4);
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public Type getType() {
        return type;
    }
}
