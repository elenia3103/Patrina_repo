package ru.stqa.pft.sandbox;

import java.awt.*;

public class MyFirstProg {
    public static void main (String[] args) {
        Point p1 = new Point(10, 3);
        Point p2 = new Point(4, 6);
        System.out.println("Расстояние между двумя точками p1 и p2 = "+p1.distance(p2));


    }
}
