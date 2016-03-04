package Breakout.Service;

import Breakout.Components.Brick.BrickInterface;
import Breakout.Components.Brick.UndestroyableException;

/**
 * Created by philipp on 03.03.16.
 */
public class BrickDestroyer {
    public boolean destroyBrick(BrickInterface brick) {

        boolean isDestroyed = false;
        try {
            brick.destroy();
            isDestroyed = true;
        } catch (UndestroyableException e) {
        }

        return isDestroyed;
    }
}
