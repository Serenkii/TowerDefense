package com.serenki.game.enemies;

import com.serenki.game.Vector;
import com.serenki.game.pathfinding.Pathfinding;

public class FastTank extends Enemy {
    public FastTank(Vector position, Pathfinding pathfinding) {
        super(position, pathfinding, 1.3, 0.2, 300, 40,4, "file:src/main/resources/com/serenki/art/enemies/FastTank.png");
    }
}
