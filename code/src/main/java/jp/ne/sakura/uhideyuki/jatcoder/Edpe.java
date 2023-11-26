package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;
import java.io.PrintWriter;

public class Edpe {
  private final FastScanner sc;
  private final PrintWriter out;
  public Edpe(){
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
    // dp[i][j] : i 番目の荷物まで調べたうちで合計価値 j となる重さの最小値
    final long INF = (1L << 60);
    final long[][] dp = new long[N+1][100005];
    for (int i = 0; i <= N; i++) {
      for (int j = 0; j < 100005; j++) {
        dp[i][j] = INF;
      }
    }
    dp[0][0] = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < 100005; j++) {
        if (dp[i][j] == INF) { continue; }
        // i 番目の荷物を入れない場合
        dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]);
        // i 番目の荷物を入れる場合
        dp[i+1][j+v[i]] = Math.min(dp[i+1][j+v[i]], dp[i][j] + w[i]);
      }
    }
    // Answer
    for (int i = 100000; i >= 0; i--) {
      if (dp[N][i] <= W) {
        out.println(i);
        break;
      }
    }
  }
}
