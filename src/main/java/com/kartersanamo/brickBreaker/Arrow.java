package com.kartersanamo.brickBreaker;

import java.awt.*;

public class Arrow {

    public void paint(Graphics g) {
        Game game = Game.getInstance();
        if (game.getBall().isMoving()) return;
        Paddle paddle = game.getPaddle();
        int startX = paddle.getX() + 50;
        int startY = paddle.getY() - 10;
        int mouseX = game.getMouseX();
        int mouseY = game.getMouseY();
        int dx = mouseX - startX;
        int dy = mouseY - startY;
        double dist = Math.sqrt(dx * dx + dy * dy);
        double maxLen = game.getArrowLength();
        double scale = dist > maxLen ? maxLen / dist : 1.0;
        int endX = startX + (int)(dx * scale);
        int endY = startY + (int)(dy * scale);
        g.setColor(Color.WHITE);
        g.drawLine(startX, startY, endX, endY);
    }
}
