package com.symbols;

import com.tictactoe.GameBoard;
import com.tictactoe.Tile;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Sign {

    protected GameBoard gameBoard;
    protected Color signColor;
    protected String symbolCode;
    protected int strokeWidth;
    protected int excessSpace;
    protected int x;
    protected int y;

    public Sign(GameBoard gameBoard){
        this.gameBoard = gameBoard;
        strokeWidth = 30;
        excessSpace = gameBoard.getStrokeWidth() + strokeWidth * 2;
    }

    protected Tile determineTile(int x, int y){
        for(int i = 0; i < gameBoard.getTiles().length; i++){
            for(int j = 0; j < gameBoard.getTiles()[i].length; j++){
                if(x > gameBoard.getTiles()[i][j].getLowerXBound() &&
                        x < gameBoard.getTiles()[i][j].getUpperXBound() &&
                        y > gameBoard.getTiles()[i][j].getLowerYBound() &&
                        y < gameBoard.getTiles()[i][j].getUpperYBound()){
                    return gameBoard.getTiles()[i][j];
                }
            }
        }
        return null;
    }

    public String getSymbolCode(){
        return symbolCode;
    }

    public abstract Tile getSignLocation();
    public abstract boolean hasBeenDrawn();
    public abstract boolean isAbleToBeDrawn();
    public abstract int getRow();
    public abstract int getCol();
    public abstract void update();
    public abstract void render(Graphics2D graphics);
    public abstract void mousePressed(MouseEvent event);
    public abstract void chooseTile();
}
