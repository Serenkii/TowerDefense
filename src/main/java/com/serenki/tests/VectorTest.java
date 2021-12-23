package com.serenki.tests;

import com.serenki.game.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VectorTest {

    @Test
    void normalize() {
        Vector v = new Vector(3, 4);
        v.normalize();
        Assertions.assertEquals(v.abs(), 1.0);
        v = new Vector(1364, 136);
        v.normalize();
        Assertions.assertEquals(v.abs(), 1.0);
    }

    @Test
    void add() {
        Vector v = new Vector(5, 11);
        v = v.add(new Vector(12, 19));
        Assertions.assertTrue(v.equals(new Vector(17, 30)));
    }

    @Test
    void subtract() {
        Vector v = new Vector(5, 11);
        v = v.subtract(new Vector(12, 19));
        Assertions.assertTrue(v.equals(new Vector(-7, -8)));
    }

    @Test
    void multiply() {
        Vector v = new Vector(10, 30);
        v = v.multiply(3);
        Assertions.assertTrue(v.equals(new Vector(30, 90)));
    }

    @Test
    void divide() {
        Vector v = new Vector(-12, -30);
        v = v.divide(-3);
        Assertions.assertTrue(v.equals(new Vector(4, 10)));
    }

    @Test
    void distanceTo() {
        Vector v1 = new Vector(0, 0);
        Assertions.assertEquals(5, v1.distanceTo(new Vector(3, 4)));
    }

    @Test
    void distanceToSquared() {
        Vector v1 = new Vector(0, 0);
        Assertions.assertEquals(25, v1.distanceToSquared(new Vector(3, 4)));
    }

    @Test
    void abs() {
        Vector v = new Vector(3, 4);
        Assertions.assertEquals(5, v.abs());
        v = new Vector(6, 8);
        Assertions.assertEquals(10, v.abs());
    }

    @Test
    void absoluteSquared() {
        Vector v = new Vector(3, 4);
        Assertions.assertEquals(25, v.absoluteSquared());
        v = new Vector(6, 8);
        Assertions.assertEquals(100, v.absoluteSquared());
    }

    @Test
    void vectorTo() {
        Vector v = new Vector(0, 0);
        Vector vecTo = v.vectorTo(new Vector(3, 0));
        Assertions.assertEquals(3, vecTo.getX());
        Assertions.assertEquals(0, vecTo.getY());

        Vector v1 = new Vector(0, 0);
        Vector vecTo1 = v1.vectorTo(new Vector(3, 4));
        Assertions.assertEquals(3, vecTo1.getX());
        Assertions.assertEquals(4, vecTo1.getY());

        Vector v2 = new Vector(2, 10);
        Vector vecTo2 = v2.vectorTo(new Vector(5, 7));
        Assertions.assertEquals(3, vecTo2.getX());
        Assertions.assertEquals(-3, vecTo2.getY());
    }

    @Test
    void dotProduct() {
        Assertions.assertEquals(0, new Vector(3, 4).dotProduct(new Vector(0, 0)));
        Assertions.assertEquals(25, new Vector(3, 4).dotProduct(new Vector(3, 4)));
        Assertions.assertEquals(35, new Vector(3, 4).dotProduct(new Vector(5, 5)));
    }

    @Test
    void orthogonal() {
        Assertions.assertEquals(new Vector(1, 0), new Vector(0, -1).orthogonal());
        Assertions.assertEquals(new Vector(3, 4), new Vector(4, -3).orthogonal());
    }

    @Test
    void angleTo() {
        Assertions.assertEquals(90, Math.toDegrees(new Vector(1, 0).angleTo(new Vector(0, 1))));
        //Assertions.assertEquals(45, Math.toDegrees(new Vector(1, 0).angleTo(new Vector(1, 1))));
        //Assertions.assertEquals(45, Math.toDegrees(new Vector(1, 0).angleTo(new Vector(1, -1))));
    }
}