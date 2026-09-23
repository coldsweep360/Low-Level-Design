package lld.ExtendedConnectFour;

import java.util.Random;
import java.util.Scanner;

enum DiscColor { RED, YELLOW }
enum GameStatus { IN_PROGRESS, WON, DRAW }

class GameConfig {
    private final int rows;
    private final int cols;
    private final int winLength;

    GameConfig(int rows, int cols, int winLength) {
        this.rows = rows;
        this.cols = cols;
        this.winLength = winLength;
    }

    int getRows() { return rows; }
    int getCols() { return cols; }
    int getWinLength() { return winLength; }
}

abstract class Player {
    private final String id;
    private final String name;
    private final DiscColor color;

    Player(String id, String name, DiscColor color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    String getId() { return id; }
    String getName() { return name; }
    DiscColor getColor() { return color; }

    abstract int chooseColumn(Board board);
}

class HumanPlayer extends Player {
    private final Scanner scanner;

    HumanPlayer(String id, String name, DiscColor color, Scanner scanner) {
        super(id, name, color);
        this.scanner = scanner;
    }

    @Override
    int chooseColumn(Board board) {
        System.out.print(getName() + ", choose a column from 0 to " + (board.getCols() - 1) + ": ");
        return scanner.nextInt();
    }
}

class AIPlayer extends Player {
    private final Random random = new Random();

    AIPlayer(String id, String name, DiscColor color) {
        super(id, name, color);
    }

    @Override
    int chooseColumn(Board board) {
        int col;
        do {
            col = random.nextInt(board.getCols());
        } while (!board.isValidMove(col));

        System.out.println(getName() + " chooses column " + col);
        return col;
    }
}

class Board {
    private final int rows;
    private final int cols;
    private final int winLength;
    private final DiscColor[][] grid;

    Board(GameConfig config) {
        this.rows = config.getRows();
        this.cols = config.getCols();
        this.winLength = config.getWinLength();
        this.grid = new DiscColor[rows][cols];
    }

    int dropDisc(int col, DiscColor color) {
        if (!isValidMove(col)) return -1;

        for (int row = rows - 1; row >= 0; row--) {
            if (grid[row][col] == null) {
                grid[row][col] = color;
                return row;
            }
        }
        return -1;
    }

    boolean isValidMove(int col) {
        return col >= 0 && col < cols && grid[0][col] == null;
    }

    boolean checkWinner(int row, int col, DiscColor color) {
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};

        for (int[] direction : directions) {
            int count = 1;
            count += countInDirection(row, col, direction[0], direction[1], color);
            count += countInDirection(row, col, -direction[0], -direction[1], color);

            if (count >= winLength) return true;
        }
        return false;
    }

    private int countInDirection(int row, int col, int rowStep, int colStep, DiscColor color) {
        int count = 0;
        int r = row + rowStep;
        int c = col + colStep;

        while (isInsideBoard(r, c) && grid[r][c] == color) {
            count++;
            r += rowStep;
            c += colStep;
        }
        return count;
    }

    private boolean isInsideBoard(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    boolean isFull() {
        for (int col = 0; col < cols; col++) {
            if (grid[0][col] == null) return false;
        }
        return true;
    }

    int getCols() { return cols; }

    void printBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print((grid[row][col] == null ? "." : grid[row][col]) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Game {
    private final Board board;
    private final Player[] players;
    private int currentPlayerIndex;
    private GameStatus status = GameStatus.IN_PROGRESS;
    private Player winner;

    Game(GameConfig config, Player player1, Player player2) {
        this.board = new Board(config);
        this.players = new Player[]{player1, player2};
    }

    void start() {
        while (getStatus() == GameStatus.IN_PROGRESS) {
            board.printBoard();

            Player currentPlayer = getCurrentPlayer();
            int col = currentPlayer.chooseColumn(board);

            if (!makeMove(currentPlayer, col)) {
                System.out.println("Invalid move. Try again.");
            }
        }

        board.printBoard();
        if (getStatus() == GameStatus.WON) {
            System.out.println(getWinner().getName() + " wins!");
        } else {
            System.out.println("The game is a draw.");
        }
    }

    public synchronized boolean makeMove(Player player, int col) {
        if (status != GameStatus.IN_PROGRESS) return false;

        Player currentPlayer = players[currentPlayerIndex];
        if (!currentPlayer.getId().equals(player.getId())) return false;

        int row = board.dropDisc(col, currentPlayer.getColor());
        if (row == -1) return false;

        if (board.checkWinner(row, col, currentPlayer.getColor())) {
            status = GameStatus.WON;
            winner = currentPlayer;
            return true;
        }

        if (board.isFull()) {
            status = GameStatus.DRAW;
            return true;
        }

        currentPlayerIndex = (currentPlayerIndex + 1) % 2;
        return true;
    }

    public synchronized GameStatus getStatus() {
        return status;
    }

    public synchronized Player getWinner() {
        return winner;
    }

    public synchronized Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }
}

class Main {
    public static void main(String[] args) {
        GameConfig config = new GameConfig(6, 7, 4);
        Scanner scanner = new Scanner(System.in);

        Player player1 = new HumanPlayer("p1", "Alice", DiscColor.RED, scanner);
        Player player2 = new AIPlayer("p2", "Bot", DiscColor.YELLOW);

        Game game = new Game(config, player1, player2);
        game.start();
    }
}