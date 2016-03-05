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
        return hitCount >= maxHitCount;
    }


    @Override
    public void destroy() {
        try {
            super.destroy();
        } catch (UndestroyableException e) {
            hit();
            setFillColor(random.nextColor());
            throw e;
        }
    }

    public void hit() {
        this.hitCount = hitCount + 1;
    }
}

