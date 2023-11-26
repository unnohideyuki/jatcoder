package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;
import java.io.PrintWriter;

public class Tska68 {
  private final FastScanner sc;
  private final PrintWriter out;
  public Tska68(){
    sc = new FastScanner();
    out = new PrintWriter(System.out);
  }

  public void flush() {
    out.flush();
  }

  public void solve() {
    final int n = sc.nextInt();
    final int m = sc.nextInt();
    final MaxFlow mf = new MaxFlow(n);
    for (int i = 0; i < m; i++) {
      final int a = sc.nextInt() - 1;
      final int b = sc.nextInt() - 1;
      final int c = sc.nextInt();
      mf.addEdge(a, b, c);
    }
    out.println(mf.maxFlow(0, n - 1));
  }
}
