package Breakout.Components.Brick;

import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by philipp on 27.02.16.
 */
public class Brick extends GRect implements BrickInterface {
    private boolean isHittable = true;

    private boolean isActive;

    public Brick(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.setFillColor(Color.BLUE);
        this.setFilled(true);
        this.setActive(true);
    }

    public boolean destroy() {
        this.setActive(false);

        return true;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isHittable() {
        return isHittable;
    }

    public void setHittable(boolean isHittable) {
        this.isHittable = true;
    }
}
