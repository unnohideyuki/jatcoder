package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class MathAndAlgoO {
    public void flush() {}
    public void solve(){
        Scanner sc = new Scanner(System.in);
        final int a = sc.nextInt();
        final int b = sc.nextInt();
        System.out.println(AC.gcd(a, b));
    }
}