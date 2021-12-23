package com.serenki.game.enemies;

import com.serenki.game.Vector;
import com.serenki.game.pathfinding.Pathfinding;

public class MilitaryTruck extends Enemy {
    public MilitaryTruck(Vector position, Pathfinding pathfinding) {
        super(position, pathfinding, 2.2, 0.25, 60, 30,3, "file:src/main/resources/com/serenki/art/enemies/MilitaryTruck.png");
    }
}
