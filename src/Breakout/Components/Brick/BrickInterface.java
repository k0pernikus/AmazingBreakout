package Breakout.Components.Brick;

/**
 * Created by philipp on 28.02.16.
 */
public interface BrickInterface {
    boolean isHittable();
    boolean isActive();

    double getWidth();

    double getHeight();


    void destroy();
}
