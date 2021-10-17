package com.serenki.game;

import com.serenki.game.enemies.Enemy;

public class Missile extends GameObject{

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
