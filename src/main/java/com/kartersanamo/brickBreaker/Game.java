package com.kartersanamo.brickBreaker;

import javax.swing.*;

public class Game {
    private final int bricksPerRow = 10;
    private final int initialBrickRows = 3;

    private Player player;
    private Paddle paddle;
    private Ball ball;
    private Brick[] bricks;
    private JFrame frame;
    private GameState gameState;
    private int round;

    public Game() {
        player = new Player();
        paddle = new Paddle();
        ball = new Ball();
        bricks = new Brick[bricksPerRow * initialBrickRows];
        round = 0;
        gameState = GameState.NOT_STARTED;
        frame = new JFrame("BrickBreaker");
        buildFrame();
    }

    private void buildFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
