package com.serenki.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Battlefield {

    private int size;       //in pixels
    private int gridSize;   //including outer squares, where you cannot place towers
    private GridCoordinate pixelPos;

    private Sprite background;

    public Battlefield(int size, GridCoordinate pixelPos, int gridSize, final String pathToImage) {
        this.size = size;
        this.gridSize = gridSize;
        this.pixelPos = pixelPos;

        background = new Sprite(pathToImage);
    }

    public void render(final GraphicsContext context) {
        background.render(pixelPos, context);
    }
}
