package jp.ne.sakura.uhideyuki.jatcoder;

public class Modint {
    static private long modint_base = -1;
    static private boolean prime = false;
    static public void set_mod(final int m){
        modint_base = m;
        // set prime for very typical case
        if ((m == 998244353) || (m == 1000000007)){
            prime = true;
        }
    }

    long _v = 0;

    static public void set_prime(final boolean b){
        prime = b;
    }

    public Modint(int x){
        _v = x % modint_base;
    }

    public Modint(){}

    private static long do_add(final long a, final long b, final long m){
        return (a + b) % m;
    }

    private static long do_sub(final long a, final long b, final long m){
        return ((a - b) % m + m) % m;
    }

    private static long do_mul(final long a, final long b, final long m){
        return a * b % m;
    }

    private static long do_div(final long a, final long b, final long m, final boolean isp){
        return a * do_inv(b, m, isp) % m;
    }

    private static long do_inv(final long a, final long m, final boolean is_prime){
        if (is_prime){
            assert a != 0;
            return do_pow(a, m - 2, m);
        } else {
            final long[] eg = inv_gcd(a, m);
            assert eg[0] == 1;
            return eg[1];
        }
    }

    private static long do_pow(long x, long n, final long m){
        assert n >= 0;
        long r = 1;
        while (n > 0){
            if ((n & 1) != 0) r = do_mul(r, x, m);
            x = do_mul(x, x, m);
            n >>= 1;
        }
        return r;
    }

    private static long[] inv_gcd(long a, final long b){
        a %= b;
        if (a == 0) return new long[]{b, 0};

        long s = b, t = a;
        long m0 = 0, m1 = 1;

        while (t != 0){
            long u = s / t;
            s -= t * u;
            m0 -= m1 * u;

            var tmp = s;
            s = t;
            t = tmp;
            tmp = m0;
            m0 = m1;
            m1 = tmp;
        }
        if (m0 < 0) m0 += b / s;
        return new long[]{s, m0};
    }

    public long val(){
        return _v;
    }

    public Modint raw(final long x){
        _v = x;
        return this;
    }

    public long mod(){
        return modint_base;
    }

    public Modint add(final Modint x){
        Modint r = new Modint();
        return r.raw(do_add(this.val(), x.val(), modint_base));
    }

    public Modint sub(final Modint x){
        Modint r = new Modint();
        return r.raw(do_sub(this.val(), x.val(), modint_base));
    }

    public Modint mul(final Modint x){
        Modint r = new Modint();
        return r.raw(do_mul(this.val(), x.val(), modint_base));
    }

    public Modint div(final Modint x){
        Modint r = new Modint();
        return r.raw(do_div(this.val(), x.val(), modint_base, prime));
    }

    public Modint inv(){
        Modint r = new Modint();
        return r.raw(do_inv(this.val(), modint_base, prime));
    }

    public Modint pow(long n){
        Modint r = new Modint();
        return r.raw(do_pow(this.val(), n, modint_base));
    }

    public static class DynamicModint {
        private long dynamic_modint_base = -1;
        private boolean dynamic_prime = false;

        private long _v = 0;
        public DynamicModint(int x, int m){
            set_mod(m);
            _v = x % dynamic_modint_base;
        }

        public DynamicModint(){}

        public void set_mod(final int m){
            dynamic_modint_base = m;
            // set prime for very typical case
            if ((m == 998244353) || (m == 1000000007)){
                dynamic_prime = true;
            }
        }

        public void set_prime(final boolean b){
            dynamic_prime = b;
        }

        public boolean get_prime(){
            return dynamic_prime;
        }

        public long val(){
            return _v;
        }

        public long mod(){
            return dynamic_modint_base;
        }

        public DynamicModint raw(final long x){
            _v = x;
            return this;
        }

        public DynamicModint add(final DynamicModint x){
            DynamicModint r = new DynamicModint();
            r.set_mod((int) this.mod());
            r.set_prime(this.get_prime());
            return r.raw(do_add(this.val(), x.val(), this.mod()));
        }

        public DynamicModint sub(final DynamicModint x){
            DynamicModint r = new DynamicModint();
            r.set_mod((int) this.mod());
            r.set_prime(this.get_prime());
            return r.raw(do_sub(this.val(), x.val(), this.mod()));
        }

        public DynamicModint mul(final DynamicModint x){
            DynamicModint r = new DynamicModint();
            r.set_mod((int) this.mod());
            r.set_prime(this.get_prime());
            return r.raw(do_mul(this.val(), x.val(), this.mod()));
        }

        public DynamicModint div(final DynamicModint x){
            DynamicModint r = new DynamicModint();
            r.set_mod((int) this.mod());
            r.set_prime(this.get_prime());
            return r.raw(do_div(this.val(), x.val(), this.mod(), this.get_prime()));
        }

        public DynamicModint inv(){
            DynamicModint r = new DynamicModint();
            r.set_mod((int) this.mod());
            r.set_prime(this.get_prime());
            return r.raw(do_inv(this.val(), this.mod(), this.get_prime()));
        }

        public DynamicModint pow(long n){
            DynamicModint r = new DynamicModint();
            r.set_mod((int) this.mod());
            r.set_prime(this.get_prime());
            return r.raw(do_pow(this.val(), n, this.mod()));
        }

    }
}
