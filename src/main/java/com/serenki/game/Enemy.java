package com.serenki.game;

public class Enemy extends GameObject{

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
