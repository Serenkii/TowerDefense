package com.serenki.game;

public abstract class GameObject {

    protected Sprite sprite;


    public abstract void update();

    public void render() {
        sprite.render();
    }
}
