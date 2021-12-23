package com.serenki.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class Sprite {

    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/image/Image.html
    private Image image;

    private double rotationAngle;

    public Sprite(String pathToImage) {
        this.image = new Image(pathToImage);
    }

    public Sprite(String pathToImage, double width, double height) {
        this.image = new Image(pathToImage, height, width, false, false);
    }

    /**
     *
     * @param position The ingame position (default from 0.0 to 15.0)
     * @param context
     */ /*
    public void render(Vector position, GraphicsContext context) {
        GridCoordinate upperLeftCorner = new GridCoordinate((int) ((position.getX() * Battlefield.SQUARE_SIZE) - image.getHeight() / 2),
                (int) ((position.getY() * Battlefield.SQUARE_SIZE) - (image.getWidth() / 2)));
        this.render(upperLeftCorner, context);
    } */

    /**
     *
     * @param pixelCoordinate The pixel-position the top left corner of the image should be placed in.
     * @param context
     */ /*
    public void render(GridCoordinate pixelCoordinate, GraphicsContext context) {
        context.drawImage(this.image, pixelCoordinate.getX(), pixelCoordinate.getY());
    } */



    /**              ROTATION STUFF             **/
    // https://stackoverflow.com/questions/18260421/how-to-draw-image-rotated-on-javafx-canvas

    /**
     * Sets the transform for the GraphicsContext to rotate around a pivot point.
     *
     * @param graphicsContext the graphics context the transform to applied to.
     * @param px the x pivot co-ordinate for the rotation (in canvas co-ordinates).
     * @param py the y pivot co-ordinate for the rotation (in canvas co-ordinates).
     */
    private void rotate(GraphicsContext graphicsContext, double px, double py) {
        Rotate r = new Rotate(this.rotationAngle, px, py);
        graphicsContext.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    /**
     *
     * @param position
     * @param graphicsContext
     */
    public void render(Vector position, GraphicsContext graphicsContext) {
        GridCoordinate upperLeftCorner = new GridCoordinate((int) ((position.getX() * Battlefield.SQUARE_SIZE) - image.getHeight() / 2),
                (int) ((position.getY() * Battlefield.SQUARE_SIZE) - (image.getWidth() / 2)));
        render(upperLeftCorner, graphicsContext);
    }

    /**
     *
     * @param pixelCoordinate The pixel-position the top left corner of the image
     * @param graphicsContext
     */
    public void render(GridCoordinate pixelCoordinate, GraphicsContext graphicsContext) {
        graphicsContext.save();
        rotate(graphicsContext, pixelCoordinate.getX() + image.getWidth() / 2, pixelCoordinate.getY() + image.getHeight() / 2);
        graphicsContext.drawImage(image, pixelCoordinate.getX(), pixelCoordinate.getY());
        graphicsContext.restore();
    }

    /**
     *
     * @param facing The vector (direction) in which the image should be facing.
     */
    public void setRotationAngle(Vector facing) {
        if (facing.getY() < 0)
            this.rotationAngle = -Math.toDegrees(new Vector(1, 0).angleTo(facing));

        else
            this.rotationAngle = Math.toDegrees(new Vector(1, 0).angleTo(facing));
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }


    /**              GETTERS                    **/

    /**
     *
     * @return image
     */
    public Image getImage() {
        return image;
    }

    public double getWidth() {
        return image.getWidth();
    }
    public double getHeight() {
        return image.getHeight();
    }
}
