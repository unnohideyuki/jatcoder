package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;
import java.io.PrintWriter;

public class Abc219d {
  private final FastScanner sc;
  private final PrintWriter out;
  public Abc219d(){
    sc = new FastScanner();
    out = new PrintWriter(System.out);
  }

  public void flush() {
    out.flush();
  }

  public void solve() {
    // Input
    final int n = sc.nextInt();
    final int x = sc.nextInt();
    final int y = sc.nextInt();
    final int[] a = new int[n];
    final int[] b = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = sc.nextInt();
      b[i] = sc.nextInt();
    }

    // DP
    // dp[i][j] = たこ焼き i 個、たい焼き j 個入手するのに必要な弁当の個数最小値
    final int INF = (1 << 30);
    final int[][][] dp = new int[2][301][301];
    for (int i = 0; i <= 300; i++) {
      for (int j = 0; j <= 300; j++) {
        dp[0][i][j] = INF;
        dp[1][i][j] = INF;
      }
    }
    dp[0][0][0] = 0;

    int xmax = 0;
    int ymax = 0;
    for (int i = 0; i < n; i++) {
      final int ni = (i + 1) & 1;
      for (int j = 0; j <= xmax; j++){
        for (int k= 0; k <= ymax; k++) {
          dp[ni][j][k] = INF;
        }
      }

      for (int j = 0; j <= xmax; j++) {
        for (int k = 0; k <= ymax; k++) {
          if (dp[i&1][j][k] == INF) { continue; }
          // i 番目のお弁当を買わない場合
          dp[ni][j][k] = Math.min(dp[ni][j][k], dp[i&1][j][k]);
          // i 番目のお弁当を買う場合
          int nj = Math.min(300, j + a[i]);
          int nk = Math.min(300, k + b[i]);
          xmax = Math.max(xmax, nj);
          ymax = Math.max(ymax, nk);
          dp[ni][nj][nk] = Math.min(dp[ni][nj][nk], dp[i&1][j][k] + 1);
        }
      }
    }

    // Answer
    int ans = INF;
    for (int i = x; i <= 300; i++) {
      for (int j = y; j <= 300; j++) {
        ans = Math.min(ans, dp[n&1][i][j]);
      }
    }
    if (ans == INF) {
      ans = -1;
    }
    out.println(ans);
  }
}
