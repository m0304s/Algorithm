import java.io.*;
import java.util.*;

class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    private static ArrayList<ArrayList<Node>> graph;
    private static int N, M, K;
    private static int S, D;
    
    private static class Node implements Comparable<Node> {
        int idx, cost, count;
        
        public Node(int idx, int cost, int count) {
            this.idx = idx;
            this.cost = cost;
            this.count = count;
        }
        
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    
    public static void main(String [] args) throws IOException {
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);  // 도시의 수
        M = Integer.parseInt(tokens[1]);  // 도로의 수
        K = Integer.parseInt(tokens[2]);  // 세금 인상 횟수
        
        tokens = br.readLine().split(" ");
        S = Integer.parseInt(tokens[0]);  // 출발 도시
        D = Integer.parseInt(tokens[1]);  // 도착 도시
        
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < M; i++) {
            tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            int k = Integer.parseInt(tokens[2]);
            
            graph.get(a).add(new Node(b, k, 0));
            graph.get(b).add(new Node(a, k, 0));
        }
        
        int [][] dist = dijkstra(S, D);
        
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i <= N; i++) {
            minDistance = Math.min(minDistance, dist[D][i]);
        }
        bw.write(minDistance + "\n");
        
        int p = 0;
        for (int i = 0; i < K; i++) {
            p += Integer.parseInt(br.readLine()); // 인상하는 세금
            int minCost = Integer.MAX_VALUE;
            for (int j = 0; j <= N; j++) {
                if (dist[D][j] != Integer.MAX_VALUE) {
                    minCost = Math.min(minCost, dist[D][j] + j * p);
                }
            }
            bw.write(minCost + "\n");
        }
        
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static int[][] dijkstra(int start, int end) {
        int [][] dist = new int[N+1][N+1]; // dist[도시의 번호][지나온 간선의 개수]
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        
        dist[S][0] = 0;
        pq.add(new Node(start, 0, 0));
        
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            
            if (curNode.cost > dist[curNode.idx][curNode.count]) continue;
            
            for (int i = 0; i < graph.get(curNode.idx).size(); i++) {
                Node nextNode = graph.get(curNode.idx).get(i);
                
                if (curNode.count + 1 <= N && dist[nextNode.idx][curNode.count + 1] > curNode.cost + nextNode.cost) {
                    dist[nextNode.idx][curNode.count + 1] = curNode.cost + nextNode.cost;
                    pq.add(new Node(nextNode.idx, dist[nextNode.idx][curNode.count + 1], curNode.count + 1));
                }
            }
        }
        return dist;
    }
}
