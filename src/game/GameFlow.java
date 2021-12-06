package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameelements.Counter;
import level.LevelInformation;

import java.util.List;


/**
 * @author Shir Hanono
 * @version 1.0 22/04/2021
 */

/**
 * This class generates a game.GameFlow object with it's animationRunner,
 * keyboardSensor, gui and score object.
 * <p>
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final biuoop.KeyboardSensor keyboardSensor;
    private final Counter score;
    private GUI gui;

    /**
     * the GameFlow constructor. add all the properties values.
     *
     * @param g  gui object
     * @param ar AnimationRunner object
     * @param ks KeyboardSensor object
     */
    public GameFlow(GUI g, AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
        this.gui = g;
    }

    /**
     * This method runs the game flow levels by the levels given as a list argument.
     * @param levels the game levels information
     */
    public void runLevels(List<LevelInformation> levels) {
        //get properties to run the animation
        Animation endScreen;
        String spaceKey = KeyboardSensor.SPACE_KEY;
        // loop on all the levels and run them with game level
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor,
                    this.animationRunner,
                    this.score,
                    this.gui);

            level.initialize();

            //level has more blocks and balls
//            while (level.getBlockCounter() > 0 && level.getBallCounter() > 0) {
                level.run();
//            }

            //no more balls
            if (level.getBallCounter() == 0) {
                endScreen = new EndScreen(this.keyboardSensor, false, this.score.getValue());
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, spaceKey, endScreen));
                this.gui.close();
                return;
            }
            this.score.increase(100);
        }

        endScreen = new EndScreen(this.keyboardSensor, true, this.score.getValue());
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, spaceKey, endScreen));
        this.gui.close();
    }
}