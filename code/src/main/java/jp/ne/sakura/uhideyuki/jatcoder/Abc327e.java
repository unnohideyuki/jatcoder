package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class Abc327e {
  private final Scanner sc;
  private int n;
  private int[] p;
  public Abc327e(){
    sc = new Scanner(System.in);
  }

  private double solvedp(){
    final double[] den = new double[n+1];
    final double[] x = new double[n+1];
    x[0] = x[1] = 1.0;

    den[0] = den[1] = 1.0;
    for (int i = 2; i <= n; i++) {
      x[i] = x[i-1] * 0.9;
      den[i] = den[i-1] + x[i];
    }

    final double[][] dp = new double[n+1][n+1];
    for (int i = 0; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        dp[i][j] = -(1e9);
      }
    }
    dp[0][0] = 0;

    for (int i = 0; i < n; i++) {
      int r = p[n - 1 - i]; //  後ろから見ていく
      for (int j = 0; j <= i; j++) {
        // 注目しているコンテストを選ばない
        dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j]);
        // 選ぶ
        final double newv = (dp[i][j] * den[j] + r * x[j+1]) / den[j+1];
        dp[i+1][j+1] = Math.max(dp[i+1][j+1], newv);
      }
    }

    double ans = -(1e9);
    for (int k = 1; k <= n; k++) {
      double rate = dp[n][k] - (1200/Math.sqrt((double)k));
      ans = Math.max(ans, rate);
    }
    return ans;
  }

  public void solve(){
    n = sc.nextInt();
    p = new int[n];
    for (int i = 0; i < n; i++) { p[i] = sc.nextInt(); }
    double ans = solvedp();
    System.out.println(String.format("%.12f", ans));
  }
}
