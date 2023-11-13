package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;
import java.io.PrintWriter;

public class Abc328a {
  private final FastScanner sc;
  private final PrintWriter out;
  public Abc328a(){
    sc = new FastScanner();
    out = new PrintWriter(System.out);
  }

  public void solve(){
    final int n = sc.nextInt();
    final int x = sc.nextInt();
    int ans = 0;
    for (int i = 0; i < n; i++) {
      int s = sc.nextInt();
      if (s <= x) {
        ans += s;
      }
    }
    out.println(ans);
    out.flush();
  }
}
