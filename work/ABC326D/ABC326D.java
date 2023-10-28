package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class ABC326D {
    private static int N;
    private static String R, C;

    private static String s;
    private static char[][] data = new char[5][5];
    private static ArrayList<ArrayList<Integer>> v;

    public static void solve() {
        // Input
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.next();
        C = sc.next();

        // Check recursively
        s = "ABC";
        for (int i = 3; i < N; i++){
            s += '.';
        }
        v = new ArrayList<>(N);
        for (int i = 0; i < N; i++){
            v.add(new ArrayList<>(N));
            for (int j = 0; j < N; j++){
                v.get(i).add(Integer.valueOf(0));
            }
        }
        AC.iota(v.get(0), Integer.valueOf(0), N);
        checkRec(0);
        System.out.println("No");
    }

    private static void checkRec(int i){
        do {
            boolean first = true;
            for (int j = 0; j < N; j++){
                char c = s.charAt(v.get(i).get(j));
                if (c != '.'){
                    if (first && c != R.charAt(i)){
                        continue;
                    }
                    first = false;
                }
                data[i][j] = c;
            }
            if (i < N - 1){
                AC.iota(v.get(i+1), Integer.valueOf(0), N);
                checkRec(i+1);
            } else {
                for (int j = 0; j < N; j++){
                    int k = 0;
                    while (data[k][j] == '.'){
                        k++;
                    }
                    if (data[k][j] != C.charAt(j)){
                        return;
                    }
                }
                // solved!
                for (int y = 0; y < N; y++){
                    String a = "";
                    for (int x = 0; x < N; x++){
                        a += data[y][x];
                    }
                    System.out.println(a);
                }
                System.exit(0);
            }
        } while (AC.nextPermutation(v.get(i)));
    }
}