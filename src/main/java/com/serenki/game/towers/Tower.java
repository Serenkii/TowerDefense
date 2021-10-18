package com.serenki.game.towers;

import com.serenki.game.Battlefield;
import com.serenki.game.GameObject;
import com.serenki.game.GridCoordinate;
import com.serenki.game.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tower extends GameObject {

    private GridCoordinate coordinate;
    private final int COOLDOWN;
    private int cooldown;   //cooldown in frames, the game runs in 60 fps (let's just ignore fluctuation (for now))
    private double range;
    private boolean selected;

    /**
     *
     * @param coordinate The square the tower is standing on.
     * @param cooldownInFrames The time duration the tower needs to reload in frames.
     * @param range The maximum range the tower can shoot. (A range of 1 should be able to reach all squares +-x and +-y, that's why 0.5 is automatically added.)
     * @param pathToPicture The path (String) to the picture. Example: "file:src/image.jpg"
     */
    public Tower (final GridCoordinate coordinate, final int cooldownInFrames, double range, String pathToPicture) {
        super(pathToPicture);

        this.setCoordinate(coordinate);
        this.COOLDOWN = cooldownInFrames;
        this.cooldown = 0;
        this.range = range + 0.5;
        this.selected = false;
    }

    /**
     *
     * @param coordinate The square the tower is standing on.
     * @param cooldown The time duration the tower needs to reload in seconds.
     * @param range The maximum range the tower can shoot. (A range of 1 should be able to reach all squares +-x and +-y, that's why 0.5 is automatically added.)
     * @param pathToPicture The path (String) to the picture. Example: "file:src/image.jpg"
     */
    public Tower (final GridCoordinate coordinate, final double cooldown, double range, String pathToPicture) {
        super(pathToPicture);

        this.setCoordinate(coordinate);
        this.COOLDOWN = (int) (cooldown * 60 + 0.5);        //60 --> framerate -- create static public final variable for that?
        this.cooldown = 0;
        this.range = range + 0.5;
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
        if (!isReady())
            return;
        //do shooty thingy
        reload();
    }

    public void reload() {
        this.cooldown = this.COOLDOWN;
    }

    public void updateCooldown() {
        if (cooldown > 0)
            cooldown--;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void changeSelectionStatus() {
        this.selected = !this.selected;
    }

    public boolean isReady() {
        return this.cooldown <= 0;
    }

    public void renderRangeIndication(GraphicsContext context) {
        context.setFill(Color.rgb(50, 255, 200, 0.2));

        context.fillOval((this.position.getX() * Battlefield.SQUARE_SIZE) - ((this.range * Battlefield.SQUARE_SIZE)),
                (this.position.getY() * Battlefield.SQUARE_SIZE) - ((this.range * Battlefield.SQUARE_SIZE)),
                this.range * Battlefield.SQUARE_SIZE * 2,
                this.range * Battlefield.SQUARE_SIZE * 2);

        context.setStroke(Color.rgb(50, 255, 200));
        context.strokeOval( ((this.position.getX() * Battlefield.SQUARE_SIZE) - ((this.range * Battlefield.SQUARE_SIZE))) + 1,
                ((this.position.getY() * Battlefield.SQUARE_SIZE) - ((this.range * Battlefield.SQUARE_SIZE))) + 1,
                (this.range * Battlefield.SQUARE_SIZE * 2) - 2,
                (this.range * Battlefield.SQUARE_SIZE * 2) - 2);
        context.setStroke(Color.BLACK);
        context.strokeOval( (this.position.getX() * Battlefield.SQUARE_SIZE) - ((this.range * Battlefield.SQUARE_SIZE)),
                (this.position.getY() * Battlefield.SQUARE_SIZE) - ((this.range * Battlefield.SQUARE_SIZE)),
                this.range * Battlefield.SQUARE_SIZE * 2,
                this.range * Battlefield.SQUARE_SIZE * 2);
    }
}
