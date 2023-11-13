package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;
import java.io.PrintWriter;

public class Abc328c {
  private final FastScanner sc;
  private final PrintWriter out;
  public Abc328c(){
    sc = new FastScanner();
    out = new PrintWriter(System.out);
  }

  public void solve(){
    int n = sc.nextInt();
    int q = sc.nextInt();
    final String s = sc.next();

    final FenwickTree<Integer> fw = FenwickTree.integerTree(n);
    for (int i = 1; i < n; i++){
      if (s.charAt(i-1) == s.charAt(i)) {
        fw.add(i, 1);
      }
    }

    for (int i = 0; i < q; i++) {
      final int l = sc.nextInt();
      final int r = sc.nextInt();
      final int a = fw.sum(l, r);
      out.println(a);
    }

    out.flush();
  }
}
