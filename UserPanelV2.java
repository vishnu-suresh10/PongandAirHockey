/**
 * @(#)UserPanel.java
 * A mixture of Air Hockey and Pong
 * The paddle objects move methods are called every 50 ms in response to a timer.
 * @authors Mrs. Dibenedetto, James Lai, and Vishnu Suresh
 * @version 1.00 2016/2/5
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


//UserPanel inherits from JPanel and uses the KeyListener and ActionListener interfaces


public class UserPanelV2 extends JPanel implements KeyListener, ActionListener, JavaArcade
{

    private PlayerV2 p1;
    private PlayerV2 p2;

    private javax.swing.Timer timer; //controls how often we updated the x, y pos of enemies and how often we repaint
    private javax.swing.Timer enemyTimer; //controls how often our points value change

    private BallV2 ball;

    private boolean start = false;
    private int screenWidth;
    private int screenHeight;


    public UserPanelV2 (int width, int height) {
        screenWidth = width;
        screenHeight = height;
        Color backColor=Color.black;


        //Status check every 50 milliseconds
        timer = new javax.swing.Timer(50, this);

        //Timer invoked every 3 seconds, just a demo of using 2 timers - currently points value decrease
        enemyTimer = new javax.swing.Timer(3000, new EnemyAnimationListener());

        addKeyListener(this);//used for key controls

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setBackground(backColor);

        setPreferredSize(new Dimension(width, height));


    }


    public void actionPerformed (ActionEvent e){ //invoked when timer expires every 5ms
        checkStats();
        repaint(); //ensures PaintComponent is called
    }


    //Because we implemented KeyListener interface, we must define these key methods
    public void keyTyped(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }

    public void keyPressed(KeyEvent e){

        switch(e.getKeyCode())
        {
            case KeyEvent.VK_ENTER://actions performed if enter key is pressed
                timer.start();
                enemyTimer.start();
                start = true;
                startGame();
                break;

            case KeyEvent.VK_UP://actions performed if up key is pressed
                p2.moveUp(true);


                break;
            case KeyEvent.VK_DOWN://actions performed if down key is pressed
                p2.moveUp(false);


                break;
            case KeyEvent.VK_LEFT://actions performed if left key is pressed
                p2.moveRight(false);


                break;
            case KeyEvent.VK_RIGHT://actions performed if right key is pressed
                p2.moveRight(true);


                break;

            case KeyEvent.VK_W://actions performed if w key is pressed
                p1.moveUp(true);


                break;
            case KeyEvent.VK_S://actions performed if s key is pressed
                p1.moveUp(false);


                break;
            case KeyEvent.VK_A://actions performed if a key is pressed
                p1.moveRight(false);


                break;
            case KeyEvent.VK_D://actions performed if d key is pressed
                p1.moveRight(true);


                break;
            case KeyEvent.VK_ESCAPE://actions performed if escape key is pressed
                System.exit(0);

                break;
            default:

        }


    }

    //draws everything
    public void paintComponent(Graphics g){

        super.paintComponent(g); //a call to JPanel's paintComponent

        g.setColor(new Color(0,255,0));
        g.drawLine(300, 0, 300, 4000);



        if(!start){//Shows instructions in the beginning
            g.drawString("Welcome to Pong + Air Hockey", (getWidth() /2) - 200, getHeight()/2 + 20);
            g.drawString("WASD to Move Left Paddle", (getWidth() /2) - 200, getHeight()/2 + 40);
            g.drawString("Arrow Keys to Move Right Paddle", (getWidth() /2) - 200, getHeight()/2 + 60);
            g.drawString("A whiff factor is involved, so the paddle has to be there before the ball comes", (getWidth() /2) - 200, getHeight()/2 + 80);
            g.drawString("Press Enter to Start and Escape to Exit", (getWidth() /2) - 200, getHeight()/2 + 100);
        }
        else{
            //Draw All Items on Screen

            ball.draw(g);
            p1.drawPaddle(g);
            p2.drawPaddle(g);

            g.drawString(p1.getName() + " Points: " + p1.getPoints(), 20, getHeight()-70);
            g.drawString("" ,20, getHeight()-20);
            g.drawString(p2.getName() + " Points: " + p2.getPoints(), 20, getHeight()-30);
        }





    }




    //Invoked by EnemyTimer to show how you handle two timers. Need to create this private class
    //to implement another ActionListener
    private class EnemyAnimationListener implements ActionListener{

        //Because we are implementing ActionListener, we must define actionPerformed
        public void actionPerformed (ActionEvent e){
            //Every 3 seconds, lose a point - motivate to win faster


        }
    }

    private void checkStats() { //called every 5ms,
        // Checks for goal, or if ball hits paddle
        if(p1.checkWin()){
            JOptionPane.showMessageDialog(null, p1.getName() + " Wins","Winner Alert", JOptionPane.ERROR_MESSAGE);
            p1.resetPoints();
            p2.resetPoints();
        }
        else if(p2.checkWin()){
            JOptionPane.showMessageDialog(null, p2.getName() + " Wins","Winner Alert", JOptionPane.ERROR_MESSAGE);
            p1.resetPoints();
            p2.resetPoints();
        }


        if(ball.isLeftHandSideGoal()){
            p2.updatePoints();
            timer.start();
            enemyTimer.start();
            start = true;
            ball.moveBallToCenter();
        }
        if(ball.isRightHandSideGoal()){
            p1.updatePoints();
            timer.start();
            enemyTimer.start();
            start = true;
            ball.moveBallToCenter();
        }

        if (ball.isMovingLeft()) {
            if (p1.getPaddle().getStartX() + Paddle.getWIDTH() <= ball.getxPosition() + 2 && p1.getPaddle().getStartX() + Paddle.getWIDTH() >= ball.getxPosition() - 2) {
                if (p1.getPaddle().getStartY() <= ball.getyPosition() && p1.getPaddle().getStartY() + Paddle.getHEIGHT() >= ball.getyPosition()) {
                    ball.changeDirection();
                }
            }
        }else {
            if ( BallV2.getBallDiameter() + ball.getxPosition() - 4 <= p2.getPaddle().getStartX() && BallV2.getBallDiameter() + ball.getxPosition() + 4 >= p2.getPaddle().getStartX() ) {
                if (p2.getPaddle().getStartY() <= ball.getyPosition() && p2.getPaddle().getStartY() + Paddle.getHEIGHT() >= ball.getyPosition()) {
                    ball.changeDirection();
                }
            }

        }

        ball.move();
    }

    // Overriding methods for JavaArcade Interface
    @Override
    public boolean running() {
        return start;
    }

    public void startGame(){
        ball = new BallV2(screenHeight, screenWidth); //Parameters are xPos, yPos, width, height. Speed is defaulted to Circle speed of 3
        Paddle paddle1 = new Paddle(screenWidth/4, screenHeight*3/5, Color.green, screenWidth, screenHeight);
        Paddle paddle2 = new Paddle(screenWidth * 3/4, screenHeight*3/5, Color.yellow, screenWidth, screenHeight);
        p1 = new PlayerV2("Vishnu", paddle1);
        p2 = new PlayerV2("James", paddle2);

        ball.move();
        ball.repaint();

    }

    @Override
    public String getGameName() {
        return "Pong";
    }

    @Override
    public void pauseGame() {
        start = false;
    }

    @Override
    public String getInstructions() {
        String retString = "Welcome to Pong + Air Hockey" + "\n";
        retString += "WASD to Move Left Paddle"+ "\n";
        retString += "Arrow Keys to Move Right Paddle"+ "\n";
        retString += "A whiff factor is involved, so the paddle has to be there before the ball comes" + "\n";
        retString += "Press Enter to Start and Escape to Exit" + "\n";

        return retString;
    }

    @Override
    public String getCredits() {
        String retString = "James Lai and Vishnu Suresh";

        return retString;
    }

    @Override
    public String getHighScore() {
        return "High Score not Possible";
    }

    @Override
    public void stopGame() {
        System.exit(0);
    }

    @Override
    public String getPoints() {
        return p1.getPoints() + "\n" + "" + p2.getPoints();
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }



}
