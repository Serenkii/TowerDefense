package com.serenki.game;

import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {

    protected Sprite sprite;
    protected Position position;

    public abstract void update();

    public void render(GraphicsContext context) {
        sprite.render(position, context);
    }
}
