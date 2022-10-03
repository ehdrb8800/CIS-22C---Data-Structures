/**
 * Graph.java
 * @author Aurielle Jocom
 * @author Donggyu Yu
 * CIS 22C, Lab 6
 */

import java.util.ArrayList;

public class Graph {
	private int vertices;
	private int edges;
	private ArrayList<List<Integer>> adj;
	private ArrayList<Character> color;
	private ArrayList<Integer> distance;
	private ArrayList<Integer> parent;

	/** Constructor */

	/**
	 * initializes an empty graph, with n vertices and 0 edges, and intitializes all
	 * arraylists to contain default values from indices 1 to n
	 * 
	 * @param n the number of vertices in the graph
	 */
	public Graph(int n) {
		vertices = n;
		edges = 0;

		adj = new ArrayList<List<Integer>>();
		color = new ArrayList<Character>();
		distance = new ArrayList<Integer>();
		parent = new ArrayList<Integer>();

		for (int i = 0; i <= n; i++) {
			adj.add(new List<Integer>());
			color.add('W');
			distance.add(-1);
			parent.add(0);
		}
	}

	/*** Accessors ***/

	/**
	 * Returns the number of edges in the graph
	 * 
	 * @return the number of edges
	 */
	public int getNumEdges() {
		return edges;
	}

	/**
	 * Returns the number of vertices in the graph
	 * 
	 * @return the number of vertices
	 */
	public int getNumVertices() {
		return vertices;
	}

	/**
	 * returns whether the graph is empty (no edges)
	 * 
	 * @return whether the graph is empty
	 */
	public boolean isEmpty() {
		return edges == 0;
	}

	/**
	 * Returns the value of the distance[v]
	 * 
	 * @param v a vertex in the graph
	 * @precondition 0 < v <= vertices
	 * @return the distance of vertex v
	 * @throws IndexOutOfBoundsException when the precondition is violated
	 */
	public Integer getDistance(Integer v) throws IndexOutOfBoundsException {
		if (v <= 0 || v > vertices) {
			throw new IndexOutOfBoundsException("getDistance(): " + "Index is out of bound");
		}

		return distance.get(v);
	}

	/**
	 * Returns the value of the parent[v]
	 * 
	 * @param v a vertex in the graph
	 * @precondition 0 < v <= vertices
	 * @return the parent of vertex v
	 * @throws IndexOutOfBoundsException when the precondition is violated
	 */
	public Integer getParent(Integer v) throws IndexOutOfBoundsException {
		if (v <= 0 || v > vertices) {
			throw new IndexOutOfBoundsException("getParent(): " + "Index is out of bound");
		}

		return parent.get(v);
	}

	/**
	 * Returns the value of the color[v]
	 * 
	 * @param v a vertex in the graph
	 * @precondition 0 < v <= vertices
	 * @return the color of vertex v
	 * @throws IndexOutOfBoundsException when the precondition is violated
	 */
	public Character getColor(Integer v) throws IndexOutOfBoundsException {
		if (v <= 0 || v > vertices) {
			throw new IndexOutOfBoundsException("getColor(): " + "Index is out of bound");
		}

		return color.get(v);
	}

	/**
	 * Returns the List stored at index v
	 * 
	 * @param v a vertex in the graph
	 * @return the adjacency List a v
	 * @precondition 0 < v <= vertices
	 * @throws IndexOutOfBoundsException when the precondition is violated
	 */
	public List<Integer> getAdjacencyList(Integer v) throws IndexOutOfBoundsException {
		if (v <= 0 || v > vertices) {
			throw new IndexOutOfBoundsException("getAdjacencyList(): " + "Index is out of bound");
		}

		return adj.get(v);
	}

	/*** Manipulation Procedures ***/

	/**
	 * Inserts vertex v into the adjacency list of vertex u (i.e. inserts v into the
	 * list at index u)
	 * 
	 * @precondition 0 < u <= vertices, 0 < v <= vertices
	 * @throws IndexOutOfBounds exception when the precondition is violated
	 */
	public void addDirectedEdge(Integer u, Integer v) throws IndexOutOfBoundsException {
		if (u <= 0 || u > vertices) {
			throw new IndexOutOfBoundsException("addDirectedEdge(): " + "Index is out of bound");
		}

		if (v <= 0 || v > vertices) {
			throw new IndexOutOfBoundsException("addDirectedEdge(): " + "Index is out of bound");
		}

		adj.get(u).placeIterator();
		if (adj.get(u).offEnd()) {
			adj.get(u).addFirst(v);
		} else if (adj.get(u).getIterator() > v) {
			adj.get(u).addFirst(v);
		} else {
			int count = 0;
			for (int i = 0; i < adj.get(u).getLength(); i++) {
				if (adj.get(u).getIterator() < v) {
					adj.get(u).advanceIterator();
					count++;
				}
			}
			adj.get(u).placeIterator();
			adj.get(u).advanceNTimes(count);
			adj.get(u).addIterator(v);
		}

		edges++;
	}

