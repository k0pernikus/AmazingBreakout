package Breakout.Components.Brick;

import java.util.Random;

/**
 * Created by philipp on 29.02.16.
 */
public class BrickFactory {
    private Random random = new Random();

    public static double hardBrickProbability = .2;

    public Brick createBrick(double startX, double startY, double width, double height) {
        float chance = this.random.nextFloat();

        if (chance < BrickFactory.hardBrickProbability) {
            return new MultiHitBrick(startX, startY, width, height);
        }

        if (chance > BrickFactory.hardBrickProbability && chance < BrickFactory.hardBrickProbability * 2) {
            return new BlinkingBrick(startX, startY, width, height);
        }

        return new Brick(startX, startY, width, height);
    }
}
