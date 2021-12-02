package com.serenki.game;

import com.serenki.game.enemies.EnemiesManager;
import com.serenki.game.enemies.Enemy;
import com.serenki.game.projectiles.GuidedMissile;
import com.serenki.game.projectiles.Missile;
import com.serenki.game.projectiles.ProjectilesManager;
import com.serenki.game.towers.Tower;
import com.serenki.game.towers.TowersManager;
import javafx.scene.canvas.GraphicsContext;


public class Game {

    public static final int FRAME_RATE = 60;

    private GraphicsContext graphicsContext;

    private TowersManager towersManager;
    private EnemiesManager enemiesManager;
    private ProjectilesManager projectilesManager;

    private Battlefield battlefield;

    public Game(GraphicsContext graphicsContextOfCanvas) {
        this.graphicsContext = graphicsContextOfCanvas;

        this.battlefield = new Battlefield();

        towersManager = new TowersManager(this.graphicsContext);
        enemiesManager = new EnemiesManager(this.graphicsContext);
        projectilesManager = new ProjectilesManager(this.graphicsContext);

        //test
        towersManager.add(new Tower(new GridCoordinate(3, 3), 60, 2.0, enemiesManager.getEnemies(),
                "file:src/main/resources/com/serenki/art/towers/DefaultTower1.jpg"));
        towersManager.add(new Tower(new GridCoordinate(1, 13), 60, 3.0, enemiesManager.getEnemies(),
                "file:src/main/resources/com/serenki/art/towers/DefaultTower1.jpg"));
        towersManager.get(1).changeSelectionStatus();
        towersManager.add(new Tower(new GridCoordinate(13, 12), 60, 2.0, enemiesManager.getEnemies(),
                "file:src/main/resources/com/serenki/art/towers/DefaultTower1.jpg"));

        //test --- at least the enemy is moving I guess
        enemiesManager.add(new Enemy(new Vector(-1, 13.5), 1, 0.2, "file:src/main/resources/com/serenki/art/enemies/DefaultEnemy.png") {

        });

        //test projectile
        projectilesManager.add(new Missile(new Vector(0.5, 0.5), new Vector(14.5, 9.5),
                5, "file:src/main/resources/com/serenki/art/projectiles/DefaultProjectile.png"));
        projectilesManager.add(new GuidedMissile(new Vector(0.5, 1.5), enemiesManager.get(0),
                10, 1.5, "file:src/main/resources/com/serenki/art/projectiles/DefaultProjectile.png"));
    }

    public void update() {
        battlefield.render(this.graphicsContext);
        towersManager.renderAndUpdate();
        enemiesManager.renderAndUpdate();
        projectilesManager.renderAndUpdate();
        towersManager.renderSelectedTower();
    }
}
