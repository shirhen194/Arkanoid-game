package gameelements;

import java.util.ArrayList;

/**
 * @author Shir Hanono
 * @version 1.0 11/04/2021
 */

/**
 * This class generates a sprites.SpriteCollection object.
 * <p>
 */
public class SpriteCollection {
    //holds the sprite collection
    private ArrayList<Sprite> spritesColl;

    /**
     * The sprites.SpriteCollection constructor
     * <p>
     * This method creates the game environment sprites.Sprite ArrayList.
     * <p>
     */
    public SpriteCollection() {
        this.spritesColl = new ArrayList<Sprite>();
    }

    /**
     * <p>
     * This method adds the new given sprites to the sprite collection.
     * <p>
     *
     * @param s the new sprite to add
     */
    public void addSprite(Sprite s) {
        this.spritesColl.add(s);
    }

    /**
     * This method removes given sprite from the sprites collection.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.spritesColl.remove(s);
    }

    /**
     * <p>
     * This method calls timePassed() on all the sprites in the collection.
     * <p>
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.spritesColl.size(); i++) {
            spritesColl.get(i).timePassed();
        }
    }

    /**
     * <p>
     * This method calls drawOn(d) on all the sprites in the collection.
     * <p>
     *
     * @param d = the DrawSurface object.
     */
    public void drawAllOn(biuoop.DrawSurface d) {
        for (int i = 0; i < this.spritesColl.size(); i++) {
            spritesColl.get(i).drawOn(d);
        }
    }
}