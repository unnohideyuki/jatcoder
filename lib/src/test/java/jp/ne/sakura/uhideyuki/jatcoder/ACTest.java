package jp.ne.sakura.uhideyuki.jatcoder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.junit.jupiter.api.Test;

class ACTest {
    @Test
    void gcdLong(){
        final long a = 12;
        final long b = 30;
        assertEquals(6, AC.gcd(a, b));
    }

    @Test
    void gcdInt(){
        final int a = 12;
        final int b = 30;
        assertEquals(6, AC.gcd(a, b));
    }

    @Test
    void lcmLong(){
        final long a = 12;
        final long b = 30;
        assertEquals(60, AC.lcm(a, b));
    }

    @Test
    void lcmInt(){
        final int a = 12;
        final int b = 30;
        assertEquals(60, AC.lcm(a, b));
    }

    @Test
    void lowerBound(){
        final ArrayList<Integer> list = new ArrayList<>(List.of(new Integer[]{1, 2, 2, 3, 4, 4, 4, 6, 7}));
        final int result = AC.lowerBound(list, 4);
        assertEquals(4, result);
        final int result2 = AC.lowerBound(list, 1000000000);
        assertEquals(list.size(), result2);
    }

    @Test
    void upperBound(){
        final ArrayList<Integer> list = new ArrayList<>(List.of(new Integer[]{1, 2, 2, 3, 4, 4, 4, 6, 7}));
        final int result = AC.upperBound(list, 4);
        assertEquals(7, result);
        final int result2 = AC.upperBound(list, 1000000000);
        assertEquals(list.size(), result2);
    }

    @Test
    void iotaListInteger(){
        final int N = 100;
        final ArrayList<Integer> list = new ArrayList<>(N);
        final int v = 0;
        AC.iota(list, v, N);
        for (int i = 0; i < N; i++){
            assertEquals(v + i, list.get(i));
        }
    }

    @Test
    void iotaListLong(){
        final int N = 100;
        final ArrayList<Long> list = new ArrayList<>(N);
        final long v = 0;
        AC.iota(list, v, N);
        for (int i = 0; i < N; i++){
            assertEquals(v + i, list.get(i));
        }
    }

    @Test
    void iotaArrayint(){
        final int N = 100;
        final int[] arr = new int[N];
        final int v = 0;
        AC.iota(arr, v);
        for (int i = 0; i < N; i++){
            assertEquals(v + i, arr[i]);
        }
    }

    @Test
    void iotaArrayInteger(){
        final int N = 100;
        final Integer[] arr = new Integer[N];
        final int v = 0;
        AC.iota(arr, v);
        for (int i = 0; i < N; i++){
            assertEquals(v + i, arr[i]);
        }
    }

    @Test
    void iotaArrayLong(){
        final int N = 100;
        final Long[] arr = new Long[N];
        final long v = 0;
        AC.iota(arr, v);
        for (int i = 0; i < N; i++){
            assertEquals(v + i, arr[i]);
        }
    }

    @Test
    void nextPermutaion(){
        final int[][] expected =
                new int[][]{{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}};
        final int N = 3;
        final ArrayList<Integer> list = new ArrayList<>(3);
        AC.iota(list, 0, N);
        int i = 0;
        do {
            for (int j = 0; j < N; j++){
                assertEquals(expected[i][j], list.get(j));
            }
            i++;
        } while (AC.nextPermutation(list));
    }

    @Test
    void nextPermutaionString(){
        final String[] expected =
                new String[]{"012", "021", "102", "120", "201", "210"};
        final int N = 3;
        String s = "012";
        int i = 0;
        do {
            assertEquals(expected[i++], s);
        } while ((s = AC.nextPermutation(s)) != null);
    }

}
