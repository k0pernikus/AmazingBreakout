package Breakout.Components.Brick;

import acm.util.RandomGenerator;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by philipp on 29.02.16.
 */
public class BrickFactory {
    private Random random = new RandomGenerator();

    public static double hardBrickProbability = .2;

    public List<BrickInterface> buildBricks(Dimension dimension, int rowsAmount, int blocksPerRowAmount, int offset) {
        double width = dimension.width / blocksPerRowAmount - offset;
        double height = dimension.height * .03;
        List<BrickInterface> bricks = new ArrayList<>();
        for (double x = 0; x <= blocksPerRowAmount; x++) {
            for (double y = 0; y <= rowsAmount; y++) {
                double startX = (x * width) + x * offset;
                double startY = (y * height) + y * offset;
                bricks.add(createBrick(startX, startY, width, height));
            }
        }

        return bricks;
    }

    private BrickInterface createBrick(double startX, double startY, double width, double height) {
        float chance = random.nextFloat();

        if (chance < BrickFactory.hardBrickProbability) {
            return new MultiHitBrick(startX, startY, width, height);
        }

        if (chance > BrickFactory.hardBrickProbability && chance < BrickFactory.hardBrickProbability * 2) {
            return new BlinkingBrick(startX, startY, width, height);
        }

        return new Brick(startX, startY, width, height);
    }
}
