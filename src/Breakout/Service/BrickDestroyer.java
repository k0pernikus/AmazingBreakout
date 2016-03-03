package Breakout.Service;

import Breakout.Components.Brick.BrickInterface;

/**
 * Created by philipp on 03.03.16.
 */
public class BrickDestroyer {
    public boolean destroyBrick(BrickInterface brick) {

        if (!brick.isActive()) {
            return false;
        }
        if (!brick.isHittable()) {
            return false;
        }

        brick.destroy();

        return true;
    }
}
