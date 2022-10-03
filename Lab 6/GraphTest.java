/**
 * GraphTest.java
 * @author Aurielle Jocom
 * @author Donggyu Yu
 * CIS 22C, Lab 6
 */
public class GraphTest {
	public static void main(String[] args) {
		Graph G = new Graph(5);
		System.out.println("Should print 0: " + G.getNumEdges());
		System.out.println("Should print 5: " + G.getNumVertices());
		System.out.println("Should print true: " + G.isEmpty());

		G.addDirectedEdge(1, 2);
		G.addDirectedEdge(2, 3);
		G.addDirectedEdge(2, 4);
		G.addDirectedEdge(1, 5);
		G.addDirectedEdge(1, 3);
		G.BFS(1);
		System.out.println("Should print 4: " + G.getNumEdges());
		System.out.println("Should print false: " + G.isEmpty());
		System.out.println("Should print 1: " + G.getDistance(5));
		System.out.println("Should print B: " + G.getColor(3));
		System.out.println("Should print 2: " + G.getParent(4));
		System.out.println("Should print 3 4: " + G.getAdjacencyList(2));
		System.out.println("\nprintBFS Method");
		G.printBFS();
		System.out.println("\ntoString Method");
		System.out.println(G);

		Graph G1 = new Graph(5);
		System.out.println("\nShould print 5: " + G1.getNumVertices());
		System.out.println("Should print W: " + G1.getColor(1));
		G1.addUndirectedEdge(1, 2);
		G1.addUndirectedEdge(1, 5);
		G1.addUndirectedEdge(2, 3);
		G1.addUndirectedEdge(2, 4);
		G1.addUndirectedEdge(3, 4);
		G1.addUndirectedEdge(4, 5);
		//G1.addUndirectedEdge(5, 5);
		System.out.println("Should print 6: " + G1.getNumEdges());
		System.out.println("Should print -1: " + G1.getDistance(3));
		System.out.println("Should print 0: " + G1.getParent(4));
		System.out.println("Should print 1 3 4: " + G1.getAdjacencyList(2));
		System.out.println("\ntoString Method");
		G1.BFS(1);
		G1.printBFS();
		System.out.println(G1);
	}
}
