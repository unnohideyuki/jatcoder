package jp.ne.sakura.uhideyuki.jatcoder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FenwickTreeTest {
    @Test
    void fwInt() {
        final FenwickTree<Integer> fw = FenwickTree.integerTree(10);
        final Integer[] v = new Integer[10];
        AC.iota(v, 0);
        for (int i = 0; i < 10; i++){
            fw.add(i, v[i]);
        }
        for (int l = 0; l <= 10; l++){
            for (int r = l; r <= 10; r++){
                Integer s = 0;
                for (int i = l; i < r; i++){
                    s += v[i];
                }
                assertEquals(s, fw.sum(l, r));
            }
        }
    }

    @Test
    void fwLong() {
        final FenwickTree<Long> fw = FenwickTree.longTree(10);
        final Long[] v = new Long[10];
        AC.iota(v, 0L);
        for (int i = 0; i < 10; i++){
            fw.add(i, v[i]);
        }
        for (int l = 0; l <= 10; l++){
            for (int r = l; r <= 10; r++){
                Long s = 0L;
                for (int i = l; i < r; i++){
                    s += v[i];
                }
                assertEquals(s, fw.sum(l, r));
            }
        }

    }
    @Test
    void fwModint() {
        final Modint.Builder modint = new Modint.Builder().setMod(13).setPrime(true);
        final FenwickTree<Modint> fw = FenwickTree.modintTree(10, modint);
        final Modint[] v = new Modint[10];
        for (int i = 0; i < 10; i++) v[i] = modint.setValue(i).build();
        for (int i = 0; i < 10; i++){
            fw.add(i, v[i]);
        }
        for (int l = 0; l <= 10; l++){
            for (int r = l; r <= 10; r++){
                Modint s = modint.setValue(0).build();
                for (int i = l; i < r; i++){
                    s = s.add(v[i]);
                }
                assertEquals(s.getValue(), fw.sum(l, r).getValue());
            }
        }
    }
}
