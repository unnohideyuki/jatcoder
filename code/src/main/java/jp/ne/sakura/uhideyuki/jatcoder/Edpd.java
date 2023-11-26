package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;
import java.io.PrintWriter;

public class Edpd {
  private final FastScanner sc;
  private final PrintWriter out;
  public Edpd(){
    sc = new FastScanner();
    out = new PrintWriter(System.out);
  }

  public void flush() {
    out.flush();
  }

  public void solve() {
    // Input
    final int N = sc.nextInt();
    final int W = sc.nextInt();
    final int[] w = new int[N];
    final int[] v = new int[N];
    for (int i = 0; i < N; i++) {
      w[i] = sc.nextInt();
      v[i] = sc.nextInt();
    }
    // DP
    final long[][] dp = new long[N+1][W+1];
    for (int i = 0; i <= N; i++) {
      for (int j = 0; j <= W; j++) {
        dp[i][j] = -1;
      }
    }
    dp[0][0] = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j <= W; j++) {
        if (dp[i][j] < 0) { continue; }
        // i 番目の荷物をナップサックに入れない場合
        dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j]);
        // i  番目の荷物をナップサックに入れる場合
        if (j + w[i] <= W) {
          dp[i+1][j + w[i]] = Math.max(dp[i+1][j + w[i]], dp[i][j] + v[i]);
        }
      }
    }
    // Answer
    long ans = 0;
    for (int i = 0; i <= W; i++) {
      ans = Math.max(ans, dp[N][i]);
    }
    out.println(ans);
  }
}
