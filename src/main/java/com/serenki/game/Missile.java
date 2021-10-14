package com.serenki.game;

public class Missile extends GameObject{

    Enemy target;

    public Missile(Enemy target) {
        this.target = target;
    }

    @Override
    public void update() {
        move();
    }

    public void move() {

    }
}
