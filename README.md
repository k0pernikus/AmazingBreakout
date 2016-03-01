# README #


### What is this Project for? ###

In your project, you will implement Breakout, a video game classic. The game consists of a single screen with three elements: a few layers of bricks at the top, a paddle that can be moved horizontally by the player at the bottom, and a ball that keeps bouncing between them.

Whenever the ball hits a brick, the brick disappears. The objective of the game is for the player to cause the ball to hit all bricks. To do so, the player must prevent the ball from hitting the bottom border of the screen by placing the paddle such that the ball hits that instead. If the ball does hit the bottom border of the screen, it disappears and the current round is over. Usually, the player is given three balls at the start of the game and can continue as long as she still has balls left.

The paddle is usually controlled by moving the mouse.

### The Physics ###

The most important problem you will have to solve is how to detect collisions between the ball and the following things:

    The paddle
    Bricks
    All four sides of the window

If the ball hits the lower boundary of the window, the current round ends. In all other cases, the ball needs to bounce off and its direction of movement needs to change accordingly. To do so, you will have to check whether the ball currently collides with anything in the first place.

 One method that may help you is getElementAt(double x, double y), which will return the GObject at the given coordinates. A simple idea for implementing collision detection is to check whether there is a GObject at either of the four corners of the ball's bounding box (the smallest rectangle that contains the ball). Of course, this will not be exact since the ball does not actually extend to the corners; it is, after all, a circle, not a rectangle. But the results are good enough for our purposes. Still, if you want to implement a more exact algorithm, feel free to do so.

### Getting Started ###

If you're not sure how to start with this assignment yet, we suggest the following:

    Start by implementing the ball and its movement. Make sure that it correctly bounces off from the window's border.
    Implement the paddle. Make sure that the user can move it properly, and that the ball correctly bounces off the paddle.
    Implement bricks. Make sure that the ball can hit them and that they disappear.
    Think about cool features to improve your game. See the last section below for a few ideas we had.


### Implementation Notes ###

The easiest way for you to solve this method is probably to keep using the ACM library for all the graphics and for the program structure. Using the library is completely fine. However, there are other, more sophisticated technologies that some of you may already be familiar with. Feel free to run off and do cool stuff with them. The only hard requirement is that your game is implemented in Java.

Whatever you do, use this opportunity to practice everything we've tried to teach you:

    Splitting your code into sensible classes and methods.
    Writing comments!

### Submitting Your Solution ###

We require that your solution is uploaded to the iLearn system at the end of the project. For this reason, we have enabled file uploads for this assignment. To turn your project into an archive file that you can upload, perform the following steps in Eclipse:

    Right-click your project in the Package Explorer and click Export.
    From the Java category, select JAR file and clickt Next.
    Make sure your project's src folder is exported. Choose not to export generated class files and resources, but only Java source files and resources.
    Choose where to save the file and click Finish.

### Going Beyond the Call of Duty ###

Here are a few ideas we've had for making the basic game even cooler. Feel free to think about and implement your own ideas. We suggest you only start with these once you have the basic game running, however.

    When the ball hits the paddle, the angle the ball bounces off in could depend on where the ball hits the paddle. For example, if the ball hits the center of the paddle, it could bounce off perfectly vertically. As the impact point gets closer to the paddle's left border, the angle could get steeper. This allows the player to aim the ball at specific bricks.
    Everybody likes highscores! Each brick the player destroys could earn him a number of points. If he scores enough points, your game could ask him to enter a name for the top 10 list. Since you already know how to load and save files, the list could be saved to be loaded again when the game is restarted.
    Many Breakout implementations make the game more interesting by having some bricks drop specials. The specials fall down with a certain speed and can be collected by the player with his paddle. Get creative when defining what the specials can do: enlarge the paddle, mount weapons on the paddle to shoot bricks with, reverse the direction the paddle moves in, ...
    There could be different types of bricks. Some could require the player to hit them a defined number of times to destroy them, some could be indestructible.
    Instead of initializing each game with a full set of bricks, your game could allow the player to progress through a number of levels that have the bricks laid out in a certain way. You will have to think about how to define each level. One way would be to do so using configuration files.
    You could limit the time the player has to complete a level. Once the limit is reached, the game could speed up, or the bricks could slowly start moving down.