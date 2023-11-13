package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;
import java.io.PrintWriter;

public class Abc328e {
  private final FastScanner sc;
  private final PrintWriter out;
  public Abc328e(){
    sc = new FastScanner();
    out = new PrintWriter(System.out);
  }

  public void solve(){
    final int n = sc.nextInt();
    final int m = sc.nextInt();
    final long k = sc.nextLong();
    final int[] u = new int[m];
    final int[] v = new int[m];
    final long[] w = new long[m];

    for (int i = 0; i < m; i++) {
      u[i] = sc.nextInt() - 1;
      v[i] = sc.nextInt() - 1;
      w[i] = sc.nextLong();
    }

    final ArrayList<Integer> vec = new ArrayList<>(m);
    for (int i = 0; i < m - (n-1); i++) {
      vec.add(0);
    }
    for (int i = 0; i < n - 1; i++) {
      vec.add(1);
    }

    long ans = (1L << 60);

    do {
      final UnionFind uf = new UnionFind(n);
      long wsum = 0;
      for (int i = 0; i < m; i++) {
        if (vec.get(i) == 1) {
          uf.merge(u[i], v[i]);
          wsum += w[i];
          wsum %= k;
        }
      }
      if (uf.size(0) == n) {
        ans = Math.min(ans, wsum);
      }
    } while (AC.nextPermutation(vec));

    out.println(ans);
    out.flush();
  }
}
