package Breakout.Components;

import acm.graphics.GOval;

import java.awt.*;

/**
 * Created by philipp on 27.02.16.
 */
public class Ball extends GOval {
    private boolean isPaused = true;

    public static int X_DIRECTION = 1;
    public static int Y_DIRECTION = 2;

    private double xDirection = 9;
    private double yDirectoon = -7;

    private double radius;

    public Ball(double startX, double startY, double radius) {
        super(0, 200);
        this.setRadius(radius);
        super.setSize(radius * 2, radius * 2);
        this.setFillColor(Color.BLACK);
        this.setFilled(true);
    }

    public void move() {
        if (this.isPaused == true) {
            return;
        }
        this.setLocation(this.getX() + xDirection, this.getY() + this.yDirectoon);
    }

    public void invertDirection() {
        this.setxDirection(-this.getxDirection());
        this.setyDirectoon(-this.getyDirectoon());
    }


    public void pause() {
        this.isPaused = true;
    }

    public void unpause() {
        this.isPaused = false;
    }

    public void togglePause() {
        this.isPaused ^= true;
    }

    /**
     * @param type
     */
    public void invertDirection(int type) {
        if (type == Ball.X_DIRECTION) {
            this.setxDirection(-this.getxDirection());
        }

        if (type == Ball.Y_DIRECTION) {
            this.setyDirectoon(-this.getyDirectoon());
        }
    }

    public double getxDirection() {
        return xDirection;
    }

    public void setxDirection(double xDirection) {
        this.xDirection = xDirection;
    }

    public double getyDirectoon() {
        return yDirectoon;
    }

    public void setyDirectoon(double yDirectoon) {
        this.yDirectoon = yDirectoon;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
