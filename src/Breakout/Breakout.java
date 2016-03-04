package Breakout;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import Breakout.Service.BallCollisionDetector;
import Breakout.Service.BrickDestroyer;
import Breakout.Service.FieldKeeper;
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

    private BrickDestroyer brickDestroyer;

    private FieldKeeper fieldKeeper;

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
        addKeyListeners();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.gameDimension = new Dimension((int) screen.getWidth() / 2, (int) screen.getHeight() - 100);
        resize((int) this.gameDimension.getWidth(), (int) this.gameDimension.getHeight());

        this.brickDestroyer = new BrickDestroyer();
        this.fieldKeeper = new FieldKeeper(this, gameDimension);
        this.ballCollisionDetector = new BallCollisionDetector(this);

        this.ball = new Ball(this.gameDimension.getWidth() * .5, this.gameDimension.getHeight() * .25, 10);
        this.paddle = Paddle.makePaddle(this.gameDimension);
        this.board = new Board(this, this.gameDimension, this.paddle, this.ball);
    }

    public void run() {
        while (true) {
            GObject hitElement = this.ballCollisionDetector.getHitElement(ball);
            handleHitGObject(hitElement);
            fieldKeeper.guardBall(ball);
            moveAllTheThings();
            board.draw();

            pause(this.loopDelay);
        }
    }

    private void moveAllTheThings() {
        ball.move();
        paddle.move();
    }

    private void handleHitGObject(GObject hitElement) {
        if (hitElement instanceof Paddle) {
            ball.invertDirection(Ball.Y_DIRECTION);
        }

        if (hitElement instanceof BrickInterface) {
            if (brickDestroyer.destroyBrick((BrickInterface) hitElement)) {
                ball.invertDirection(Ball.Y_DIRECTION);
            }
        }
    }
}
