package Breakout.Components;

import acm.graphics.GRect;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by philipp on 28.02.16.
 */
public class Paddle extends GRect implements MouseMotionListener {
    public static double BASE_SPEED = 10;

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

    public void move(double xSpeed) {
        if (xSpeed == 0) {
            return;
        }
        double x = xSpeed + getX();

        if (x < 0) {
            x = 0;
        }

        if (x + getWidth() > gameDimension.getWidth()) {
            x = gameDimension.getWidth() - getWidth();
        }

        setLocation(x, getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getXOnScreen();
        double targetX = (double) mouseX + getWidth() / 2;

        /**
         * needs spaceship operator
         * return targetX <=> getX() :D
         */

        int directionModifier = 0;
        if (targetX < getX()) {
            directionModifier = 1;
        }

        if (targetX > getX()) {
            directionModifier = -1;
        }

        if (directionModifier == 0) {
            return;
        }

        move(BASE_SPEED * directionModifier);
    }
}
