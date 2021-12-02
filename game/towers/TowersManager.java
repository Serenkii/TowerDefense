package com.serenki.game.towers;

import com.serenki.game.GridCoordinate;
import javafx.scene.canvas.GraphicsContext;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

//In theory, I could make one superclass with generics or something, but imo this is easier

public class TowersManager {

    private GraphicsContext graphicsContext;

    private ArrayList<Tower> towers;
    private Tower selectedTower;

    public TowersManager(GraphicsContext graphicsContext) {
        towers = new ArrayList<>();
        this.graphicsContext = graphicsContext;
    }

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
}
