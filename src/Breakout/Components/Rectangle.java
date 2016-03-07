package Breakout.Components;

import acm.graphics.GRectangle;

/**
 * Created by philipp on 07.03.16.
 */
public class Rectangle {

    private GRectangle rect;

    public Rectangle(GRectangle rect) {
        this.rect = rect;
    }

    public double getLeft() {
        return  rect.getX();
    }

    public double getRight() {
        return  rect.getX() + rect.getWidth();
    }

    public double getTop() {
        return  rect.getY();
    }

    public double getBottom() {
        return  rect.getY() + rect.getHeight();
    }


}
