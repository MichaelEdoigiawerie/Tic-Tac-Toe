package com.gamestates;

import java.awt.*;

public class GameStateManager {

    private GameState menuState;
    private GameState singlePlayer;
    private GameState multiPlayer;
    private GameState currentState;

    public GameStateManager(){
        menuState = new Menu(this);
        singlePlayer = new SinglePlayer(this);
        multiPlayer = new MultiPlayer(this);
        currentState = menuState;
    }

    public void update(){
        currentState.update();
    }

    public void render(Graphics2D graphics){
        currentState.render(graphics);
    }

    public void setCurrentState(GameState newState){
        currentState = newState;
    }

    public GameState getCurrentState() {
        return currentState;
    }
    public GameState getMenuState(){
        return menuState;
    }

    public GameState getSinglePlayer(){
        return singlePlayer;
    }

    public GameState getMultiPlayer(){
        return multiPlayer;
    }
}
