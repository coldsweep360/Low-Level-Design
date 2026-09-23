package lld.ConnectFour;

public class Board {

    private DiscColor[][] board;

    public Board() {
        this.board = new DiscColor[6][7];
    }

    public int dropDisc(int column, DiscColor discColor) {
        if(!isValidMove(column)) return -1;
        for (int row = 5; row >= 0; row--) {
            if (board[row][column] == null) {
                board[row][column] = discColor;
                return row;
            }
        }
        return -1;
    }

    public boolean checkWinner(int row, int column, DiscColor discColor) {
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        for (int[] direction : directions) {

            int count = 1; // current piece

            count += countInDirection(row, column, direction[0], direction[1], discColor);
            count += countInDirection(row, column, -direction[0], -direction[1], discColor);

            if (count >= 4) {
                return true;
            }
        }

        return false;
    }

    private int countInDirection(int row, int col, int rowDirection, int colDirection, DiscColor color) {

        int count = 0;

        int r = row + rowDirection;
        int c = col + colDirection;

        while (isValid(r, c) && board[r][c] == color) {
            count++;
            r += rowDirection;
            c += colDirection;
        }

        return count;
    }

    private boolean isValid(int row, int col) {
        return row >= 0
                && row < board.length
                && col >= 0
                && col < board[0].length;
    }

    public boolean checkDraw(){
        for(int col=0; col<7; col++){
            if(board[0][col]==null){
                return false;
            }
        }
        return true;
    }

    public boolean isValidMove(int col){
        return board[0][col] == null;
    }


}
