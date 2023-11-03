package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class Edpb {
  final Scanner sc;
  public Edpb(){
    sc = new Scanner(System.in);
  }

  public void solve(){
    final int n = sc.nextInt();
    final int k = sc.nextInt();
    final long[] h = new long[n];
    for (int i = 0; i < n; i++) {
      h[i] = sc.nextInt();
    }

    final long[] dp = new long[n];
    final long INF = (1L << 60);
    for (int i = 0; i < n; i++) {
      dp[i] = INF;
    }
    dp[0] = 0;

    for (int i = 0; i < n; i++){
      for (int j = 1; j <= k && i + j < n; j++) {
        dp[i + j] = Math.min(dp[i + j], dp[i] + Math.abs(h[i + j] - h[i]));
      }
    }

    System.out.println(dp[n-1]);
  }
}
