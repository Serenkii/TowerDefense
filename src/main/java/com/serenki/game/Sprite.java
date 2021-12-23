package com.serenki.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {

    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/image/Image.html
    private Image image;

    private double rotation;

    public Sprite(String pathToImage) {
        this.image = new Image(pathToImage);
        this.rotation = 0;
    }

    public Sprite(String pathToImage, double width, double height) {
        this.image = new Image(pathToImage, height, width, false, false);
        this.rotation = 0;
    }

    /**
     *
     * @param position The ingame position (default from 0.0 to 15.0)
     * @param context
     */
    public void render(Vector position, GraphicsContext context) {
        GridCoordinate upperLeftCorner = new GridCoordinate((int) ((position.getX() * Battlefield.SQUARE_SIZE) - image.getHeight() / 2),
                (int) ((position.getY() * Battlefield.SQUARE_SIZE) - (image.getWidth() / 2)));
        this.render(upperLeftCorner, context);
    }

    /**
     *
     * @param pixelCoordinate The pixel-position the top left corner of the image should be placed in.
     * @param context
     */
    public void render(GridCoordinate pixelCoordinate, GraphicsContext context) {
        context.drawImage(this.image, pixelCoordinate.getX(), pixelCoordinate.getY());
    }

    public Image getImage() {
        return image;
    }

    public void changeImageRotationTo(double rotationInDegrees) {
        this.rotation = Math.toRadians(rotationInDegrees);
    }

    public double getWidth() {
        return image.getWidth();
    }
    public double getHeight() {
        return image.getHeight();
    }
}
