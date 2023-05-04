package com.canvas;

import com.gamestates.GameStateManager;
import com.userinput.UserInputManager;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    private GameCanvas gameCanvas;
    private String title;
    private Thread gameThread;
    private BufferStrategy bufferStrategy;
    private Graphics2D graphics;
    private UserInputManager userInputManager;
    private static GameStateManager gameStateManager;
    private static int WIDTH;
    private static int HEIGHT;
    private boolean isRunning;

    public Game(String title, int width, int height){
        this.title = title;
        WIDTH = width;
        HEIGHT = height;
        isRunning = false;
    }

    private void init(){
        gameCanvas = new GameCanvas(title, WIDTH, HEIGHT);
        gameStateManager = new GameStateManager();
        userInputManager = new UserInputManager(gameStateManager);
        gameCanvas.getFrame().addKeyListener(userInputManager);
        gameCanvas.getCanvas().addMouseListener(userInputManager);
    }

    private void update(){
        gameStateManager.update();
    }

    private void render(){
        bufferStrategy = gameCanvas.getCanvas().getBufferStrategy();
        if(bufferStrategy == null){
            gameCanvas.getCanvas().createBufferStrategy(3);
            return;
        }
        graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
        gameStateManager.render(graphics);
        bufferStrategy.show();
        graphics.dispose();
    }

    @Override
    public void run(){
        this.init();
        int FPS = 60;
        double delay = 1000000000 / FPS;
        double delta = 0;
        long currentTime;
        long previousTime = System.nanoTime();
        while(isRunning) {
            currentTime = System.nanoTime();
            delta += (currentTime - previousTime) / delay;
            previousTime = currentTime;
            if(delta >= 1) {
                this.update();
                this.render();
                delta--;
            }
        }
        this.stop();
    }

    public synchronized void start(){
        if(!isRunning){
            isRunning = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public synchronized void stop(){
        if(!isRunning){
            return;
        } else {
            isRunning = false;
            try {
                gameThread.join();
            } catch (InterruptedException error) {
                error.printStackTrace();
            }
        }
    }

    public static GameStateManager getGameStateManager(){
        return gameStateManager;
    }

    public static int getWidth(){
        return WIDTH;
    }

    public static int getHeight(){
        return HEIGHT;
    }
}
