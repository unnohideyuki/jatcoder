package jp.ne.sakura.uhideyuki.jatcoder;

import java.util.*;
import java.io.PrintWriter;
import java.util.function.BiFunction;

public class Edpq {
  private final FastScanner sc;
  private final PrintWriter out;
  public Edpq(){
    sc = new FastScanner();
    out = new PrintWriter(System.out);
  }
  public void flush() {}

  public void solve(){
    final int n = sc.nextInt();
    final long[] h = new long[n];
    final long[] a = new long[n];
    for (int i = 0; i < n; i++) { h[i] = sc.nextLong(); }
    for (int i = 0; i < n; i++) { a[i] = sc.nextLong(); }

    final BiFunction<Long, Long, Long> op = (x, y) -> {
      return Math.max(x, y);
    };

    final Segtree<Long> dp = new Segtree<>(op,0L, n + 1);

    long res = 0;
    for (int i = 0; i < n; i++) {
      long x = dp.prod(0, (int) h[i]);
      if (dp.prod((int) h[i], (int) h[i]+1) < x + a[i]) {
        dp.set((int) h[i], x + a[i]);
        res = Math.max(res, x + a[i]);
      }
    }
    out.println(res);
    out.flush();
  }
}
