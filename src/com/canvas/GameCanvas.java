package com.canvas;

import javax.swing.*;
import java.awt.*;

public class GameCanvas {

    private ImageIcon iconImage;
    private JFrame frame;
    private Canvas canvas;
    private Dimension dimensions;
    private String title;
    private int width;
    private int height;

    public GameCanvas(String title, int width, int height){
        iconImage = new ImageIcon(this.getClass().getResource(
                "/com/Resources/gameIcon.png"));
        frame = new JFrame(title);
        frame.setIconImage(iconImage.getImage());
        canvas = new Canvas();
        this.title = title;
        this.width = width;
        this.height = height;
        dimensions = new Dimension(width, height);
        this.addDisplay();
    }

    private void addDisplay(){
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setCanvasFeatures();
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    private void setCanvasFeatures(){
        canvas.setPreferredSize(dimensions);
        canvas.setMinimumSize(dimensions);
        canvas.setMaximumSize(dimensions);
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public JFrame getFrame(){
        return frame;
    }
}
