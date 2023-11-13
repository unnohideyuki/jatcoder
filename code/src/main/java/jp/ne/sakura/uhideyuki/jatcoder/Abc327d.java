package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class Abc327d {
  private final Scanner sc;
  public Abc327d(){
    sc = new Scanner(System.in);
  }
  public void flush() {}

  private int n;
  private int m;

  private ArrayList<ArrayList<Integer>> edges;
  private int[] v;
  private boolean[] visited;

  public void solve(){
    n = sc.nextInt();
    m = sc.nextInt();
    final int[] a = new int[m];
    final int[] b = new int[m];
    for (int i = 0; i < m; i++) { a[i] = sc.nextInt() - 1; }
    for (int i = 0; i < m; i++) { b[i] = sc.nextInt() - 1; }

    v = new int[n];
    visited = new boolean[n];
    for (int i = 0; i < n; i++) {
      v[i] = -1;
      visited[i] = false;
    }

    edges = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      edges.add(new ArrayList<>());
    }

    for (int i = 0; i < m; i++) {
      int u = a[i];
      int v = b[i];
      edges.get(u).add(v);
      edges.get(v).add(u);
    }

    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        dfs(i, 0);
      }
    }

    System.out.println("Yes");
  }

  private void dfs(int i, int value) {
    if (visited[i]) {
      if (v[i] != value) {
        System.out.println("No");
        System.exit(0);
      }
      return;
    }
    visited[i] = true;
    v[i] = value;
    int nvalue = value ^ 1;
    for (int j : edges.get(i)) {
      dfs(j, nvalue);
    }
  }
}
