import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {

	@Test
	void testAlphaBetaBoardInt() {
		//1 == x, -1 == o
		// Win in one move
		boolean show1 = false;
		testEval(new int[][]{{-1, 0,-1},{0, 0, 0},{ 0, 0, 0}},-1, 7, show1);
		testEval(new int[][]{{ 1, 0, 0},{0, 0, 1},{ 1, 0, 0}}, 1, 6, show1);
		testEval(new int[][]{{ 1, 1, 0},{0, 0, 0},{ 0, 0, 1}}, 1, 6, show1);
		testEval(new int[][]{{ 1, 0, 1},{0, 1,-1},{-1, 0,-1}}, 1, 3, show1);    // Stellung (6)
		testEval(new int[][]{{-1, 0,-1},{0, 1, 0},{-1, 0, 0}},-1, 5, show1);
		testEval(new int[][]{{ 1, 0},{0, 0}},-1,-2, show1);
		testEval(new int[][]{{ 1, 0},{0, 0}}, 1, 3, show1);
		// Lose in two moves
		boolean show2 = false;
		testEval(new int[][]{{-1, 0,-1},{0, 0, 0},{-1, 0, 0}}, 1, -5, show2);
		testEval(new int[][]{{ 1, 0, 1},{0, 0, 0},{ 1, 0,-1}}, -1, -4, show2);
		// Win in 3 moves
		testEval(new int[][]{{ 1, 0, 0},{0, 1,-1},{ 0, 0,-1}}, 1, 3, false);	// Stellung (4)
		// Lose in 4 moves
		testEval(new int[][]{{ 1, 0, 0},{0, 1,-1},{ 0, 0, 0}}, -1, -3, false);	// Stellung (3)
		testEval(new int[][]{{ 0, 0, 0},{0, 1,-1},{ 0, 0, 0}}, 1, 3, false);	// Stellung (2)
		// draw
		testEval(new int[][]{{ 0, 0, 0},{0, 1, 0},{ 0, 0, 0}}, -1, 0, false);	// Stellung (1)
		// Edge cases
		testEval(new int[][]{{ 1, 1, 1},{1, 1, 1},{ 1, 1, 1}}, -1, -1, false);
		testEval(new int[][]{{ 0, 0, 0},{0, 0, 0},{ 0, 0, 0}},  1, 0, false);
	}

	static void testEval(int[][] board, int playerToAct, int Evaluation, boolean print) {
		Board test = new Board(board);
		int eval = TicTacToe.alphaBeta(test, playerToAct);
		if(print) {
			test.print();
			if(playerToAct == 1) {
				System.out.println("X: " + eval);
			} else {
				System.out.println("O: " + eval);
			}
			System.out.println();
		}
		assertEquals(Evaluation, eval);
	}

	@Test
	void testEvaluatePossibleMoves() {
		// 1 == x, -1 == o
		Board test;
		test = new Board(new int[][] {{0,0},{0,0}});
		testEvalPossMoves(test, -1, "Evaluation for player ’o’:\n  2  2\n  2  2\n");

		test = new Board(new int[][] {{ 0,-1, 0},{0, 0, 0},{ 0, 0, 1}});	// Beispiel 1 (PDF)
		testEvalPossMoves(test, 1, "Evaluation for player ’x’:\n  0  o  3\n  0  3 -2\n  3  0  x\n");

		test = new Board(3);	// test case (results.txt)
		test.doMove(new Position(1,0), -1);
		test.doMove(new Position(2,2),  1);
		testEvalPossMoves(test, 1, "Evaluation for player ’x’:\n  0  o  3\n  0  3 -2\n  3  0  x\n");

		test = new Board(3);
		testEvalPossMoves(test, 1, "Evaluation for player ’x’:\n  0  0  0\n  0  0  0\n  0  0  0\n");
	}

	void testEvalPossMoves(Board test, int player, String expected) {
		final PrintStream originalOut = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		TicTacToe.evaluatePossibleMoves(test, player);
		assertEquals(expected, outContent.toString());
		System.setOut(originalOut);
	}

/*
	@Test
	void testNeededTime() {
		// no alpha beta pruning
		// 1: 81ms, 2: 46ms, 3: 276ms, 4: -too long
		// with alpha beta pruning
		// 1: 38ms, 2: 82, 3: 164ms, 4: -too long
		Board test = new Board(3);
		TicTacToe.evaluatePossibleMoves(test, 1);
	}

 */

}


