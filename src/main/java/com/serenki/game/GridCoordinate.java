package com.serenki.game;

public class GridCoordinate {

    private int x;
    private int y;

    public GridCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GridCoordinate(GridCoordinate position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
