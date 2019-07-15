import java.util.*;

public class Deck {

    private List<Card> list;
    private int score;

    public Deck() {
        this.list = new ArrayList<Card>();
        this.score = calculateScore();
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
    private int calculateScore() {
        int sum = 0;
        for (Card card : list) {
            sum += card.getNumber();
        }
        return sum;
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
        }
        return stringBuilder.toString();
    }
}
