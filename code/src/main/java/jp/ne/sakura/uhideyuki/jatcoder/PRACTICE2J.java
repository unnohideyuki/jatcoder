package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class PRACTICE2J {
  private final Scanner sc;
  public PRACTICE2J(){
    sc = new Scanner(System.in);
  }
  public void flush() {}

  public void solve(){
    // Input
    final int n = sc.nextInt();
    final int q = sc.nextInt();
    final BiFunction<Integer, Integer, Integer> op = (a, b) -> { return Math.max(a, b); };
    final Integer e = 0;
    final Segtree<Integer> seg = new Segtree<>(op, e, n);
    for (int i = 0; i < n; i++) {
      final int ai = sc.nextInt();
      seg.set(i, ai);
    }

    // Queries
    for (int i = 0; i < q; i++) {
      final int ty = sc.nextInt();
      switch (ty) {
        case 1 -> {
          final int x = sc.nextInt() - 1;
          final int v = sc.nextInt();
          seg.set(x, v);
        }
        case 2 -> {
          final int l = sc.nextInt() - 1;
          final int r = sc.nextInt();
          System.out.println(seg.prod(l, r));
        }
        case 3 -> {
          final int x = sc.nextInt() - 1;
          final int v = sc.nextInt();
          final Function<Integer, Boolean> f = (mx) -> { return mx < v; };
          System.out.println(seg.maxRight(x, f) + 1);
        }
      }
    }
  }
}
