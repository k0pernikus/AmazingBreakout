package Breakout.Components;

import Breakout.Components.Brick.Brick;
import Breakout.Components.Brick.BrickFactory;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by philipp on 27.02.16.
 */
public class Board {
    private Ball ball;

    private ArrayList<Brick> bricks;

    private GraphicsProgram graphicEngine;

    private Paddle paddle;

    public Board(GraphicsProgram graphicEngine, Dimension gameDimension, Paddle paddle, Ball ball) {
        this.graphicEngine = graphicEngine;
        this.setBall(ball);
        this.setPaddle(paddle);

        int rowsAmount = 3;
        int blocksPerRowAmount = 10;
        double offset = 10;
        double width = gameDimension.width / blocksPerRowAmount - offset;
        double height = gameDimension.height * .03;

        BrickFactory brickFactory = new BrickFactory();

        ArrayList<Brick> bricks = new ArrayList<>();
        for (double x = 0; x <= blocksPerRowAmount; x++) {
            for (double y = 0; y <= rowsAmount; y++) {
                double startX = (x * width) + x * offset;
                double startY = (y * height) + y * offset;
                bricks.add(brickFactory.createBrick(startX, startY, width, height));
            }
        }


        this.setBricks(bricks);
    }

    public void draw() {
        this.graphicEngine.remove(this.getPaddle());
        this.graphicEngine.add(this.getPaddle());

        this.graphicEngine.remove(this.getBall());
        this.graphicEngine.add(this.getBall());

        this.getBall().sendToBack();    

        for (Brick brick : this.getBricks()) {
            this.graphicEngine.remove(brick);
            if (brick.isActive()) {
                this.graphicEngine.add(brick);
            }
        }
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(ArrayList<Brick> bricks) {
        this.bricks = bricks;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }
}
