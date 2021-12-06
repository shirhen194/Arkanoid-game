package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Shir Hanono
 * @version 1.0 22/04/2021
 */
/**
 * This method generates the KeyPressStoppableAnimation animation
 * that runs the given animation until the stop key press.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String stopKey;
    private Animation animationToRun;
    private Boolean stopAnimation = false;
    private boolean isAlreadyPressed = true;

    /**
     * the KeyPressStoppableAnimation constructor.
     *
     * @param sensor    the KeyboardSensor
     * @param key       the end key
     * @param animation the animation to run
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.stopKey = key;
        this.animationToRun = animation;
    }

    @Override
    /**
     * This method runs the animation for one frame.
     *
     * @param d the DrawSurface object
     */
    public void doOneFrame(DrawSurface d) {
        this.animationToRun.doOneFrame(d);
        if (this.keyboardSensor.isPressed(this.stopKey)) {
            if (!this.isAlreadyPressed) {
            this.stopAnimation = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    /**
     * this method checks and returns id the game should stop.
     *
     * @return boolean should stop game
     */
    public boolean shouldStop() {
        return this.stopAnimation;
    }
}