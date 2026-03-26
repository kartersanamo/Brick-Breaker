package com.kartersanamo.brickBreaker;

import java.awt.*;

public class Paddle {
    private int x;
    private final int speed = 5;
    private int y;
    private final int height = 10;
    private final int width = 100;

    public Paddle() {
        x = Game.getInstance().getWidth() / 2 - (width / 2);
        y = Game.getInstance().getHeight() - height;
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public boolean collidesWith(Ball ball) {
        return ball.getX() + ball.getWidth() > x &&
                ball.getX() < x + width &&
                ball.getY() + ball.getHeight() > y &&
                ball.getY() < y + height;
    }

    public void moveLeft() {
        if (x < 0) return;
        Game.getInstance().getBall().incX(-speed);
        x -= speed;
    }

    public void moveRight() {
        if (x + width > Game.getInstance().getWidth()) return;
        Game.getInstance().getBall().incX(speed);
        x += speed;
    }
}
