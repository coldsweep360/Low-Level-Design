package lld.ConnectFour;

public class Game {
    private Board board;
    private Player[] players = new Player[2];
    private Player currentPlayer;
    private GameStatus gameStatus;
    private Player winner;

    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.players[0] = player1;
        this.players[1] = player2;
        this.currentPlayer = player1;
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public boolean makeMove(int col){
        if(getGameStatus()==GameStatus.WON || getGameStatus()==GameStatus.DRAW) return false;
        int row = board.dropDisc(col,currentPlayer.getColor());

        if(row==-1){
            return false;
        }

        if(board.checkWinner(row, col, currentPlayer.getColor())){
            gameStatus = GameStatus.WON;
            winner = currentPlayer;
            return true;
        }

        if(board.checkDraw()){
            gameStatus = GameStatus.DRAW;
            return true;
        }

        checkTurn();
        return true;
    }

    public Player checkTurn(){
        if(currentPlayer==players[0]){
            currentPlayer = players[1];
        }
        else {
            currentPlayer = players[0];
        }
        return currentPlayer;
    }

    public GameStatus getGameStatus(){
        return gameStatus;
    }

    public Player getWinner(){
        return winner;
    }
}
