package com.kartersanamo.brickBreaker;

public class Player {
    private int score;

    public Player() {
        score = 0;
    }

    public int getScore() {
        return score;
    }
    public void incScore() {
        score++;
    }
}
