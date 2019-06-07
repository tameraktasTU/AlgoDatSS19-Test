import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

class MazeTest {

	@Test
	void testVisual() {
		Maze m = new Maze(30, 0);
		GridGraph graph = new GridGraph(m.M());
		graph.plot(m.findWay(0, 899), 0);

		StdDraw.pause(10000);
	}

	@Test
	void testMazegrid()
	{
		String expected = "16 vertices, 24 edges \n" +
			"0: 4 1 \n" +
			"1: 0 5 2 \n" +
			"2: 1 6 3 \n" +
			"3: 2 7 \n" +
			"4: 0 8 5 \n" +
			"5: 1 4 9 6 \n" +
			"6: 2 5 10 7 \n" +
			"7: 3 6 11 \n" +
			"8: 4 12 9 \n" +
			"9: 5 8 13 10 \n" +
			"10: 6 9 14 11 \n" +
			"11: 7 10 15 \n" +
			"12: 8 13 \n" +
			"13: 9 12 14 \n" +
			"14: 10 13 15 \n" +
			"15: 11 14 \n";

		Graph grid = new Maze(4, 0).mazegrid();
		assertEquals(expected, grid.toString());
	}

	@Test
	void testFindWay() {
		Maze m = new Maze(20, 0);

		List<Integer> path = m.findWay(0, 399);

		assertNotNull(path);
		assertNotEquals(path.size(), 0);

		assertEquals(path.get(0), 0);
		assertEquals(path.get(path.size() - 1), 399);

		for(int i = 1; i < path.size(); i++) {
			assertTrue(m.hasEdge(path.get(i - 1), path.get(i)));
		}
	}

}

