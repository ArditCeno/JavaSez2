package Lab3.MarioGame2;

import java.awt.*;

class Platform2 {
    public enum Type {GROUND, BRICK, QUESTION, BREAKABLE, MOVING}

    private int x, y, width, height;
    private Type type = Type.GROUND;

    private int startX, startY;
    private int moveSpeed = 2;
    private int moveRange = 0;
    private int direction = 1;
    private int moveTimer = 0;
    private boolean moving = false;
    private boolean broken = false;
    private boolean activated = false;
    private boolean vertical = false;

    public Platform2(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.startX = x;
        this.startY = y;
    }

    public Platform2(int x, int y, int width, int height, Type type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.startX = x;
        this.startY = y;
    }

    public Platform2(int x, int y, int width, int height, Type type, int moveRange, boolean vertical) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.startX = x;
        this.startY = y;
        this.moveRange = moveRange;
        this.vertical = vertical;
    }

    public void update() {
        if (moving && !broken) {
            moveTimer++;
            if (moveTimer > 10) {
                if (vertical) {
                    y += moveSpeed * direction;
                    if (y <= startY - moveRange || y >= startY + moveRange) {
                        direction *= -1;
                    }
                } else {
                    x += moveSpeed * direction;
                    if (x <= startX - moveRange || x >= startX + moveRange) {
                        direction *= -1;
                    }
                }
                moveTimer = 0;
            }
        }
    }

    public void draw(Graphics2D g2d) {
        if (broken) return;

        switch (type) {
            case GROUND:
                drawGround(g2d);
                break;
            case BRICK:
                drawBrick(g2d);
                break;
            case QUESTION:
                drawQuestion(g2d);
                break;
            case BREAKABLE:
                drawBreakable(g2d);
                break;
            case MOVING:
                drawMoving(g2d);
                break;
        }
    }

    private void drawGround(Graphics2D g2d) {
        g2d.setColor(new Color(139, 90, 43));
        g2d.fillRect(x, y, width, height);

        g2d.setColor(new Color(160, 82, 45));
        for (int i = 0; i < width; i += 40) {
            g2d.drawLine(x + i, y, x + i, y + height);
        }

        g2d.setColor(new Color(34, 139, 34));
        g2d.fillRect(x, y - 8, width, 12);

        g2d.setColor(new Color(0, 100, 0));
        for (int i = 0; i < width; i += 20) {
            int h = 4 + (i % 3) * 3;
            g2d.fillRect(x + i + 5, y - 8 - h, 4, h);
        }
    }

    private void drawBrick(Graphics2D g2d) {
        g2d.setColor(new Color(180, 80, 50));
        g2d.fillRect(x, y, width, height);

        g2d.setColor(new Color(140, 60, 40));
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < width; i += 20) {
            g2d.drawLine(x + i, y, x + i, y + height);
            if (i + 20 < width) {
                g2d.drawLine(x + i + 10, y, x + i + 10, y + height / 2);
                g2d.drawLine(x + i, y + height / 2, x + i + 20, y + height / 2);
            }
            g2d.drawLine(x + i, y + height / 2, x + i, y + height);
            g2d.drawLine(x + i + 10, y + height / 2, x + i + 10, y + height);
        }
    }

    private void drawQuestion(Graphics2D g2d) {
        Color blockColor = activated ? new Color(255, 200, 100) : new Color(255, 215, 0);

        g2d.setColor(blockColor);
        g2d.fillRect(x, y, width, height);

        g2d.setColor(new Color(200, 100, 0));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(x + 2, y + 2, width - 4, height - 4);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, height - 8));
        g2d.drawString("?", x + width / 2 - 6, y + height - 6);
    }

    private void drawBreakable(Graphics2D g2d) {
        g2d.setColor(new Color(180, 80, 50));
        g2d.fillRect(x, y, width, height);

        g2d.setColor(new Color(140, 60, 40));
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < width / 20; i++) {
            for (int j = 0; j < height / 20; j++) {
                g2d.drawRect(x + i * 20, y + j * 20, 20, 20);
            }
        }
    }

    private void drawMoving(Graphics2D g2d) {
        g2d.setColor(new Color(100, 100, 120));
        g2d.fillRect(x, y, width, height);

        g2d.setColor(new Color(150, 150, 180));
        g2d.fillRect(x, y, width, 8);

        g2d.setColor(new Color(200, 200, 50));
        for (int i = 0; i < width; i += 30) {
            g2d.fillOval(x + i + 5, y + 4, 10, 10);
        }
    }

    public void activate() {
        if (type == Type.QUESTION) {
            activated = true;
        }
    }

    public void startMoving() {
        moving = true;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public Type getType() {
        return type;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
