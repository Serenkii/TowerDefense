package com.serenki.game;

public class Tower extends GameObject{

    private GridCoordinate coordinate;
    private final int COOLDOWN;
    private int cooldown;   //cooldown in frames, the game runs in 60 fps (let's just ignore fluctuation (for now))

    /**
     *
     * @param coordinate
     * @param cooldownInFrames
     */
    public Tower (final GridCoordinate coordinate, final int cooldownInFrames) {
        this.coordinate = new GridCoordinate(coordinate);
        this.COOLDOWN = cooldown;
        this.cooldown = 0;
    }

    /**
     *
     * @param coordinate in the grid
     * @param cooldown in seconds
     */
    public Tower (final GridCoordinate coordinate, final double cooldown) {
        this.coordinate = new GridCoordinate(coordinate);
        this.COOLDOWN = (int) (cooldown * 60 + 0.5);        //60 --> framerate -- create static public final variable for that?
        this.cooldown = 0;
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

    public boolean isReady() {
        return this.cooldown <= 0;
    }
}
