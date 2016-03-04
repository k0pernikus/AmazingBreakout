package Breakout.Components.Brick;

import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by philipp on 27.02.16.
 */
public class Brick extends GRect implements BrickInterface {
    private boolean isHittable;

    private boolean destroyed;

    public Brick(double x, double y, double width, double height) {
        super(x, y, width, height);
        setFillColor(Color.BLUE);
        setFilled(true);
        setDestroyed(false);
        setHittable(true);
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    @Override
    public boolean isDestroyable() {
        return true;
    }

    public void destroy() {
        if (!isDestroyable()) {
            throw new UndestroyableException();
        }

        this.setDestroyed(true);
    }

    private void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isHittable() {
        return isHittable;
    }

    private void setHittable(boolean isHittable) {
        this.isHittable = true;
    }
}
