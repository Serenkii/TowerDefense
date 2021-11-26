package com.serenki.game;

import org.jetbrains.annotations.NotNull;

/**
 * TODO: Refactor so that Vector has x and y which are final and each method returns a new Vector instead of changing this one
 */


public class Vector {

    private final double x;
    private final double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(@NotNull Vector position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Returns a normalized vector of this vector.
     * @return A normalized vector facing in the same direction.
     */
    public Vector normalize() {
        double d = this.abs();      //solving the equation for x: sqrt((ax^2+bx^2) = 1 gives you: x = 1/sqrt(a^2+b^2)
                                        //for the case that the length is 0, d will be infinity, lets just hope nothing crazy happens...
        return new Vector(this.x / d, this.y / d);
    }

    /**
     * Adds the x and y value of a second vector to this one.
     * @param vector The values to add.
     * @return Returns the vector you get when adding vector to this.
     */
    public Vector add(@NotNull final Vector vector) {
        return new Vector(this.x + vector.getX(), this.y + vector.getY());
    }

    /**
     * Subtracts the x and y value of a seconds vector to this one.
     * @param vector The values to subtract.
     * @return Returns the vector you get when subtracting vector from this.
     */
    public Vector subtract(@NotNull final Vector vector) {
        return new Vector(this.x - vector.getX(), this.y - vector.getY());
    }

    /**
     * Multiplies both x and y with the parameter d.
     * @param factor The value to multiply with.
     * @return Returns a vector multiplied with the factor.
     */
    public Vector multiply(final double factor) {
        return new Vector(this.x * factor, this.y * factor);
    }

    /**
     * Divides both x and y by the parameter d.
     * @param divisor The value to divide by.
     * @return Returns a vector divided by the divisor.
     */
    public Vector divide(final double divisor) {
        return new Vector(this.x / divisor, this.y / divisor);
    }

    /**
     * @return Returns the left orthogonal vector of this.
     */
    public Vector orthogonal() {
        return new Vector(-this.y, this.x);
    }

    /**
     * Calculates the dot product of the two vectors.
     * @param vector The other vector.
     * @return Returns the dot product of these two vectors.
     */
    public double dotProduct(@NotNull final Vector vector) {
        return ((this.x * vector.getX()) + (this.y * vector.getY()));
    }

    /**
     * @param vector The point to which you want to know the distance to.
     * @return The distance from this object to the point vector.
     */
    public double distanceTo(@NotNull final Vector vector) {
        return this.vectorTo(vector).abs();
    }

    /**
     * Use this method for efficiency, so you do not have to use the Math.sqrt(...)-Method several times.
     * @param vector The point to which you want to know the distance to.
     * @return The distance from this object to the point vector, SQUARED(!). (For efficiency, so the Math.sqrt(...)-Method does not need to be run)
     */
    public double distanceToSquared(@NotNull final Vector vector) {
        return this.vectorTo(vector).absoluteSquared();
    }

    /**
     * Returns the length of the vector.
     * @return The absolute value, so to speak the length of the vector if seen as an arrow.
     */
    public double abs() {
        return Math.sqrt(this.absoluteSquared());
    }

    /**
     * Use this method for efficiency, so you do not have to use the Math.sqrt(...)-Method several times.
     * @return The absolute value, so to speak the length of the vector if seen as an arrow, SQUARED(!). (For efficiency, so the Math.sqrt(...)-Method does not need to be run)
     */
    public double absoluteSquared() {
        return (this.x * this.x) + (this.y * this.y);
    }

    /**
     * Returns the vector you get when pointing from this vector to the target vector.
     * @param target The vector the return vector should face to.
     * @return The vector you get if you face the target vector from this vector.
     */
    public Vector vectorTo(@NotNull final Vector target) {
        return target.subtract(this);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Double.compare(vector.x, x) == 0 && Double.compare(vector.y, y) == 0;
    }
}
