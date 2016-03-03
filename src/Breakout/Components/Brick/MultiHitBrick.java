package Breakout.Components.Brick;

import java.awt.*;

/**
 * Created by philipp on 29.02.16.
 */
public class MultiHitBrick extends Brick implements BrickInterface {
    private int hitCount = 0;

    private Color[] colors = {
            Color.red,
            Color.orange,
            Color.pink,
            Color.green
    };

    public MultiHitBrick(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.setFillColor(this.colors[this.hitCount]);
    }

    @Override
    public boolean destroy() {
        if (this.hitCount > 3) {
            super.destroy();
            return true;
        }

        this.hitCount = this.hitCount + 1;

        if (this.hitCount >= 0 && this.hitCount <= 3) {
            this.setFillColor(this.colors[this.hitCount]);
        }

        return false;
    }
}
