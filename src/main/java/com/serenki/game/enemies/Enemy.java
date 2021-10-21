package com.serenki.game.enemies;

import com.serenki.game.GameObject;
import com.serenki.game.Vector;

public abstract class Enemy extends GameObject {

    private Vector destination;
    private Vector velocity;
    private double speed;           //distance per frame
    private double hitBoxRadius;


    /**
     * @param position The exact position on the board. (Default: From 0.0 to 15.0, both x and y)
     * @param speed The speed per second the enemy moves.
     * @param hitBoxRadius The radius of the circular hitbox of the enemy. (Used by missiles)
     * @param pathToImage
     */
    public Enemy(Vector position, double speed, double hitBoxRadius, String pathToImage) {
        super(position, pathToImage);
        setSpeed(speed);
        setHitBoxRadius(hitBoxRadius);
    }

    private void setSpeed(double speed) {
        if (speed <= 0)
            this.speed = 0.001;
        else
            this.speed = speed / 60;
    }

    private void setHitBoxRadius(double hitBoxRadius) {
        if (hitBoxRadius <= 0)
            this.hitBoxRadius = 0.1;
        else
            this.hitBoxRadius = hitBoxRadius;
    }


    /**
     * @param pathToImage
     * @deprecated
     */
    @Deprecated
    public Enemy(String pathToImage) {
        super(pathToImage);
    }

    @Override
    public void update() {
        move();
    }

    public void move() {

    }
}
