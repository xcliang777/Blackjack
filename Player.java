import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player extends Gamer {

    private int bank;
    private Deck splitList;

    public Player(String id, Deck deck, int bank) {
        super(id, deck);
        this.bank = bank;
        splitList = new Deck();
    }

    public Deck getSplitList() {
        return splitList;
    }

    public void setSplitList(Deck splitList) {
        this.splitList = splitList;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    /**
     * Pay the money before each round
     * @param amount amount of money player would like to pay
     * @return whether payment successful
     */
    public boolean payMoney(int amount) {
        if (amount > bank) return false;
        else {
            bank -= amount;
            return true;
        }
    }

    /**
     * Hit operation
     * @param num the deck number you want to do the action
     * @return if action succeeds
     */
    public boolean hit(int num) {
        if (num == 0) {
            return super.hit();
        } else {
            if (splitList.getScore() > 21) return false;
            splitList.addCard(Pool.getRandomCard());
            return true;
        }
    }

    /**
     * Stand operation
     * @param num the deck number you want to do the action
     */
    public void stand(int num) {
    }

    /**
     * doubleUp operation
     * @param amount amount of money it would cost
     * @param num amount of money it would cost
     * @return if action succeeds:
     */
    public boolean doubleUp(int amount, int num) {
        if (amount > bank) return false;
        return hit(num);
    }

    /**
     * doubleUp operation
     * @param amount amount of money it would cost
     * @return if action succeeds:
     */
    public boolean split(int amount) {
        splitList.addCard(super.getDeck().getList().get(0));
        super.getDeck().removeCard();
        return true;
    }

    /**
     * check whether split action is available
     * @return split availablity
     */
    public boolean checkSplit(int amount) {
        return (amount > bank || super.getDeck().getList().size() == 2 && splitList.getList().size() == 0 && super.getDeck().getList().get(0).getValue() ==  super.getDeck().getList().get(1).getValue());
    }

    /**
     * clear the deck after each round
     */
    public void clearInfo() {
        super.clearDeck();
        splitList.clearDeck();
    }
}
