package com.serenki.game;

import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {

    protected Sprite sprite;
    protected Vector position;

    public abstract void update();

    public GameObject(final String pathToImage) {
        sprite = new Sprite(pathToImage);
    }

    public GameObject(final String pathToImage, final double imageWidth, final double imageHeight) {
        sprite = new Sprite(pathToImage, imageWidth, imageHeight);
    }

    public GameObject(final Vector position, final String pathToImage) {
        this.position = new Vector(position);
        sprite = new Sprite(pathToImage);
    }

    public GameObject(final Vector position, final String pathToImage, final double imageWidth, final double imageHeight) {
        this.position = new Vector(position);
        sprite = new Sprite(pathToImage, imageWidth, imageHeight);
    }

    public Vector getPosition() {
        return position;
    }

    public void render(GraphicsContext context) {
        sprite.render(position, context);
    }
}
