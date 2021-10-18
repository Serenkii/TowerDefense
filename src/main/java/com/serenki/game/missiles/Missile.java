package com.serenki.game.missiles;

import com.serenki.game.GameObject;
import com.serenki.game.enemies.Enemy;

public abstract class Missile extends GameObject {

    Enemy target;

    public Missile(Enemy target, String pathToImage) {
        super(pathToImage);
        this.target = target;
    }

    @Override
    public void update() {
        move();
    }

    public void move() {

    }
}

/**
 * TODO: Make one class for guided missiles and one for position locked? Is the upper class Missile even needed in that case?
 */
