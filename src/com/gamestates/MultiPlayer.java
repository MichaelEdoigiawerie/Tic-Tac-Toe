package com.gamestates;

import com.tictactoe.MultiPlayerGameBoard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MultiPlayer extends GameState{

    private MultiPlayerGameBoard gameBoard;

    public MultiPlayer(GameStateManager gameStateManager){
        super(gameStateManager);
        gameBoard = new MultiPlayerGameBoard(this);
    }

    public void update(){
        gameBoard.update();
    }

    public void render(Graphics2D graphics){
        gameBoard.render(graphics);
    }

    public void mousePressed(MouseEvent event){
        gameBoard.mousePressed(event);
    }
}
