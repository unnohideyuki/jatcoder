package jp.ne.sakura.uhideyuki.jatcoder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class PairTest {
    @Test
    void integerLongPairEquals(){
        final Pair<Integer, Long> p = new Pair<Integer, Long>(2, (long) 4);
        final Pair<Integer, Long> q = new Pair<Integer, Long>(2, (long) 4);
        final Pair<Integer, Long> r = new Pair<Integer, Long>(2, (long) 3);
        assertTrue(p.equals(q));
        assertFalse(p.equals(r));
    }

    @Test
    void pairInPriorityQueue(){
        final Pair<Integer, String> a = new Pair<Integer, String>(1, "ab");
        final Pair<Integer, String> b = new Pair<Integer, String>(1, "cd");
        final Pair<Integer, String> c = new Pair<Integer, String>(2, "ab");

        final PriorityQueue<Pair<Integer, String>> que =
                new PriorityQueue<Pair<Integer, String>>();

        que.add(c);
        que.add(b);
        que.add(a);
        assertEquals(a, que.poll());
        assertEquals(b, que.poll());
        assertEquals(c, que.poll());
    }
    @Test
    void pairInPriorityQueue2(){
        final Pair<Integer, String> a = new Pair<Integer, String>(1, "ab");
        final Pair<Integer, String> b = new Pair<Integer, String>(1, "cd");
        final Pair<Integer, String> c = new Pair<Integer, String>(2, "ab");

        final PriorityQueue<Pair<Integer, String>> que =
                new PriorityQueue<Pair<Integer, String>>(Collections.reverseOrder());

        que.add(a);
        que.add(b);
        que.add(c);
        assertEquals(c, que.poll());
        assertEquals(b, que.poll());
        assertEquals(a, que.poll());
    }

    @Test
    void pairInMap(){
        final Pair<Integer, String> a = new Pair<Integer, String>(1, "ab");
        final Pair<Integer, String> b = new Pair<Integer, String>(1, "cd");
        final Pair<Integer, String> c = new Pair<Integer, String>(2, "ab");

        final Map<Pair<Integer, String>, Integer> mp = new HashMap<>();
        mp.put(a, 100);
        mp.put(b, 200);
        mp.put(c, 300);

        assertEquals(100, mp.get(a));
        assertEquals(200, mp.get(b));
        assertEquals(300, mp.get(c));
    }
}
