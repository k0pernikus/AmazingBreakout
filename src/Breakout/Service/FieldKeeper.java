package Breakout.Service;

import Breakout.Components.Ball;
import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * Created by philipp on 03.03.16.
 */
public class FieldKeeper {

    private GraphicsProgram engine;
    private Dimension gameDimension;

    public FieldKeeper(GraphicsProgram engine, Dimension dimension) {
        this.engine = engine;
        this.gameDimension = dimension;
    }


    public void guardBall(Ball ball) {
        if (ball.getY() < 0) {
            ball.invertDirection(Ball.Y_DIRECTION);
        }

        if (ball.getX() < 0 || ball.getX() > gameDimension.getWidth()) {
            ball.invertDirection(Ball.X_DIRECTION);
        }

        if (ball.getY() < 0) {
            ball.setyDirection(10);
        }

        if (ball.getY() + ball.getWidth() > gameDimension.getHeight()) {
            /**
             * todo: lose the game
             */
            ball.setyDirection(-10);
        }
    }
}
