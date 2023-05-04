package com.tictactoe;

import com.canvas.Game;
import com.gamestates.GameState;
import com.symbols.Sign;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.security.Key;
import java.util.ArrayList;

public abstract class GameBoard {

    protected GameState gameState;
    protected ArrayList<Sign> signs;
    protected Tile[][] tiles;
    protected String[][] tileContents;
    protected String winner;
    protected Color bgColor;
    protected WinningStroke winningStroke;
    protected int[] lowerXBounds;
    protected int[] upperXBounds;
    protected int[] lowerYBounds;
    protected int[] upperYBounds;
    protected int index;
    protected int strokeWidth;
    protected int numLines;
    protected int x;
    protected int y;
    protected int vx;
    protected int hy;
    protected int xBound;
    protected int yBound;
    protected int dx;
    protected int dy;
    protected int r, g, b;
    protected int deltaC;
    protected boolean gridDrawn;
    protected boolean inputAllowed;
    protected boolean mouseInput;
    protected boolean leftToRightDiag;
    protected boolean rightToLeftDiag;
    protected boolean gameOver;

    public GameBoard(GameState gameState){
        this.gameState = gameState;
        strokeWidth = 30;
        numLines = 2;
        x = 0;
        y = 0;
        vx = Game.getWidth() / 3;
        hy = Game.getHeight() / 3;
        xBound = Game.getWidth();
        yBound = Game.getHeight();
        dx = 15;
        dy = 15;
        inputAllowed = false;
        r = 255;
        g = 255;
        b = 255;
        deltaC = 5;
        bgColor = new Color(r, g, b);
        index = 0;
        signs = new ArrayList<Sign>();
        lowerXBounds = new int[]{0, 325, 635};
        upperXBounds = new int[]{295, 605, 930};
        lowerYBounds = new int[]{0, 325, 635};
        upperYBounds = new int[]{295, 605, 930};
        tileContents = new String[3][3];
        leftToRightDiag = false;
        rightToLeftDiag = false;
    }

    protected boolean playerHasWon(){
        winner = "X";
        if((this.byRow() || this.byColumn() ||
                this.byDiagonal()) && winner.equals("X")){
            gameOver = true;
            return true;
        }
        return false;
    }

    protected boolean playerHasLost(){
        winner = "O";
        if((this.byRow() || this.byColumn() ||
                this.byDiagonal()) && winner.equals("O")){
            gameOver = true;
            return true;
        }
        return false;
    }

    protected boolean isATie(){
        if(!this.playerHasWon() && !this.playerHasLost()
                && this.boardIsFull()){
            gameOver = true;
            return true;
        }
        return false;
    }

    protected boolean boardIsFull(){
        if(signs.size() == tiles.length * tiles[0].length){
            return true;
        }
        return false;
    }

    protected boolean byRow(){
        for(int i = 0; i < tileContents.length; i++){
            int count = 0;
            for(int j = 0; j < tileContents[i].length; j++){
                if(tileContents[i][j] != null) {
                    if (tileContents[i][j].equals(winner)) {
                        count++;
                    }
                }
            }
            if(count == 3){
                return true;
            }
        }
        return false;
    }

    protected boolean byColumn(){
        for(int i = 0; i < tileContents.length; i++){
            int count = 0;
            for(int j = 0; j < tileContents[i].length; j++){
                if(tileContents[j][i] != null) {
                    if (tileContents[j][i].equals(winner)) {
                        count++;
                    }
                }
            }
            if(count == 3){
                return true;
            }
        }
        return false;
    }

    protected boolean byDiagonal(){
        int count = 0;
        for(int i = 0; i < tileContents.length; i++){
            if(tileContents[i][i] != null){
                if(tileContents[i][i].equals(winner)){
                    count++;
                }
                if(count == 3){
                    leftToRightDiag = true;
                    return true;
                }
            }
        }
        int invCount = 0;
        for(int i = 0; i < tileContents.length; i++){
            int j = tileContents.length - 1;
            if(tileContents[i][j - i] != null){
                if(tileContents[i][j - i].equals(winner)){
                    invCount++;
                }
                if(invCount == 3){
                    rightToLeftDiag = true;
                    return true;
                }
            }
        }
        return false;
    }

    protected void animateGrid(){
        x += dx;
        if(x >= xBound){
            x = xBound;
            dx = 0;
        }
        y += dy;
        if(y >= yBound){
            y = yBound;
            dy = 0;
        }
        gridDrawn = x == xBound && y == yBound;
    }

    protected void animateBgCol(){
        if(this.playerHasWon()){
            r -= deltaC;
            if(r <= Color.CYAN.getRed()){
                r = Color.CYAN.getRed();
            }
        }
        else if(this.playerHasLost()){
            g -= deltaC;
            b -= deltaC;
            if(g <= Color.PINK.getGreen()){
                g = Color.PINK.getGreen();
            }
            if(b <= Color.PINK.getBlue()){
                b = Color.PINK.getBlue();
            }
        }
        else if(this.isATie()){
            g -= deltaC;
            b -= deltaC;
            if(g <= Color.ORANGE.getGreen()){
                g = Color.ORANGE.getGreen();
            }
            if(b <= Color.ORANGE.getBlue()){
                b = Color.ORANGE.getBlue();
            }
        }
        bgColor = new Color(r, g, b);
    }

    public ArrayList<Sign> getSigns(){
        return signs;
    }

    public String[][] getTileContents(){
        return tileContents;
    }

    public Tile[][] getTiles(){
        return tiles;
    }

    public int[] getLowerXBounds(){
        return lowerXBounds;
    }

    public int[] getUpperXBounds(){
        return upperXBounds;
    }

    public int[] getLowerYBounds(){
        return lowerYBounds;
    }

    public int[] getUpperYBounds(){
        return upperYBounds;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getStrokeWidth(){
        return strokeWidth;
    }
    public abstract void update();
    public abstract void render(Graphics2D graphics);
    public abstract void mousePressed(MouseEvent event);
}
