package Breakout.Components;

import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by philipp on 28.02.16.
 */
public class Paddle extends GRect {

    private double xMovement = 0;

    private Dimension gameDimension;

    public Paddle(Dimension gameDimension, double startX, double startY, double width, double height) {
        super(startX, startY, width, height);
        this.setFillColor(Color.GREEN);
        this.setFilled(true);
        this.gameDimension = gameDimension;
    }

    public static Paddle makePaddle(Dimension gameDimension) {
        double width = gameDimension.getWidth() * .2;
        double height = gameDimension.getHeight() * .05;
        return new Paddle(gameDimension, gameDimension.getWidth() / 2 - width / 2, gameDimension.getHeight() * .8, width, height);
    }

    public double getxMovement() {
        return xMovement;
    }

    public void setxMovement(double xMovement) {
        this.xMovement = xMovement;
    }

    public void move() {
        double x = this.getxMovement() + this.getX();

        if (x < 0) {
            x = 0;
        }

        if (x + this.getWidth() > this.gameDimension.getWidth()) {
            x = this.gameDimension.getWidth() - this.getWidth();
        }

        this.setLocation(x, this.getY());
    }
}
