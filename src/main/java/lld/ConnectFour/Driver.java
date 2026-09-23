package lld.ConnectFour;

public class Driver {
    public static void main(String[] args) {
        Player player1 = new Player("Alice", DiscColor.RED);
        Player player2 = new Player("Bob", DiscColor.YELLOW);
        Game game = new Game(player1, player2);

        // RED wins horizontally along the bottom row.
        int[] moves = {0, 0, 1, 1, 2, 2, 3};

        for (int col : moves) {
            boolean moveAccepted = game.makeMove(col);

            if (!moveAccepted) {
                System.out.println("Invalid move in column " + col);
                continue;
            }

            if (game.getGameStatus() == GameStatus.WON) {
                System.out.println(game.getWinner().getId() + " wins!");
                break;
            }

            if (game.getGameStatus() == GameStatus.DRAW) {
                System.out.println("Game is a draw.");
                break;
            }
        }
    }
}