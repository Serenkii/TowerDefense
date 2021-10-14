package com.serenki.game;

public class Tower extends GameObject{

    Position position;

    public Tower (Position position) {
        this.position = new Position(position);
    }

    @Override
    public void update() {
        shoot();
    }

    public void shoot() {

    }
}
