package jp.ne.sakura.uhideyuki.jatcoder;

import java.util.*;

public class Practice2A {
    public void flush() {}
    public void solve(){
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int Q = sc.nextInt();

        UnionFind uf = new UnionFind(N);

        for (int i = 0; i < Q; i++){
            int t = sc.nextInt();
            int u = sc.nextInt();
            int v = sc.nextInt();
            if (t == 0){
                uf.merge(u, v);
            } else {
                System.out.println(uf.same(u, v) ? 1 : 0);
            }
        }
    }
}
