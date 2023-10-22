package jp.ne.sakura.uhideyuki.jatcoder;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class UnionFindTest {
    @Test
    void initialize(){
        final int n = 10;
        final UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++){
            assertEquals(i, uf.leader(i));
            assertEquals(1, uf.size(i));
        }
    }

    @Test
    void mergeAndSame(){
        final int n = 2;
        final UnionFind uf = new UnionFind(n);
        assertFalse(uf.same(0, 1));
        assertEquals(uf.leader(0), uf.merge(0, 1));
        assertTrue(uf.same(0, 1));
    }

    @Test
    void mergeTwise(){
        final int n = 2;
        final UnionFind uf = new UnionFind(n);
        assertEquals(uf.leader(0), uf.merge(0, 1));
        assertTrue(uf.same(0, 1));
        assertEquals(2, uf.size(0));
        assertEquals(uf.leader(0), uf.merge(0, 1));
        assertTrue(uf.same(0, 1));
        assertEquals(2, uf.size(0));
    }

    @Test
    void groups1(){
        final int n = 5;
        final UnionFind uf = new UnionFind(n);
        final int[] v = new int[n];
        for (ArrayList<Integer> arr : uf.groups()){
            for (int i : arr){
                v[i]++;
            }
        }
        for (int i = 0; i < n; i++){
            assertEquals(1, v[i]);
        }
    }
}
