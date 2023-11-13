package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;
import java.io.PrintWriter;

public class Edpc {
  private final FastScanner sc;
  private final PrintWriter out;
  public Edpc(){
    sc = new FastScanner();
    out = new PrintWriter(System.out);
  }
  public void flush() {}

  public void solve(){
    // Input
    final int n = sc.nextInt();
    final int[] a = new int[n];
    final int[] b = new int[n];
    final int[] c = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = sc.nextInt();
      b[i] = sc.nextInt();
      c[i] = sc.nextInt();
    }

    // DP
    // d[i][j]: i 日めに j を選んだ時の総和の最大値
    final long[][] dp = new long[n+1][3];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < 3; j++) {
        if (j != 0) {
          dp[i + 1][0] = Math.max(dp[i + 1][0], dp[i][j] + a[i]);
        }
        if (j != 1) {
          dp[i + 1][1] = Math.max(dp[i + 1][1], dp[i][j] + b[i]);
        }
        if (j != 2) {
          dp[i + 1][2] = Math.max(dp[i + 1][2], dp[i][j] + c[i]);
        }
      }
    }

    // Answer
    long ans = 0;
    for (int i = 0; i < 3; i++) {
      ans = Math.max(ans, dp[n][i]);
    }
    out.println(ans);
    out.flush();
  }
}
