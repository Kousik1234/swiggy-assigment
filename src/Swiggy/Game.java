package Swiggy;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Game {

    private List<Player> players;
    private Stack<Card> drawPile;
    private Stack<Card> discardPile;
    private int currentPlayerIndex;
    private int direction;


    public Game (List<Player> players) {

        this.players = players;
        this.drawPile = new Stack<>();
        this.discardPile = new Stack<>();
        this.currentPlayerIndex = 0;
        this.direction = 1;

        initializeDeck();
        dealCards();
        discardPile.push(drawPile.pop());
    }


    private void initializeDeck() {

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                drawPile.push(new Card(rank,suit));
            }
        }
        Collections.shuffle(drawPile);
    }

    private void dealCards() {
        for (int i=0 ; i<5 ;i++) {
            for (Player player : players) {
                player.addCardToHand(drawPile.pop());
            }
        }
    }

    private Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }


}
