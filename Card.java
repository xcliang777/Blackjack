public class Card {

    private String suit;
    private String number;
    private int index;
    private int value;

    public Card(String suit, String number, int index) {
        this.suit = suit;
        this.number = number;
        this.index = index;
        value = getValue();
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String toString() {
        return this.suit + " " + this.number;
    }

    /**
     * get blackjack value for current card
     * @return
     */
    public int getValue() {
        if (index == 1) return -1;
        else if (index <= 10) return index;
        else return 10;
    }
}
