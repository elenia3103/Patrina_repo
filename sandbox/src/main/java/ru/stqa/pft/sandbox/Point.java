package ru.stqa.pft.sandbox;

public class Point {
    int x;
    int y;
    Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance (Point p1, Point p2) {
        double a = Math.pow((p1.x-p2.x),2);
        double b = Math.pow((p1.y-p2.y),2);
        return Math.sqrt(a+b);
    }


}
