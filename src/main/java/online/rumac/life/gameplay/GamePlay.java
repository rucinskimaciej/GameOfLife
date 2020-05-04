package online.rumac.life.gameplay;

import online.rumac.life.board.Board;
import online.rumac.life.cell.Cell;
import online.rumac.life.output.Printable;
import online.rumac.life.play.commandLine.PlayWithCommandLine;

import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;

public class GamePlay {
//    public static void main(String[] args) {
//        int dimensions = 50;
//
//        var initialBoard = new String[][]{
//                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
//                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
//                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
//                {" ", " ", " ", " ", " ", "z", " ", " ", " ", " "},
//                {"z", "z", "z", "z", "z", "z", "z", "z", "z", "z"},
//                {" ", " ", " ", " ", " ", "z", " ", " ", " ", " "},
//                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
//                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
//                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
//                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
//        };
//
//
//        Set<Cell> initial = imgTranslator(initialBoard);
//        initial = putToMiddle(dimensions, dimensions, initial);
//
//            playLife(new Board(dimensions, dimensions, '0'), initial, new PlayWithCommandLine());
//    }
    public void playLife(Printable output, Board board, Set<Cell> initialCells) {
        Set<Cell> currentAliveCells = initialCells;

        try {
            int cycleCounter = 0;
            while (cycleCounter < 300) {
                fillBoard(currentAliveCells, board);
                output.boardToScreen(board);
                Set<Cell> futureCells = mapCurrentAliveToFutureAlive(currentAliveCells);
                if (futureCells.isEmpty() || futureCells.equals(currentAliveCells)) {
                    break;
                }
                currentAliveCells = new HashSet<>(futureCells);
                cycleCounter++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("GAME OVER!");
            System.exit(0);
        }
    }

    public Set<Cell> putToMiddle(int boardWidth, int boardHeight, Set<Cell> initialCells) {
        return initialCells.stream()
                .map(cell -> {
                    int x = cell.getPoint().x + boardWidth / 2 - 3;
                    int y = cell.getPoint().y + boardHeight / 2 - 3;
                    return new Cell(x, y);
                })
                .collect(Collectors.toSet());
    }

    public Set<Cell> imgTranslator(String[][] initialBoard) {
        Set<Cell> cells = new HashSet<>();
        for (int x = 0; x < initialBoard.length; x++) {
            for (int y = 0; y < initialBoard[x].length; y++) {
                if (!initialBoard[x][y].equals(" ")) {
                    cells.add(new Cell(x, y));
                }
            }
        }
        return cells;
    }

    private Set<Cell> mapCurrentAliveToFutureAlive(Set<Cell> currentAliveCells) {
        Set<Cell> futureAliveCells = new LinkedHashSet<>();

        Map<Point, Boolean> potentialPointsMap = createPointsMapOfAliveCellsAndNeighbours(currentAliveCells);
        Set<Point> aliveCells = filterAliveCells(potentialPointsMap);

        for (Point potentialAlivePoint : potentialPointsMap.keySet()) {
            var countAliveNeighbours = 0;
            Cell cell = new Cell(potentialAlivePoint.x, potentialAlivePoint.y);

            if (aliveCells.contains(potentialAlivePoint)) {
                // if point has 2 or 3 alive neighbours -> futureAliveCells.add(cell);
                for (Point p : cell.getNeighbours()) {
                    if (aliveCells.contains(p)) {
                        countAliveNeighbours++;
                    }
                }
                if (countAliveNeighbours == 2 || countAliveNeighbours == 3) {
                    futureAliveCells.add(cell);
                }
            } else {
                for (Point p : cell.getNeighbours()) {
                    if (aliveCells.contains(p)) {
                        countAliveNeighbours++;
                        if (countAliveNeighbours > 3) {
                            break;
                        }
                    }
                }
                if (countAliveNeighbours == 3) {
                    futureAliveCells.add(cell);
                }
            }
        }
        return futureAliveCells;
    }

    private Set<Point> filterAliveCells(Map<Point, Boolean> potentialPointsMap) {
        Set<Point> aliveCells = new HashSet<>();
        potentialPointsMap.entrySet().stream()
                .filter(Map.Entry::getValue)
                .forEach(entry -> aliveCells.add(entry.getKey()));
        return aliveCells;
    }

    private Map<Point, Boolean> createPointsMapOfAliveCellsAndNeighbours(Set<Cell> currentAliveCells) {
        Map<Point, Boolean> potentialPointsMap = new HashMap<>();

        for (Cell cell : currentAliveCells) {
            potentialPointsMap.put(cell.getPoint(), true);
            potentialPointsMap.computeIfPresent(cell.getPoint(), (key, val) -> val = true);
            cell.getNeighbours().forEach(c -> potentialPointsMap.putIfAbsent(c, false));
        }
        return potentialPointsMap;
    }

    private void fillBoard(Set<Cell> currentAliveCells, Board board) {
        board.clearBoard();
        for (Cell cell : currentAliveCells) {
            var x = cell.getPoint().x;
            var y = cell.getPoint().y;
            board.fillPointOnBoard(String.valueOf(board.getAliveCellMark()), x, y);
        }
    }
}
