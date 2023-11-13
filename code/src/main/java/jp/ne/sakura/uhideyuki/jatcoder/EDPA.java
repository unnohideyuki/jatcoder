package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class EDPA {
  private final Scanner sc;
  public EDPA(){
    sc = new Scanner(System.in);
  }
  public void flush() {}

  public void solve(){
    final int n = sc.nextInt();
    final int[] h = new int[n];
    for (int i = 0; i < n; i++) {
      h[i] = sc.nextInt();
    }

    final long[] dp = new long[n];
    final long INF = (1L << 60);
    for (int i = 0; i < n; i++) { dp[i] = INF; }
    dp[0] = 0L;

    for (int i = 0; i < n - 1; i++) {
      dp[i+1] = Math.min(dp[i+1], dp[i] + Math.abs(h[i+1] - h[i]));
      if (i < n - 2) {
        dp[i+2] = Math.min(dp[i+2], dp[i] + Math.abs(h[i+2] - h[i]));
      }
    }

    System.out.println(dp[n-1]);
  }
}
