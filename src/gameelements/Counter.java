package gameelements;

/**
 * @author Shir Hanono
 * @version 1.0 11/04/2021
 */

/**
 * This class generates a gameelements.Counter object with a count.
 * <p>
 */
public class Counter {
    private int count = 0;

    /**
     * This method adds number to current count.
     *
     * @param number add number
     */
    public void increase(int number) {
        this.count = count + number;
    }

    /**
     * This method subtracts number from current count.
     *
     * @param number subtract number
     */
    public void decrease(int number) {
        this.count = count - number;
    }

    /**
     * get current count.
     *
     * @return current count.
     */
    public int getValue() {
        return this.count;
    }
}