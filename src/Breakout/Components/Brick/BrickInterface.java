package Breakout.Components.Brick;

/**
 * Created by philipp on 28.02.16.
 */
public interface BrickInterface {
    boolean isHittable();

    boolean isDestroyed();

    void destroy();

    double getWidth();

    double getHeight();

    boolean isDestroyable();
}
