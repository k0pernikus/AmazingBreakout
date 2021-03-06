package Breakout;

import Breakout.Components.Ball;
import Breakout.Components.Board;
import Breakout.Components.Brick.BrickFactory;
import Breakout.Components.Brick.BrickInterface;
import Breakout.Components.Paddle;
import Breakout.Components.Rectangle;
import Breakout.Service.BallCollisionDetector;
import Breakout.Service.BrickDestroyer;
import Breakout.Service.Collision;
import Breakout.Service.FieldKeeper;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;


public class Breakout extends GraphicsProgram {

    private boolean isPaused;

    private Dimension gameDimension;

    private Board board;

    public Breakout() {
        this.isPaused = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.isPaused = !isPaused;
    }

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
        this.gameDimension = new Dimension((int) screen.getWidth(), (int) screen.getHeight() - 100);
        resize((int) this.gameDimension.getWidth(), (int) this.gameDimension.getHeight());

        this.brickDestroyer = new BrickDestroyer();
        this.fieldKeeper = new FieldKeeper(this, gameDimension);
        this.ballCollisionDetector = new BallCollisionDetector(this);

        this.ball = new Ball(gameDimension.getWidth() * .5, gameDimension.getHeight() * .25, 10);
        addMouseListeners(ball);

        this.paddle = Paddle.makePaddle(this.gameDimension);
        addMouseListeners(paddle);

        this.addMouseListeners(this);

        List<BrickInterface> bricks = new BrickFactory().buildBricks(gameDimension, 2, 8, 0);
        this.board = new Board(this, gameDimension, paddle, ball, bricks);
    }

    public void run() {
        board.draw();

        while (true) {
            pause(loopDelay);
            if (isPaused) {
                continue;
            }

            GObject hitElement = ballCollisionDetector.getHitElement(ball);
            handleHitGObject(hitElement);
            fieldKeeper.guardBall(ball);
            moveAllTheThings();
            board.draw();
        }
    }

    private void moveAllTheThings() {
        ball.move();
        paddle.moveToTarget(Paddle.BASE_SPEED);
    }

    private void handleHitGObject(GObject hitElement) {
        if (hitElement == null) {
            return;
        }

        Rectangle box1 = new Rectangle(ball.getBounds());
        Rectangle box2 = new Rectangle(hitElement.getBounds());

        Collision collision = ballCollisionDetector.getCollisionSide(box1, box2);

        boolean flipX = collision.isHorizontalCollsion();
        boolean flipY = collision.isVerticalCollsion();


        if (flipX) {
            ball.invertDirection(Ball.X_DIRECTION);
        }

        if (flipY) {
            ball.invertDirection(Ball.Y_DIRECTION);
        }

        if (hitElement instanceof BrickInterface) {
            brickDestroyer.destroyBrick((BrickInterface) hitElement);
        }
    }
}
