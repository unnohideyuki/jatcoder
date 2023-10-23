package jp.ne.sakura.uhideyuki.jatcoder;
public class Pair<L extends Comparable<L>, R extends Comparable<R>> implements Comparable<Pair<L, R>> {
    public L first;
    public R second;
    public Pair(final L a, final R b){
        first = a;
        second = b;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(final Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        final Pair<L, R> p = (Pair<L, R>) obj;
        return first == p.first && second == p.second;
    }

    @Override
    public int compareTo(final Pair<L, R> p){
        return (first != p.first) ? first.compareTo(p.first) : second.compareTo(p.second);
    }

    @Override
    public int hashCode(){
        return first.hashCode() ^ second.hashCode();
    }
}
