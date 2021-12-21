package com.serenki.game.towers;

import com.serenki.game.Battlefield;
import com.serenki.game.GridCoordinate;
import com.serenki.game.enemies.EnemiesManager;
import com.serenki.game.pathfinding.Pathfinding;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;

//In theory, I could make one superclass with generics or something, but imo this is easier

public class TowersManager {

    private GraphicsContext graphicsContext;

    private ArrayList<Tower> towers;
    private Tower selectedTower;

    private EnemiesManager enemiesManager;

    private MediaPlayer placingSoundPlayer;

    private Pathfinding pathfinding;

    public TowersManager(GraphicsContext graphicsContext) {
        towers = new ArrayList<>();
        this.graphicsContext = graphicsContext;
        initiatePlacingSoundPlayer();
    }

    public void setEnemiesManager(EnemiesManager enemiesManager) {
        this.enemiesManager = enemiesManager;
    }

    public void setPathfinding(@NotNull Pathfinding pathfinding) {
        this.pathfinding = pathfinding;
    }

    /**
     * Tries to place a tower at the specified position.
     * @param tower The tower you want to place.
     * @param gridCoordinate The position you want to place the tower.
     * @return True if it was successful, false if it was not.
     */
    public boolean placeTower(@NotNull Tower tower, @NotNull GridCoordinate gridCoordinate) {
        if (this.getTower(gridCoordinate) != null) {
            return false;
        }
        if (gridCoordinate.getX() < Battlefield.EDGE_SIZE || gridCoordinate.getX() >= Battlefield.GRID_SIZE - Battlefield.EDGE_SIZE ||
                gridCoordinate.getY() < Battlefield.EDGE_SIZE || gridCoordinate.getY() >= Battlefield.GRID_SIZE - Battlefield.EDGE_SIZE) {
            return false;
        }
        tower.setCoordinate(gridCoordinate);
        this.towers.add(tower);
        initiatePlacingSoundPlayer();       //WHY DO I NEED THIS???
        this.placingSoundPlayer.play();

        pathfinding.getGrid().updateForNewTowers(this);
        if (enemiesManager == null) {
            System.err.println("enemiesManager was never initiated in this TowersManager - You need to call the setEnemiesManager()-method!");
            return true;
        }
        enemiesManager.findNewPaths();

        return true;
    }

    private void initiatePlacingSoundPlayer() {
        Media sound = new Media(new File("src/main/resources/com/serenki/art/towers/placing.wav").toURI().toString());
        this.placingSoundPlayer = new MediaPlayer(sound);
    }

    /**
     * Adds a tower to the Arraylist
     * @param tower
     * @deprecated
     */
    @Deprecated
    public void add(@NotNull Tower tower) {
        this.towers.add(tower);
    }

    public Tower get(int i) {
        return towers.get(i);
    }

    /**
     * Updates and renders every tower. Call "renderSelectedTower()" later to render the selected tower with its range indication.
     */
    public void renderAndUpdate() {
        for (Tower tower : towers) {
            tower.update();
            tower.render(this.graphicsContext);
            if (tower.isSelected())     //this will maybe be changed when I add selecting towers
                this.selectedTower = tower;
        }
    }

    public void unselectAll() {
        this.selectedTower = null;
        for (Tower tower : towers) {
            tower.unselect();
        }
    }

    public void selectTower(GridCoordinate coordinate) {
        this.selectedTower = null;
        for (Tower tower : towers) {
            tower.unselect();
            if (tower.getCoordinate().equals(coordinate)) {
                tower.select();
                this.selectedTower = tower;
            }
        }
    }

    public Tower getTower(GridCoordinate position) {
        for (Tower tower : towers) {
            if (tower.getCoordinate().equals(position))
                return tower;
        }
        return null;
    }

    public void renderSelectedTower() {
        if (selectedTower == null)
            return;
        selectedTower.render(this.graphicsContext);
        selectedTower.renderRangeIndication(this.graphicsContext);
    }

    public int towerCount() {
        return this.towers.size();
    }
}
