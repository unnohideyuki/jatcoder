package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class ARC125B {
  private final Scanner sc;
  public ARC125B(){
    sc = new Scanner(System.in);
  }
  public void flush() {}

  public void solve(){
    final long n = sc.nextLong();
    final Modint.Builder modint = new Modint.Builder().setMod(998244353);
    final Modint ans = modint.setValue(0).build();
  }
}
