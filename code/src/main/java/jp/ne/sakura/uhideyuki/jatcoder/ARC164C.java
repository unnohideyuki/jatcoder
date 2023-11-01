package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class ARC164C {
  private final Scanner sc;
  public ARC164C(){
    sc = new Scanner(System.in);
  }

  public void solve(){
    final int n = sc.nextInt();
    final long[] a = new long[n];
    final long[] b = new long[n];
    for (int i = 0; i < n; i++) {
      a[i] = sc.nextInt();
      b[i] = sc.nextInt();
    }

    //  裏返した、取った記録（冗長、バグ検出用途）
    final boolean[] reversed = new boolean[n];
    final boolean[] removed = new boolean[n];

    // まだ裏返っていないもの　a - b の大きい順
    final PriorityQueue<Pair<Long, Integer>> que1 = new PriorityQueue<>(Collections.reverseOrder());
    for (int i = 0; i < n; i++) {
      que1.add(new Pair<Long, Integer>(a[i] - b[i], i));
    }

    // 裏返したもの b - a の大きい順
    final PriorityQueue<Pair<Long, Integer>> que2 = new PriorityQueue<>(Collections.reverseOrder());

    final long INF = (1L << 60);
    final Pair<Long, Integer> minp = new Pair<>(-INF, -1);
    long ans = 0;
    for (int i = 0; i < n; i++){
      // Alice のターン
      long diff1 =  (que1.peek() != null ? que1.peek() : minp).getFirst();
      long diff2 =  (que2.peek() != null ? que2.peek() : minp).getFirst();
      if (diff1 > diff2) {
        final var p = que1.poll();
        final long d = p.getFirst();
        final int j = p.getSecond();
        assert !reversed[j];
        assert !removed[j];
        assert d == a[j] - b[j];
        que2.add(new Pair<Long, Integer>(-d, j));
        reversed[j] = true;
      } else {
        final var p = que2.poll();
        final long d = p.getFirst();
        final int j = p.getSecond();
        assert reversed[j];
        assert !removed[j];
        assert d == b[j] - a[j];
        que1.add(new Pair<Long, Integer>(-d, j));
        reversed[j] = false;
      }

      // Bob のターン
      diff1 =  (que1.peek() != null ? que1.peek() : minp).getFirst();
      diff2 =  (que2.peek() != null ? que2.peek() : minp).getFirst();
      if (diff1 > diff2) {
        final var p = que1.poll();
        final long d = p.getFirst();
        final int j = p.getSecond();
        assert !reversed[j];
        assert !removed[j];
        assert d == a[j] - b[j];
        ans += a[j];
        removed[j] = true;
      } else {
        final var p = que2.poll();
        final long d = p.getFirst();
        final int j = p.getSecond();
        assert reversed[j];
        assert !removed[j];
        assert d == b[j] - a[j];
        ans += b[j];
        removed[j] = true;
      }
    }

    System.out.println(ans);
  }
}
