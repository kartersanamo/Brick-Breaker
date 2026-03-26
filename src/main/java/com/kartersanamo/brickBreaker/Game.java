package com.kartersanamo.brickBreaker;

import javax.swing.*;
import java.awt.*;

public class Game {
    private static Game instance;
    private final int bricksPerRow = 10;
    private final int initialBrickRows = 3;
    private final int arrowLength = 75;

    private Player player;
    private Paddle paddle;
    private Ball ball;
    private Arrow arrow;
    private Brick[] bricks;
    private JFrame frame;
    private GamePanel panel;
    private GameState gameState;
    private int round;

    private int mouseX = 400;
    private int mouseY = 300;

    private final int width = 800;
    private final int height = 600;

    private Timer gameTimer;

    public Game() {
        instance = this;
        player = new Player();
        paddle = new Paddle();
        ball = new Ball();
        arrow = new Arrow();
        bricks = new Brick[bricksPerRow * initialBrickRows];

        int brickWidth = 60;
        int brickHeight = 20;
        for (int i = 0; i < initialBrickRows; i++) {
            for (int j = 0; j < bricksPerRow; j++) {
                int x = 10 + j * (brickWidth + 5);
                int y = 50 + i * (brickHeight + 5);
                Brick brick = new Brick(x, y, brickWidth, brickHeight);
                bricks[i * bricksPerRow + j] = brick;
            }
        }
        round = 0;
        gameState = GameState.NOT_STARTED;
        frame = new JFrame("Brick Breaker");
        buildFrame();
    }

    private void buildFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        panel = new GamePanel(this);
        panel.setPreferredSize(new Dimension(800, 600));
        panel.setBackground(Color.BLACK);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        // Start game loop timer (60 FPS)
        gameTimer = new Timer(1000 / 60, e -> {
            if (gameState == GameState.PLAYING) {
                ball.update();
            }
            panel.repaint();
        });
        gameTimer.start();
    }

    public int getMouseX() {
        return mouseX;
    }
    public int getMouseY() {
        return mouseY;
    }
    public void setMousePosition(int x, int y) {
        this.mouseX = x;
        this.mouseY = y;
        if (panel != null) panel.repaint();
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public Ball getBall() {
        return ball;
    }

    public Player getPlayer() {
        return player;
    }

    public double getArrowLength() {
        return arrowLength;
    }

    public Arrow getArrow() { return arrow; }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static Game getInstance() {
        return instance;
    }

    public GameState getGameState() { return gameState; }

    public Brick[] getBricks() { return bricks; }

    public void startGame() {
        // Called when splash is dismissed
        this.gameState = GameState.PLAYING;
        if (panel != null) {
            panel.repaint();
        }
    }

    public void setMouseX(int x) {
        this.mouseX = x;
    }

    public void setMouseY(int y) {
        this.mouseY = y;
    }

    public GamePanel getPanel() {
        return panel;
    }
}
