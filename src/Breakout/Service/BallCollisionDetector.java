package Breakout.Service;

import Breakout.Components.Ball;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import java.util.ArrayList;
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

        List<GPoint> list = new ArrayList<>();
        GPoint p1 = new GPoint(x, y);
        GPoint p2 = new GPoint(xLower, y);
        GPoint p3 = new GPoint(x, yLower);
        GPoint p4 = new GPoint(xLower, yLower);

        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);

        return list;
    }
}
