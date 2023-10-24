package jp.ne.sakura.uhideyuki.jatcoder;

public class Modint {
    static private long modintBase = -1;
    static private boolean prime = false;
    static public void setMod(final int m){
        modintBase = m;
        // set prime for very typical case
        if ((m == 998244353) || (m == 1000000007)){
            prime = true;
        }
    }

    long _v = 0;

    static public void setPrime(final boolean b){
        prime = b;
    }

    public Modint(int x){
        _v = x % modintBase;
    }

    public Modint(){}

    private static long doAdd(final long a, final long b, final long m){
        return (a + b) % m;
    }

    private static long doSub(final long a, final long b, final long m){
        return ((a - b) % m + m) % m;
    }

    private static long doMul(final long a, final long b, final long m){
        return a * b % m;
    }

    private static long doDiv(final long a, final long b, final long m, final boolean isp){
        return a * doInv(b, m, isp) % m;
    }

    private static long doInv(final long a, final long m, final boolean isPrime){
        if (isPrime){
            assert a != 0;
            return doPow(a, m - 2, m);
        } else {
            final long[] eg = invGcd(a, m);
            assert eg[0] == 1;
            return eg[1];
        }
    }

    private static long doPow(long x, long n, final long m){
        assert n >= 0;
        long r = 1;
        while (n > 0){
            if ((n & 1) != 0) r = doMul(r, x, m);
            x = doMul(x, x, m);
            n >>= 1;
        }
        return r;
    }

    private static long[] invGcd(long a, final long b){
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
        return modintBase;
    }

    public Modint add(final Modint x){
        Modint r = new Modint();
        return r.raw(doAdd(this.val(), x.val(), modintBase));
    }

    public Modint sub(final Modint x){
        Modint r = new Modint();
        return r.raw(doSub(this.val(), x.val(), modintBase));
    }

    public Modint mul(final Modint x){
        Modint r = new Modint();
        return r.raw(doMul(this.val(), x.val(), modintBase));
    }

    public Modint div(final Modint x){
        Modint r = new Modint();
        return r.raw(doDiv(this.val(), x.val(), modintBase, prime));
    }

    public Modint inv(){
        Modint r = new Modint();
        return r.raw(doInv(this.val(), modintBase, prime));
    }

    public Modint pow(long n){
        Modint r = new Modint();
        return r.raw(doPow(this.val(), n, modintBase));
    }

    public static class DynamicModint {
        private long dynamicModintBase = -1;
        private boolean dynamicPrime = false;

        private long _v = 0;
        public DynamicModint(int x, int m){
            setMod(m);
            _v = x % dynamicModintBase;
        }

        public DynamicModint(){}

        public void setMod(final int m){
            dynamicModintBase = m;
            // set prime for very typical case
            if ((m == 998244353) || (m == 1000000007)){
                dynamicPrime = true;
            }
        }

        public void setPrime(final boolean b){
            dynamicPrime = b;
        }

        public boolean getPrime(){
            return dynamicPrime;
        }

        public long val(){
            return _v;
        }

        public long mod(){
            return dynamicModintBase;
        }

        public DynamicModint raw(final long x){
            _v = x;
            return this;
        }

        public DynamicModint add(final DynamicModint x){
            final DynamicModint r = new DynamicModint();
            r.setMod((int) this.mod());
            r.setPrime(this.getPrime());
            return r.raw(doAdd(this.val(), x.val(), this.mod()));
        }

        public DynamicModint sub(final DynamicModint x){
            final DynamicModint r = new DynamicModint();
            r.setMod((int) this.mod());
            r.setPrime(this.getPrime());
            return r.raw(doSub(this.val(), x.val(), this.mod()));
        }

        public DynamicModint mul(final DynamicModint x){
            final DynamicModint r = new DynamicModint();
            r.setMod((int) this.mod());
            r.setPrime(this.getPrime());
            return r.raw(doMul(this.val(), x.val(), this.mod()));
        }

        public DynamicModint div(final DynamicModint x){
            final DynamicModint r = new DynamicModint();
            r.setMod((int) this.mod());
            r.setPrime(this.getPrime());
            return r.raw(doDiv(this.val(), x.val(), this.mod(), this.getPrime()));
        }

        public DynamicModint inv(){
            final DynamicModint r = new DynamicModint();
            r.setMod((int) this.mod());
            r.setPrime(this.getPrime());
            return r.raw(doInv(this.val(), this.mod(), this.getPrime()));
        }

        public DynamicModint pow(long n){
            final DynamicModint r = new DynamicModint();
            r.setMod((int) this.mod());
            r.setPrime(this.getPrime());
            return r.raw(doPow(this.val(), n, this.mod()));
        }
    }
}
