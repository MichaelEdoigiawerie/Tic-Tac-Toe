package com.tictactoe;

import com.canvas.Game;

import java.awt.*;

public class WinningStroke {

    private GameBoard gameBoard;
    private Color strokeColor;
    private int[] midPoints;
    private int strokeWidth;
    private int x;
    private int y;
    private int x1;
    private int y1;
    private int xBound;
    private int yBound;
    private int dx;
    private int dy;
    private boolean init;

    public WinningStroke(GameBoard gameBoard){
        this.gameBoard = gameBoard;
        strokeColor = null;
        strokeWidth = gameBoard.getStrokeWidth();
        xBound = Game.getWidth() - (strokeWidth + 10);
        yBound = Game.getHeight() - (strokeWidth + 10);
        midPoints = new int[]{140, 465, 790};
        dx = 50;
        dy = 50;
        init = false;
    }

    public void update(){
        this.init();
        this.animateStroke();
    }

    public void render(Graphics2D graphics){
            graphics.setColor(strokeColor);
            graphics.setStroke(new BasicStroke(strokeWidth));
            graphics.drawLine(x, y, x1, y1);
    }

    public void setStrokeColor(Color color){
        strokeColor = color;
    }

    private void init(){
        if(!init) {
            if (gameBoard.byRow()) {
                x = strokeWidth + 10;
                x1 = x;
                y = midPoints[gameBoard.getSigns().get(0).getRow()];
                y1 = y;
            } else if (gameBoard.byColumn()) {
                x = midPoints[gameBoard.getSigns().get(0).getCol()];
                x1 = x;
                y = strokeWidth + 10;
                y1 = y;
            } else if (gameBoard.byDiagonal() && gameBoard.leftToRightDiag) {
                x = strokeWidth + 10;
                y = x;
                x1 = x;
                y1 = y;
            } else if (gameBoard.byDiagonal() && gameBoard.rightToLeftDiag) {
                x = xBound;
                y = strokeWidth + 10;
                x1 = x;
                y1 = y;
            }
        }
    }

    private void animateStroke(){
        init = true;
        if(gameBoard.byRow()){
            x1 += dx;
            if(x1 >= xBound){
                x1 = xBound;
            }
        }
        else if(gameBoard.byColumn()){
            y1 += dy;
            if(y1 >= yBound){
                y1 = yBound;
            }
        }
        else if(gameBoard.byDiagonal() && gameBoard.leftToRightDiag){
            x1 += dx;
            y1 += dy;
            if(x1 >= xBound && y1 >= yBound){
                x1 = xBound;
                y1 = yBound;
            }
        }
        else if(gameBoard.byDiagonal() && gameBoard.rightToLeftDiag){
            x1 -= dx;
            y1 += dy;
            if(x1 <= (strokeWidth + 10) && y1 >= yBound){
                x1 = strokeWidth + 10;
                y1 = yBound;
            }
        }
    }
}
