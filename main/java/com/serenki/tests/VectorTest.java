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
        v.add(new Vector(12, 19));
        Assertions.assertTrue(v.equals(new Vector(17, 30)));
    }

    @Test
    void subtract() {
        Vector v = new Vector(5, 11);
        v.subtract(new Vector(12, 19));
        Assertions.assertTrue(v.equals(new Vector(-7, -8)));
    }

    @Test
    void multiply() {
        Vector v = new Vector(10, 30);
        v.multiply(3);
        Assertions.assertTrue(v.equals(new Vector(30, 90)));
    }

    @Test
    void divide() {
        Vector v = new Vector(-12, -30);
        v.divide(-3);
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
    void distanceBetweenPoints() {
        Assertions.assertEquals(5, Vector.distanceBetweenPoints(new Vector(0,0), new Vector(4, 3)));
    }

    @Test
    void distanceBetweenPointsSquared() {
        Assertions.assertEquals(25, Vector.distanceBetweenPointsSquared(new Vector(0,0), new Vector(4, 3)));
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
}