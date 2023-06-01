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

    /* method is used to set up the initial deck of cards for the game.
    It iterates over all possible combinations of ranks and suits (using nested loops)
     and creates a Card object for each combination. These Card objects are then added to
     the drawPile stack, representing the deck of cards.

     By shuffling the deck, each player will have an equal
     chance of receiving any particular card during the initial dealing process.
     */

    private void initializeDeck() {

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                drawPile.push(new Card(rank,suit));
            }
        }
        Collections.shuffle(drawPile);
    }


    /*
    The dealCards() method is responsible for distributing the initial cards
    to each player at the start of the game. It uses nested loops to iterate
    over each player and deals a fixed number of cards (5 in this case) to each player.

    The outer loop iterates 5 times because we want to deal 5 cards to each player.
    The inner loop iterates over each player in the players list and assigns a card
    to that player by calling the addCardToHand() method on the player object.
    The card is taken from the top of the drawPile stack using the pop() method.
    * */

    private void dealCards() {
        for (int i=0 ; i<5 ;i++) {
            for (Player player : players) {
                player.addCardToHand(drawPile.pop());
            }
        }
    }

    Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }
    private void skipNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + direction) % players.size();
    }
    private void reverseDirection() {
        direction *= -1;
    }

    private void drawCards(int numCards) {
        Player currentPlayer = getCurrentPlayer();
        for (int i = 0; i < numCards; i++) {
            if (drawPile.isEmpty()) {
                // Game ends in a draw
                return;
            }
            currentPlayer.addCardToHand(drawPile.pop());
        }
    }

    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + direction + players.size()) % players.size();
    }

    private void declareWinner(Player player) {
        System.out.println("Player " + player.getName() + " wins!");
    }


    public void playCard(int index) {
        Player currentPlayer = getCurrentPlayer();
        Card card = currentPlayer.playCard(index);
        discardPile.push(card);

        if (card.getRank() == Rank.ACE) {
            skipNextPlayer();
        } else if (card.getRank() == Rank.KING) {
            reverseDirection();
        } else if (card.getRank() == Rank.QUEEN) {
            drawCards(2);
        } else if (card.getRank() == Rank.JACK) {
            drawCards(4);
        }

        if (currentPlayer.getHand().isEmpty()) {
            declareWinner(currentPlayer);
        } else {
            nextPlayer();
        }
    }

    public Stack<Card> getDiscardPile() {
        return discardPile;
    }


    public boolean isGameOver() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void printGameState() {
        System.out.println("Current player: " + getCurrentPlayer().getName());
        System.out.println("Top card on discard pile: " + discardPile.peek());
        System.out.println("Draw pile size: " + drawPile.size());
        System.out.println("Current direction: " + (direction == 1 ? "Clockwise" : "Counter-clockwise"));

        for (Player player : players) {
            System.out.println(player.getName() + "'s hand: " + player.getHand());
        }
    }

}
