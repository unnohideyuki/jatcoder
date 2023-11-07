package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LazySegtree<S, F> {
    private final int n;
    private final int size;
    private final int log;
    private final BiFunction<S, S, S> op;
    private final S e;
    private final BiFunction<F, S, S> mapping;
    private final BiFunction<F, F, F> composition;
    private final F id;
    private final ArrayList<S> d;
    private final ArrayList<F> lz;

    public LazySegtree(final BiFunction<S, S, S> op,
                       final S e,
                       final BiFunction<F, S, S> mapping,
                       final BiFunction<F, F, F> composition,
                       final F id,
                       final int n){
        this.op = op;
        this.e = e;
        this.mapping = mapping;
        this.composition = composition;
        this.id = id;
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
        lz = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            lz.add(id);
        }
        for (int i = size - 1; i >= 1; i--) {
            update(i);
        }
    }

    public LazySegtree(final BiFunction<S, S, S> op,
                       final S e,
                       final BiFunction<F, S, S> mapping,
                       final BiFunction<F, F, F> composition,
                       final F id,
                       final List<S> list) {
        this(op, e, mapping, composition, id, list.size());
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

    private void allApply(final int k, final F f){
        d.set(k, mapping.apply(f, d.get(k)));
        if (k < size) {
            lz.set(k, composition.apply(f, lz.get(k)));
        }
    }

    private void push(final int k) {
        allApply(2 * k, lz.get(k));
        allApply(2 * k + 1, lz.get(k));
        lz.set(k, id);
    }

    public void set(int p, final S x) {
        assert (0 <= p) && (p < n);
        p += size;
        for (int i = log; i >= 1; i--) {
            push(p >> i);
        }
        d.set(p, x);
        for (int i = 1; i <= log; i++) {
            update(p >> i);
        }
    }

    public S get(int p) {
        assert (0 <= p) && (p < n);
        p += size;
        for (int i = log; i >= 1; i--) {
            push(p >> i);
        }
        return d.get(p);
    }

    public S prod(int l, int r) {
        assert (0 <= l) && (l <= r) && (r <= n);
        if (l == r) return e;
        l += size;
        r += size;

        for (int i = log; i >= 1; i--) {
            if (((l >> i) << i) != l) { push(l >> i); }
            if (((r >> i) << i) != r) { push((r - 1) >> i); }
        }

        S sml = e;
        S smr = e;
        while (l < r) {
            if ((l & 1) == 1) { sml = op.apply(sml, d.get(l++)); }
            if ((r & 1) == 1) { smr = op.apply(d.get(--r), smr); }
            l >>= 1;
            r >>= 1;
        }
        return op.apply(sml, smr);
    }

    public S allProd() { return d.get(1); }

    public void apply(int p, final F f) {
        assert (0 <= p) && (p < n);
        p += size;
        for (int i = log; i >= 1; i--) { push(p >> i); }
        d.set(p, mapping.apply(f, d.get(p)));
        for (int i = 1; i <= log; i++) { update(p >> i); }
    }

    public void apply(int l, int r, final F f) {
        assert (0 <= l) && (l <= r) && (r <= n);
        if (l == r) { return; }

        l += size;
        r += size;

        for (int i = log; i >= 1; i--) {
            if (((l >> i) << i) != l) { push(l >> i); }
            if (((r >> i) << i) != r) { push((r - 1) >> i); }
        }

        {
            final int l2 = l;
            final int r2 = r;
            while (l < r) {
                if ((l & 1) == 1) { allApply(l++, f); }
                if ((r & 1) == 1) { allApply(--r, f); }
                l >>= 1;
                r >>= 1;
            }
            l = l2;
            r = r2;
        }

        for (int i = 1; i <= log; i++) {
            if (((l >> i) << i) != l) { update(l >> i); }
            if (((r >> i) << i) != r) { update((r - 1) >> i); }
        }
    }
    public int maxRight(int l, final Function<S, Boolean> g) {
        assert (0 <= l) && (l <= n);
        assert g.apply(e);
        if (l == n) { return n; }
        l += size;
        for (int i = log; i >= 1; i--) { push(l >> i); }
        S sm = e;
        do {
            while (l % 2 == 0) { l >>= 1; }
            if (!g.apply(op.apply(sm, d.get(l)))) {
                while (l < size) {
                    push(l);
                    l = (2 * l);
                    if (g.apply(op.apply(sm, d.get(l)))) {
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

    public int minLeft(int r, final Function<S, Boolean> g) {
        assert (0 <= r) && (r <= n);
        assert g.apply(e);
        if (r == 0) { return 0; }
        r += size;
        for (int i = log; i >= 1; i--) { push((r - 1) >> i); }
        S sm = e;
        do {
            r--;
            while ((r > 1) && (r % 2 == 1)) { r >>= 1; }
            if(!g.apply(op.apply(d.get(r), sm))) {
                while (r < size) {
                    push(r);
                    r = 2 * r + 1;
                    if(g.apply(op.apply(d.get(r), sm))) {
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
