package Lab3.MarioGame2;

import java.awt.*;

class PowerUp {
    public enum Type {MUSHROOM, FIRE_FLOWER, STAR, ONE_UP}

    private int x, y;
    private int size = 32;
    private Type type;
    private boolean active = true;
    private boolean collected = false;
    private int velocityY = 0;
    private int animationFrame = 0;
    private int animationTimer = 0;
    private int startY;
    private boolean emerging = false;
    private int emergeTimer = 0;
    private int groundY = 420;

    public void setGroundY(int groundY) {
        this.groundY = groundY;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public int getSize() {
        return size;
    }

    public void land(int newY) {
        y = newY;
        velocityY = 0;
    }

    public PowerUp(int x, int y, Type type) {
        this.x = x;
        this.y = y;
        this.startY = y;
        this.type = type;
        if (type == Type.MUSHROOM || type == Type.ONE_UP) {
            emerging = true;
            this.y -= size;
        }
    }

    public void update() {
        if (!active || collected) return;

        if (emerging) {
            emergeTimer++;
            y -= 2;
            if (emergeTimer >= 16) {
                emerging = false;
                y = startY - size;
            }
            return;
        }

        velocityY += 1;
        if (velocityY > 8) velocityY = 8;
        y += velocityY;

        if (y + size > groundY) {
            y = groundY - size;
            velocityY = 0;
        }

        animationTimer++;
        if (animationTimer > 8) {
            animationFrame = (animationFrame + 1) % 2;
            animationTimer = 0;
        }
    }

    public void draw(Graphics2D g2d) {
        if (!active || collected) return;

        Graphics2D g = (Graphics2D) g2d.create();

        switch (type) {
            case MUSHROOM:
                drawMushroom(g);
                break;
            case FIRE_FLOWER:
                drawFireFlower(g);
                break;
            case STAR:
                drawStar(g);
                break;
            case ONE_UP:
                drawOneUp(g);
                break;
        }

        g.dispose();
    }

    private void drawMushroom(Graphics2D g) {
        int offset = animationFrame * 2;
        int stemHeight = 10;
        
        g.setColor(new Color(220, 20, 60));
        g.fillArc(x + 2, y + offset - 4, size - 4, size, 0, 360);
        
        g.setColor(Color.WHITE);
        g.fillOval(x + 5, y + 4 + offset, 7, 7);
        g.fillOval(x + 20, y + 8 + offset, 6, 6);
        g.fillOval(x + 10, y + 14 + offset, 5, 5);
        
        g.setColor(new Color(139, 69, 19));
        g.fillRect(x + 11, y + size - stemHeight + offset, 10, stemHeight);
        
        g.setColor(new Color(180, 100, 50));
        g.fillRect(x + 12, y + size - stemHeight + offset, 3, stemHeight - 2);
    }

    private void drawFireFlower(Graphics2D g) {
        int bounce = animationFrame * 2;
        
        g.setColor(new Color(34, 139, 34));
        g.fillRect(x + 13, y + 18 + bounce, 6, 14);
        
        g.setColor(new Color(255, 69, 0));
        g.fillOval(x + 3, y + 3 + bounce, 12, 10);
        
        g.setColor(new Color(255, 165, 0));
        g.fillOval(x + 17, y + 3 + bounce, 12, 10);
        
        g.setColor(new Color(255, 255, 0));
        g.fillOval(x + 10, y + bounce, 12, 10);
        
        g.setColor(Color.WHITE);
        g.fillOval(x + 13, y + 6 + bounce, 4, 4);
        
        g.setColor(new Color(34, 139, 34));
        g.fillRect(x + 13, y + 20 + bounce, 6, 12);
    }

    private void drawStar(Graphics2D g) {
        int bounce = animationFrame * 3;
        
        int starCenterX = x + 16;
        int starCenterY = y + 16 + bounce;
        
        g.setColor(new Color(255, 215, 0));
        Polygon star = new Polygon();
        for (int i = 0; i < 5; i++) {
            double outerAngle = Math.toRadians(i * 72 - 90);
            int outerX = starCenterX + (int)(Math.cos(outerAngle) * 14);
            int outerY = starCenterY + (int)(Math.sin(outerAngle) * 14);
            star.addPoint(outerX, outerY);
            
            double innerAngle = Math.toRadians(i * 72 - 90 + 36);
            int innerX = starCenterX + (int)(Math.cos(innerAngle) * 6);
            int innerY = starCenterY + (int)(Math.sin(innerAngle) * 6);
            star.addPoint(innerX, innerY);
        }
        g.fillPolygon(star);
        
        g.setColor(Color.WHITE);
        g.fillRect(starCenterX - 2, starCenterY - 2, 4, 4);
    }

    private void drawOneUp(Graphics2D g) {
        int offset = animationFrame * 2;
        
        g.setColor(new Color(34, 139, 34));
        g.fillArc(x + 2, y - 4 + offset, size - 4, size + 4, 180, 180);
        
        g.setColor(new Color(255, 255, 255));
        g.fillOval(x + 4, y + 6 + offset, 8, 8);
        
        g.setColor(new Color(34, 139, 34));
        g.fillRect(x + 10, y + size - 12 + offset, 12, 10);
        
        g.setColor(new Color(255, 0, 0));
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("1", x + 10, y + 22 + offset);
    }

    public void collect() {
        collected = true;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public Type getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
