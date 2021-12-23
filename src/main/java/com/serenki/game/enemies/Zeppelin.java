package com.serenki.game.enemies;

import com.serenki.game.Vector;
import com.serenki.game.pathfinding.Pathfinding;

public class Zeppelin extends Enemy {
    public Zeppelin(Vector position, Pathfinding pathfinding) {
        super(position, pathfinding, 0.3, 1, 5000, 300,50, "file:src/main/resources/com/serenki/art/enemies/Zeppelin.png");
    }
}
