import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static final int INF = 1000000000;
    static int N, M;
    static List<List<Edge>> graph;

    public static void main(String[] args) throws IOException {
        String line = br.readLine();
        if (line == null) return;
        
        int T = Integer.parseInt(line.trim());
        
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 관계의 수
            M = Integer.parseInt(st.nextToken()); // 사람의 수
            
            graph = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                graph.add(new ArrayList<>());
            }
            
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                
                graph.get(u).add(new Edge(u, v, z));
                graph.get(v).add(new Edge(v, u, z));
            }
            
            PriorityQueue<Node> pq = new PriorityQueue<>();
            int[] dist = new int[M];
            int[] parent = new int[M];
            Arrays.fill(dist, INF);
            Arrays.fill(parent, -1);
            
            dist[0] = 0;
            pq.add(new Node(0, 0));
            
            while (!pq.isEmpty()) {
                Node curr = pq.poll();
                if (dist[curr.next] < curr.totalCost) continue;
                
                for (Edge next : graph.get(curr.next)) {
                    int newCost = curr.totalCost + next.cost;
                    
                    if (dist[next.to] > newCost) {
                        dist[next.to] = newCost;
                        parent[next.to] = curr.next;
                        pq.add(new Node(next.to, newCost));
                    }
                }
            }
            
            bw.write("Case #" + t + ": ");
            if (dist[M - 1] == INF) {
                bw.write("-1\n");
            } else {
                Stack<Integer> path = new Stack<>();
                int currNode = M - 1;
                while (currNode != -1) {
                    path.push(currNode);
                    currNode = parent[currNode];
                }
                
                while (!path.isEmpty()) {
                    bw.write(path.pop() + (path.size() == 0 ? "" : " "));
                }
                bw.write("\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
    
    static class Edge {
        int from, to, cost;
        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    
    static class Node implements Comparable<Node> {
        int next, totalCost;
        public Node(int next, int totalCost) {
            this.next = next;
            this.totalCost = totalCost;
        }
        @Override
        public int compareTo(Node o) {
            return this.totalCost - o.totalCost;
        }
    }
}