	/**
	 * Inserts vertex v into the adjacency list of vertex u (i.e. inserts v into the
	 * list at index u) and inserts u into the adjacent vertex list of v
	 * 
	 * @precondition 0 < u <= vertices, 0 < v <= vertices
	 * @throws IndexOutOfBounds exception when the precondition is violated
	 */
	public void addUndirectedEdge(Integer u, Integer v) throws IndexOutOfBoundsException {
		if (u <= 0 || u > vertices) {
			throw new IndexOutOfBoundsException("addUndirectedEdge(): " + "Index is out of bound");
		}

		if (v <= 0 || v > vertices) {
			throw new IndexOutOfBoundsException("addUndirectedEdge(): " + "Index is out of bound");
		}

		adj.get(v).placeIterator();
		if (adj.get(v).offEnd()) {
			adj.get(v).addFirst(u);
		} else if (adj.get(v).getIterator() > u) {
			adj.get(v).addFirst(u);
		} else {
			int count = 0;
			for (int i = 0; i < adj.get(v).getLength(); i++) {
				if (adj.get(v).getIterator() < u) {
					adj.get(v).advanceIterator();
					count++;
				}
			}
			adj.get(v).placeIterator();
			adj.get(v).advanceNTimes(count);
			adj.get(v).addIterator(u);
		}

		adj.get(u).placeIterator();
		if (adj.get(u).offEnd()) {
			adj.get(u).addFirst(v);
		} else if (adj.get(u).getIterator() > v) {
			adj.get(u).addFirst(v);
		} else {
			int count = 0;
			for (int i = 0; i < adj.get(u).getLength(); i++) {
				if (adj.get(u).getIterator() < v) {
					adj.get(u).advanceIterator();
					count++;
				}
			}
			adj.get(u).placeIterator();
			adj.get(u).advanceNTimes(count);
			adj.get(u).addIterator(v);
		}

		edges++;
	}

	/*** Additional Operations ***/

	/**
	 * Creates a String representation of the Graph Prints the adjacency list of
	 * each vertex in the graph, vertex: <space separated list of adjacent vertices>
	 */
	@Override
	public String toString() {
		String result = "";

		for (int i = 1; i <= vertices; i++) {
			result += i + ": " + adj.get(i).toString() + "\n";
		}

		return result;
	}

	/**
	 * Prints the current values in the parallel ArrayLists after executing BFS.
	 * First prints the heading: v <tab> c <tab> p <tab> d Then, prints out this
	 * information for each vertex in the graph Note that this method is intended
	 * purely to help you debug your code
	 */
	public void printBFS() {
		System.out.println("v \t c \t p \t d");

		for (int i = 1; i <= vertices; i++) {
			System.out.println(i + " \t " + color.get(i) + " \t " + parent.get(i) + " \t " + distance.get(i));
		}
	}

	/**
	 * Performs breath first search on this Graph give a source vertex
	 * 
	 * @param source the source vertex
	 * @precondition graph must not be empty
	 * @precondition source is a vertex in the graph
	 * @throws IllegalStateException     if the graph is empty
	 * @throws IndexOutOfBoundsException when vertex is outside the bounds of the
	 *                                   graph
	 */

	public void BFS(Integer source) throws IllegalStateException, IndexOutOfBoundsException {
		if (isEmpty()) {
			throw new IllegalStateException("BFS(): " + "Graph is empty");
		}

		if (source <= 0 || source > vertices) {
			throw new IndexOutOfBoundsException("BFS(): " + "Index is out of bound");
		}

		List L = new List<Integer>();
		int x;

		for (int i = 1; i <= vertices; i++) {
			color.set(i, 'W');
			distance.set(i, -1);
			parent.set(i, 0);
		}

		color.set(source, 'G');
		distance.set(source, 0);
		L.addLast(source);

		while (!L.isEmpty()) {
			x = (int) L.getFirst();
			L.removeFirst();

			adj.get(x).placeIterator();

			for (int i = 1; i <= adj.get(x).getLength(); i++) {
				if (color.get(adj.get(x).getIterator()) == 'W') {
					color.set(adj.get(x).getIterator(), 'G');
					distance.set(adj.get(x).getIterator(), distance.get(x) + 1);
					parent.set(adj.get(x).getIterator(), x);

					L.addLast(adj.get(x).getIterator());
				}
				adj.get(x).advanceIterator();
			}

			color.set(x, 'B');
		}
	}
}