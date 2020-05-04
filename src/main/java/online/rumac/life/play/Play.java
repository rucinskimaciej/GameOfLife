package online.rumac.life.play;

import online.rumac.life.board.Board;
import online.rumac.life.cell.Cell;
import online.rumac.life.gameplay.GamePlay;
import online.rumac.life.play.commandLine.PlayWithCommandLine;

import java.util.Set;

public class Play {
    public static void main(String[] args) {
        GamePlay game = new GamePlay();
        int dimensions = 50;

        var initialBoard = new String[][]{
                {" ", " ", " ", " ", "z", "z", " ", " ", " ", " "},
                {" ", " ", " ", " ", "z", "z", " ", " ", " ", " "},
                {" ", " ", " ", " ", "z", "z", " ", " ", " ", " "},
                {" ", " ", " ", " ", "z", "z", " ", " ", " ", " "},
                {"z", "z", "z", "z", "z", "z", "z", "z", "z", "z"},
                {" ", " ", " ", " ", "z", "z", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
        };


        Set<Cell> initial = game.imgTranslator(initialBoard);
        initial = game.putToMiddle(dimensions, dimensions, initial);

        game.playLife(new PlayWithCommandLine(), new Board(dimensions, dimensions, '0'), initial);

    }
}
