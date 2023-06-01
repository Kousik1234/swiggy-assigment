package Swiggy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameTest {
    private Game game;
    private List<Player> players;

    @Before
    public void setup() {
        players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        game = new Game(players);
    }

    @Test
    public void testPlayCard() {
        Player currentPlayer = game.getCurrentPlayer();
        Card card = currentPlayer.getHand().get(0);

        game.playCard(0);

        Assert.assertFalse(currentPlayer.getHand().contains(card));
        Assert.assertEquals(card, game.getDiscardPile().peek());
    }

    @Test
    public void testIsGameOver() {
        Assert.assertFalse(game.isGameOver());

        for (Player player : players) {
            player.getHand().clear();
        }

        Assert.assertTrue(game.isGameOver());
    }
}

