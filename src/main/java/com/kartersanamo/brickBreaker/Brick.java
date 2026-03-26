package com.kartersanamo.brickBreaker;

import java.awt.*;

public class Brick {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible = true;

    public Brick(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics g) {
        if (!visible) return;
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }

    public boolean collidesWith(Ball ball) {
        if (!visible) return false;
        return ball.getX() + ball.getWidth() > x &&
                ball.getX() < x + width &&
                ball.getY() + ball.getHeight() > y &&
                ball.getY() < y + height;
    }
}
