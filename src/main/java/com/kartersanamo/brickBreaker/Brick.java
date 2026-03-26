package com.kartersanamo.brickBreaker;

import java.awt.*;

public class Brick {
    private final int width;
    private final int height;
    private boolean visible = true;

    public Brick(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics g, int x, int y) {
        if (!visible) return;
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }

    public boolean collidesWith(Ball ball, int x, int y) {
        if (!visible) return false;
        return ball.getX() + ball.getWidth() > x &&
                ball.getX() < x + width &&
                ball.getY() + ball.getHeight() > y &&
                ball.getY() < y + height;
    }
}
