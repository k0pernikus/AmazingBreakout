package Breakout;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.List;

import Breakout.Components.Brick.BrickFactory;
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

    public double BASE_SPEED_IN_PIXEL = 10;

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
                paddle.move(Paddle.BASE_SPEED);
                break;
            case KeyEvent.VK_LEFT:
                paddle.move(Paddle.BASE_SPEED);
                break;
        }
    }

    @Override
    public void init() {
        super.init();
        addKeyListeners();
        addMouseListeners();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.gameDimension = new Dimension((int) screen.getWidth() / 2, (int) screen.getHeight() - 100);
        resize((int) this.gameDimension.getWidth(), (int) this.gameDimension.getHeight());

        this.brickDestroyer = new BrickDestroyer();
        this.fieldKeeper = new FieldKeeper(this, gameDimension);
        this.ballCollisionDetector = new BallCollisionDetector(this);

        this.ball = new Ball(this.gameDimension.getWidth() * .5, this.gameDimension.getHeight() * .25, 10);
        this.paddle = Paddle.makePaddle(this.gameDimension);
        List<BrickInterface> bricks = new BrickFactory().buildBricks(gameDimension, 10, 3, 10);
        this.board = new Board(this, this.gameDimension, this.paddle, this.ball, bricks);
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
