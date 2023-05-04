package com.symbols;

import com.canvas.Game;
import com.tictactoe.GameBoard;
import com.tictactoe.Tile;

import java.awt.*;
import java.awt.event.MouseEvent;

public class XSign extends Sign{

    private Tile clickedTile;
    private int x1;
    private int y1;
    private int invX;
    private int invY;
    private int invX1;
    private int invY1;
    private int dx;
    private int dy;
    private int invDX;
    private int invDY;

    public XSign(GameBoard gameBoard){
        super(gameBoard);
        x = excessSpace - strokeWidth;
        y = excessSpace - strokeWidth;
        x1 = x;
        y1 = y;
        invX1 = Game.getWidth() / 3 - excessSpace;
        invY1 = excessSpace - strokeWidth;
        invX = invX1;
        invY = invY1;
        signColor = Color.BLUE;
        symbolCode = "X";
        dx = 20;
        dy = 20;
        invDX = 20;
        invDY = 20;
    }

    public void update() {
        this.animateSign();
    }

    public void render(Graphics2D graphics) {
        int positionShiftX =
                ((Game.getWidth() / 3) + (strokeWidth / 2)) * clickedTile.getCol();
        int positionShiftY =
                ((Game.getHeight() / 3) + (strokeWidth / 2)) * clickedTile.getRow();
        graphics.setColor(signColor);
        graphics.setStroke(new BasicStroke(strokeWidth));
        graphics.drawLine(x + positionShiftX, y + positionShiftY,
                x1 + positionShiftX, y1 + positionShiftY);
        if(dx == 0 && dy == 0) {
            graphics.drawLine(invX + positionShiftX, invY + positionShiftY,
                    invX1 + positionShiftX, invY1 + positionShiftY);
        }
    }

    public Tile getSignLocation(){
        return gameBoard.getTiles()[clickedTile.getRow()][clickedTile.getCol()];
    }

    public void mousePressed(MouseEvent event){
        clickedTile = this.determineTile(event.getX(), event.getY());
    }

    public boolean hasBeenDrawn(){
        return x1 == Game.getWidth() / 3 - excessSpace &&
                y1 == Game.getHeight() / 3 - excessSpace &&
                invX == excessSpace - strokeWidth &&
                invY == Game.getHeight() / 3 - excessSpace;
    }

    public boolean isAbleToBeDrawn(){
        return clickedTile != null;
    };

    public int getRow(){
        return clickedTile.getRow();
    }

    public int getCol() {
        return clickedTile.getCol();
    }

    private void animateSign(){
        x1 += dx;
        y1 += dy;
        if(dx == 0 && dy == 0) {
            invX -= invDX;
            invY += invDY;
        }
        if(x1 >= Game.getWidth() / 3 - excessSpace){
            x1 = Game.getWidth() / 3 - excessSpace;
            dx = 0;
        }
        if(y1 >= Game.getHeight() / 3 - excessSpace){
            y1 = Game.getHeight() / 3 - excessSpace;
            dy = 0;
        }
        if(invX <= excessSpace - strokeWidth){
            invX = excessSpace - strokeWidth;
            invDX = 0;
        }
        if(invY >= Game.getHeight() / 3 - excessSpace){
            invY = Game.getHeight() / 3 - excessSpace;
            invDY = 0;
        }
    }

    public void chooseTile() {

    }
}
