package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class ABC317D {
    public static void solve() {
        // Input
        final Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int[] x = new int[N];
        final int[] y = new int[N];
        final int[] z = new int[N];
        int sumz = 0;
        for (int i = 0; i < N; i++){
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
            z[i] = sc.nextInt();
            sumz += z[i];
        }

        // Solve
        final long INF = (1L << 60);
        final long[][] dp = new long[2][100005];
        for (int i = 0; i < 100005; i++){
            dp[0][i] = dp[1][i] = INF;
        }

        dp[0][0] = 0;

        int maxCount = 0;
        long ans = INF;
        int th = (sumz + 1) / 2;
        for (int i = 0; i < N; i++){
            final int j = i & 1;
            final int nj = (j + 1) & 1;
             for (int k = 0; k <= maxCount; k++){
                if (dp[j][k] == INF) continue;
                if (x[i] > y[i]){ //  高橋派が初めから勝っている
                    // 追加の鞍替えなしで z[i] 議席獲得
                    dp[nj][k + z[i]] = Math.min(dp[nj][k + z[i]], dp[j][k]);
                    if (k + z[i] >= th){
                        ans = Math.min(ans, dp[nj][k + z[i]]);
                    }
                } else {
                    // 鞍替えさせない場合
                    dp[nj][k] = Math.min(dp[nj][k], dp[j][k]);
                    if (k >= th){
                        ans = Math.min(ans, dp[nj][k]);
                    }
                    // 鞍替えさせる場合
                    final int d = (x[i] + y[i] + 1) / 2 - x[i];
                    dp[nj][k + z[i]] = Math.min(dp[nj][k + z[i]], dp[j][k] + d);
                    if (k + z[i] >= th){
                        ans = Math.min(ans, dp[nj][k + z[i]]);
                    }
                }
                dp[j][k] = INF; //  使い終わったら初期化
            }
            maxCount += z[i];
        }

        System.out.println(ans);
    }
}