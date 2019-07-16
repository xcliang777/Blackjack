public class Dealer extends Gamer {

    public Dealer(String id, Deck deck) {
        super(id, deck);
    }

    public String toString(boolean ifHide) {
        StringBuilder sb = new StringBuilder();
        sb.append(getId()+"'s cards\n");
        sb.append(getDeck().toString(ifHide));
        return sb.toString();
    }

}
