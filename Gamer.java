public class Gamer {

    private String id;
    private Deck deck;

    public Gamer(String id, Deck deck) {
        this.id = id;
        this.deck = deck;
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
