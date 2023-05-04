package com.tictactoe;

import com.canvas.Game;
import com.gamestates.GameState;
import com.symbols.OSignMulti;
import com.symbols.Sign;
import com.symbols.XSign;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MultiPlayerGameBoard extends GameBoard {

    private boolean xTurn;
    private Sign sign;

    public MultiPlayerGameBoard(GameState gameState){
        super(gameState);
        tiles = new Tile[3][3];
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                tiles[i][j] = new Tile(i, j, this);
            }
        }
        winningStroke = new WinningStroke(this);
        xTurn = true;
    }

    public void update() {
        this.animateGrid();
        if(gridDrawn){
            inputAllowed = true;
            mouseInput = true;
        }
        if(this.playerHasWon()){
            winningStroke.setStrokeColor(Color.GREEN);
        } else {
            winningStroke.setStrokeColor(Color.ORANGE);
        }
        if(this.playerHasWon() || this.playerHasLost() || this.isATie()){
            mouseInput = false;
            winningStroke.update();
            this.animateBgCol();
        }
        if(inputAllowed){
            if(signs.size() > 0){
                for(int i = 0; i < signs.size(); i++) {
                    signs.get(i).update();
                    for(int j = 0; j < tileContents.length; j++) {
                        for (int k = 0; k < tileContents[j].length; k++) {
                            if (signs.get(i).getSignLocation().getRow() == j &&
                                    signs.get(i).getSignLocation().getCol() == k) {
                                tileContents[j][k] = signs.get(i).getSymbolCode();
                            }
                        }
                    }
                }
            }
        }
    }


    public void render(Graphics2D graphics) {
        graphics.setColor(bgColor);
        graphics.fillRect(0, 0, Game.getWidth(), Game.getHeight());
        graphics.setColor(Color.BLACK);
        graphics.setStroke(new BasicStroke(strokeWidth));
        for(int i = 0; i < numLines; i++) {
            graphics.drawLine(
                    0, hy * (i + 1),
                    x, hy * (i + 1));
            graphics.drawLine(vx * (i + 1), 0,
                    vx * (i + 1), y);
        }
        if(inputAllowed) {
            if (signs.size() > 0){
                for (int i = 0; i < signs.size(); i++) {
                    signs.get(i).render(graphics);
                }
            }
        }
        if((this.playerHasWon() || this.playerHasLost()) && !mouseInput){
            winningStroke.render(graphics);
        }
    }

    public void mousePressed(MouseEvent event){
        sign = new XSign(this);
        if(!xTurn){
            sign = new OSignMulti(this);
        }
        if(inputAllowed && mouseInput) {
            if (event.getButton() == MouseEvent.BUTTON1) {
                signs.add(index, sign);
                signs.get(index).mousePressed(event);
                if(!signs.get(index).isAbleToBeDrawn()){
                    signs.remove(index);
                    return;
                }
                if(signs.size() > 1){
                    if (tiles[signs.get(index).getRow()]
                            [signs.get(index).getCol()].isClaimed(
                            signs.get(index).getRow(), signs.get(index).getCol())) {
                        signs.remove(index);
                    }
                }
            }
            this.switchTurns();
        }
    }

    private void switchTurns(){
        xTurn = !xTurn;
    }
}
