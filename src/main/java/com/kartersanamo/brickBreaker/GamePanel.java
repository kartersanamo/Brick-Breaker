package com.kartersanamo.brickBreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private final Game game;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    public GamePanel(Game game) {
        this.game = game;
        setFocusable(true);
        requestFocusInWindow();
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
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) movingLeft = true;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) movingRight = true;
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) movingLeft = false;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) movingRight = false;
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
            int brickCols = game.getBricks()[0].length;
            int brickRows = game.getBricks().length;
            int brickWidth = (game.getWidth() / brickCols) - 6;
            int brickHeight = 20;
            for (int row = 0; row < brickRows; row++) {
                for (int col = 0; col < brickCols; col++) {
                    Brick brick = game.getBricks()[row][col];
                    if (brick != null && brick.isVisible()) {
                        int x = 10 + col * (brickWidth + 5);
                        int y = 50 + row * (brickHeight + 5);
                        brick.paint(g, x, y);
                    }
                }
            }
            paddle.paint(g);
            ball.paint(g);
            arrow.paint(g);
            g.setColor(Color.WHITE);
            g.drawString("Score: " + player.getScore(), 10, 20);
            g.drawString("Round: " + Game.getInstance().getRound(), Game.getInstance().getWidth() - 80, 20);
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

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }
}
