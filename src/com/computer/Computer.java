package com.computer;

import com.symbols.OSign;
import com.tictactoe.GameBoard;
import com.tictactoe.Tile;

import java.util.ArrayList;
import java.util.Random;

public class Computer {

    private GameBoard gameBoard;
    private ArrayList<Tile> availableTiles;
    private Random randomSeed;
    private Tile chosenTile;

    public Computer(GameBoard gameBoard){
        this.gameBoard = gameBoard;
        randomSeed = new Random();
    }

    private ArrayList<Tile> createTileOptions(){
        availableTiles = new ArrayList<Tile>();
        for(int i = 0; i < gameBoard.getTiles().length; i++){
            for(int j = 0; j < gameBoard.getTiles()[i].length; j++){
                if(!gameBoard.getTiles()[i][j].isClaimed(i, j)){
                    availableTiles.add(gameBoard.getTiles()[i][j]);
                } else {
                    availableTiles.remove(gameBoard.getTiles()[i][j]);
                }
            }
        }
        return availableTiles;
    }

    public Tile getChosenTile(){
        return chosenTile;
    }

    public void makeOption(){
        if(this.createTileOptions().size() > 0) {
            chosenTile = this.createTileOptions().get(
                    randomSeed.nextInt(this.createTileOptions().size()));
        }
    }

    public void playTurn(){
        if(chosenTile != null) {
            gameBoard.getSigns().add(0, new OSign(gameBoard, this));
            gameBoard.getSigns().get(0).chooseTile();
        }
    }
}
