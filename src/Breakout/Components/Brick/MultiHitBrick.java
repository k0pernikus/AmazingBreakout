package Breakout.Components.Brick;

import acm.util.RandomGenerator;

import java.awt.*;

/**
 * Created by philipp on 29.02.16.
 */
public class MultiHitBrick extends Brick implements BrickInterface {
    private int hitCount = 0;

    private int maxHitCount = 2;

    private RandomGenerator random;

    public MultiHitBrick(double x, double y, double width, double height) {

        super(x, y, width, height);
        this.setFillColor(Color.red);
        random = new RandomGenerator();
    }

    public boolean isDestroyable() {
        return hitCount > 3;
    }


    @Override
    public void destroy() {
        if (hitCount > maxHitCount) {
            super.destroy();
        }

        this.hitCount = hitCount + 1;

        if (hitCount < maxHitCount) {
            setFillColor(random.nextColor());
        }
    }
}
