import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

	@Test
	void testBoard() {
        boolean thrown = false;
	    try {
            new Board(0);
	    } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
	    thrown = false;
        try {
            new Board(new int[][] {{0,0},{0}});
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
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
		Board test = new Board(3);
		test.doMove(new Position(0,0),1);
		assertEquals(3*3 - 1, test.nFreeFields());
		test.undoMove(new Position(0,0));
		assertEquals(3*3 - 0, test.nFreeFields());
		test.undoMove(new Position(0,0));
		assertEquals(3*3 - 0, test.nFreeFields());
		test.doMove(new Position(0,1),-1);
		assertEquals(3*3 - 1, test.nFreeFields());
		test.doMove(new Position(0,1),1);
		assertEquals(3*3 - 1, test.nFreeFields());

		test = new Board(1);
		assertEquals(1, test.nFreeFields());
	}

	@Test
	void testGetField() {
		Board test = new Board(2);
		test.doMove(new Position(1,0), 1);
		assertEquals(test.getField(new Position(1,0)),1);

		test = new Board(new int[][] {{ 0, 1},{ 0, 0}});
		assertEquals(test.getField(new Position(1,0)),1);
	}

	@Test
	void testSetField() {
        Board test;
        test = new Board(new int[][] {{ 1, -1},{ 0, 0}});
        test.setField(new Position(1,1), 1);
        test.setField(new Position(1,0), 0);
        test.setField(new Position(0,1), -1);

        assertEquals( 1, test.getField(new Position(0,0)));
        assertEquals(-1, test.getField(new Position(0,1)));
        assertEquals( 0, test.getField(new Position(1,0)));
        assertEquals( 1, test.getField(new Position(1,1)));
	}

	@Test
	void testDoMove() {
		Board test = new Board(3);
		test.doMove(new Position(1,0), -1);
		test.doMove(new Position(2,2),  1);
		//System.out.println("Expect: \n- O - \n- - - \n- - X \nActual:");
		//test.print();
	}

	@Test
	void testUndoMove() {
        Board test;
        test = new Board(new int[][] {{ 0, -1},{ 0, 0}});
        test.undoMove(new Position(1,0));
        assertEquals( 0, test.getField(new Position(1,0)));
	}

	@Test
	void testIsGameWon() {
		testGame(new int[][] {{-1}}, true);	// small board
		testGame(new int[][] {{0,0},{0,0}}, false);
		testGame(new int[][] {{0,0,0},{1,1,1},{0,0,0}}, true);	// row
		testGame(new int[][] {{0,0,1},{0,0,1},{0,0,1}}, true);	// column
		testGame(new int[][] {{1,0,0},{0,1,0},{0,0,1}}, true);	// diagonal
		testGame(new int[][] {{0,0,1},{0,1,0},{1,0,0}}, true);	// diagonal
		testGame(new int[][] {{-1,0,1},{0,-1,0},{-1,1,0}}, false);	// random
	}

	static void testGame (int[][] board, boolean won) {
		Board test = new Board(board);
		assertEquals(won, test.isGameWon());
	}

	@Test
	void testValidMoves() {
		Board test = new Board(new int[][] {{0,0,0},{1,0,1},{-1,-1,-1}});
		ArrayList<Position> valid = (ArrayList) test.validMoves();
		assertEquals(4, valid.size());
	}

	@Test
	void testPrint() {
	    Board test;

        test = new Board(2);
        test.doMove(new Position(1,0), 1);
        //System.out.println("Expect: \n- X \n- - \nActual:");
        //test.print();
        testOutput(test, "- X \n- - \n");

		test = new Board(new int[][] {{0,0,0},{1,1,1},{-1,-1,-1}});
		testOutput(test, "- - - \nX X X \nO O O \n");
	}

	void testOutput(Board test, String expected) {
        final PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        test.print();
        assertEquals(expected, outContent.toString());
        System.setOut(originalOut);
    }
}


