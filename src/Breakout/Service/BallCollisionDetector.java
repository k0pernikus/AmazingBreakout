package Breakout.Service;

import Breakout.Components.Ball;
import Breakout.Components.Rectangle;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.graphics.GRectangle;
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


    public Collision getCollisionSide(Rectangle ball, Rectangle brick) {

        double left = 0;
        double right = 0;
        double top = 0;
        double bottom = 0;

        if (!(ball.getTop() <= brick.getTop() || ball.getBottom() >= brick.getBottom())) {
            if (brick.getLeft() >= ball.getLeft() ) {
                left = 1;
            }

            if (brick.getRight() <= ball.getRight()) {
                right = 1;
            }
        }

        if (!(ball.getLeft() <= brick.getLeft() || ball.getRight() >= brick.getRight())) {
            if (brick.getTop() <= ball.getTop()) {
                top = 1;
            }

            if (brick.getBottom() >= ball.getBottom()) {
                bottom = 1;
            }

        }




        return new Collision(left == 1 || right == 1, top == 1 || bottom == 1);
    }

    public GObject getHitElement(Ball ball) {
        List<GPoint> list = getOuterBoundPoints(ball.getBounds());
        GObject hitElement = null;

        for (GPoint point : list) {
            hitElement = graphicsEngine.getElementAt(point);

            if (hitElement != null) {
                break;
            }
        }

        return hitElement;
    }

    private List<GPoint> getOuterBoundPoints(GRectangle rect) {
        /**
         * fixme: The collision between Paddle and ball is not properly detected when hit from above
         * todo: Treat ball as ball and not as a rectangle. Nice to have.
         */
        double x = rect.getX();
        double xLower = x + rect.getWidth();

        double y = rect.getY();
        double yLower = y + rect.getHeight();

        return new ArrayList<>(Arrays.asList(
                new GPoint(x, y),
                new GPoint(xLower, y),
                new GPoint(x, yLower),
                new GPoint(xLower, yLower)
        ));
    }
}
