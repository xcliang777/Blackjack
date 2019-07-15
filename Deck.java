import java.util.*;

public class Deck {

    private List<Card> list;
    private int score;

    public Deck(List<Card> list, int score) {
        this.list = list;
        this.score = score;
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
}
