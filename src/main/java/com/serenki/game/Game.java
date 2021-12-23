package com.serenki.game;

import com.serenki.game.UI.GameWindowController;
import com.serenki.game.enemies.EnemiesManager;
import com.serenki.game.pathfinding.Grid;
import com.serenki.game.pathfinding.Pathfinding;
import com.serenki.game.projectiles.ProjectilesManager;
import com.serenki.game.towers.TowersManager;
import javafx.scene.canvas.Canvas;
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

    private LevelManager levelManager;


    public Game(GameWindowController gameWindowController) {
        this.gameWindowController = gameWindowController;

        this.graphicsContext = this.gameWindowController.getGraphicsContextOfCanvas();

        this.battlefield = new Battlefield();

        towersManager = new TowersManager(this);
        projectilesManager = new ProjectilesManager(this);
        enemiesManager = new EnemiesManager(this);

        player = new Player(this);

        gameWindowController.initializeManually(this);

        Grid grid = new Grid(towersManager);
        this.pathfinding = new Pathfinding(grid);

        this.levelManager = new LevelManager(this);


        //test --- at least the enemy is moving I guess
        /*for (int i = -20; i > -100; i--) {
            enemiesManager.add(new Soldier(new Vector(i, Math.random() * 15), pathfinding));
        }*/
    }

    public void update() {
        levelManager.update();
        battlefield.render(this.graphicsContext);
        towersManager.renderAndUpdate();
        enemiesManager.renderAndUpdate();
        projectilesManager.renderAndUpdate();
        towersManager.renderSelectedTower();
        player.renderTower();
        gameWindowController.updateDisplays();
    }


    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public TowersManager getTowersManager() {
        return towersManager;
    }

    public EnemiesManager getEnemiesManager() {
        return enemiesManager;
    }

    public ProjectilesManager getProjectilesManager() {
        return projectilesManager;
    }

    public Battlefield getBattlefield() {
        return battlefield;
    }

    public Player getPlayer() {
        return player;
    }

    public GameWindowController getGameWindowController() {
        return gameWindowController;
    }

    public Pathfinding getPathfinding() {
        return pathfinding;
    }

    public Canvas getCanvas() {
        return this.graphicsContext.getCanvas();
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }
}
