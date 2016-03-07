package Breakout.Service;

/**
 * Created by philipp on 06.03.16.
 */
public class Collision {
    public boolean isHorizontalCollsion() {
        return isHorizontalCollsion;
    }

    public boolean isVerticalCollsion() {
        return isVerticalCollsion;
    }

    private boolean isHorizontalCollsion;

    private boolean isVerticalCollsion;

    public Collision(boolean xAxis, boolean yAxis) {
        this.isHorizontalCollsion = xAxis;
        this.isVerticalCollsion = yAxis;
    }

}
