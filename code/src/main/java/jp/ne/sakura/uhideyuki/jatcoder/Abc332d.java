package jp.ne.sakura.uhideyuki.jatcoder;

import java.util.*;
import java.io.PrintWriter;

public class Abc332d {
  private final FastScanner sc;
  private final PrintWriter out;
  public Abc332d(){
    sc = new FastScanner();
    out = new PrintWriter(System.out);
  }

  public void flush() {
    out.flush();
  }

  private int inversion(final List<Integer> lst) {
    final int n = lst.size();
    FenwickTree<Integer> fw = FenwickTree.integerTree(n);
    int inv = 0;
    for (int x : lst) {
      inv += fw.sum(x+1, n);
      fw.add(x, 1);
    }
    return inv;
  }

  public void solve() {
    final int H = sc.nextInt();
    final int W = sc.nextInt();
    final int[][] A = new int[H][W];
    final int[][] B = new int[H][W];
    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        A[i][j] = sc.nextInt();
      }
    }
    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        B[i][j] = sc.nextInt();
      }
    }

    final int inf = 1 << 30;
    int ans = inf;

    final ArrayList<Integer> rows = new ArrayList<>(H);
    final ArrayList<Integer> cols = new ArrayList<>(W);
    
    AC.iota(rows, 0, H);
    do {
      AC.iota(cols, 0, W);
      do {
        boolean ok = true;
        for (int i = 0; i < H; i++) {
          for (int j = 0; j < W; j++) {
            if (A[rows.get(i)][cols.get(j)] != B[i][j]) {
              ok = false;
              break;
            }
          }
        }
        if (ok) {
          ans = Math.min(ans,
                  inversion(rows) + inversion(cols)
                  );
        }
      } while (AC.nextPermutation(cols));
    } while (AC.nextPermutation(rows));

    if (ans == inf) {
      ans = -1;
    }
    out.println(ans);
  }
}
