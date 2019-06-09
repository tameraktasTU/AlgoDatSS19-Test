import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.List;

class DepthFirstPathsTest {
	@Test
	void testDfs() {
		Graph g = new Graph(9);
		g.addEdge(0, 3);
		g.addEdge(3, 4);
		g.addEdge(1, 2);
		g.addEdge(5, 4);
		g.addEdge(5, 7);
		g.addEdge(5, 8);
		g.addEdge(5, 2);
		g.addEdge(6, 7);
		g.addEdge(4, 7);

		DepthFirstPaths p = new DepthFirstPaths(g, 0);
		p.dfs(g);

		int[] expectedPre = {0, 3, 4, 5, 7, 6, 8, 2, 1};
		int[] expectedPost = {6, 7, 8, 1, 2, 5, 4, 3, 0};
		int[] expextedEdge = {0, 2, 5, 0, 3, 4, 7, 5, 5};
		int[] expextedDist = {0, 5, 4, 1, 2, 3, 5, 4, 4};

		Queue<Integer> pre = p.pre();
		Queue<Integer> post = p.post();
		int[] edge = p.edge();
		int[] dist = p.dist();
		int i = 0;
		while(!pre.isEmpty()) assertEquals(pre.remove(), expectedPre[i++]);
		i = 0;
		while(!post.isEmpty()) assertEquals(post.remove(), expectedPost[i++]);

		assertEquals(edge.length, dist.length);
		for(i = 0; i < edge.length; i++) {
			assertEquals(edge[i], expextedEdge[i]);
			assertEquals(dist[i], expextedDist[i]);
		}
	}

    // note: this test may also fail (or pass even though it shouldn't), if mazegrid() is not working correctly
	@Test
	void testSameResultsRecursiveAndNonRecursive() {
		Graph g = new Maze(5, 0).mazegrid();

		DepthFirstPaths p = new DepthFirstPaths(g, 0);
		DepthFirstPaths p2 = new DepthFirstPaths(g, 0);
		p.dfs(g);
		p2.nonrecursiveDFS(g);

		Queue<Integer> pre1 = p.pre();
		Queue<Integer> post1 = p.post();
		int[] edge1 = p.edge();
		int[] dist1 = p.dist();

		Queue<Integer> pre2 = p2.pre();
		Queue<Integer> post2 = p2.post();
		int[] edge2 = p2.edge();
		int[] dist2 = p2.dist();

		assertEquals(pre1.size(), pre2.size());
		assertEquals(post1.size(), post2.size());
		while(!pre1.isEmpty()) {
			assertEquals(pre1.remove(), pre2.remove());
		}
		while(!post1.isEmpty()) {
			assertEquals(post1.remove(), post2.remove());
		}
		assertEquals(edge1.length, edge2.length);
		assertEquals(dist1.length, dist2.length);

		assertEquals(dist1.length, edge1.length);
		for(int i = 0; i < edge1.length; i++) {
			assertEquals(edge1[i], edge2[i]);
			assertEquals(dist1[i], dist2[i]);
		}
	}

	@Test
	void testPathTo() {
		Graph g = new Graph(9);
		g.addEdge(0, 3);
		g.addEdge(3, 4);
		g.addEdge(1, 2);
		g.addEdge(5, 4);
		g.addEdge(5, 7);
		g.addEdge(5, 2);
		g.addEdge(6, 7);
		g.addEdge(4, 7);

		DepthFirstPaths p = new DepthFirstPaths(g, 0);
		p.dfs(g);

		assertNull(p.pathTo(8));

		List<Integer> path = p.pathTo(7);
		assertNotNull(path);

		int[] expectedPath = {7, 5, 4, 3, 0};
		assertEquals(path.size(), expectedPath.length);

		for (int i = 0; i < path.size(); i++) {
			assertEquals(path.get(i), expectedPath[i]);
		}
	}

}

