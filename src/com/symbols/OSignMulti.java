package com.symbols;

import com.canvas.Game;
import com.tictactoe.GameBoard;
import com.tictactoe.Tile;

import java.awt.*;
import java.awt.event.MouseEvent;

public class OSignMulti extends Sign {

    private Tile clickedTile;
    private int width;
    private int height;
    private int start;
    private int end;
    private int angle;
    private int delta;

    public OSignMulti(GameBoard gameBoard){
        super(gameBoard);
        symbolCode = "O";
        signColor = Color.RED;
        width = 200;
        height = 200;
        start = 90;
        end = start;
        delta = 15;
        angle = 450;
        x = 50;
        y = 50;
    }

    public Tile getSignLocation() {
        return gameBoard.getTiles()[clickedTile.getRow()][clickedTile.getCol()];
    }

    public void update() {
        this.animateSign();
    }

    public void render(Graphics2D graphics) {
        int pShiftX = ((Game.getWidth() / 3) +
                (strokeWidth / 6)) * clickedTile.getCol();
        int pShiftY = ((Game.getHeight() / 3) +
                (strokeWidth / 6)) * clickedTile.getRow();
        graphics.setColor(signColor);
        graphics.setStroke(new BasicStroke(strokeWidth));
        graphics.drawArc(x + pShiftX, y + pShiftY, width, height, start, end);
    }

    public void mousePressed(MouseEvent event) {
        clickedTile = this.determineTile(event.getX(), event.getY());
    }

    public void chooseTile() {

    }

    public boolean hasBeenDrawn(){
        return end == angle;
    }

    public boolean isAbleToBeDrawn() {
        return clickedTile != null;
    }

    public int getRow() {
        return clickedTile.getRow();
    }

    public int getCol() {
        return clickedTile.getCol();
    }

    private void animateSign(){
        end += delta;
        if(end >= angle){
            end = angle;
        }
    }
}

