package jp.ne.sakura.uhideyuki.jatcoder;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.BiFunction;

public class Practice2k {
  private final FastScanner sc;
  private final PrintWriter out;
  public Practice2k(){
    sc = new FastScanner();
    out = new PrintWriter(System.out);
  }
  public void flush() {}

  private final long MOD = 998244353L;

  private class S {
    public long a;
    public long size;
    public S(final long a, final long size) {
      this.a = a;
      this.size = size;
    }
  }

  private class F {
    public long a;
    public long b;
    public F(final long a, final long b) {
      this.a = a;
      this.b = b;
    }
    public F() {
      this.a = 0L;
      this.b = 0L;
    }
  }

  private BiFunction<S, S, S> op = (l, r) ->
  {
    final long a = (l.a + r.a) % MOD;
    final long b = l.size + r.size;
    return new S(a, b);
  };
  private BiFunction<F, S, S> mapping = (l, r) ->
  {
    final long a = (r.a * l.a % MOD + r.size * l.b % MOD) % MOD;
    final long size = r.size;
    return new S(a, size);
  };

  private BiFunction<F, F, F> composition = (l, r) -> {
    final long a = r.a * l.a % MOD;
    final long b = (r.b * l.a % MOD + l.b) % MOD;
    return new F(a, b);
  };

  public void solve(){
    final int n = sc.nextInt();
    final int q = sc.nextInt();

    final ArrayList<S> a = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      final long x = Long.parseLong(sc.next());
      a.add(new S(x, 1L));
    }

    final S e = new S(0L, 0L);
    final F id = new F(1L, 0L);
    final LazySegtree<S, F> seg = new LazySegtree<>(op, e, mapping, composition, id, a);

    F f = new F();
    for (int i = 0; i < q; i++) {
      int t = sc.nextInt();
      if (t == 0) {
        int l = sc.nextInt();
        int r = sc.nextInt();
        f.a = sc.nextLong();
        f.b = sc.nextLong();
        seg.apply(l, r, f);
      } else {
        int l = sc.nextInt();
        int r = sc.nextInt();
        out.println(seg.prod(l, r).a);
      }
    }
    out.flush();
  }
}
