public class Player extends Gamer {

    private int bank;

    public Player(String id, Deck deck, int bank) {
        super(id, deck);
        this.bank = bank;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }
}
