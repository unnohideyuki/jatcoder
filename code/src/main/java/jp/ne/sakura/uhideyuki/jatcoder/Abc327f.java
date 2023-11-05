package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;
import java.util.function.BiFunction;

public class Abc327f {
  private final Scanner sc;
  public Abc327f(){
    sc = new Scanner(System.in);
  }

  public void solve(){
    final int n = sc.nextInt();
    final int d = sc.nextInt();
    final int w = sc.nextInt();
    int ans = 0;
    final int L = 200400;
    final BiFunction<
            Pair<Integer, Integer>,
            Pair<Integer, Integer>,
            Pair<Integer, Integer>> op = (l, r) -> {
      final int first = l.getFirst() + r.getFirst();
      final int second = Math.max(l.getSecond(), l.getFirst() + r.getSecond());
      return new Pair<>(first, second);
    };
    final Pair<Integer, Integer> e = new Pair<>(0, 0);
    final Segtree<Pair<Integer, Integer>> seg = new Segtree<>(op, e, L);
    final ArrayList<ArrayList<Pair<Integer, Integer>>> g = new ArrayList<>(L);
    for (int i = 0; i < L; i++) { g.add(new ArrayList<>()); }
    for (int i = 0; i < n; i++) {
      final int t = sc.nextInt();
      final int x = sc.nextInt();
      for (int j = 0; j < 4; j++) {
        int s = 1;
        int a = t;
        int b = x;
        if ((j & 1) == 1){ a += d; s *= -1; }
        if ((j & 2) == 2){ b += w; s *= -1; }
        if (Math.max(a, b) < L) { g.get(a).add(new Pair<>(b, s)); };
      }
    }
    for (int i = 0; i < L; i++) {
      for (var x : g.get(i)) {
        var tmp = new Pair<>(seg.get(x.getFirst()));
        tmp.setFirst(tmp.getFirst() + x.getSecond());
        tmp.setSecond(Math.max(0, tmp.getFirst()));
        seg.set(x.getFirst(), tmp);
      }
      ans = Math.max(ans, seg.allProd().getSecond());
    }
    System.out.println(ans);
  }
}
