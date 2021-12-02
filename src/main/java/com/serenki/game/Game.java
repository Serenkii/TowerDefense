package com.serenki.game;

import com.serenki.game.enemies.Enemy;
import com.serenki.game.projectiles.GuidedMissile;
import com.serenki.game.projectiles.Missile;
import com.serenki.game.projectiles.Projectile;
import com.serenki.game.towers.Tower;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

//TODO: Make classes "TowersManager", "EnemiesManager", "ProjectilesManager" instead of having all of that in one update() function here

public class Game {

    public static final int FRAME_RATE = 60;

    private GraphicsContext graphicsContext;

    private ArrayList<Tower> towers;
    private ArrayList<Enemy> enemies;
    private ArrayList<Projectile> projectiles;

    private Battlefield battlefield;

    public Game(GraphicsContext graphicsContextOfCanvas) {
        this.battlefield = new Battlefield();

        //gameObjects = new ArrayList<>();
        towers = new ArrayList<>();
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();

        this.graphicsContext = graphicsContextOfCanvas;

        //test
        towers.add(new Tower(new GridCoordinate(3, 3), 60, 2.0, this.enemies,"file:src/main/resources/com/serenki/art/towers/DefaultTower1.jpg"));
        towers.add(new Tower(new GridCoordinate(1, 13), 60, 3.0, this.enemies,"file:src/main/resources/com/serenki/art/towers/DefaultTower1.jpg"));
        towers.get(1).changeSelectionStatus();
        towers.add(new Tower(new GridCoordinate(13, 12), 60, 2.0, this.enemies,"file:src/main/resources/com/serenki/art/towers/DefaultTower1.jpg"));

        //test --- at least the enemy is moving I guess
        enemies.add(new Enemy(new Vector(-1, 13.5), 1, 0.2, "file:src/main/resources/com/serenki/art/enemies/DefaultEnemy.png") {

        });

        //test projectile
        projectiles.add(new Missile(new Vector(0.5, 0.5), new Vector(14.5, 9.5),
                5, "file:src/main/resources/com/serenki/art/projectiles/DefaultProjectile.png"));
        projectiles.add(new GuidedMissile(new Vector(0.5, 1.5), enemies.get(0),
                10, 2, "file:src/main/resources/com/serenki/art/projectiles/DefaultProjectile.png"));
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
        for (GameObject missile : projectiles) {
            missile.update();
            missile.render(this.graphicsContext);
        }

        if (selectedTower == null)
            return;
        selectedTower.render(this.graphicsContext);
        selectedTower.renderRangeIndication(this.graphicsContext);
    }
}
