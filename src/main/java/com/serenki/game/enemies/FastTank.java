package com.serenki.game.enemies;

import com.serenki.game.Vector;
import com.serenki.game.pathfinding.Pathfinding;

public class FastTank extends Enemy {
    public FastTank(Vector position, Pathfinding pathfinding) {
        super(position, pathfinding, 1.7, 0.3, 200, 40,4, "file:src/main/resources/com/serenki/art/enemies/FastTank.png");
    }
}
