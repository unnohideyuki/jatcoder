package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.Arrays;
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
        Arrays.fill(data, 0);
        final BiFunction<Integer, Integer, Integer> fadd =
                Integer::sum;
        final BiFunction<Integer, Integer, Integer> fsub =
                (a, b) -> a - b;
        final Integer zero = 0;
        return new FenwickTree<>(size, data, fadd, fsub, zero);
    }

    public static FenwickTree<Long> longTree(final int size){
        final Long[] data = new Long[size];
        Arrays.fill(data, 0L);
        final BiFunction<Long, Long, Long> fadd =
                Long::sum;
        final BiFunction<Long, Long, Long> fsub =
                (a, b) -> a - b;
        final Long zero = 0L;
        return new FenwickTree<>(size, data, fadd, fsub, zero);
    }

    public static FenwickTree<Modint> modintTree(final int size, final Modint.Builder modint){
        final Modint[] data = new Modint[size];
        for (int i = 0; i < size; i++) data[i] = modint.setValue(0).build();
        final BiFunction<Modint, Modint, Modint> fadd =
                Modint::add;
        final BiFunction<Modint, Modint, Modint> fsub =
                Modint::sub;
        final Modint zero = modint.setValue(0).build();
        return new FenwickTree<>(size, data, fadd, fsub, zero);
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
        return fsub.apply(sum(r), sum(l));
    }
}
