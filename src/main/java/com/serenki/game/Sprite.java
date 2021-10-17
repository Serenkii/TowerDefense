package com.serenki.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {

    Image image;

    public Sprite(String pathToImage) {
        this.image = new Image(pathToImage);
    }

    /**
     *
     * @param position The ingame position (default from 0.0 to 15.0)
     * @param context
     */
    public void render(Position position, GraphicsContext context) {
        GridCoordinate upperLeftCorner = new GridCoordinate((int) ((position.getX() * Battlefield.SQUARE_SIZE) - image.getHeight() / 2),
                (int) ((position.getY() * Battlefield.SQUARE_SIZE) - (image.getWidth() / 2)));
        this.render(upperLeftCorner, context);
    }

    /**
     *
     * @param pixelCoordinate Top left corner of the screen
     * @param context
     */
    public void render(GridCoordinate pixelCoordinate, GraphicsContext context) {
        context.drawImage(this.image, pixelCoordinate.getX(), pixelCoordinate.getY());
    }

    public double getWidth() {
        return image.getWidth();
    }
    public double getHeight() {
        return image.getHeight();
    }
}
