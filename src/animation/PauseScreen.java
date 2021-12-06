package animation;

import biuoop.DrawSurface;

/**
 * @author Shir Hanono
 * @version 1.0 22/04/2021
 */
/**
 * This method generates the pause screen with KeyboardSensor and stop property.
 */
public class PauseScreen implements Animation {
    private boolean stopAnimation;

    /**
     * The PauseScreen constructor.
     *
     */
    public PauseScreen() {
        this.stopAnimation = false;
    }

    /**
     * This method runs the animation for one frame.
     *
     * @param d the DrawSurface object
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * this method checks and returns id the game should stop.
     *
     * @return boolean should stop game
     */
    public boolean shouldStop() {
        return this.stopAnimation;
    }
}