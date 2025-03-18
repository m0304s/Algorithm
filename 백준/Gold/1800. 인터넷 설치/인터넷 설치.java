import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, P, K;
    static ArrayList<ArrayList<Cable>> graph;

    static class Cable implements Comparable<Cable> {
        int to, cost;

        public Cable(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Cable o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        P = Integer.parseInt(tokens[1]);
        K = Integer.parseInt(tokens[2]);

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < P; i++) {
            tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            int c = Integer.parseInt(tokens[2]);

            graph.get(a).add(new Cable(b, c));
            graph.get(b).add(new Cable(a, c));
        }

        int left = 0;
        int right = getMaxCost();
        int answer = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (isPossible(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean isPossible(int cost) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Cable> pq = new PriorityQueue<>();
        pq.add(new Cable(1, 0));
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Cable curCable = pq.poll();
            if (curCable.cost > dist[curCable.to]) continue;

            for (Cable nextCable : graph.get(curCable.to)) {
                int nextCost = curCable.cost + (nextCable.cost > cost ? 1 : 0);
                if (nextCost < dist[nextCable.to]) {
                    dist[nextCable.to] = nextCost;
                    pq.add(new Cable(nextCable.to, nextCost));
                }
            }
        }
        return dist[N] <= K;
    }

    static int getMaxCost() {
        int maxCost = 0;
        for (ArrayList<Cable> edges : graph) {
            for (Cable cable : edges) {
                maxCost = Math.max(maxCost, cable.cost);
            }
        }
        return maxCost;
    }
}
