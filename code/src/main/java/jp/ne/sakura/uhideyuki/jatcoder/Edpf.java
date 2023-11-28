package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;
import java.io.PrintWriter;

public class Edpf {
  private final FastScanner sc;
  private final PrintWriter out;
  public Edpf(){
    sc = new FastScanner();
    out = new PrintWriter(System.out);
  }

  public void flush() {
    out.flush();
  }

  public void solve() {
    // Input
    final String s = sc.next();
    final String t = sc.next();
    final int n = s.length();
    final int m = t.length();
    // DP
    final int[][] dp = new int[n+1][m+1];
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        dp[i][j] = (s.charAt(i-1) == t.charAt(j-1)
                ? dp[i][j] = dp[i-1][j-1] + 1
                : Math.max(dp[i][j-1], dp[i-1][j]));
      }
    }
    // 逆にたどる
    int i = n;
    int j = m;
    final StringBuilder sb = new StringBuilder();
    while (dp[i][j] > 0) {
      if (dp[i][j] == dp[i-1][j]) {
        i--;
      } else if (dp[i][j] == dp[i][j-1]) {
        j--;
      } else {
        assert s.charAt(i-1) == t.charAt(j-1);
        sb.append(s.charAt(i-1));
        i--;
        j--;
      }
    }
    sb.reverse();
    out.println(sb);
  }
}
