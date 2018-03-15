/**
 * @(#)Paddle.java
 * Draws and moves a Paddle
 * @authors Vishnu Suresh and James Lai
 */
import java.awt.*;

public class Paddle {
    private final static int WIDTH = 15;
    private final static int HEIGHT = 75;
    private final static int MOVE_NUMBER = 30;


    private int startX; // Paddle X Coordinate
    private int startY; // Paddle Y Coordinate
    private Color color;


    private int boardWidth;
    private int boardHeight;

    public Paddle(int startX, int startY, Color color, int boardWidth, int boardHeight) {
        this.startX = startX;
        this.startY = startY;
        this.color = color;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    public void draw(Graphics g){
        g.setColor(getColor());
        g.fillRect(startX, startY,getWIDTH(),getHEIGHT());
    }

    // Moves Paddle Up or Down
    public void moveUp(boolean up) {
        if(up && startY - MOVE_NUMBER >= 0) {
            setStartY(startY - MOVE_NUMBER);
        }else if(up){
            setStartY(0);
        }
        else if(!up && startY + MOVE_NUMBER <= getBoardHeight()) {
            setStartY(startY + MOVE_NUMBER);
        }else{
            setStartY(getBoardHeight()-75);
        }
    }

    // Moves Paddle Right or Left
    public void moveRight(boolean right) {
        if(right && startX + MOVE_NUMBER <= getBoardWidth()) {
            setStartX(startX + MOVE_NUMBER);
        }else if(right){
            setStartX(0);
        }
        else if(!right && startX - MOVE_NUMBER >= 0) {
            setStartX(startX - MOVE_NUMBER);
        }else{
            setStartX(getBoardWidth()-HEIGHT);
        }
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }
}
