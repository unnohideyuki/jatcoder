package jp.ne.sakura.uhideyuki.jatcoder;

import java.util.ArrayList;
import java.util.List;

public class Str {
    public static int[] zAlgorithm(final String s) {
        final int n = s.length();
        final ArrayList<Character> s2 = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            s2.add(s.charAt(i));
        }
        return zAlgorithm(s2);
    }
    public static <T extends Comparable<? super T>> int[] zAlgorithm(final List<T> s) {
        final int n = s.size();
        if (n == 0) {
            return new int[0];
        }
        final int[] z = new int[n];
        z[0] = 0;
        for (int i = 1, j = 0; i < n; i++) {
            z[i] = (j + z[j] <= i) ? 0 : Math.min(j + z[j] - i, z[i - j]);
            while (i + z[i] < n && s.get(z[i]).equals(s.get(i + z[i]))) { z[i]++; }
            if (j + z[j] < i + z[i]) { j = i; }
        }
        z[0] = n;
        return z;
    }
}
