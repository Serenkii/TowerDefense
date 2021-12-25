package com.serenki.game.enemies;

import com.serenki.game.Vector;
import com.serenki.game.pathfinding.Pathfinding;

public class MilitaryCar extends Enemy {
    public MilitaryCar(Vector position, Pathfinding pathfinding) {
        super(position, pathfinding, 2, 0.15, 120, 30,3, "file:src/main/resources/com/serenki/art/enemies/MilitaryCar.png");
    }
}
