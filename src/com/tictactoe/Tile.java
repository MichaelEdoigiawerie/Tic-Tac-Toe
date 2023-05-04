package com.tictactoe;

public class Tile {

    private GameBoard gameBoard;
    private int row;
    private int col;
    private int lowerXBound;
    private int upperXBound;
    private int lowerYBound;
    private int upperYBound;

    public Tile(int row, int col, GameBoard gameBoard){
        this.gameBoard = gameBoard;
        this.row = row;
        this.col = col;
        lowerXBound = gameBoard.getLowerXBounds()[col];
        upperXBound = gameBoard.getUpperXBounds()[col];
        lowerYBound = gameBoard.getLowerYBounds()[row];
        upperYBound = gameBoard.getUpperYBounds()[row];
    }

    public boolean isClaimed(int row, int col){
        for(int i = 0; i < gameBoard.getTileContents().length; i++){
            for(int j = 0; j < gameBoard.getTileContents()[i].length; j++){
                if(i == row && j == col) {
                    if (gameBoard.getTileContents()[i][j] != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getLowerXBound(){
        return lowerXBound;
    }

    public int getUpperXBound(){
        return upperXBound;
    }

    public int getLowerYBound(){
        return lowerYBound;
    }

    public int getUpperYBound(){
        return upperYBound;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    @Override
    public String toString(){
        return "Row: " + row + ", Col: " + col;
    }
}
