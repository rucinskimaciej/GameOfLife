package online.rumac.life.board;

import java.util.Arrays;

public class Board {
    private final String[][] board;
    private final char aliveCellMark;

    public Board(int width, int height, char aliveCellMark) {
        this.aliveCellMark = aliveCellMark;

        board = new String[width][height];
        clearBoard();
    }

    public void clearBoard() {
        for (String[] strings : board) {
            Arrays.fill(strings, " ");
        }
    }

    public String[][] getBoard() {
        return board;
    }

    public void fillPointOnBoard(String fill, int x, int y) {
        board[x][y] = fill;
    }

    public char getAliveCellMark() {
        return aliveCellMark;
    }


}
