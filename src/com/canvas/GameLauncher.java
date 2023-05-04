package com.canvas;

public class GameLauncher {

    private static Game ticTacToe;

    public static void main(String[] args) {
        ticTacToe = new Game("Tic Tac Toe", 930, 930);
        ticTacToe.start();
    }
}
