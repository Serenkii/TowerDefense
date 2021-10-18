package com.serenki.game;

public class Position {

    private double x;
    private double y;

    public Position(double x, double y) {
        setX(x);
        setY(y);
    }

    public Position(Position position) {
        this.setX(position.getX());
        this.setY(position.getY());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
