package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class ABC323E {
    public void flush() {}
    public void solve(){
        // Input
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int X = sc.nextInt();
        final int[] t = new int[N];
        for (int i = 0; i < N; i++) t[i] = sc.nextInt();

        //
        // Solve
        //
        Modint.Builder modint = new Modint.Builder(998244353);
        // dp[i]: i 秒後に次の曲が始まる確率
        final Modint[] dp = new Modint[X+1];
        for (int i = 1; i <= X; i++) dp[i] = modint.build(0);
        dp[0] = modint.build(1);

        // DP
        final Modint invN = modint.build(N).inv(); // 1/N
        for (int tm = 0; tm <= X; tm++){
            for (int i = 0; i < N; i++){
                if (tm + t[i] <= X){
                    dp[tm + t[i]] = dp[tm + t[i]].add(dp[tm].mul(invN));
                }
            }
        }

        // Answer
        Modint ans = modint.build(0);
        for (int i = 0; i < t[0]; i++){
            if (X - i < 0) break;
            ans = ans.add(dp[X - i].mul(invN));
        }
        System.out.println(ans.getValue());
    }
}
