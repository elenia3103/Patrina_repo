package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG {
    @Test
    public void testDistance() {
        Point p1 = new Point(3, 3);
        Point p2 = new Point(4, 6);
        Assert.assertEquals(p1.distance(p2),3.1622776601683795);
    }
    @Test
    public void testDistance1() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(3, 3);
        Assert.assertEquals(p1.distance(p2),2.8284271247461903);
    }
    @Test
    public void testDistance2() {
        Point p1 = new Point(5, 3);
        Point p2 = new Point(7, 8);
        Assert.assertEquals(p1.distance(p2),5.385164807134504);
    }
}
