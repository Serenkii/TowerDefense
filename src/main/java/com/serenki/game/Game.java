package com.serenki.game;

import com.serenki.game.UI.GameWindowController;
import com.serenki.game.enemies.EnemiesManager;
import com.serenki.game.enemies.Soldier;
import com.serenki.game.pathfinding.Grid;
import com.serenki.game.pathfinding.Pathfinding;
import com.serenki.game.projectiles.ProjectilesManager;
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

    private Pathfinding pathfinding;

    public Game(GameWindowController gameWindowController) {
        this.gameWindowController = gameWindowController;

        this.graphicsContext = this.gameWindowController.getGraphicsContextOfCanvas();

        this.battlefield = new Battlefield();

        towersManager = new TowersManager(this.graphicsContext);
        projectilesManager = new ProjectilesManager(this.graphicsContext);
        player = new Player(this.graphicsContext.getCanvas(), this.towersManager);
        enemiesManager = new EnemiesManager(this.graphicsContext, this.player);

        towersManager.setEnemiesManager(enemiesManager);

        gameWindowController.initializeManually(this.player, enemiesManager, projectilesManager);

        Grid grid = new Grid(towersManager);
        this.pathfinding = new Pathfinding(grid);

        towersManager.setPathfinding(pathfinding);

        //test --- at least the enemy is moving I guess
        for (int i = -20; i > -100; i--) {
            enemiesManager.add(new Soldier(new Vector(i, Math.random() * 15), pathfinding));
        }
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
