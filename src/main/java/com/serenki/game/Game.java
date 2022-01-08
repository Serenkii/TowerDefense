package com.serenki.game;

import com.serenki.game.UI.GameWindowController;
import com.serenki.game.enemies.EnemiesManager;
import com.serenki.game.pathfinding.Grid;
import com.serenki.game.pathfinding.Pathfinding;
import com.serenki.game.projectiles.ProjectilesManager;
import com.serenki.game.towers.TowersManager;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class Game {     //TODO Refactor at some point so Game just extends AnimationTimer
                        //TODO Make Game, ProjectilesManager, Battlefield, etc to classes that can only be created once by making the
                        //TODO      constructor private and the class static returning our Game with a public Game.get() function.

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

    private AnimationTimer gameloop;


    public Game(GameWindowController gameWindowController, AnimationTimer gameloop) {
        this.gameWindowController = gameWindowController;
        this.gameloop = gameloop;

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


    public void loose() {
        gameloop.stop();
    }

    public AnimationTimer getGameloop() {
        return gameloop;
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
