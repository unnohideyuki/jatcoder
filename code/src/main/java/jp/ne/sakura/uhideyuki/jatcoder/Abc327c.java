package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class Abc327c {
  private final Scanner sc;

  public Abc327c(){
    sc = new Scanner(System.in);
  }
  public void flush() {}

  public void solve(){
    final int[][] a = new int[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        a[i][j] = sc.nextInt();
      }
    }

    final int[][] row = new int[9][9];
    final int[][] col = new int[9][9];
    final int[][] blk = new int[9][9];

    // row check
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        row[i][a[i][j] - 1]++;
        if (row[i][a[i][j] - 1] > 1) {
          System.out.println("No");
          return;
        }
      }
    }

    // col check
    for (int j = 0; j < 9; j++) {
      for (int i = 0; i < 9; i++) {
        col[j][a[i][j] - 1]++;
        if (col[j][a[i][j] - 1] > 1) {
          System.out.println("No");
          return;
        }
      }
    }

    // blk check
    for (int k = 0; k < 9; k++) {
      int basei = (k / 3) * 3;
      int basej = (k % 3) * 3;
      for (int i = basei; i < basei + 3; i++) {
        for (int j = basej; j < basej + 3; j++) {
          blk[k][a[i][j] - 1]++;
          if (blk[k][a[i][j] - 1] > 1) {
            System.out.println("No");
            return;
          }
        }
      }
    }

    System.out.println("Yes");
  }
}
