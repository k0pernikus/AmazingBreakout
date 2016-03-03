package Breakout.Service;

import Breakout.Components.Brick.BrickInterface;

/**
 * Created by philipp on 03.03.16.
 */
public class BrickDestroyer {
    public boolean destroyBrick(BrickInterface brick) {

        if (!isDestoryable(brick)) return false;

        return brick.destroy();
    }

    private boolean isDestoryable(BrickInterface brick) {
        if (brick.isActive()) {
            return true;
        }
        if (brick.isHittable()) {
            return true;
        }
        return false;
    }
}
