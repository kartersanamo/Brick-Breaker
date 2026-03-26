package com.kartersanamo.brickBreaker;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Ball {
	private int x;
	private int y;
    private double vx = 0;
    private double vy = 0;
    double speed = 8; // in pixels per second
    private final int width = 20;
    private final int height = 20;
    private final Color color = Color.LIGHT_GRAY;
    private boolean moving = false;

    public Ball() {
        x = Game.getInstance().getWidth() / 2 - width / 2;
        y = Game.getInstance().getHeight() - Game.getInstance().getPaddle().getHeight() - height;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }

    public void shoot(MouseEvent event) {
        if (moving) { return; }
        // Get arrow base (same as Arrow.java)
        Paddle paddle = Game.getInstance().getPaddle();
        int baseX = paddle.getX() + 50;
        int baseY = paddle.getY() - 10;
        // Place ball at arrow base
        x = baseX - width / 2;
        y = baseY - height / 2;
        moving = true;
        int cursorX = event.getX();
        int cursorY = event.getY();
        int dx = cursorX - baseX;
        int dy = cursorY - baseY;
        double angle = Math.atan2(dy, dx);
        vx = speed * Math.cos(angle);
        vy = speed * Math.sin(angle);
    }

    public void update() {
        // off the right side, bounce
        if (x + vx + width > Game.getInstance().getWidth()) {
            vx = -vx;
            x =  Game.getInstance().getWidth() - width;
        }
        // off the left side, bounce
        if (x + vx < 0) {
            vx = -vx;
            x = 0;
        }
        // off the top, bounce
        if (y + vy < 0) {
            vy = -vy;
            y = 0;
        }
        // the bottom, reset
        if (y + height > Game.getInstance().getHeight()) {
            vx = 0;
            vy = 0;
            x = Game.getInstance().getPaddle().getX() + (Game.getInstance().getPaddle().getWidth() / 2) - (width / 2);
            y = Game.getInstance().getPaddle().getY() - height;
            Game.getInstance().newRound();
            moving = false;
        }

        for (int row = 0; row < Game.getInstance().getBricks().length; row++) {
            for (int col = 0; col < Game.getInstance().getBricks()[row].length; col++) {
                Brick brick = Game.getInstance().getBricks()[row][col];
                if (brick != null && brick.isVisible()) {
                    int brickWidth = (Game.getInstance().getWidth() / Game.getInstance().getBricks()[row].length) - 6;
                    int brickHeight = 20;
                    int x = 10 + col * (brickWidth + 5);
                    int y = 50 + row * (brickHeight + 5);
                    if (brick.collidesWith(this, x, y)) {
                        vy = -vy;
                        brick.setVisible(false);
                        Game.getInstance().getPlayer().incScore();
                    }
                }
            }
        }

        if (Game.getInstance().getPaddle().collidesWith(this)) {
            vy = -vy;
            y = Game.getInstance().getHeight() - Game.getInstance().getPaddle().getHeight() - height;
        }

        x += vx;
        y += vy;
    }

    public int getX() {
        return x;
    }

    public void incX(int inc) {
        if (moving) { return; }
        x += inc;
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

    public boolean isMoving() {
        return moving;
    }
}
