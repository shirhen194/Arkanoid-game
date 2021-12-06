package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Shir Hanono
 * @version 1.0 22/04/2021
 */

/**
 * This class generates an AnimationRunner object with it's gui,framesPerSecond and sleeper object.
 * <p>
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * The constructor of the Animation runner class. properties:
     *
     * @param g   the gui object
     * @param fPS frames per second
     * @param s   the sleeper object
     */
    public AnimationRunner(GUI g, int fPS, Sleeper s) {
        this.gui = g;
        this.framesPerSecond = fPS;
        this.sleeper = s;
    }

    /**
     * This method runs the animation.
     *
     * @param animation the animation object
     */
    public void run(Animation animation) {
        //define the game timing animation properies
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            animation.doOneFrame(d);

            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Setter for framesPerSecond.
     *
     * @param fPS the fps to set
     */
    public void setFramesPerSecond(int fPS) {
        this.framesPerSecond = fPS;
    }
}