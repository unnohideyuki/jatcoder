package jp.ne.sakura.uhideyuki.jatcoder;
public class Pair<L extends Comparable<L>, R extends Comparable<R>> implements Comparable<Pair<L, R>> {
    private L first;
    private R second;
    public Pair(final L a, final R b){
        first = a;
        second = b;
    }

    public L getFirst() { return first; }
    public R getSecond() { return second; }

    public void setFirst(L first) {
        this.first = first;
    }

    public void setSecond(R second) {
        this.second = second;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(final Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Pair) {
            final Pair<L, R> p = (Pair<L, R>) obj;
            return first.equals(p.first) && second.equals(p.second);
        }
        return super.equals(obj);
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
