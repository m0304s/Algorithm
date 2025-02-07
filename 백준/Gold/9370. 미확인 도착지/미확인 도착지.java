import java.io.*;
import java.util.*;

public class Main {
    private static class Node implements Comparable<Node> {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M, T, S, G, H;
    private static final int INF = 1000000000;
    private static List<List<Node>> graph;
    private static List<Integer> possibleTargets;

    public static void main(String[] args) throws IOException {
        int CASE = Integer.parseInt(br.readLine());

        for (int x = 0; x < CASE; x++) {
            String[] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            M = Integer.parseInt(tokens[1]);
            T = Integer.parseInt(tokens[2]);

            tokens = br.readLine().split(" ");
            S = Integer.parseInt(tokens[0]);
            G = Integer.parseInt(tokens[1]);
            H = Integer.parseInt(tokens[2]);

            graph = new ArrayList<>();
            possibleTargets = new ArrayList<>();

            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                tokens = br.readLine().split(" ");
                int a = Integer.parseInt(tokens[0]);
                int b = Integer.parseInt(tokens[1]);
                int d = Integer.parseInt(tokens[2]);

                graph.get(a).add(new Node(b, d));
                graph.get(b).add(new Node(a, d));
            }

            for (int i = 0; i < T; i++) {
                possibleTargets.add(Integer.parseInt(br.readLine()));
            }

            int[] distFromS = dijkstra(S);
            int[] distFromG = dijkstra(G);
            int[] distFromH = dijkstra(H);

            List<Integer> answer = new ArrayList<>();
            for (int target : possibleTargets) {
                if (distFromS[target] == INF) continue;
                int throughGH = Math.min(distFromS[G] + distFromG[H] + distFromH[target],
                                         distFromS[H] + distFromH[G] + distFromG[target]);
                if (distFromS[target] == throughGH) {
                    answer.add(target);
                }
            }

            Collections.sort(answer);
            for (int num : answer) {
                bw.write(num + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.cost > dist[cur.idx]) continue;

            for (Node next : graph.get(cur.idx)) {
                int newCost = cur.cost + next.cost;
                if (newCost < dist[next.idx]) {
                    dist[next.idx] = newCost;
                    pq.add(new Node(next.idx, newCost));
                }
            }
        }
        return dist;
    }
}
