package jp.ne.sakura.uhideyuki.jatcoder;
public class Pair<L extends Comparable<L>, R extends Comparable<R>>
        implements Comparable<Pair<L, R>>{
    public L first;
    public R second;
    public Pair(final L a, final R b){
        first = a;
        second = b;
    }

    public boolean equals(final Pair<L, R> p){
        return first == p.first && second == p.second;
    }

    public int compareTo(final Pair<L, R> p){
        return (first != p.first) ? first.compareTo(p.first) : second.compareTo(p.second);
    }

    public int hashCode(){
        return first.hashCode() ^ second.hashCode();
    }
}
