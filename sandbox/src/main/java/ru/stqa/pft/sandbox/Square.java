package ru.stqa.pft.sandbox;

public class Square {
    public double l;
    Square (double l) {
        this.l = l;
    }
    public double area(Square s) {
        return this.l*this.l;
    }
}
