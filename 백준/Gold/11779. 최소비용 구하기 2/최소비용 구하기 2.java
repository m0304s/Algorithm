import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int INF = 100000000;

    static int N;
    static int M;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int [] prev;

    static class Node implements Comparable<Node>{
        int end;
        int weight;
        public Node(int end,int weight){
            this.end = end;
            this.weight = weight;
        }
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }
    
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=1;i<=M;i++){
            String [] tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            int weight = Integer.parseInt(tokens[2]);

            graph.get(a).add(new Node(b,weight));
        }

        String [] tokens = br.readLine().split(" ");
        int startPos = Integer.parseInt(tokens[0]);
        int endPos = Integer.parseInt(tokens[1]);

        int result = dijkstra(startPos, endPos);

        // 최단 경로의 거리 출력
        bw.write(result + "\n");

        // 경로 출력
        Stack<Integer> path = new Stack<>();
        int cur = endPos;
        while (cur != -1) {
            path.push(cur);
            cur = prev[cur];
        }

        // 경로 길이 출력
        bw.write(path.size() + "\n");

        // 경로 출력
        while (!path.isEmpty()) {
            bw.write(path.pop() + " ");
        }
        bw.write("\n");
        bw.flush();
        
    }
    static int dijkstra(int start, int end){
        int[] dist = new int[N+1];
        boolean [] visited = new boolean[N+1];
        prev = new int[N+1];

        Arrays.fill(dist, INF);
        Arrays.fill(prev, -1);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[start] = 0;
        pq.add(new Node(start,0));

        while(!pq.isEmpty()){
            Node curNode = pq.poll();
            int cur = curNode.end;
            if(!visited[cur]){
                visited[cur] = true;

                for(Node node : graph.get(cur)){
                    if(!visited[node.end] && dist[node.end] > dist[cur] + node.weight){
                        dist[node.end] = dist[cur] + node.weight;
                        prev[node.end] = cur;   //이전 노드 기록
                        pq.add(new Node(node.end, dist[node.end]));
                    }
                }
            }
        }
        return dist[end];
    }
}
