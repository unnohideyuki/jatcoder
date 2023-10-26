package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;
public class Practice2B {
    public static void solve(){
		final Scanner sc = new Scanner(System.in);
		final int N = sc.nextInt();
		final int Q = sc.nextInt();
		FenwickTree<Long> fw = FenwickTree.longTree(N);
		for (int i = 0; i < N; i++){
			Long ai = sc.nextLong();
			fw.add(i, ai);
		}

		for (int i = 0; i < Q; i++) {
			int ty = sc.nextInt();
			switch(ty) {
				case 0 -> {
					int p = sc.nextInt();
					Long x = sc.nextLong();
					fw.add(p, x);
				}
				case 1 -> {
					int l = sc.nextInt();
					int r = sc.nextInt();
					Long ans = fw.sum(l, r);
					System.out.println(ans);
				}
			}
		}
	}
}
