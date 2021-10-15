package com.serenki.game;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Game {

    private GraphicsContext graphicsContext;

    private ArrayList<GameObject> gameObjects;
    private Battlefield battlefield;

    public Game(GraphicsContext graphicsContextOfCanvas) {
        this.battlefield = new Battlefield(1080, new GridCoordinate(0,0),  15,  "file:src/main/resources/com/serenki/art/background/PrototypeGrid.jpg");

        gameObjects = new ArrayList<>();

        this.graphicsContext = graphicsContextOfCanvas;

        //test
        gameObjects.add(new Tower(new GridCoordinate(3, 3), 60, "file:src/main/resources/com/serenki/art/towers/DefaultTower1.jpg"));
        gameObjects.add(new Tower(new GridCoordinate(1, 13), 60, "file:src/main/resources/com/serenki/art/towers/DefaultTower1.jpg"));
    }

    public void update() {
        battlefield.render(this.graphicsContext);
        for (GameObject object : gameObjects) {
            object.update();
            object.render(this.graphicsContext);
        }
    }
}
