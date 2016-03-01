package Breakout;


import java.awt.*;
import java.awt.event.KeyEvent;

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
        this.gameDimension = new Dimension((int) screen.getWidth() / 2, (int) screen.getHeight() - 100);
        this.resize((int) this.gameDimension.getWidth(), (int) this.gameDimension.getHeight());
        this.ball = new Ball();
        this.paddle = Paddle.makePaddle(this.gameDimension);
        this.board = new Board(this, this.gameDimension, this.paddle, this.ball);
        this.addKeyListeners();
    }

    public void run() {
        while (true) {
            GObject hitElement = getHitElement(this.ball);

            handleBricks(hitElement);

            if (hitElement instanceof Paddle) {
                ball.invertDirection(Ball.Y_DIRECTION);
            }


            if (ball.getY() < 0) {
                ball.invertDirection(Ball.Y_DIRECTION);
            }

            if (ball.getX() < 0 || ball.getX() > this.gameDimension.getWidth()) {
                ball.invertDirection(Ball.X_DIRECTION);
            }

            if (ball.getY() < 0) {
                ball.setyDirectoon(10);
            }


            if (ball.getY() > this.gameDimension.getHeight()) {
                /**
                 * todo: lose the game
                 */
                ball.setyDirectoon(-10);
            }

            ball.move();
            paddle.move();
            board.draw();

            this.pause(loopDelay);
        }
    }

    private void handleBricks(GObject hitElement) {
        if (hitElement instanceof BrickInterface) {
            if (!((BrickInterface) hitElement).isActive()) {
                return;
            }
            if (!((BrickInterface) hitElement).isHittable()) {
                return;
            }

            ((BrickInterface) hitElement).destroy();
            ball.invertDirection(Ball.Y_DIRECTION);
        }
    }

    private GObject getHitElement(Ball ball) {
        /**
         * fixme: The collision between Paddle and ball is not properly detected when hit from above
         * todo: Treat ball as ball and not as a rectangle. Nice to have.
         */

        double top = ball.getX();
        double bottom = top + 2 * ball.getRadius();
        double left = ball.getY();
        double right = ball.getY() + 2 * ball.getRadius();

        double x = ball.getX();
        double y = ball.getY();
        double r = 2 * ball.getRadius();

        GObject hitElement = getElementAt(x, y);

        if (null == hitElement) {
            this.getElementAt(top, left);
        }

        if (null == hitElement) {
            this.getElementAt(top, right);
        }

        if (null == hitElement) {
            this.getElementAt(bottom, right);
        }

        if (null == hitElement) {
            this.getElementAt(bottom, left);
        }

        return hitElement;
    }
}
