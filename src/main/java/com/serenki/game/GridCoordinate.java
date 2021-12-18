package com.serenki.game;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridCoordinate that = (GridCoordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
