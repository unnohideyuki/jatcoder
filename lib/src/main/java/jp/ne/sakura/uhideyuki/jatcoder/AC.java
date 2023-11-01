package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class AC {
    public static long gcd(final long a, final long b){
        if (b == 0) return a;
        else return gcd(b, a % b);
    }

    public static int gcd(final int a, final int b){
        return (int) gcd((long) a, (long) b);
    }

    public static long lcm(final long a, final long b){
        return a / gcd(a, b) * b;
    }

    public static int lcm(final int a, final int b){
        return (int) lcm((long) a, (long) b);
    }

    public static <T extends Comparable<? super T>> int lowerBound(final List<T> list, final T key){
        return ~Collections.binarySearch(list, key, (x, y) -> x.compareTo(y) >= 0 ? 1 : -1);
    }
    public static <T extends Comparable<? super T>> int upperBound(final List<T> list, final T key){
        return ~Collections.binarySearch(list, key, (x, y) -> x.compareTo(y) > 0 ? 1 : -1);
    }

    public static void iota(final List<Integer> list, Integer value, final int size){
        for (int i = 0; i < size; i++) list.add(i, value++);
    }
    public static void iota(final List<Long> list, Long value, final int size){
        for (int i = 0; i < size; i++) list.add(value++);
    }
    public static void iota(final int[] arr, int value){
        for (int i = 0; i < arr.length ; i++) arr[i] = value++;
    }
    public static void iota(final long[] arr, long value){
        for (int i = 0; i < arr.length ; i++) arr[i] = value++;
    }
    public static void iota(final Integer[] arr, Integer value){
        for (int i = 0; i < arr.length ; i++) arr[i] = value++;
    }
    public static void iota(final Long[] arr, Long value){
        for (int i = 0; i < arr.length ; i++) arr[i] = value++;
    }

    // nextPermutation is based on https://tomerun.hatenadiary.org/entry/20081203/1228321480
    public static <T extends Comparable<? super T>> boolean nextPermutation(final List<T> l) {
        final int size = l.size();
        for (int i = size - 1; i > 0; --i) {
            if (l.get(i - 1).compareTo(l.get(i)) < 0) {
                final int swapIndex = find(l.get(i - 1), l, i, size - 1);
                Collections.swap(l, i - 1, swapIndex);
                Collections.sort(l.subList(i, size));
                return true;
            }
        }
        return false;
    }

    private static <T extends Comparable<? super T>> int find(T dest, List<T> a, int s, int e) {
        if (s == e) return s;
        final int m = (s + e + 1) / 2;
        return a.get(m).compareTo(dest) <= 0
                ? find(dest, a, s, m - 1)
                : find(dest, a, m, e);
    }

    public static String nextPermutation(final String s){
        final ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) list.add(s.charAt(i));
        if (nextPermutation(list)){
            final StringBuilder sb = new StringBuilder();
            for (Character c : list) sb.append(c);
            return sb.toString();
        }
        return null;
    }
}
