package online.rumac.life.cell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    private Cell cell;

    @BeforeEach
    void setUp() {
        cell = new Cell(0, 0);
    }

    @DisplayName("Constructor setup")
    @Test
    void getPoint() {
        var expected = new Point(0, 0);
        var actual = cell.getPoint();

        assertEquals(expected, actual);
    }

    @DisplayName("Cell is set dead")
    @Test
    void isDead() {
        assertFalse(cell.isAlive());
    }
}