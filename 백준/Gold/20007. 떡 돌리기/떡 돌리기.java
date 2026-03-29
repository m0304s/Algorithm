import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1000000000;
    static int N, M, X, Y;
    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        int[] dist = dijkstra();
        Arrays.sort(dist);

        // 가장 먼 곳의 왕복 거리가 X보다 크면 불가능
        if (dist[N - 1] * 2 > X) {
            System.out.println("-1");
            return;
        }

        int day = 1;
        int walkToday = 0;

        for (int i = 0; i < N; i++) {
            int roundTrip = dist[i] * 2;
            
            // 오늘 더 갈 수 있는지 확인
            if (walkToday + roundTrip <= X) {
                walkToday += roundTrip;
            } else {
                // 오늘 못 가면 내일 가야 함
                day++;
                walkToday = roundTrip;
            }
        }

        System.out.println(day);
    }

    static int[] dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        
        dist[Y] = 0;
        pq.add(new Node(Y, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (dist[curr.city] < curr.cost) continue;

            for (Edge edge : graph[curr.city]) {
                if (dist[edge.to] > dist[curr.city] + edge.cost) {
                    dist[edge.to] = dist[curr.city] + edge.cost;
                    pq.add(new Node(edge.to, dist[edge.to]));
                }
            }
        }
        return dist;
    }

    static class Node implements Comparable<Node> {
        int city, cost;
        Node(int city, int cost) { this.city = city; this.cost = cost; }
        @Override
        public int compareTo(Node o) { return this.cost - o.cost; }
    }

    static class Edge {
        int to, cost;
        Edge(int to, int cost) { this.to = to; this.cost = cost; }
    }
}