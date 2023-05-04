package com.userinput;

import com.gamestates.GameStateManager;
import com.gamestates.Menu;
import com.gamestates.MultiPlayer;
import com.gamestates.SinglePlayer;

import java.awt.event.*;

public class UserInputManager
        implements KeyListener, MouseListener {

    private Menu menu;
    private SinglePlayer singlePlayer;
    private MultiPlayer multiPlayer;

    public UserInputManager(GameStateManager gameStateManager){
        menu = (Menu) gameStateManager.getMenuState();
        singlePlayer = (SinglePlayer) gameStateManager.getSinglePlayer();
        multiPlayer = (MultiPlayer) gameStateManager.getMultiPlayer();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        menu.keyPressed(event);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        singlePlayer.mousePressed(event);
        multiPlayer.mousePressed(event);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        singlePlayer.mouseReleased(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {

    }

    @Override
    public void keyTyped(KeyEvent event) {

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
