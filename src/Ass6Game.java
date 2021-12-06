
/**
 * @author Shir Hanono
 * @version 1.0 22/04/2021
 */

import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.GameFlow;
import level.LevelFour;
import level.LevelInformation;
import level.LevelOne;
import level.LevelThree;
import level.LevelTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * This class generates a Ass3Game object with it's sprites,sleeper and game env object.
 * <p>
 */
public class Ass6Game {
    /**
     * this main method calls all the method to initialize and run the game.
     *
     * @param args holding command line args
     */
    public static void main(String[] args) {
        int i;
        List<LevelInformation> levels = new ArrayList<>();
        int counter = 0;
        for (String s : args) {
            try {
                i = Integer.parseInt(s);
            } catch (NumberFormatException nfe) {
                continue;
            }
            if (i == 1) {
                counter++;
                levels.add(new LevelOne());
            } else if (i == 2) {
                counter++;
                levels.add(new LevelTwo());
            } else if (i == 3) {
                counter++;
                levels.add(new LevelThree());
            } else if (i == 4) {
                counter++;
                levels.add(new LevelFour());
            }
        }
        if (counter == 0) {
            levels.add(new LevelOne());
            levels.add(new LevelTwo());
            levels.add(new LevelThree());
            levels.add(new LevelFour());
        }
        GUI gui = new GUI("game", 800, 600);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Sleeper sleeper = new Sleeper();
        AnimationRunner animationRunner = new AnimationRunner(gui, 60, sleeper);
        GameFlow gameFlow = new GameFlow(gui, animationRunner, keyboard);
        gameFlow.runLevels(levels);
    }
}