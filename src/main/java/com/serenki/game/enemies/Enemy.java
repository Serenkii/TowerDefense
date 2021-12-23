package com.serenki.game.enemies;

import com.serenki.game.Game;
import com.serenki.game.GameObject;
import com.serenki.game.Vector;
import com.serenki.game.pathfinding.Node;
import com.serenki.game.pathfinding.Pathfinding;

import java.util.Stack;

public abstract class Enemy extends GameObject {


    private int moneyValue;
    private int damageValue;

    private double speed;           //distance per frame
    private double hitBoxRadius;

    private int healthPoints;

    private Pathfinding pathfinding;
    private Vector destination;
    private Stack<Node> path;

    /**
     * @param position The exact position on the board. (Default: From 0.0 to 15.0, both x and y)
     * @param speed The distance per second the enemy moves.
     * @param hitBoxRadius The radius of the circular hitbox of the enemy. (Used by missiles)
     * @param pathToImage
     * @param HP
     */
    public Enemy(Vector position, Pathfinding pathfinding, double speed, double hitBoxRadius, int HP, int moneyValue, int damageValue, String pathToImage) {
        super(position, pathToImage);
        setSpeed(speed);
        setHitBoxRadius(hitBoxRadius);
        setDestinationBasedOnStart(position);
        setHealthPoints(HP);
        setMoneyValue(moneyValue);
        setDamageValue(damageValue);
        this.pathfinding = pathfinding;
        findPath();
    }

    public void findPath() {
        this.path = pathfinding.findPath(this.position, this.destination);
    }

    private void setDamageValue(int damageValue) {
        if (damageValue < 1)
            this.damageValue = 1;
        else
            this.damageValue = damageValue;
    }

    private void setMoneyValue(int moneyValue) {
        if (moneyValue < 0)
            this.moneyValue = 0;
        else
            this.moneyValue = moneyValue;
    }

    public void dealDamage(int damagePoints) {
        if (damagePoints <= 0)
            return;
        this.healthPoints -= damagePoints;
    }

    public boolean isInArea(Vector position, double radius) {
        //TODO!
        return false;
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }

    private void setDestinationBasedOnStart(Vector position) {
        if (position.getX() < 0) {
            destination = new Vector(15.5, position.getY());
            return;
        }
        if (position.getY() < 0) {
            destination = new Vector(position.getX(), 15.5);
            return;
        }
        destination = new Vector(15.5, 15.5);
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

    public double getHitBoxRadius() {
        return this.hitBoxRadius;
    }

    @Override
    public void update() {
        move();
    }

    public void move() {
        if (this.path == null) {        //If the path is null because none was found, just cheat you way above everything^^
            cheatMove();
            return;
        }
        if (this.path.size() == 0) {
            this.position = destination;
            return;
        }
        Vector velocity = this.position.vectorTo(path.peek().getPosition());
        velocity = velocity.normalize();
        velocity = velocity.multiply(speed);
        this.position = position.add(velocity);

        this.sprite.setRotationAngle(velocity);

        if (this.position.distanceTo(path.peek().getPosition()) < 0.05) {
            this.path.pop();
        }
    }

    private void cheatMove() {
        //System.out.println("cheatMove is used by: " + this + " (at Position (" + this.getPosition().getX() + ", " + this.getPosition().getY() + "))");
        Vector velocity = this.position.vectorTo(destination);
        velocity = velocity.normalize();
        velocity = velocity.multiply(speed);
        this.position = position.add(velocity);

        if (this.pathfinding.getGrid().getNodeFromPosition(this.position).isWalkable()) {       //As soon as the enemy is on a walkable node again, try pathfinding again
            findPath();                                                                     //Might be intensive because findPath() is called A LOT
        }
    }

    public boolean reachedDestination() {
        if (this.position.distanceTo(destination) <= 0.2)
            return true;
        return false;
    }


    public int getMoneyValue() {
        return moneyValue;
    }

    public int getDamageValue() {
        return damageValue;
    }

    protected double getSpeed() {
        return speed;
    }

    protected Vector getDestination() {
        return destination;
    }


}
