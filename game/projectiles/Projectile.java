package com.serenki.game.projectiles;

import com.serenki.game.Game;
import com.serenki.game.GameObject;
import com.serenki.game.Vector;

public abstract class Projectile extends GameObject {

    //distance per frame
    private double speed;
    private boolean reachedTarget;

    /**
     *
     * @param speed The distance per second the projectile moves.
     * @param pathToImage
     */
    public Projectile(Vector position, double speed, String pathToImage) {
        super(position, pathToImage);
        setSpeed(speed);
    }

    /**
     *
     * @return True if the projectile has reached its target, false if it has not.
     */
    public boolean hasReachedTarget() {
        return reachedTarget;
    }

    /**
     * Sets the boolean reachedTarget to true.
     */
    protected void reachedTarget() {
        this.reachedTarget = true;
    }

    private void setSpeed(double speed) {
        if (speed <= 0)
            this.speed = 0.001;
        else
            this.speed = speed / Game.FRAME_RATE;
    }

    @Override
    public void update() {
        move();
    }

    protected double getSpeed() {
        return this.speed;
    }



    public abstract void move();
}

/**
 * TODO: Make one class for guided missiles and one for position locked? Is the upper class Missile even needed in that case?
 */
