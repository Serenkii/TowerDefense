package com.serenki.game.enemies;

import com.serenki.game.Vector;
import com.serenki.game.pathfinding.Pathfinding;

public class Airplane extends Enemy {

    public Airplane(Vector position, Pathfinding pathfinding) {
        super(position, pathfinding, 2, 0.2, 50, 20,1, "file:src/main/resources/com/serenki/art/enemies/Airplane.png");
    }

    @Override
    public void move() {
        Vector velocity = this.position.vectorTo(super.getDestination());
        velocity = velocity.normalize();
        velocity = velocity.multiply(super.getSpeed());
        this.position = position.add(velocity);
    }
}
