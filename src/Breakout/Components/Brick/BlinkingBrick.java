package Breakout.Components.Brick;

import java.awt.*;

/**
 * Created by philipp on 01.03.16.
 */
public class BlinkingBrick extends Brick {
    private long lastChangedAt;

    private static int timeToBlinkInMs = 1000;

    public BlinkingBrick(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.lastChangedAt = System.currentTimeMillis();
        this.setFillColor(Color.YELLOW);
        this.setFilled(true);
    }

    public void blink() {
        this.setVisible(!this.isVisible());
    }
}
