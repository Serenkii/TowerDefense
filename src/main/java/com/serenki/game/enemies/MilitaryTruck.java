package com.serenki.game.enemies;

import com.serenki.game.Vector;
import com.serenki.game.pathfinding.Pathfinding;

public class MilitaryTruck extends Enemy {
    public MilitaryTruck(Vector position, Pathfinding pathfinding) {
        super(position, pathfinding, 1.5, 0.25, 200, 30,3, "file:src/main/resources/com/serenki/art/enemies/MilitaryTruck.png");
    }
}
