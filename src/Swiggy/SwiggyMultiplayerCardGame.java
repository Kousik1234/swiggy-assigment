package Swiggy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SwiggyMultiplayerCardGame {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        List<Player> players = new ArrayList<>();

        System.out.println("Enter The Number Of Player :- ");
        int no_of_player = scanner.nextInt();

        for (int i = 1; i <= no_of_player; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String playerName = scanner.nextLine();
            players.add(new Player(playerName));
        }

        Game game = new Game(players);
        game.printGameState();

        while (true) {
            System.out.print("Current player: " + game.getCurrentPlayer().getName() + ". Enter the index of the card to play (0-" + (game.getCurrentPlayer().getHand().size() - 1) + "): ");
            int cardIndex = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            game.playCard(cardIndex);
            game.printGameState();

            if (game.isGameOver()) {
                break;
            }
        }
    }

}
