package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;
import java.util.function.*;

public class FenwickTree<T> {
    final private int size;
    final private T[] data;
    final private BiFunction<T, T, T> fadd;
    final private BiFunction<T, T, T> fsub;
    final private T zero;

    public FenwickTree(final int size,
                       final T[] data,
                       final BiFunction<T, T, T> fadd,
                       final BiFunction<T, T, T> fsub,
                       final T zero) {
        assert size > 0;
        assert data.length == size;
        this.size = size;
        this.data = data;
        this.fadd = fadd;
        this.fsub = fsub;
        this.zero = zero;
        assert data[0].getClass().equals(zero.getClass());
    }

    public static FenwickTree<Integer> integerTree(final int size){
        final Integer[] data = new Integer[size];
        for (int i = 0; i < size; i++) data[i] = Integer.valueOf(0);
        BiFunction<Integer, Integer, Integer> fadd =
                (a, b) -> {
                    return a + b;
                };
        BiFunction<Integer, Integer, Integer> fsub =
                (a, b) -> {
                    return a - b;
                };
        final Integer zero = Integer.valueOf(0);
        return new FenwickTree<Integer>(size, data, fadd, fsub, zero);
    }

    public static FenwickTree<Long> longTree(final int size){
        final Long[] data = new Long[size];
        for (int i = 0; i < size; i++) data[i] = Long.valueOf(0L);
        BiFunction<Long, Long, Long> fadd =
                (a, b) -> {
                    return a + b;
                };
        BiFunction<Long, Long, Long> fsub =
                (a, b) -> {
                    return a - b;
                };
        final Long zero = Long.valueOf(0L);
        return new FenwickTree<Long>(size, data, fadd, fsub, zero);
    }

    public static FenwickTree<Modint> modintTree(final int size, final Modint.Builder modint){
        final Modint[] data = new Modint[size];
        for (int i = 0; i < size; i++) data[i] = modint.build(0);
        BiFunction<Modint, Modint, Modint> fadd =
                (a, b) -> {
                    return a.add(b);
                };
        BiFunction<Modint, Modint, Modint> fsub =
                (a, b) -> {
                    return a.sub(b);
                };
        final Modint zero = modint.build(0);
        return new FenwickTree<Modint>(size, data, fadd, fsub, zero);
    }

    private T sum(int r) {
        T s = zero;
        while (r > 0) {
            s = fadd.apply(s, data[r-1]);
            r -= (r & (-r));
        }
        return s;
    }

    public void add(int p, T x) {
        assert zero.getClass().equals(x.getClass());
        assert (0 <= p && p < size);
        p++;
        while (p <= size) {
            data[p - 1] = fadd.apply(data[p - 1], x);
            p += p & -p;
        }
    }

    public T sum(int l, int r) {
        assert (0 <= l && l <= r && r <= size);
        return (T) fsub.apply(sum(r), sum(l));
    }
}
