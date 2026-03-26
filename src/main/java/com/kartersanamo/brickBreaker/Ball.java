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
        moving = true;
        int cursorX = event.getX();
        int cursorY = event.getY();
        int centerX = x + width / 2;
        int centerY = y + height / 2;
        int dx = cursorX - centerX;
        int dy = cursorY - centerY;
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
        if (y + vy + height > Game.getInstance().getHeight()) {
            vx = 0;
            vy = 0;
            x = Game.getInstance().getWidth() / 2 - width / 2;
            y = Game.getInstance().getHeight() - Game.getInstance().getPaddle().getHeight() - height;
            moving = false;
        }

        for (Brick brick: Game.getInstance().getBricks()) {
            if (brick.collidesWith(this)) {
                vy = -vy;
                brick.setVisible(false);
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
