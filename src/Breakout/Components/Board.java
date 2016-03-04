package Breakout.Components;

import Breakout.Components.Brick.Brick;
import Breakout.Components.Brick.BrickFactory;
import Breakout.Components.Brick.BrickInterface;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import java.awt.Dimension;
import java.util.*;
import java.util.List;

public class Board {
    private Ball ball;

    private List<BrickInterface> bricks;

    private GraphicsProgram graphicEngine;

    private Paddle paddle;

    public Board(GraphicsProgram graphicEngine, Dimension gameDimension, Paddle paddle, Ball ball, List<BrickInterface> bricks) {
        this.graphicEngine = graphicEngine;
        this.setBall(ball);
        this.setPaddle(paddle);
        this.setBricks(bricks);
    }

    public void draw() {
        graphicEngine.remove(getPaddle());
        graphicEngine.add(getPaddle());

        graphicEngine.remove(getBall());
        graphicEngine.add(getBall());

        for (BrickInterface brick : getBricks()) {
            graphicEngine.remove((GObject) brick);
            if (!brick.isDestroyed()) {
                graphicEngine.add((GObject) brick);
            }
        }
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public List<BrickInterface> getBricks() {
        return bricks;
    }

    public void setBricks(List<BrickInterface> bricks) {
        this.bricks = bricks;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }
}
