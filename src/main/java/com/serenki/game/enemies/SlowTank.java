package com.serenki.game.enemies;

import com.serenki.game.Vector;
import com.serenki.game.pathfinding.Pathfinding;

public class SlowTank extends Enemy{
    public SlowTank(Vector position, Pathfinding pathfinding) {
        super(position, pathfinding, 0.4, 0.3, 700, 100,5, "file:src/main/resources/com/serenki/art/enemies/HeavyTank.png");
    }
}
