package gameelements;
import listeners.HitListener;

/**
 * @author Shir Hanono
 * @version 1.0 11/04/2021
 */

/**
 * This interface generates a gameelements.HitNotifier, that is notifier to a hit in the block.
 * <p>
 */
public interface HitNotifier {
    /**
     * This method adds hl as a listener to hit events.
     *
     * @param hl a listener to hit events.
     */
    void addHitListener(HitListener hl);

    /**
     * This method removes hl from the list of listeners to hit events.
     *
     * @param hl a listener to hit events.
     */
    void removeHitListener(HitListener hl);
}