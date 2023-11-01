package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class WTF22DAY1A {
  private final Scanner sc;
  public WTF22DAY1A(){
    sc = new Scanner(System.in);
  }

  public void solve(){
    final int n = sc.nextInt();
    final int m = sc.nextInt();
    int a = sc.nextInt();
    int b = sc.nextInt();

    // tagetCt[i]: モンスター i が標的になる回数
    final int[] targetCt = new int[n];
    for (int i = 0; i < m; i++){
      final int x = sc.nextInt() - 1;
      targetCt[x]++;
    }

    final ArrayList<Pair<Integer, Integer>> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      if (targetCt[i] > 0) {
        list.add(new Pair<>(targetCt[i], i));
      }
    }
    Collections.sort(list);

    // Solve
    int ans = n;

    if (a >= b) {
      for (final Pair<Integer, Integer> p : list) {
        final int ct = p.getFirst();
        final int i = p.getSecond();
        final int attack_ct = Math.min(ct, Math.min(a, b + 1));
        final int defence_ct = Math.min(ct, Math.min(b, attack_ct));
        a -= attack_ct;
        b -= defence_ct;
        if (attack_ct > defence_ct) ans--;
      }
    }

    // Answer
    System.out.println(ans);
  }
}
