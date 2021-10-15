package com.serenki.game;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Game {

    private GraphicsContext graphicsContext;

    private ArrayList<GameObject> gameObjects;
    private Battlefield battlefield;

    public Game() {
        this.battlefield = new Battlefield(1080, 0,  15, 0);
        gameObjects = new ArrayList<>();
    }

    public void update() {
        for (GameObject object : gameObjects) {
            object.update();
        }
    }
}
