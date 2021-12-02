package com.serenki.game.enemies;

import com.serenki.game.Vector;

public class Soldier extends Enemy {
    public Soldier(Vector position) {
        super(position, 1, 0.2, 50, "file:src/main/resources/com/serenki/art/enemies/DefaultEnemy.png");
    }
}
