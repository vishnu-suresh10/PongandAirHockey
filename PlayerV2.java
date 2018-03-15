/**
 * @(#)PlayerV2.java
 * Creates a Player that has a Name, Points, and a Paddle Object
 * @authors Vishnu Suresh and James Lai
 */
import java.awt.*;

public class PlayerV2 {
    private String name;
    private Paddle paddle;
    private int points;


    public PlayerV2(String name, Paddle p) {
        this.name = name;
        paddle = p;
        points = 0;
    }

    public boolean checkWin(){
        boolean won = false;

        if(points >= 9){
            updatePoints();
            won = true;
        }

        return won;
    }

    public void resetPoints(){
        setPoints(0);
    }

    public void updatePoints(){
        points++;
    }

    public void drawPaddle(Graphics g) {
        paddle.draw(g);
    }


    public void moveUp(boolean up) {
            paddle.moveUp(up);
    }

    public void moveRight(boolean right) {
            paddle.moveRight(right);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
