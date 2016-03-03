package Breakout;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import Breakout.Service.BallCollisionDetector;
import Breakout.Service.BrickDestroyer;
import acm.graphics.*;
import acm.program.*;
import Breakout.Components.Ball;
import Breakout.Components.Brick.BrickInterface;
import Breakout.Components.Board;
import Breakout.Components.Paddle;


public class Breakout extends GraphicsProgram {

    private Dimension gameDimension;

    private Board board;

    private Ball ball;

    private Paddle paddle;

    final double loopDelay = 20;

    private BallCollisionDetector ballCollisionDetector;

    private BrickDestroyer brickDestryper;

    @Override
    public void keyPressed(KeyEvent event) {
        ball.unpause();

        super.keyPressed(event);

        int c = event.getKeyCode();

        switch (c) {
            case KeyEvent.VK_RIGHT:
                this.paddle.setxMovement(10);
                break;
            case KeyEvent.VK_LEFT:
                this.paddle.setxMovement(-10);
                break;
        }
    }


    @Override
    public void keyReleased(KeyEvent event) {
        super.keyReleased(event);
        this.paddle.setxMovement(0);
    }

    @Override
    public void init() {
        super.init();
        this.addKeyListeners();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.brickDestryper = new BrickDestroyer();
        this.gameDimension = new Dimension((int) screen.getWidth() / 2, (int) screen.getHeight() - 100);
        this.resize((int) this.gameDimension.getWidth(), (int) this.gameDimension.getHeight());
        this.ball = new Ball(this.gameDimension.getWidth() * .5, this.gameDimension.getHeight() * .5, 10);
        this.paddle = Paddle.makePaddle(this.gameDimension);
        this.ballCollisionDetector = new BallCollisionDetector(this);
        this.board = new Board(this, this.gameDimension, this.paddle, this.ball);
        this.addKeyListeners();
    }

    public void run() {
        while (true) {
            GObject hitElement = this.ballCollisionDetector.getHitElement(this.ball);
            this.handleBricks(hitElement);

            if (hitElement instanceof Paddle) {
                ball.invertDirection(Ball.Y_DIRECTION);
            }

            this.handleGameFieldCorners();

            ball.move();
            paddle.move();
            board.draw();

            this.pause(loopDelay);
        }
    }

    private void handleGameFieldCorners() {
        if (ball.getY() < 0) {
            ball.invertDirection(Ball.Y_DIRECTION);
        }

        if (ball.getX() < 0 || ball.getX() > this.gameDimension.getWidth()) {
            ball.invertDirection(Ball.X_DIRECTION);
        }

        if (ball.getY() < 0) {
            ball.setyDirectoon(10);
        }


        if (ball.getY() + ball.getWidth() > this.gameDimension.getHeight()) {
            /**
             * todo: lose the game
             */
            ball.setyDirectoon(-10);
        }
    }

    private void handleBricks(GObject hitElement) {
        if (hitElement instanceof BrickInterface) {
            if (this.brickDestryper.destroyBrick((BrickInterface) hitElement)) {
                ball.invertDirection(Ball.Y_DIRECTION);
            }
        }
    }

}
