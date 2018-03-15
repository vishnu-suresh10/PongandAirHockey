/**
 * @(#)Pong.java
 * Creates a Ball that has moves and detects collisions
 * Also executes Boolean Functions
 * @authors Mrs. Dibenedetto, James Lai, and Vishnu Suresh
 */
import javax.swing.*;
import java.awt.*;

public class Pong {
    public static JFrame arcade = new JFrame();

    public static void main(String[] args) {
        arcade.setTitle("AP Java Game Room");
        arcade.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new UserPanelV2(600, 450);

        Container pane = arcade.getContentPane();
        pane.setLayout(new GridLayout(1, 1));
        pane.add(panel);

        arcade.pack();
        arcade.setVisible(true);
        panel.requestFocus();


    }
}

