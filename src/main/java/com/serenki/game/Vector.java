package com.serenki.game;

import org.jetbrains.annotations.NotNull;

public class Vector {

    private double x;
    private double y;

    public Vector(double x, double y) {
        setX(x);
        setY(y);
    }

    public Vector(@NotNull Vector position) {
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

    /**
     * Adds the x and y value of a second vector to this one.
     * @param vector The values to add.
     */
    public void add(@NotNull Vector vector) {
        this.setX(this.getX() + vector.getX());
        this.setY(this.getY() + vector.getY());
    }

    /**
     * Subtracts the x and y value of a seconds vector to this one.
     * @param vector The values to subtract.
     */
    public void subtract(@NotNull Vector vector) {
        this.setX(this.getX() - vector.getX());
        this.setY(this.getY() - vector.getY());
    }

    /**
     * Multiplies both x and y with the parameter d.
     * @param d The value to multiply with.
     */
    public void multiply(double d) {
        this.setX(this.getX() * d);
        this.setY(this.getY() * d);
    }

    /**
     * Divides both x and y by the parameter d.
     * @param d The value to divide by.
     */
    public void divide(double d) {
        this.setX(this.getX() / d);
        this.setY(this.getY() / d);
    }

    /**
     * @param vector The point to which you want to know the distance to.
     * @return The distance from this object to the point vector.
     */
    public double distanceTo(@NotNull final Vector vector) {
        Vector v = new Vector(this);
        v.subtract(vector);
        return v.abs();
    }

    /**
     * Use this method for efficiency, so you do not have to use the Math.sqrt(...)-Method several times.
     * @param vector The point to which you want to know the distance to.
     * @return The distance from this object to the point vector, SQUARED(!). (For efficiency, so the Math.sqrt(...)-Method does not need to be run)
     */
    public double distanceToSquared(@NotNull final Vector vector) {
        Vector v = new Vector(this);
        v.subtract(vector);
        return v.absoluteSquared();
    }

    /**
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The distance between those two vectors.
     */
    public static double distanceBetweenPoints(@NotNull final Vector vector1, @NotNull final Vector vector2) {
        return vector1.distanceTo(vector2);
    }

    /**
     * Use this method for efficiency, so you do not have to use the Math.sqrt(...)-Method several times.
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The distance between the two vectors, SQUARED(!). (For efficiency, so the Math.sqrt(...)-Method does not need to be run)
     */
    public static double distanceBetweenPointsSquared(@NotNull final Vector vector1, @NotNull final Vector vector2) {
        return vector1.distanceToSquared(vector2);
    }

    /**
     * Returns the length of the vector.
     * @return The absolute value, so to speak the length of the vector if seen as an arrow.
     */
    public double abs() {
        return Math.sqrt(absoluteSquared());
    }

    /**
     * Use this method for efficiency, so you do not have to use the Math.sqrt(...)-Method several times.
     * @return The absolute value, so to speak the length of the vector if seen as an arrow, SQUARED(!). (For efficiency, so the Math.sqrt(...)-Method does not need to be run)
     */
    public double absoluteSquared() {
        return ((this.x * this.x) + (this.y + this.y));
    }
}
