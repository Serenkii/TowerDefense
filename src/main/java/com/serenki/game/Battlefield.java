package com.serenki.game;

public class Battlefield {

    private int size;
    private int gridSize;   //including outer squares, where you cannot place towers
    private int xPosition;  //x Position on screen (left == 0 px)
    private int yPosition;  //y Position on screen (top == 0 px)

    public Battlefield(int size, int xPosition, int gridSize, int yPosition) {
        this.size = size;
        this.gridSize = gridSize;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}
