package Breakout;

import Breakout.Components.Ball;
import Breakout.Components.Board;
import Breakout.Components.Brick.BrickFactory;
import Breakout.Components.Brick.BrickInterface;
import Breakout.Components.Paddle;
import Breakout.Service.BallCollisionDetector;
import Breakout.Service.BrickDestroyer;
import Breakout.Service.FieldKeeper;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;


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
    }

    @Override
    public void init() {
        super.init();
        addKeyListeners();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.gameDimension = new Dimension((int) screen.getWidth() / 4, (int) screen.getHeight() - 100);
        resize((int) this.gameDimension.getWidth(), (int) this.gameDimension.getHeight());

        this.brickDestroyer = new BrickDestroyer();
        this.fieldKeeper = new FieldKeeper(this, gameDimension);
        this.ballCollisionDetector = new BallCollisionDetector(this);

        this.ball = new Ball(gameDimension.getWidth() * .5, gameDimension.getHeight() * .25, 10);
        this.paddle = Paddle.makePaddle(this.gameDimension);
        addMouseListeners(paddle);

        List<BrickInterface> bricks = new BrickFactory().buildBricks(gameDimension, 2, 10, 10);
        this.board = new Board(this, gameDimension, paddle, ball, bricks);
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
