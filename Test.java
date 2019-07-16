public class Test {

    public static void main(String[] args) {
        Pool pool = new Pool();
        Deck deck = new Deck();
        deck.addCard(new Card("asdf", "J", 11));
        deck.addCard(new Card("asdf", "Q", 12));
        deck.addCard(new Card("asdf", "K", 13));
        System.out.println(deck.toString(true));
        System.out.println(deck.toString(false));
        System.out.println(deck.getScore());
        System.out.println(deck.checkBlackJack());
    }
}
