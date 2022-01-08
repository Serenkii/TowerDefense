package com.serenki.game;

import javafx.scene.canvas.GraphicsContext;
import org.jetbrains.annotations.ApiStatus;


public class Battlefield {

    public final static int PIXEL_SIZE = 1080;
    public final static int GRID_SIZE = 15;
    public final static int EDGE_SIZE = 1;
    public final static int SQUARE_SIZE = PIXEL_SIZE / GRID_SIZE;   //72
    public final static GridCoordinate PIXEL_POS = new GridCoordinate(0, 0);

    private Sprite background;



    public Battlefield() {
        background = new Sprite("file:src/main/resources/com/serenki/art/background/Battlefield2.png", Battlefield.PIXEL_SIZE, Battlefield.PIXEL_SIZE);
    }

    /**
     * Renders the Battlefield-image on the canvas.
     * @param context GraphicsContext of the canvas.
     */
    public void render(final GraphicsContext context) {
        background.render(PIXEL_POS, context);
    }

    /**
     * TODO!
     * @param pixPos
     * @return
     */
    public static Vector getPositionFromPixPos(GridCoordinate pixPos) {
        return null; //TODO
    }

    /**
     * Calculates the game-coordinate based on a monitor-pixel.
     * @param pixPos The coordinate of the pixel. (0-1080)
     * @return The GridCoordinate of the game. (0-15)
     */
    public static GridCoordinate getCoordinateFromPixPos(GridCoordinate pixPos) {
        return new GridCoordinate( pixPos.getX() / SQUARE_SIZE,  pixPos.getY() / SQUARE_SIZE);
    }

    /**
     * Dunno if I may ever change to something like that.
     * @deprecated
     * @param size
     * @param pixelPos
     * @param gridSize
     * @param pathToImage
     */
    @ApiStatus.Experimental
    public Battlefield(int size, GridCoordinate pixelPos, int gridSize, final String pathToImage) {
        /*this.size = size;
        this.gridSize = gridSize;
        this.pixelPos = pixelPos;   */

        background = new Sprite(pathToImage);
    }
}
