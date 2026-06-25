package Lab3.MarioGame2;

import java.awt.*;
import java.awt.AlphaComposite;

class Coin2 {
    private int x, y;
    private int radius = 12;
    private int animationFrame = 0;
    private int animationTimer = 0;
    private boolean collected = false;
    private int collectAnimation = 0;
    private int value = 1;

    public Coin2(int x, int y) {
        this(x, y, 1);
    }

    public Coin2(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public void update() {
        if (collected) {
            collectAnimation++;
        } else {
            animationTimer++;
            if (animationTimer > 10) {
                animationFrame = (animationFrame + 1) % 6;
                animationTimer = 0;
            }
        }
    }

    public void draw(Graphics2D g2d) {
        if (collected && collectAnimation > 20) return;

        Graphics2D g = (Graphics2D) g2d.create();

        if (collected) {
            g.translate(x, y - collectAnimation * 3);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1 - collectAnimation / 20f));
        }

        int[] widths = {2, 8, 14, 8, 2};

        for (int i = 0; i < widths.length; i++) {
            int alpha = 255 - Math.abs(i - 2) * 40;
            g.setColor(new Color(255, 215, 0, alpha));
            int w = widths[i];
            g.fillOval(x - w / 2, y - radius, w, radius * 2);
        }

        g.setColor(new Color(255, 245, 100));
        g.fillOval(x - 4, y - 6, 8, 12);

        g.setColor(new Color(200, 160, 0));
        g.drawString("$", x - 4, y + 5);

        g.dispose();
    }

    public void collect() {
        collected = true;
    }

    public Rectangle getBounds() {
        return new Rectangle(x - radius, y - radius, radius * 2, radius * 2);
    }

    public int getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
