package jp.ne.sakura.uhideyuki.jatcoder;

public final class Modint implements Comparable<Modint> {
    private final long mod;
    private final boolean prime;
    private final long value;
    @Override
    public boolean equals(final Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Modint m) {
            return value == m.getValue();
        }
        return super.equals(obj);
    }

    @Override
    public int compareTo(final Modint m){
        return Long.compare(value, m.getValue());
    }

    @Override
    public int hashCode(){
        return Long.valueOf(value).hashCode();
    }

    public static class Builder {
        private long value = 0;
        private long mod = -1;
        private boolean prime = false;

        public Builder setValue(final long value) {
            assert value >= 0;
            this.value = value;
            return this;
        }
        public long getValue() {
            return value;
        }
        public Builder setMod(final int mod){
            assert mod >= 0;
            this.mod = (long) mod;
            // よく使われる mod に対してのみ prime を設定
            this.prime = ((mod == 998244353) || (mod == 1000000007));
            return this;
        }
        public long getMod() {
            return mod;
        }
        public Builder setPrime(final boolean prime) {
            this.prime = prime;
            return this;
        }
        public boolean getPrime() {
            return prime;
        }
        public Modint build() {
            assert value >= 0;
            assert mod > 0;
            return new Modint(this);
        }
    }

    private Modint(final Builder builder){
        mod = builder.mod;
        prime = builder.prime;
        value = builder.value % mod;
    }

    private Modint(final Modint x, final long value) {
        mod = x.mod;
        prime = x.prime;
        this.value = value;
    }

    Builder toBuilder() {
        return new Builder().setMod(mod()).setPrime(prime).setValue(value);
    }

    public int getValue(){
        return (int) value;
    }

    public int mod(){
        return (int) mod;
    }

    public Modint add(final Modint x){
        assert mod == x.mod();
        return new Modint(this, ((this.getValue() + x.getValue()) % mod));
    }

    public Modint sub(final Modint x){
        assert mod == x.mod();
        return new Modint(this, ((((getValue() - x.getValue()) % mod) + mod) % mod));
    }

    public Modint mul(final Modint x){
        assert mod == x.mod();
        return new Modint(this, (((long) getValue() * x.getValue()) % mod));
    }

    public Modint div(final Modint x){
        assert mod == x.mod();
        assert prime == x.prime;
        return new Modint(this, (((long) getValue() * x.inv().getValue()) % mod));
    }
    public Modint inv(){
        if (prime){
            assert value != 0;
            return this.pow(mod - 2);
        } else {
            final long[] eg = invGcd(value, mod);
            assert eg[0] == 1;
            return new Modint(this, eg[1]);
        }
    }

    private static long[] invGcd(long a, final long b){
        a %= b;
        if (a == 0) return new long[]{b, 0};

        long s = b, t = a;
        long m0 = 0, m1 = 1;

        while (t != 0){
            final long u = s / t;
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

    public Modint pow(long n){
        assert n >= 0;
        long a = 1, x = value;
        while (n > 0){
            if ((n & 1) != 0) a = a * x % mod;
            x = x * x % mod;
            n >>= 1;
        }
        return new Modint(this, a);
    }
}
