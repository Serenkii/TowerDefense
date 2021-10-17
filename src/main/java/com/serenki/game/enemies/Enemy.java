package com.serenki.game.enemies;

import com.serenki.game.GameObject;

public class Enemy extends GameObject {

    public Enemy(String pathToImage) {
        super(pathToImage);
    }

    @Override
    public void update() {
        move();
    }

    public void move() {

    }
}
