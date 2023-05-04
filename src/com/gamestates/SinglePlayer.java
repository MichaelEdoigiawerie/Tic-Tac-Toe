package com.gamestates;

import com.tictactoe.SinglePlayerGameBoard;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class SinglePlayer extends GameState {

    private SinglePlayerGameBoard gameBoard;

    public SinglePlayer(GameStateManager gameStateManager){
        super(gameStateManager);
        gameBoard = new SinglePlayerGameBoard(this);
    }

    public void update(){
        gameBoard.update();
    }

    public void render(Graphics2D graphics){
        gameBoard.render(graphics);
    }

    public void mousePressed(MouseEvent event) {
        gameBoard.mousePressed(event);
    }

    public void mouseReleased(MouseEvent event){
        gameBoard.mouseReleased(event);
    }
}
