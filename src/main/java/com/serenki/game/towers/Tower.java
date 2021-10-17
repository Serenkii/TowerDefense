package com.serenki.game.towers;

import com.serenki.game.Battlefield;
import com.serenki.game.GameObject;
import com.serenki.game.GridCoordinate;
import com.serenki.game.Position;
import javafx.scene.canvas.GraphicsContext;

public class Tower extends GameObject {

    private GridCoordinate coordinate;
    private final int COOLDOWN;
    private int cooldown;   //cooldown in frames, the game runs in 60 fps (let's just ignore fluctuation (for now))
    private double range;
    private boolean selected;

    /**
     *
     * @param coordinate
     * @param cooldownInFrames
     */
    public Tower (final GridCoordinate coordinate, final int cooldownInFrames, double range, String pathToPicture) {
        super(pathToPicture);

        this.setCoordinate(coordinate);
        this.COOLDOWN = cooldownInFrames;
        this.cooldown = 0;
        this.range = range;
        this.selected = true;
    }

    /**
     *
     * @param coordinate in the grid
     * @param cooldown in seconds
     */
    public Tower (final GridCoordinate coordinate, final double cooldown, double range, String pathToPicture) {
        super(pathToPicture);

        this.setCoordinate(coordinate);
        this.COOLDOWN = (int) (cooldown * 60 + 0.5);        //60 --> framerate -- create static public final variable for that?
        this.cooldown = 0;
        this.range = range;
        this.selected = false;
    }

    private void setCoordinate(final GridCoordinate coordinate) {
        this.coordinate = new GridCoordinate(coordinate);
        this.position = new Position(coordinate.getX() + 0.5, coordinate.getY() + 0.5);
    }

    @Override
    public void update() {

        shoot();
    }

    public void shoot() {
        updateCooldown();

    }

    public void updateCooldown() {
        if (cooldown > 0)
            cooldown--;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void renderRangeIndication(GraphicsContext context) {            //TODO: Fix for proper display of range
        context.strokeOval((this.position.getX() * Battlefield.GRID_SIZE) - ((this.range * Battlefield.SQUARE_SIZE) / 2),
                (this.position.getY() * Battlefield.GRID_SIZE) - ((this.range * Battlefield.SQUARE_SIZE) / 2),
                this.range * Battlefield.SQUARE_SIZE,
                this.range * Battlefield.SQUARE_SIZE);
    }

    public void changeSelectionStatus() {
        this.selected = !this.selected;
    }

    public boolean isReady() {
        return this.cooldown <= 0;
    }
}
