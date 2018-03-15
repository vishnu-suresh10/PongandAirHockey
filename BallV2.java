/**
 * @(#)PlayerV2.java
 * Creates a Ball that has moves and detects collisions
 * Also executes Boolean Functions
 * @authors Vishnu Suresh and James Lai
 */
import javax.swing.*;
import java.awt.*;

public class BallV2 extends JPanel{
    private int width;
    private int height;
    private int xPosition;
    private int yPosition;
    private int xVelocity;
    private int yVelocity;
    private final static int BALL_DIAMETER = 20;
    private final static int SPEED = 5;

    public BallV2(int w, int h) {
        width = w;
        height = h;
        xPosition = width/2;
        yPosition = height/2;
        xVelocity = SPEED;
        yVelocity = SPEED;
    }

    public void draw(Graphics g){
        g.fillOval(xPosition, yPosition, BALL_DIAMETER, BALL_DIAMETER);
    }

    public void move()
    {
        //change direction if it touched top and bottom

        //touchingTop
        if(yPosition <= 0)
            yVelocity = -yVelocity;
        //touchingBottom
        if(yPosition >= width-getBallDiameter())
            yVelocity = -yVelocity;

        xPosition += xVelocity;
        yPosition += yVelocity;


    }

    public void moveBallToCenter(){

        setxPosition(width/2);
        setyPosition(height/2);
    }

    public boolean isLeftHandSideGoal(){
        boolean isLeftHandSideGoal = false;

        if(xPosition <= 10 ){
            isLeftHandSideGoal = true;
        }

        return isLeftHandSideGoal;
    }

    public boolean isRightHandSideGoal(){
        boolean isRightHandSideGoal = false;

        if(xPosition >= 600 ){
            isRightHandSideGoal = true;
        }

        return isRightHandSideGoal;
    }

    public boolean isMovingLeft(){
        boolean isMovingLeft = false;

        if(xVelocity < 0)
            isMovingLeft = true;

        return isMovingLeft;
    }

    public boolean isMovingRight(){
        boolean isMovingRight = false;

        if(xVelocity > 0)
            isMovingRight = true;

        return isMovingRight;
    }

    // Moves the Ball Object in a Random Direction once the Paddle Object is hit
    public void changeDirection(){
        double randomNumber = Math.random();
        setxVelocity(-xVelocity);

        if(randomNumber <= 0.33){
            setyVelocity(-SPEED);
        }
        else if(randomNumber > 0.33 && randomNumber <= 0.66){
            setyVelocity(0);
        }
        else{
            setyVelocity(SPEED);
        }
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public static int getBallDiameter() {
        return BALL_DIAMETER;
    }
}
