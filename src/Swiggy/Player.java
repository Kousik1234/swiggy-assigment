package Swiggy;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private  String name;
    private  List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public Card playCard(int index) {
        return hand.remove(index);
    }
}
