package com.serenki.game.enemies;

import com.serenki.game.Game;
import com.serenki.game.GameObject;
import com.serenki.game.Vector;

public abstract class Enemy extends GameObject {

    private Vector destination;
    //private Vector velocity;
    private double speed;           //distance per frame
    private double hitBoxRadius;

    private int healthPoints;


    /**
     * @param position The exact position on the board. (Default: From 0.0 to 15.0, both x and y)
     * @param speed The distance per second the enemy moves.
     * @param hitBoxRadius The radius of the circular hitbox of the enemy. (Used by missiles)
     * @param pathToImage
     * @param HP
     */
    public Enemy(Vector position, double speed, double hitBoxRadius, int HP, String pathToImage) {
        super(position, pathToImage);
        setSpeed(speed);
        setHitBoxRadius(hitBoxRadius);
        setDestinationBasedOnStart(position);
        setHealthPoints(HP);
    }

    private void setDestinationBasedOnStart(Vector position) {
        if (position.getX() < 0) {
            destination = new Vector(16, position.getY());
            return;
        }
        if (position.getY() < 0) {
            destination = new Vector(position.getX(), 16);
            return;
        }
        destination = new Vector(7.5, 7.5);     //to delete
    }

    private void setSpeed(double speed) {
        if (speed <= 0)
            this.speed = 0.001;
        else
            this.speed = speed / Game.FRAME_RATE;
    }

    private void setHitBoxRadius(double hitBoxRadius) {
        if (hitBoxRadius <= 0)
            this.hitBoxRadius = 0.1;
        else
            this.hitBoxRadius = hitBoxRadius;
    }

    private void setHealthPoints(int HP) {
        if (HP <= 0)
            this.healthPoints = 1;
        else
            this.healthPoints = HP;
    }


    /**
     * @param position The exact position on the board. (Default: From 0.0 to 15.0, both x and y)
     * @param speed The speed per second the enemy moves.
     * @param hitBoxRadius The radius of the circular hitbox of the enemy. (Used by missiles)
     * @param pathToImage
     * @deprecated Doesn't give HP
     */
    @Deprecated
    public Enemy(Vector position, double speed, double hitBoxRadius, String pathToImage) {
        super(position, pathToImage);
        setSpeed(speed);
        setHitBoxRadius(hitBoxRadius);
        setDestinationBasedOnStart(position);
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
        Vector velocity = this.position.vectorTo(destination);
        velocity = velocity.normalize();
        velocity = velocity.multiply(speed);
        this.position = position.add(velocity);
    }
}
