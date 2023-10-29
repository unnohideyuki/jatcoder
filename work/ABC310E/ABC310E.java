package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class ABC310E {
  private final Scanner sc;
  public ABC310E(){
    sc = new Scanner(System.in);
  }

  public void solve(){
    final int n = sc.nextInt();
    final String s = sc.next();

    long ans = 0;
    // dp[i][0] : {..., a[i-1], a[i-1]} のうち NAND の結果が 0 になるものの個数
    // dp[i][1] : {..., a[i-1], a[i-1]} のうち NAND の結果が 1 になるものの個数
    final long[][] dp = new long[2][2];

    for (int i = 0; i < n; i++){
      final int j = (i & 1);
      final int nj = ((i + 1) & 1);

      if (s.charAt(i) == '0') {
        // Ai == 0
        dp[nj][0] = 1;
        // NAND(0, 1), NAND(0, 0) ともに 1
        dp[nj][1] = dp[j][0] + dp[j][1];
      } else {
        // NAND(1, 1) == 0
        dp[nj][0] = dp[j][1];
        // Ai と NAND(1, 0) が 1
        dp[nj][1] = 1 + dp[j][0];
      }

      ans += dp[nj][1];
    }

    System.out.println(ans);
  }
}
