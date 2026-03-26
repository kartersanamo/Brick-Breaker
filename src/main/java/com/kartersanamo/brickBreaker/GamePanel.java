package com.kartersanamo.brickBreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GamePanel extends JPanel {
    private final Game game;

    public GamePanel(Game game) {
        this.game = game;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (game.getGameState() == GameState.NOT_STARTED && SwingUtilities.isLeftMouseButton(e)) {
                    game.startGame();
                } else {
                    game.getBall().shoot(e);
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                game.setMouseX(e.getX());
                game.setMouseY(e.getY());
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Paddle paddle = game.getPaddle();
        Ball ball = game.getBall();
        Player player = game.getPlayer();
        Arrow arrow = game.getArrow();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw game elements if not in splash
        if (game.getGameState() != GameState.NOT_STARTED) {
            for (Brick brick : game.getBricks()) {
                if (brick != null) brick.paint(g);
            }
            paddle.paint(g);
            ball.paint(g);
            arrow.paint(g);
            g.setColor(Color.WHITE);
            g.drawString("Score: " + player.getScore(), 10, 20);
        } else {
            // Draw splash overlay
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(100, 100, 100, 200));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setFont(new Font("Arial", Font.BOLD, 40));
            g2.setColor(Color.WHITE);
            FontMetrics fm = g2.getFontMetrics();
            String line1 = "Welcome to BrickBreaker";
            String line2 = "Left-Click Anywhere to Start!";
            int y = getHeight() / 2 - fm.getHeight();
            int x1 = (getWidth() - fm.stringWidth(line1)) / 2;
            int x2 = (getWidth() - fm.stringWidth(line2)) / 2;
            g2.drawString(line1, x1, y);
            g2.drawString(line2, x2, y + fm.getHeight() + 20);
            g2.dispose();
        }
    }
}
