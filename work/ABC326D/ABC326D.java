package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class ABC326D {
  private final Scanner sc;
  private int n;
  private String r, c;

  private ArrayList<ArrayList<String>> row;
  private ArrayList<String> grid;
  private boolean fnd = false;
  public ABC326D(){
    sc = new Scanner(System.in);

    row = new ArrayList<>(3);
    for (int i = 0; i < 3; i++)
      row.add(new ArrayList<>());

    grid = new ArrayList<>();
  }

  public void solve(){
    n = sc.nextInt();
    r = sc.next();
    c = sc.next();

    final char[] tmp = new char[n];
    tmp[0] = 'A';
    tmp[1] = 'B';
    tmp[2] = 'C';
    for (int i = 3; i < n; i++) tmp[i] = '.';
    Arrays.sort(tmp);
    String abc = new String(tmp);

    // 各行の文字列候補を一番左にくるのが A, B, C
    // どれかによって分類して列挙する
    do {
      for (int i = 0; i < n; i++){
        if (abc.charAt(i) != '.'){
          int idx = abc.charAt(i) - 'A';
          row.get(idx).add(new String(abc));
          break;
        }
      }
    } while ((abc = AC.nextPermutation(abc)) != null);

    dfs(0, (1 << (4*n)) - 1);
    if (!fnd) { System.out.println("No"); }
  }

  private void dfs(final int i, final int fl) {
    if (fnd) { return; }
    if (i == n) {
      if (fl == 0) {
        System.out.println("Yes");
        for (String s : grid) System.out.println(s);
        fnd = true;
      }
      return;
    }

    for (String ns : row.get(r.charAt(i) - 'A')) {
      grid.add(ns); // pushBack

      int ufl = fl;
      boolean und = true;
      for (int j = 0; j < n; j++) {
        if (ns.charAt(j) == '.') { continue; }
        final int kind = (ns.charAt(j) - 'A');
        if ((fl & (1 << (4 * j + kind))) == 0) { und = false; break; }
        ufl ^= (1 << (4 * j + kind));
        if ((fl & (1 << (4 * j + 3))) != 0) {
          if (ns.charAt(j) != c.charAt(j)) { und = false; break; }
          ufl ^= (1 << (4 * j + 3));
        }
      }

      if (und) { dfs(i+1, ufl); }
      grid.remove(grid.size() - 1); // popBack
    }
  }
}
