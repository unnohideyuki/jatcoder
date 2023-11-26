package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class MaxFlow {
    public static class Edge {
        private final int to;
        private int cap;
        private final int rev;
        public Edge(final int to, final int cap, final int rev) {
            this.to = to;
            this.cap = cap;
            this.rev = rev;
        }
        public int getTo() {
            return to;
        }
        public int getCap() {
            return cap;
        }
        public void addCap(final int x) {
            this.cap += x;
        }
        public int getRev() {
            return rev;
        }
    }
    private final int size;
    private final boolean[] used;
    private final ArrayList<ArrayList<Edge>> graph;
    public MaxFlow(final int n) {
        size = n;
        used = new boolean[n];
        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
    }
    // 頂点 a から b に向かう、容量 c の辺を追加
    public void addEdge(final int a, final int b, final int c) {
        final int currentGa = graph.get(a).size();
        final int currentGb = graph.get(b).size();
        graph.get(a).add(new Edge(b, c, currentGb));
        graph.get(b).add(new Edge(a, 0, currentGa));
    }
    private int dfs(final int pos, final int goal, final int F) {
        if (pos == goal) { return F; } // すでにゴールについている
        used[pos] = true;
        for (int i = 0; i < graph.get(pos).size(); i++) {
            // 容量 0 の辺は使えない
            if (graph.get(pos).get(i).getCap() == 0) {
                continue;
            }
            // 既に訪問した頂点に行っても意味がない
            if (used[graph.get(pos).get(i).getTo()]) {
                continue;
            }
            // 目的地までのパスを探す
            final int flow = dfs(
                    graph.get(pos).get(i).getTo(),
                    goal,
                    Math.min(F, graph.get(pos).get(i).getCap()));
            // フローを流せる場合、残余グラフの容量を flow だけ増減させる
            if  (flow >= 1) {
                graph.get(pos).get(i).addCap(-flow);
                graph.get(graph.get(pos).get(i).getTo())
                        .get(graph.get(pos).get(i).getRev())
                        .addCap(flow);
                return flow;
            }
        }
        // すべての辺を探しても見つからなかった
        return 0;
    }

    public int maxFlow(final int s, final int t) {
        int totalFlow = 0;
        while (true) {
            for (int i = 0; i < size; i++) {
                used[i] = false;
            }
            final int F = dfs(s, t, (1 << 30));
            if (F == 0) { break; }
            totalFlow += F;
        }
        return totalFlow;
    }
}
