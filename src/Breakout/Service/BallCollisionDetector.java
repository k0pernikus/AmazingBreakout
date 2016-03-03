package Breakout.Service;

import Breakout.Components.Ball;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by philipp on 03.03.16.
 */
public class BallCollisionDetector {
    private GraphicsProgram graphicsEngine;

    public BallCollisionDetector(GraphicsProgram grapic) {
        this.graphicsEngine = grapic;
    }

    public GObject getHitElement(Ball ball) {
        List<GPoint> list = getOuterBoundPoints(ball);

        GObject hitElement = null;

        for (GPoint point : list) {
            hitElement = this.graphicsEngine.getElementAt(point);

            if (hitElement != null) {
                break;
            }
        }

        return hitElement;
    }

    private List<GPoint> getOuterBoundPoints(Ball ball) {
        /**
         * fixme: The collision between Paddle and ball is not properly detected when hit from above
         * todo: Treat ball as ball and not as a rectangle. Nice to have.
         */
        double x = ball.getX();
        double xLower = x + ball.getWidth();

        double y = ball.getY();
        double yLower = y + ball.getHeight();

        return new ArrayList<>(Arrays.asList(
                new GPoint(x, y),
                new GPoint(xLower, y),
                new GPoint(x, yLower),
                new GPoint(xLower, yLower)
        ));
    }
}
