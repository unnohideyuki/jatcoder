package jp.ne.sakura.uhideyuki.jatcoder;

import java.util.*;
import java.io.PrintWriter;

public class Abc150f {
  private final FastScanner sc;
  private final PrintWriter out;
  public Abc150f(){
    sc = new FastScanner();
    out = new PrintWriter(System.out);
  }

  public void flush() {
    out.flush();
  }

  public void solve() {
    final int n = sc.nextInt();
    final int[] a = new int[n];
    final int[] b = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = sc.nextInt();
    }
    for (int i = 0; i < n; i++) {
      b[i] = sc.nextInt();
    }

    final int[] c = new int[n];
    final int[] d = new int[n];
    for (int i = 0; i < n; i++){
      c[i] = a[i] ^ a[(i+1)%n];
      d[i] = b[i] ^ b[(i+1)%n];
    }

    final ArrayList<Integer> v = new ArrayList<>(3*n);
    for (int i = 0; i < n; i++) {
      v.add(c[i]);
    }
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < n; j++) {
        v.add(d[j]);
      }
    }

    final int[] z = Str.zAlgorithm(v);

    final ArrayList<Pair<Integer, Integer>> ans = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      if (z[i+n] >= n) {
        final int k = (n - i) % n;
        final int x = a[0] ^ b[i];
        ans.add(new Pair<>(k, x));
      }
    }

    Collections.sort(ans);
    for (Pair<Integer, Integer> p : ans) {
      out.print(p.getFirst());
      out.print(" ");
      out.println(p.getSecond());
    }
  }
}
