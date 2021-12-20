package com.serenki.game;

import com.serenki.game.UI.GameWindowController;
import com.serenki.game.enemies.EnemiesManager;
import com.serenki.game.enemies.Soldier;
import com.serenki.game.projectiles.ProjectilesManager;
import com.serenki.game.towers.FireCannon;
import com.serenki.game.towers.QuadrupleShooter;
import com.serenki.game.towers.TowersManager;
import javafx.scene.canvas.GraphicsContext;


public class Game {

    public static final int FRAME_RATE = 60;

    private GraphicsContext graphicsContext;

    private TowersManager towersManager;
    private EnemiesManager enemiesManager;
    private ProjectilesManager projectilesManager;

    private Battlefield battlefield;

    private Player player;

    private GameWindowController gameWindowController;

    public Game(GameWindowController gameWindowController) {
        this.gameWindowController = gameWindowController;

        this.graphicsContext = this.gameWindowController.getGraphicsContextOfCanvas();

        this.battlefield = new Battlefield();

        towersManager = new TowersManager(this.graphicsContext);
        enemiesManager = new EnemiesManager(this.graphicsContext);
        projectilesManager = new ProjectilesManager(this.graphicsContext);

        player = new Player(this.graphicsContext.getCanvas(), this.towersManager);

        gameWindowController.initializeManually(this.player, enemiesManager, projectilesManager);

        //test
    /*    towersManager.add(new Tower(new GridCoordinate(3, 3), 60, 2.0, enemiesManager.getEnemies(),
                "file:src/main/resources/com/serenki/art/towers/DefaultTower1.jpg"));
        towersManager.add(new Tower(new GridCoordinate(1, 13), 60, 3.0, enemiesManager.getEnemies(),
                "file:src/main/resources/com/serenki/art/towers/DefaultTower1.jpg"));
        towersManager.get(1).changeSelectionStatus();
        towersManager.add(new Tower(new GridCoordinate(13, 12), 60, 2.0, enemiesManager.getEnemies(),
                "file:src/main/resources/com/serenki/art/towers/DefaultTower1.jpg"));       */
        towersManager.add(new QuadrupleShooter(new GridCoordinate(5, 8), enemiesManager, projectilesManager));
        towersManager.add(new FireCannon(new GridCoordinate(3, 12), enemiesManager, projectilesManager));
        towersManager.get(1).changeSelectionStatus();

        //test --- at least the enemy is moving I guess
        for (int i = -20; i > -100; i--) {
            enemiesManager.add(new Soldier(new Vector(i, Math.random() * 15)));
        }


        player.setTowerToPlace(new QuadrupleShooter(this.enemiesManager, this.projectilesManager));
    }

    public void update() {
        battlefield.render(this.graphicsContext);
        towersManager.renderAndUpdate();
        enemiesManager.renderAndUpdate();
        projectilesManager.renderAndUpdate();
        towersManager.renderSelectedTower();
        player.renderTower();
        gameWindowController.updateDisplays();
    }
}
