package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;
import java.io.PrintWriter;
import java.util.function.BiFunction;

public class Abc327f2 {
  private final FastScanner sc;
  private final PrintWriter out;
  public Abc327f2(){
    sc = new FastScanner();
    out = new PrintWriter(System.out);
  }

  private final int TMax = 200000;
  private final int XMax = 200000;

  private final BiFunction<Integer, Integer, Integer> op = (l, r) ->
  {
    return Math.max(l, r);
  };

  private final BiFunction<Integer, Integer, Integer> mapping = (f, x) ->
  {
    return f + x;
  };

  private final BiFunction<Integer, Integer, Integer> composition = (f, g) ->
  {
    return f + g;
  };

  private final Integer e = 0;
  private final Integer id = 0;

  public void solve(){
    final int n = sc.nextInt();
    final int d = sc.nextInt();
    final int w = sc.nextInt();

    final ArrayList<ArrayList<int[]>> q =
            new ArrayList<>(TMax + 1);
    for (int i = 0; i < TMax + 1; i++) {
      q.add(new ArrayList<>());
    }

    for (int i = 0; i < n; i++) {
      final int t = sc.nextInt();
      final int x = sc.nextInt();
      q.get(Math.max(t - d, 0)).add(new int[]{x, 1});
      q.get(t).add(new int[]{x, -1});
    }

    final LazySegtree<Integer, Integer> seg = new LazySegtree<>(op, e, mapping, composition, id, XMax);

    Integer ans = 0;
    Integer f = 0;
    for (int i = 0; i < TMax; i++) {
      final int sz = q.get(i).size();
      for (int j = 0; j < sz; j++) {
        f = q.get(i).get(j)[1];
        seg.apply(
                Math.max(0, q.get(i).get(j)[0] - w),
                q.get(i).get(j)[0],
                f);
      }
      ans = Math.max(ans, seg.prod(0, XMax));
    }

    out.println(ans);
    out.flush();
  }
}
