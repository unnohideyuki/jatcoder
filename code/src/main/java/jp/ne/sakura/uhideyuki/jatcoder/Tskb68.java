package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;
import java.io.PrintWriter;

public class Tskb68 {
  private final FastScanner sc;
  private final PrintWriter out;
  public Tskb68(){
    sc = new FastScanner();
    out = new PrintWriter(System.out);
  }

  public void flush() {
    out.flush();
  }

  public void solve() {
    final int n = sc.nextInt();
    final int m = sc.nextInt();
    final int[] p = new int[n];
    for (int i = 0; i < n; i++) {
      p[i] = sc.nextInt();
    }
    final int s = 0;
    final int g = n + 1;
    final MaxFlow mf = new MaxFlow(n + 2);
    int bias = 0;
    for (int i = 0; i < n; i++) {
      if (p[i] >= 0) {
        // 効果が正の場合は、特急駅にしない場合 (s -> i) のコストを p[i] に
        mf.addEdge(s, i + 1, p[i]);
        mf.addEdge(i + 1, g, 0);
        bias += p[i];
      } else {
        // 効果が負の場合は、特急駅にする場合（i -> g) のコストを -p[i] に
        mf.addEdge(s, i + 1, 0);
        mf.addEdge(i + 1, g, -p[i]);
      }
    }
    for (int i = 0; i < m; i++) {
      final int a = sc.nextInt();
      final int b = sc.nextInt();
      mf.addEdge(a, b, 1000000);
    }

    final int ans = bias - mf.maxFlow(s, g);
    out.println(ans);
  }
}
