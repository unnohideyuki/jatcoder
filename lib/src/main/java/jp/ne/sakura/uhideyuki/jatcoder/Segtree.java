package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.ArrayList;
import java.util.List;
public class Segtree<T> {
    private final BiFunction<T, T, T> op;
    private final T e;
    private final int n;
    private final int log;
    private final int size;


    private final ArrayList<T> d;

    public Segtree(final BiFunction<T, T, T> op,
                   final T e,
                   final int n){
        this.op = op;
        this.e = e;
        this.n = n;
        int x = 1;
        while ((1 << x) < n) {
            x++;
        }
        log = x;
        size = (1 << log);
        d = new ArrayList<>(2 * size);
        for (int i = 0; i < 2 * size; i++) {
            d.add(e);
        }
        for (int i = size - 1; i >= 1; i--) {
            update(i);
        }
    }

    public Segtree(final BiFunction<T, T, T> op,
                   final T e,
                   final List<T> list) {
        this(op, e, list.size());
        for (int i = 0; i < n; i++) {
            d.set(size + i, list.get(i));
        }
        for (int i = size - 1; i >= 1; i--) {
            update(i);
        }
    }

    private void update(final int k) {
        d.set(k, op.apply(d.get(2 * k), d.get(2 * k + 1)));
    }

    public void set(int p, final T x) {
        assert (0 <= p) && (p < n);
        p += size;
        d.set(p, x);
        for (int i = 1; i <= log; i++) {
            update(p >> i);
        }
    }

    public T get(final int p) {
        assert (0 <= p) && (p < n);
        return d.get(p + size);
    }

    public T prod(int l, int r) {
        assert (0 <= l) && (l <= r) && (r <= n);
        T sml = e;
        T smr = e;
        l += size;
        r += size;
        while (l < r) {
            if ((l & 1) == 1) { sml = op.apply(sml, d.get(l++)); }
            if ((r & 1) == 1) { smr = op.apply(d.get(--r), smr); }
            l >>= 1;
            r >>= 1;
        }
        return op.apply(sml, smr);
    }

    public T allProd() { return d.get(1); }

    public int maxRight(int l, final Function<T, Boolean> f) {
        assert (0 <= l) && (l <= n);
        assert f.apply(e);
        if (l == n) { return n; }
        l += size;
        T sm = e;
        do {
            while (l % 2 == 0) { l >>= 1; }
            if (!f.apply(op.apply(sm, d.get(l)))) {
                while (l < size) {
                    l = (2 * l);
                    if (f.apply(op.apply(sm, d.get(l)))) {
                        sm = op.apply(sm, d.get(l));
                        l++;
                    }
                }
                return l - size;
            }
            sm = op.apply(sm, d.get(l));
            l++;
        } while ((l & -l) != l);
        return n;
    }

    public int minLeft(int r, final Function<T, Boolean> f) {
        assert (0 <= r) && (r <= n);
        assert f.apply(e);
        if (r == 0) { return 0; }
        r += size;
        T sm = e;
        do {
            r--;
            while ((r > 1) && (r % 2 == 1)) { r >>= 1; }
            if(!f.apply(op.apply(d.get(r), sm))) {
                while (r < size) {
                    r = 2 * r + 1;
                    if(f.apply(op.apply(d.get(r), sm))) {
                        sm = op.apply(d.get(r), sm);
                        r--;
                    }
                }
                return r + 1 - size;
            }
            sm = op.apply(d.get(r), sm);
        } while ((r & -r) != r);
        return 0;
    }
}
