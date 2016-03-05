package Breakout.Components;

import acm.graphics.GOval;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by philipp on 27.02.16.
 */
public class Ball extends GOval implements MouseInputListener{
    private boolean isPaused = true;

    public static int X_DIRECTION = 1;
    public static int Y_DIRECTION = 2;

    private double xDirection = 9;
    private double yDirectoon = -7;

    private double radius;

    public Ball(double startX, double startY, double radius) {
        super(startX, startY, radius * 2, radius * 2);
        setRadius(radius);
        setFillColor(Color.BLACK);
        setFilled(true);
    }

    public void move() {
        if (this.isPaused == true) {
            return;
        }
        this.setLocation(this.getX() + xDirection, this.getY() + this.yDirectoon);
    }

    public void pause() {
        this.isPaused = true;
    }

    public void unpause() {
        this.isPaused = false;
    }

    public void togglePause() {
//        this.isPaused ^= true;
        this.isPaused = !isPaused;
    }

    /**
     * @param type
     */
    public void invertDirection(int type) {
        if (type == Ball.X_DIRECTION) {
            setxDirection(-getxDirection());
        }

        if (type == Ball.Y_DIRECTION) {
            setyDirectoon(-getyDirectoon());
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

    @Override
    public void mouseClicked(MouseEvent e) {
        togglePause();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
