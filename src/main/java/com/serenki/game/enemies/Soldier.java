package com.serenki.game.enemies;

import com.serenki.game.Vector;
import com.serenki.game.pathfinding.Pathfinding;

public class Soldier extends Enemy {
    public Soldier(Vector position, Pathfinding pathfinding) {
        super(position, pathfinding, 1.5, 0.2, 30, 10,1, "file:src/main/resources/com/serenki/art/enemies/DefaultEnemy.png");
    }
}
