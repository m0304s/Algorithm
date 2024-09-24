import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int INF = 80000004;
    static int N;
    static int E;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int v1, v2;

    static class Node implements Comparable<Node>{
        int end;
        int length;
        public Node(int end,int length){
            this.end = end;
            this.length = length;
        }
        public int compareTo(Node o){
            return this.length - o.length;
        }
    }
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        E = Integer.parseInt(tokens[1]);

        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<E;i++){
            tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            int c = Integer.parseInt(tokens[2]);

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        // 반드시 거쳐야 하는 두 개의 서로 다른 정점 번호 v1, v2
        tokens = br.readLine().split(" ");
        v1 = Integer.parseInt(tokens[0]);
        v2 = Integer.parseInt(tokens[1]);

        int result1 = dijkstra(1,v1) + dijkstra(v1, v2) + dijkstra(v2, N);
        int result2 = dijkstra(1,v2) + dijkstra(v2, v1) + dijkstra(v1, N);

        int finalResult = (result1 >= INF && result2>= INF) ? -1 : Math.min(result1, result2);
        bw.write(finalResult+"\n");
        bw.flush();
    }
    static int dijkstra(int startPos, int endPos){
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int [] dist = new int[N+1];
        boolean [] visited = new boolean[N+1];

        Arrays.fill(dist, INF);

        dist[startPos] = 0;
        pq.offer(new Node(startPos,0));        

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.end;
            if(!visited[cur]){
                visited[cur] = true;

                for(Node node : graph.get(cur)){
                    if(!visited[node.end] && dist[node.end] > dist[cur] + node.length){
                        dist[node.end] = dist[cur] + node.length;
                        pq.add(new Node(node.end, dist[node.end]));
                    }
                }
            }
        }
        return dist[endPos];
    }
}
