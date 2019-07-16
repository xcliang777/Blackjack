import java.util.Arrays;

public class Pool {

    private static int[] pool;
    private static final String[] SUITS = {"Spade", "Diamond", "Club", "Heart"};
    private static final String[] NUMBER = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    public Pool() {
        pool = new int[52];
        Arrays.fill(pool, 1);
    }


    /**
     * Get random card from game pool
     * @return Card with corresponding number and suit
     */
    public static Card getRandomCard() {
        int cardNo = -1;
        while (cardNo < 0 || pool[cardNo] != 1) {
            cardNo = (int)(Math.random() * 52);
        }
        pool[cardNo] = 0;
        return int2Card(cardNo);
    }

    /**
     * Map number in the game pool to corresponding card
     * 1-13: spade
     * 14-26: diamond
     * 27-39: club
     * 40-52: heart
     * @param i number in total pool
     * @return Card with corresponding number and suit
     */
    private static Card int2Card (int i) {
        return new Card(SUITS[i/13], NUMBER[i%13], i%13+1);
    }


    /**
     * Clear pool before each new game round
     */
    public static void clearPool() {
        Arrays.fill(pool, 1);
    }

}
