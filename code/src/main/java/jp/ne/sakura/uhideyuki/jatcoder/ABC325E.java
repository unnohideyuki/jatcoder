package jp.ne.sakura.uhideyuki.jatcoder;
import java.util.*;

public class ABC325E {
    public void solve(){
        // Input
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int A = sc.nextInt();
        final int B = sc.nextInt();
        final int C = sc.nextInt();
        final long[][] d = new long[N][N];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                d[i][j] = sc.nextInt();
            }
        }

        // Solve: Dijkstra
        final long INF = (1L << 60);
        final long[][] dist = new long[2][N];
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < N; j++){
                dist[i][j] = INF;
            }
        }

        final PriorityQueue<Pair<Long, Pair<Integer, Integer>>> que = new PriorityQueue<>();
        que.add(new Pair<>(0L, new Pair<>(0, 0)));
        dist[0][0] = 0;

        while (que.size() > 0){
            final var p = que.poll();
            final long currD = p.getFirst();
            final int train = p.getSecond().getFirst();
            final int i = p.getSecond().getSecond();

            if (dist[train][i] < currD) continue;

            if (train == 0){ // using car
                for (int j = 0; j < N; j++){
                    if (dist[0][j] > dist[0][i] + d[i][j] * A){
                        final long nd = dist[0][i] + d[i][j] * A;
                        dist[0][j] = nd;
                        que.add(new Pair<>(nd, new Pair<>(0, j)));
                    }
                    if (dist[1][j] > dist[0][i] + d[i][j] * B + C){
                        final long nd = dist[0][i] + d[i][j] * B + C;
                        dist[1][j] = nd;
                        que.add(new Pair<>(nd, new Pair<>(1, j)));
                    }
                }
            } else { // using train
                for (int j = 0; j < N; j++){
                    if (dist[1][j] > dist[1][i] + d[i][j] * B + C){
                        final long nd = dist[1][i] + d[i][j] * B + C;
                        dist[1][j] = nd;
                        que.add(new Pair<>(nd, new Pair<>(1, j)));
                    }
                }
            }
        }

        // Print
        Long ans = Math.min(dist[0][N-1], dist[1][N-1]);
        System.out.println(ans);
    }
}
