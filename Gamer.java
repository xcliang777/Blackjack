public class Gamer {

    private String id;
    private Deck deck;

    public Gamer(String id, Deck deck) {
        this.id = id;
        this.deck = deck;
    }

    /**
     * Hit operation
     * @return if action succeeds
     */
    public boolean hit() {
        if (deck.getScore() > 21) return false;
        deck.addCard(Pool.getRandomCard());
        return true;
    }

    /**
     * Clear the deck after each round
     */
    public void clearDeck() {
        deck.clearDeck();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
