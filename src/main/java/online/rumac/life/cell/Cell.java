package online.rumac.life.cell;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Cell {

    private final Point point;
    private boolean alive;
    private Set<Point> neighbours;

    public Cell(int x, int y) {
        this.point = new Point(x, y);
        alive = true;
        setNeighbours();
    }

    public boolean isAlive() {
        return alive;
    }

    private void setNeighbours() {
        neighbours = new HashSet<>(List.of(
            new Point(point.x, this.point.y - 1),         // down
            new Point(point.x, this.point.y + 1),         // up
            new Point(point.x + 1, this.point.y),         // right
            new Point(point.x - 1, this.point.y),         // left
            new Point(point.x - 1, this.point.y + 1),  // upLeft
            new Point(point.x + 1, this.point.y + 1),  // upRight
            new Point(point.x - 1, this.point.y - 1),  // downLeft
            new Point(point.x + 1, this.point.y - 1)   // downRight
        ));
    }

    public Set<Point> getNeighbours() {
        return neighbours;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }


    public Point getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return point.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return isAlive() == cell.isAlive() &&
                Objects.equals(getPoint(), cell.getPoint()) &&
                Objects.equals(getNeighbours(), cell.getNeighbours());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPoint(), isAlive(), getNeighbours());
    }
}
