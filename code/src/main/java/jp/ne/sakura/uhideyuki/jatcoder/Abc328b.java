package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;
import java.io.PrintWriter;

public class Abc328b {
  private final FastScanner sc;
  private final PrintWriter out;
  public Abc328b(){
    sc = new FastScanner();
    out = new PrintWriter(System.out);
  }

  public void solve(){
    final int n = sc.nextInt();
    final int[] d = new int[n];
    for (int i = 0; i < n; i++) {
      d[i] = sc.nextInt();
    }

    int ans = 0;
    for (int i = 1; i <= n; i++) {
      final String s = String.valueOf(i);
      char c = s.charAt(0);
      if (i < 10 || (i >= 10 && i < 100 && s.charAt(0) == s.charAt(1))){
        // nothing to do
      } else {
        continue;
      }
      final int di = d[i-1];
      for (int j = 1; j <= di; j++){
        final String t = String.valueOf(j);
        boolean ok = true;
        for (int k = 0; k < t.length(); k++) {
          if (t.charAt(k) != c) {
            ok = false;
            break;
          }
        }
        if (ok) {
          ans++;
        }
      }
    }
    out.println(ans);
    out.flush();
  }
}
