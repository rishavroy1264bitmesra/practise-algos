package solved.questions.hackerearth;

import java.util.Scanner;

import datastructures.utils.Graph;


public class ArticulationPoint {
  private int[] low;
  private int[] pre;
  private int cnt;
  private boolean[] articulation;

  public ArticulationPoint(Graph G) {
    low = new int[G.V()];
    pre = new int[G.V()];
    articulation = new boolean[G.V()];// Only Number of Vertex is required
    for (int v = 0; v < G.V(); v++)
      low[v] = -1;
    for (int v = 0; v < G.V(); v++)
      pre[v] = -1;

    for (int v = 0; v < G.V(); v++)
      if (pre[v] == -1)
        dfs(G, v, v);
  }

  private void dfs(Graph G, int u, int v) {
    int children = 0;
    pre[v] = cnt++;
    low[v] = pre[v];
    for (int w : G.adj(v)) {
      if (pre[w] == -1) {
        children++;
        dfs(G, v, w);
        // update low number
        low[v] = Math.min(low[v], low[w]);
        // non-root of DFS is an articulation point if low[w] >= pre[v]
        if (low[w] >= pre[v] && u != v)
          articulation[v] = true;
      }

      // update low number - ignore reverse of edge leading to v
      else if (w != u)
        low[v] = Math.min(low[v], pre[w]);
    }

    // root of DFS is an articulation point if it has more than 1 child
    if (u == v && children > 1)
      articulation[v] = true;
  }

  // is vertex v an articulation point?
  public boolean isArticulation(int v) {
    return articulation[v];
  }

  // test client
  public static void main(String[] args) {
    Graph G = new Graph(new Scanner(System.in));
    System.out.println(G);

    ArticulationPoint bic = new ArticulationPoint(G);

    // print out articulation points
    System.out.println();
    System.out.println("Articulation points");
    System.out.println("-------------------");
    for (int v = 0; v < G.V(); v++)
      if (bic.isArticulation(v))
        System.out.println(v);
  }


  private static final class Edge implements Comparable<Edge> {
    private int v;
    private int w;

    private Edge(int v, int w) {
      if (v < w) {
        this.v = v;
        this.w = w;
      } else {
        this.v = w;
        this.w = v;
      }
    }

    public int compareTo(Edge that) {
      if (this.v < that.v)
        return -1;
      if (this.v > that.v)
        return +1;
      if (this.w < that.w)
        return -1;
      if (this.w > that.w)
        return +1;
      return 0;
    }
  }
}
