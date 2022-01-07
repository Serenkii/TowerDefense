package com.serenki.game.enemies;

import com.serenki.game.Vector;
import com.serenki.game.pathfinding.Pathfinding;

public class Airplane extends Enemy {

    private Vector velocity;

    public Airplane(Vector position, Pathfinding pathfinding) {
        super(position, pathfinding, 2, 0.2, 70, 20,1, "file:src/main/resources/com/serenki/art/enemies/Airplane.png");

        calculateVelocity();
    }

    /**
     * Calculates the velocity of the airplane based on the destination.
     */
    private void calculateVelocity() {
        this.velocity = this.position.vectorTo(super.getDestination());
        this.velocity = this.velocity.normalize();
        this.velocity = this.velocity.multiply(super.getSpeed());
        this.position = this.position.add(this.velocity);

        this.sprite.setRotationAngle(this.velocity);
    }

    @Override
    public void move() {
        this.position = this.position.add(this.velocity);
    }
}
