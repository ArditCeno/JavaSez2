package Lab3.MarioGame;

import java.awt.*;

public class Platform {
    protected int x, y, width, height;

    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g2d) {
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

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}