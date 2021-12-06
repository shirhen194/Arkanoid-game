package gameelements;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Shir Hanono
 * @version 1.0 11/04/2021
 */

/**
 * This class generates a sprites.ScoreIndicator object with a score sprites.Counter.
 * <p>
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * The sprites.ScoreIndicator constructor. adds the given score Count,
     * as the score property.
     *
     * @param scoreCount the given score Count
     */
    public ScoreIndicator(Counter scoreCount) {
        this.score = scoreCount;
    }

    /**
     * This method draws the score on the screen.
     *
     * @param d the draw surface of the game
     */
    @Override
    public void drawOn(DrawSurface d) {
        String scoreStr = "Score: " + this.score.getValue();
        d.setColor(Color.black);
        d.drawText(370, 17, scoreStr, 17);
    }

    /**
     * This method should change on time pass.
     */
    @Override
    public void timePassed() {

    }
}
