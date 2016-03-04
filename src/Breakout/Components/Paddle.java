package Breakout.Components;

import acm.graphics.GRect;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by philipp on 28.02.16.
 */
public class Paddle extends GRect implements MouseMotionListener {
    public double getTargetXLocation() {
        return targetXLocation;
    }

    public void setTargetXLocation(double targetXLocation) {
        this.targetXLocation = targetXLocation;
    }

    double targetXLocation;

    public static double BASE_SPEED = 20;

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

    public void moveToTarget(double xSpeed) {
        if (Double.isNaN(getTargetXLocation())) {
            return;
        }

        double target = getTargetXLocation();
        xSpeed = Math.abs(xSpeed);
        double oldX = getX();

        if (target < oldX) {
            xSpeed *= -1;
        }

        double x = oldX + xSpeed;

        double offset = Math.abs(x - target);

        if (offset < xSpeed) {
            setTargetXLocation(Double.NaN);
        }

        x = keepOnBoard(x);


        setLocation(x, getY());
    }

    private double keepOnBoard(double x) {
        if (x < 0) {
            setTargetXLocation(Double.NaN);
            x = 0;
        }

        if (x + getWidth() > gameDimension.getWidth()) {
            setTargetXLocation(Double.NaN);
            x = gameDimension.getWidth() - getWidth();
        }
        return x;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double targetX = e.getX();
        targetX -= getWidth() / 2;
        setTargetXLocation(targetX);
    }
}
