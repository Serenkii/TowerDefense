package com.serenki.game.projectiles;

import com.serenki.game.Game;
import com.serenki.game.GameObject;
import com.serenki.game.Vector;

public abstract class Projectile extends GameObject {

    private double speed;
    private boolean reachedTarget;

    private boolean rotatableProjectile;

    /**
     *
     * @param position Spawn-Position of the Projectile.
     * @param speed The distance per second the projectile moves.
     * @param rotatableProjectile True if the Projectile should be able to rotate according to the direction of the velocity-vector.
     * @param pathToImage
     */
    public Projectile(Vector position, double speed, boolean rotatableProjectile, String pathToImage) {
        super(position, pathToImage);
        setSpeed(speed);
        this.rotatableProjectile = rotatableProjectile;
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

    protected boolean isRotatableProjectile() {
        return rotatableProjectile;
    }

    protected abstract void move();
}

/**
 * TODO: Make one class for guided missiles and one for position locked? Is the upper class Missile even needed in that case?
 */
