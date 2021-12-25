package com.serenki.game.towers;

import com.serenki.game.Battlefield;
import com.serenki.game.Game;
import com.serenki.game.GridCoordinate;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;

//In theory, I could make one superclass with generics or something, but imo this is easier

public class TowersManager {

    private Game game;

    private ArrayList<Tower> towers;
    private Tower selectedTower;

    private MediaPlayer placingSoundPlayer;


    public TowersManager(@NotNull final Game game) {
        towers = new ArrayList<>();
        this.game = game;
        initiatePlacingSoundPlayer();
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

        this.game.getPathfinding().getGrid().updateForNewTowers(this);
        if (this.game.getEnemiesManager() == null) {
            System.err.println("enemiesManager was never initiated in this TowersManager - You need to call the setEnemiesManager()-method!");
            return true;
        }
        this.game.getEnemiesManager().findNewPaths();

        return true;
    }

    private void initiatePlacingSoundPlayer() {
        Media sound = new Media(new File("src/main/resources/com/serenki/art/towers/placing.wav").toURI().toString());
        System.out.println(new File("src/main/resources/com/serenki/art/towers/placing.wav").toURI().toString());
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
            tower.render(this.game.getGraphicsContext());
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
        selectedTower.render(this.game.getGraphicsContext());
        selectedTower.renderRangeIndication(this.game.getGraphicsContext());
    }

    public int towerCount() {
        return this.towers.size();
    }
}
