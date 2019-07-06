import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartialSolutionTest {

    @Test
    void compareTo() {
        String filename;

        filename = "samples/tests.board1.txt";
        Board board1 = new Board(filename);
        filename = "samples/tests.board2.txt";
        Board board2 = new Board(filename);

        PartialSolution psol = new PartialSolution(board1);
        PartialSolution pso2 = new PartialSolution(board2);

        int compared = psol.compareTo(pso2);
        assertEquals(-1, compared);
    }
}