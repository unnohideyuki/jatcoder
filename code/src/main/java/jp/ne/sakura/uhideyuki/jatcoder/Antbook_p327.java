package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class Antbook_p327 {
  private final Scanner sc;
  public Antbook_p327(){
    sc = new Scanner(System.in);
  }

  public void solve(){
    // 入力
    final int n = sc.nextInt();
    final int k = sc.nextInt();
    final String s = sc.next();

    // 前処理
    final String agct = "AGCT";
    final int[][] next = new int[k][4]; // 一文字加えた際に移動する先の状態
    for (int i = 0; i < k; i++) {
      for (int j = 0; j < 4; j++) {
        // 一致している i 文字に 1 文字加えた文字列
        String t = s.substring(0, i) + agct.charAt(j);
        // s の先頭に一致するまで先頭から 1 文字削るkezuru
        while (!s.substring(0, t.length()).equals(t)) {
          t = t.substring(1);
        }
        next[i][j] = t.length();
      }
    }

    // DP
    final int MOD = 10009;
    final int[][] dp = new int[n+1][k];
    dp[0][0] = 1;
    for (int i = 1; i < k; i++) { dp[0][i] = 0; }
    for (int t = 0; t < n; t++) {
      for (int i = 0; i < k; i++) { dp[t+1][i] = 0; }
      for (int i = 0; i < k; i++) {
        for (int j = 0; j < 4; j++) {
          final int ti = next[i][j];
          if (ti == k) { continue; } // s が出現してしまうのでダメ
          dp[t+1][ti] = (dp[t+1][ti] + dp[t][i]) % MOD;
        }
      }
    }

    int ans = 0;
    for (int i = 0; i < k; i++) {
      ans = (ans + dp[n][i]) % MOD;
    }
    System.out.println(ans);
  }
}
