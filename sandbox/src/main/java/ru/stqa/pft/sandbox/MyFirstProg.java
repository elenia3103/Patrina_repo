package ru.stqa.pft.sandbox;

import java.awt.*;

public class MyFirstProg {
    public static void main (String[] args) {
        Point p1 = new Point(10, 3);
        Point p2 = new Point(4, 6);
        System.out.println("Расстояние между двумя точками p1 и p2 = "+distance(p1, p2));
    }
    public static double distance (Point p1, Point p2) {

        return Math.sqrt(Math.pow((p1.x-p2.x),2)+Math.pow((p1.y-p2.y),2));
    }

}
