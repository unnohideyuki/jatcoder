package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class Abc327b {
  private final Scanner sc;
  public Abc327b(){
    sc = new Scanner(System.in);
  }

  public void solve(){
    final long b = sc.nextLong();

    long powA = 1;
    for (long a = 1; powA <= b; a++) {
      powA = pow(a, a);
      if (powA == b) {
        System.out.println(a);
        return;
      }
    }
    System.out.println(-1);
  }

  private long pow(long x, long n) {
    long a = 1;
    while (n > 0) {
      if ((n & 1) != 0) a = a * x;
      x = x * x;
      n >>= 1;
    }
    return a;
  }
}
