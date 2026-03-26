package com.kartersanamo.brickBreaker;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ArrowListener extends MouseMotionAdapter {
    private final Game game;

    public ArrowListener(Game game) {
        this.game = game;
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        game.setMousePosition(event.getX(), event.getY());
    }
}