/**
 * @(#)JavaArcade.java
 * Arcade Interface, with basic arcade game functions
 * @authors Vishnu Suresh and James Lai
 */
public interface JavaArcade {
    public boolean running();
    public void startGame();
    public String getGameName();
    public void pauseGame();
    public String getInstructions();
    public String getCredits();
    public String getHighScore();
    public void stopGame();
    public String getPoints();
}