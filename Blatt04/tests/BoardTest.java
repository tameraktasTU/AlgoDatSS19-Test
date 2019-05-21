import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void testBoard() {
        assertThrows(InputMismatchException.class, () -> {
            new Board(0);
        });

        assertThrows(InputMismatchException.class, () -> {
            new Board(11);
        });
    }

    @Test
    void testGetN() {
        Board test = new Board(3);
        assertEquals(3, test.getN());

        test = new Board(1);
        assertEquals(1, test.getN());

        test = new Board(6);
        assertEquals(6, test.getN());
    }

    @Test
    void testNFreeFields() {
        int n = 3;
        Board test = new Board(n);
        test.doMove(new Position(0, 0), 1);
        assertEquals(n * n - 1, test.nFreeFields());
        test.undoMove(new Position(0, 0));
        assertEquals(n * n, test.nFreeFields());
        test.undoMove(new Position(0, 0));
        assertEquals(n * n, test.nFreeFields());
        test.doMove(new Position(0, 1), -1);
        assertEquals(n * n - 1, test.nFreeFields());

        test = new Board(1);
        assertEquals(1, test.nFreeFields());
    }

    @Test
    void testGetField() {
        Board test = new Board(2);
        test.doMove(new Position(1, 0), 1);
        assertEquals(test.getField(new Position(1, 0)), 1);

        test = new Board(new int[][]{{0, 1}, {0, 0}});
        assertEquals(test.getField(new Position(1, 0)), 1);
    }

    @Test
    void testSetField() {
        Board test;
        test = new Board(new int[][]{{1, -1}, {0, 0}});
        test.setField(new Position(1, 1), 1);
        test.setField(new Position(1, 0), 0);
        test.setField(new Position(0, 1), -1);

        assertEquals(1, test.getField(new Position(0, 0)));
        assertEquals(-1, test.getField(new Position(0, 1)));
        assertEquals(0, test.getField(new Position(1, 0)));
        assertEquals(1, test.getField(new Position(1, 1)));
    }

    @Test
    void testDoMove() {
        Board test = new Board(3);
        test.doMove(new Position(1, 0), -1);
        test.doMove(new Position(2, 2), 1);
    }

    @Test
    void testUndoMove() {
        Board test;
        test = new Board(new int[][]{{0, -1}, {0, 0}});
        test.undoMove(new Position(1, 0));
        assertEquals(0, test.getField(new Position(1, 0)));
    }

    @Test
    void testIsGameWon() {
        testGame(new int[][]{{0, 0, 0}, {1, 1, 1}, {0, 0, 0}}, true);    // row
        testGame(new int[][]{{0, 0, 1}, {0, 0, 1}, {0, 0, 1}}, true);    // column
        testGame(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}, true);    // diagonal
        testGame(new int[][]{{0, 0, 1}, {0, 1, 0}, {1, 0, 0}}, true);    // diagonal
        testGame(new int[][]{{-1, 0, 1}, {0, -1, 0}, {-1, 1, 0}}, false);    // random
    }

    static void testGame(int[][] board, boolean won) {
        Board test = new Board(board);
        assertEquals(won, test.isGameWon());
    }

    @Test
    void testValidMoves() {
        Board test = new Board(new int[][]{{0, 0, 0}, {1, 0, 1}, {-1, -1, -1}});
        Collection<Position> valid = (Collection<Position>) test.validMoves();
        assertEquals(4, valid.size());
    }

    @Test
    void testPrint() {
        Board test;

        test = new Board(2);
        test.doMove(new Position(1, 0), 1);
        testOutput(test, "- X \n- - \n");

        test = new Board(new int[][]{{0, 0, 0}, {1, 1, 1}, {-1, -1, -1}});
        testOutput(test, "- - - \nX X X \nO O O \n");
    }

    void testOutput(Board test, String expected) {
        final PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.out.println(test);
        assertEquals(expected, outContent.toString());
        System.setOut(originalOut);
    }
}
