package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;
import java.io.PrintWriter;

public class Abc315e {
  private final FastScanner sc;
  private final PrintWriter out;
  public Abc315e(){
    sc = new FastScanner();
    out = new PrintWriter(System.out);
  }

  public void flush() {
    out.flush();
  }

  private ArrayList<ArrayList<Integer>> edges;
  private ArrayList<ArrayList<Integer>> redges;
  private boolean[] needed;

  private void dfs(final int i) {
    if (needed[i]) { // already visited
      return;
    }
    needed[i] = true;
    for (int j : redges.get(i)) {
      dfs(j);
    }
  }

  public void solve() {
    // Input
    final int n = sc.nextInt();
    edges = new ArrayList<>(n);
    redges = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      edges.add(new ArrayList<>());
      redges.add(new ArrayList<>());
    }
    for (int i = 0; i < n; i++) {
      final int c = sc.nextInt();
      for (int j = 0; j < c; j++) {
        final int p = sc.nextInt() - 1;
        edges.get(p).add(i); // edge p -> i
        redges.get(i).add(p); // reverse edge
      }
    }

    // 0 に連結するノードを dfs で求める
    needed = new boolean[n];
    dfs(0);

    // topological sort
    final int[] ideg = new int[n]; // 入り次数
    for (int i = 1; i < n; i++) {
      if (!needed[i]) { continue; }
      for (int j : edges.get(i)) {
        if (needed[j]) {
          ideg[j]++;
        }
      }
    }
    final Queue<Integer> que = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      if (needed[i] && ideg[i] == 0) {
        que.add(i);
      }
    }
    final ArrayList<Integer> ans = new ArrayList<>();
    while (!que.isEmpty()) {
      final int i = que.poll();
      if (i == 0) { break; }
      ans.add(i + 1);
      for (int j : edges.get(i)) {
        if (needed[j]) {
          ideg[j]--;
          if (ideg[j] == 0) {
            que.add(j);
          }
        }
      }
    }
    // Answer
    for (int a : ans) {
      out.print(a);
      out.print(" ");
    }
    out.println("");
  }

}
