package com.gamestates;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Menu extends GameState{

    private BufferedImage bufferedImage;
    private Color textColor;
    private Color choiceColor;
    private Font font;
    private Font choiceFont;
    private String[] options;
    private int currentChoice;
    private boolean isSelected;

    public Menu(GameStateManager gameStateManager){
        super(gameStateManager);
        font = new Font("Cooper Black", Font.BOLD, 100);
        choiceFont = new Font("Cooper Black", Font.PLAIN, font.getSize() / 2);
        isSelected = false;
        try{
            bufferedImage = ImageIO.read(
                    this.getClass().getResourceAsStream(
                            "/com/Resources/GameBackground.jpg")
            );
        } catch(Exception error){
            error.printStackTrace();
        }
        textColor = Color.BLACK;
        choiceColor = Color.GRAY;
        currentChoice = 0;
        options = new String[]{
                "Single Player",
                "Multi-Player",
                "Quit Game"
        };
    }

    public void update(){
        if(isSelected) {
            if (currentChoice == 0) {
                gameStateManager.setCurrentState(gameStateManager.getSinglePlayer());
            } else if (currentChoice == 1) {
                gameStateManager.setCurrentState(gameStateManager.getMultiPlayer());
            } else {
                System.exit(0);
            }
        }
    }

    public void render(Graphics2D graphics){
        graphics.drawImage(
                bufferedImage, 0, 0, width, height, null);
        graphics.setColor(textColor);
        graphics.setFont(font);
        graphics.drawString("Tic Tac Toe",
                width / 2 - font.getSize() * 2, 150);
        graphics.setFont(choiceFont);
        for(int i = 0; i < options.length; i++){
            if(i == currentChoice){
                graphics.setColor(choiceColor);
            } else {
                graphics.setColor(textColor);
            }
            graphics.drawString(
                    options[i], width / 2 - choiceFont.getSize(),
                    200 + (choiceFont.getSize() * (i + 1)));
        }
    }

    public void keyPressed(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_DOWN){
            currentChoice++;
            if(currentChoice == options.length){
                currentChoice = 0;
            }
        }
        if(event.getKeyCode() == KeyEvent.VK_UP){
            currentChoice--;
            if(currentChoice == -1){
                currentChoice = options.length - 1;
            }
        }
        if(event.getKeyCode() == KeyEvent.VK_ENTER){
            isSelected = true;
        }
    }
}
