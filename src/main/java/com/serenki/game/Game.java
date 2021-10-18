package com.serenki.game;

import com.serenki.game.enemies.Enemy;
import com.serenki.game.missiles.Missile;
import com.serenki.game.towers.Tower;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Game {

    private GraphicsContext graphicsContext;

    private ArrayList<Tower> towers;
    private ArrayList<Enemy> enemies;
    private ArrayList<Missile> missiles;

    private Battlefield battlefield;

    public Game(GraphicsContext graphicsContextOfCanvas) {
        this.battlefield = new Battlefield();

        //gameObjects = new ArrayList<>();
        towers = new ArrayList<>();
        enemies = new ArrayList<>();
        missiles = new ArrayList<>();

        this.graphicsContext = graphicsContextOfCanvas;

        //test
        towers.add(new Tower(new GridCoordinate(3, 3), 60, 2.0, "file:src/main/resources/com/serenki/art/towers/DefaultTower1.jpg"));
        towers.add(new Tower(new GridCoordinate(1, 13), 60, 3.0, "file:src/main/resources/com/serenki/art/towers/DefaultTower1.jpg"));
        towers.get(1).changeSelectionStatus();
        towers.add(new Tower(new GridCoordinate(13, 12), 60, 2.0, "file:src/main/resources/com/serenki/art/towers/DefaultTower1.jpg"));
    }

    public void update() {
        Tower selectedTower = null;
        battlefield.render(this.graphicsContext);
        for (Tower tower : towers) {
            tower.update();
            tower.render(this.graphicsContext);
            if (tower.isSelected())
                selectedTower = tower;
        }
        for (GameObject enemy : enemies) {
            enemy.update();
            enemy.render(this.graphicsContext);
        }
        for (GameObject missile : missiles) {
            missile.update();
            missile.render(this.graphicsContext);
        }

        if (selectedTower == null)
            return;
        selectedTower.render(this.graphicsContext);
        selectedTower.renderRangeIndication(this.graphicsContext);
    }
}
