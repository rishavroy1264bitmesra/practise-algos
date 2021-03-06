package datastructures.utils;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

public class Graph {

  private final int V;
  private int E;
  private Map<Integer, LinkedHashSet<Integer>> adj;

  /**
   * Initializes an empty graph with {@code V} vertices and 0 edges. param V the number of vertices
   *
   * @param V number of vertices
   * @throws IllegalArgumentException if {@code V < 0}
   */
  public Graph(int V) {
    if (V < 0)
      throw new IllegalArgumentException("Number of vertices must be nonnegative");
    this.V = V;
    this.E = 0;
    adj = new HashMap<>();
    for (int v = 0; v < V; v++) {
      adj.put(V, new LinkedHashSet<Integer>());
    }
  }

  /**
   * Initializes a graph from the specified input stream. The format is the number of vertices <em>V</em>, followed by
   * the number of edges <em>E</em>, followed by <em>E</em> pairs of vertices, with each entry separated by whitespace.
   *
   * @param in the input stream
   * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
   * @throws IllegalArgumentException if the number of vertices or edges is negative
   * @throws IllegalArgumentException if the input stream is in the wrong format
   */
  public Graph(Scanner in) {
    try {
      this.V = in.nextInt();
      if (V < 0)
        throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
      adj = new HashMap<>();
      for (int v = 0; v < V; v++) {
        adj.put(v, new LinkedHashSet<Integer>());
      }
      System.out.println(adj);
      int E = in.nextInt();
      if (E < 0)
        throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
      for (int i = 0; i < E; i++) {
        int v = in.nextInt();
        int w = in.nextInt();
        validateVertex(v);
        validateVertex(w);
        addEdge(v, w);
      }
    } catch (NoSuchElementException e) {
      throw new IllegalArgumentException("invalid input format in Graph constructor", e);
    }
  }


  /**
   * Initializes a new graph that is a deep copy of {@code G}.
   *
   * @param G the graph to copy
   */
  public Graph(Graph G) {
    this(G.V());
    this.E = G.E();
    for (int v = 0; v < G.V(); v++) {
      // reverse so that adjacency is in same order as original
      Stack<Integer> reverse = new Stack<Integer>();
      for (int w : G.adj.get(v)) {
        reverse.push(w);
      }
      for (int w : reverse) {
        adj.get(v).add(w);
      }
    }
  }

  /**
   * Returns the number of vertices in this graph.
   *
   * @return the number of vertices in this graph
   */
  public int V() {
    return V;
  }

  /**
   * Returns the number of edges in this graph.
   *
   * @return the number of edges in this graph
   */
  public int E() {
    return E;
  }

  // throw an IllegalArgumentException unless {@code 0 <= v < V}
  private void validateVertex(int v) {
    if (v < 0 || v >= V)
      throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
  }

  /**
   * Adds the undirected edge v-w to this graph.
   *
   * @param v one vertex in the edge
   * @param w the other vertex in the edge
   * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
   */
  public void addEdge(int v, int w) {
    validateVertex(v);
    validateVertex(w);
    E++;
    adj.get(v).add(w);
    adj.get(w).add(v);
  }


  /**
   * Returns the vertices adjacent to vertex {@code v}.
   *
   * @param v the vertex
   * @return the vertices adjacent to vertex {@code v}, as an iterable
   * @throws IllegalArgumentException unless {@code 0 <= v < V}
   */
  public Iterable<Integer> adj(int v) {
    validateVertex(v);
    return adj.get(v);
  }

  /**
   * Returns the degree of vertex {@code v}.
   *
   * @param v the vertex
   * @return the degree of vertex {@code v}
   * @throws IllegalArgumentException unless {@code 0 <= v < V}
   */
  public int degree(int v) {
    validateVertex(v);
    return adj.get(v).size();
  }


  /**
   * Returns a string representation of this graph.
   *
   * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>, followed by the <em>V</em>
   *         adjacency s
   */
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(V + " vertices, " + E + " edges \n" );
    for (int v = 0; v < V; v++) {
      s.append(v + ": ");
      for (int w : adj.get(v)) {
        s.append(w + " ");
      }
      s.append("\n");
    }
    return s.toString();
  }

}