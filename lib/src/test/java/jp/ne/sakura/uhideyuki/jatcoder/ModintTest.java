package jp.ne.sakura.uhideyuki.jatcoder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ModintTest {
    @Test
    void modintSetGetValue() {
        final int mod = 13;
        final Modint x = new Modint.Builder(mod).build();
        assertEquals(0, x.getValue());
        x.setValue(10);
        assertEquals(10, x.getValue());
    }

    @Test
    void modintMod(){
        final int mod = 13;
        final Modint x = new Modint.Builder(13).build();
        assertEquals(mod, x.mod());
    }

    @Test
    void twoDifferentMods(){
        final int mod1 = 13;
        final int mod2 = 7;
        final Modint x1 = new Modint.Builder(mod1).build(100);
        final Modint x2 = new Modint.Builder(mod2).build(100);
        assertFalse(x1.getValue() == x2.getValue());
        assertEquals(100 % mod1, x1.getValue());
        assertEquals(100 % mod2, x2.getValue());
    }

    @Test
    void modintAdd() {
        final int mod = 13;
        final Modint.Builder modint = new Modint.Builder(13, true);
        for (int x = 0; x < 20; x++){
            for (int y = 0; y < 20; y++){
                final Modint a = modint.build(x);
                final Modint b = modint.build(y);
                final Modint c = a.add(b);
                assertEquals((x + y) % mod, c.getValue());
            }
        }
    }

    @Test
    void modIntSub(){
        final int mod = 13;
        final Modint.Builder modint = new Modint.Builder(mod, true);
        for (int x = 0; x < 20; x++){
            for (int y = 0; y < 100; y++){
                final Modint a = modint.build(x);
                final Modint b = modint.build(y);
                final Modint c = a.sub(b);
                assertEquals(((x - y) % mod + mod) % mod, c.getValue());
            }
        }
    }

    @Test
    void modIntMul(){
        final int mod = 13;
        final Modint.Builder modint = new Modint.Builder(mod, true);
        for (int x = 0; x < 20; x++){
            for (int y = 0; y < 20; y++){
                final Modint a = modint.build(x);
                final Modint b = modint.build(y);
                final Modint c = a.mul(b);
                assertEquals((x * y) % mod, c.getValue());
            }
        }
    }

    @Test
    void modIntDivPrime(){
        final int mod = 998244353;
        final Modint.Builder modint = new Modint.Builder(mod, true);
        for (int x = 0; x < 20; x++){
            for (int y = 0; y < 20; y++){
                if (y % mod == 0) continue;
                final Modint a = modint.build(x);
                final Modint b = modint.build(y);
                final Modint c = a.div(b);
                assertEquals(x % mod, (long) c.getValue() * y % mod);
            }
        }
    }

    @Test
    void modIntDivNotPrime(){
        final int mod = 998244353;
        final Modint.Builder modint = new Modint.Builder(mod, false);
        for (int x = 0; x < 20; x++){
            for (int y = 0; y < 20; y++){
                if (y % mod == 0) continue;
                final Modint a = modint.build(x);
                final Modint b = modint.build(y);
                final Modint c = a.div(b);
                assertEquals(x % mod, (long) c.getValue() * y % mod);
            }
        }
    }

    @Test
    void modintInvPrime(){
        final int mod = 1000000007;
        final Modint.Builder modint = new Modint.Builder(mod, true);
        for (int i = 1; i < 100; i++){
            final Modint x = modint.build(i);
            final Modint y = x.inv();
            assertEquals(1, x.mul(y).getValue());
        }
    }

    @Test
    void modintInvNotPrime(){
        final int mod = 1000000007;
        final Modint.Builder modint = new Modint.Builder(mod, false);
        for (int i = 1; i < 100; i++){
            final Modint x = modint.build(i);
            final Modint y = x.inv();
            assertEquals(1, x.mul(y).getValue());
        }
    }

    @Test
    void modintPow(){
        final int mod = 13;
        long x = 1;
        int a = 9;
        int p = 100;
        for (int i = 0; i < p; i++){
            x *= a;
            x %= mod;
        }
        final Modint y = new Modint.Builder(mod).build(a).pow(p);
        assertEquals(x, y.getValue());
    }

    @Test
    void modintCompare() {
        final Modint.Builder modint = new Modint.Builder(998244353);
        for (long x = 0L; x < 10L; x++){
            Modint a = modint.build((int) x);
            for (long y = 0L; y < 10L; y++){
                Modint b = modint.build((int) y);
                assertEquals(Long.valueOf(x).equals(y), a.equals(b));
                assertEquals(Long.valueOf(x).compareTo(y), a.compareTo(b));
            }
        }
    }
}
