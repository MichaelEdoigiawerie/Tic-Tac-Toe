package com.gamestates;

import com.canvas.Game;

import java.awt.*;

public abstract class GameState {

    protected GameStateManager gameStateManager;
    protected int width;
    protected int height;

    public GameState(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        width = Game.getWidth();
        height = Game.getHeight();
    }

    public GameStateManager getGameStateManager(){
        return gameStateManager;
    }

    public abstract void update();
    public abstract void render(Graphics2D graphics);
}
