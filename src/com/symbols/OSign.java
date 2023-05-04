package com.symbols;

import com.computer.Computer;
import com.canvas.Game;
import com.tictactoe.GameBoard;
import com.tictactoe.Tile;

import java.awt.*;
import java.awt.event.MouseEvent;

public class OSign extends Sign {

    private Computer computer;
    private Tile chosenTile;
    private int width;
    private int height;
    private int start;
    private int end;
    private int angle;
    private int delta;

    public OSign(GameBoard gameBoard, Computer computer){
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
        this.computer = computer;
    }

    public Tile getSignLocation() {
        return chosenTile;
    }

    public void update() {
        this.animateSign();
    }

    public void render(Graphics2D graphics) {
        int pShiftX = ((Game.getWidth() / 3) +
                (strokeWidth / 6)) * chosenTile.getCol();
        int pShiftY = ((Game.getHeight() / 3) +
                (strokeWidth / 6)) * chosenTile.getRow();
        graphics.setColor(signColor);
        graphics.setStroke(new BasicStroke(strokeWidth));
        graphics.drawArc(x + pShiftX, y + pShiftY, width, height, start, end);
    }

    public void chooseTile(){
        chosenTile = computer.getChosenTile();
    }

    public void mousePressed(MouseEvent event) {

    }

    public boolean hasBeenDrawn(){
        return end == angle;
    }

    public boolean isAbleToBeDrawn() {
        return true;
    }

    public int getRow() {
        return chosenTile.getRow();
    }

    public int getCol() {
        return chosenTile.getCol();
    }

    private void animateSign(){
        end += delta;
        if(end >= angle){
            end = angle;
        }
    }
}
