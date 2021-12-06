package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Shir Hanono
 * @version 1.0 22/04/2021
 */
/**
 * This method generates the EndScreen screen with KeyboardSensor and stop property.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stopAnimation;
    private boolean isWinner;
    private int score;
    private final int gameWidth = 800;
    private final int gameHeight = 600;

    /**
     * The EndScreen constructor.
     *
     * @param k      the KeyboardSensor object
     * @param didWin did win the game
     * @param s      the game score
     */
    public EndScreen(KeyboardSensor k, Boolean didWin, int s) {
        this.keyboard = k;
        this.stopAnimation = false;
        this.isWinner = didWin;
        this.score = s;
    }

    /**
     * This method runs the animation for one frame.
     *
     * @param d the DrawSurface object
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 20, this.gameWidth, this.gameHeight);
        String str;
        if (this.isWinner) {
            d.setColor(Color.GREEN);
            str = "You Win! Your score is " + score;
        } else {
            d.setColor(Color.RED);
            str = "Game Over. Your score is " + score;
        }
        d.drawText(210, d.getHeight() / 2, str, 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stopAnimation = true;
        }
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
