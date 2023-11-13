package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class Abc327a {
  private final Scanner sc;
  public Abc327a(){
    sc = new Scanner(System.in);
  }
  public void flush() {}
  public void solve(){
    final int n = sc.nextInt();
    final String s = sc.next();
    for (int i = 0; i < s.length() - 1; i++){
      final char c0 = s.charAt(i);
      final char c1 = s.charAt(i+1);
      if ((c0 == 'a' && c1 == 'b') || (c0 == 'b' && c1 == 'a')) {
        System.out.println("Yes");
        return;
      }
    }

    System.out.println("No");
  }
}
