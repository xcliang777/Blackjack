import java.util.*;

public class Deck {

    private List<Card> list;
    private int score;

    public Deck() {
        this.list = new ArrayList<Card>();
    }

    public List<Card> getList() {
        return list;
    }

    public void setList(List<Card> list) {
        this.list = list;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Calculate the score of current deck
     * @return deck score
     */
    private void calculateScore() {
        int count = 0;
        int sum = 0;
        for (Card card : list) {
            if (card.getValue() == -1) count++;
            else sum += card.getValue();
        }
        score = count == 0 ? sum : aceCalculate(count, sum);
    }

    /**
     * Add new card to deck
     * @param card card to be added
     */
    public void addCard(Card card) {
        list.add(card);
        calculateScore();
    }

    /**
     * Remove first card in the deck
     */
    public void removeCard() {
        list.remove(0);
        calculateScore();
    }

    /**
     * Clear whole deck
     */
    public void clearDeck() {
        list.clear();
    }

    /**
     * Print current deck
     * @param ifHide if need to hide first card
     * @return deck result
     */
    public String toString(boolean ifHide) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (ifHide && i == 0) stringBuilder.append("XXXX: XX");
            else stringBuilder.append(list.get(i).toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Check if there is a blackjack or what type of blackjack it is
     * @return checking result;
     * -1: no blackjack
     * 1; natural blackjack
     * 2: non-natural blackjack
     */
    public int checkBlackJack() {
        if (getScore() != 21) return -1;
        return list.size() == 2 ? 1 : 2;
    }

    /**
     *
     * @param num number of ace
     * @param residue rest sum
     * @return final score
     */
    private int aceCalculate(int num, int residue) {
        int[] conditions = new int[num+1];
        for (int i = 0; i <= num; i++) {
            conditions[i] = 11 * i + (num-i) + residue;
        }
        int result = conditions[0];
        for (int i : conditions) {
            if (i <= 21 && i > result) result = i;
        }
        return result;
    }
}
