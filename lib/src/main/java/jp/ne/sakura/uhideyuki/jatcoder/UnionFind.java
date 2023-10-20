package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class UnionFind {
    private final int[] data;
    public UnionFind(final int n){
        data = new int[n];
        for (int i = 0; i < n; i++){
            data[i] = -1;
        }
    }

    public int leader(final int a){
        return (data[a] < 0 ? a : (data[a] = leader(data[a])));
    }

    public int size(final int a){
        return -data[leader(a)];
    }

    public boolean same(final int a, final int b){
        return leader(a) == leader(b);
    }

    public int merge(final int a, final int b){
        final int i = leader(a);
        final int j = leader(b);
        if (i == j) return i;
        data[i] += data[j];
        return data[j] = i;
    }

    public ArrayList<ArrayList<Integer>> groups(){
        final int n = data.length;
        final int[] leader_buf = new int[n];
        final int[] group_size = new int[n];
        for (int i = 0; i < n; i++){
            leader_buf[i] = leader(i);
            group_size[leader_buf[i]]++;
        }
        final ArrayList<ArrayList<Integer>> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++){
            result.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++){
            result.get(i).ensureCapacity(group_size[i]);
        }
        for (int i = 0; i < n; i++){
            result.get(leader_buf[i]).add(i);
        }
        result.removeIf(ArrayList::isEmpty);
        return result;
    }
}
