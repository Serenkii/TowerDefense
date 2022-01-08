package com.serenki.tests;

import com.serenki.game.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VectorTest {

    @Test
    void normalize() {
        Vector v = new Vector(3, 4);
        Vector normalizedVector = v.normalize();
        assertEquals(1.0, normalizedVector.abs());
        v = new Vector(1364, 136);
        Vector normalizedVector2 = v.normalize();
        assertEquals(1.0, normalizedVector2.abs());
    }

    @Test
    void add() {
        Vector v = new Vector(5, 11);
        v = v.add(new Vector(12, 19));
        assertEquals(v, new Vector(17, 30));
    }

    @Test
    void subtract() {
        Vector v = new Vector(5, 11);
        v = v.subtract(new Vector(12, 19));
        assertEquals(v, new Vector(-7, -8));
    }

    @Test
    void multiply() {
        Vector v = new Vector(10, 30);
        v = v.multiply(3);
        assertEquals(v, new Vector(30, 90));
    }

    @Test
    void divide() {
        Vector v = new Vector(-12, -30);
        v = v.divide(-3);
        assertEquals(v, new Vector(4, 10));
    }

    @Test
    void distanceTo() {
        Vector v1 = new Vector(0, 0);
        assertEquals(5, v1.distanceTo(new Vector(3, 4)));
    }

    @Test
    void distanceToSquared() {
        Vector v1 = new Vector(0, 0);
        assertEquals(25, v1.distanceToSquared(new Vector(3, 4)));
    }

    @Test
    void abs() {
        Vector v = new Vector(3, 4);
        assertEquals(5, v.abs());
        v = new Vector(6, 8);
        assertEquals(10, v.abs());
    }

    @Test
    void absoluteSquared() {
        Vector v = new Vector(3, 4);
        assertEquals(25, v.absoluteSquared());
        v = new Vector(6, 8);
        assertEquals(100, v.absoluteSquared());
    }

    @Test
    void vectorTo() {
        Vector v = new Vector(0, 0);
        Vector vecTo = v.vectorTo(new Vector(3, 0));
        assertEquals(3, vecTo.getX());
        assertEquals(0, vecTo.getY());

        Vector v1 = new Vector(0, 0);
        Vector vecTo1 = v1.vectorTo(new Vector(3, 4));
        assertEquals(3, vecTo1.getX());
        assertEquals(4, vecTo1.getY());

        Vector v2 = new Vector(2, 10);
        Vector vecTo2 = v2.vectorTo(new Vector(5, 7));
        assertEquals(3, vecTo2.getX());
        assertEquals(-3, vecTo2.getY());
    }

    @Test
    void dotProduct() {
        assertEquals(0, new Vector(3, 4).dotProduct(new Vector(0, 0)));
        assertEquals(25, new Vector(3, 4).dotProduct(new Vector(3, 4)));
        assertEquals(35, new Vector(3, 4).dotProduct(new Vector(5, 5)));
    }

    @Test
    void orthogonal() {
        assertEquals(new Vector(1, 0), new Vector(0, -1).orthogonal());
        assertEquals(new Vector(3, 4), new Vector(4, -3).orthogonal());
    }

    @Test
    void angleTo() {
        assertEquals(90, Math.toDegrees(new Vector(1, 0).angleTo(new Vector(0, 1))));
        assertEquals(45, Math.round(Math.toDegrees(new Vector(1, 0).angleTo(new Vector(1, 1)))));
        assertEquals(45, Math.round(Math.toDegrees(new Vector(1, 0).angleTo(new Vector(1, -1)))));
        assertEquals(180, Math.toDegrees(new Vector(1, 0).angleTo(new Vector(-1, 0))));
        assertEquals(180, Math.toDegrees(new Vector(0, 1).angleTo(new Vector(0, -1))));
    }
}