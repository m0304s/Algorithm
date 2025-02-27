import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static long MAX = 15625000001L;
    private static int N,M;
    private static int [] oilCost;
    private static ArrayList<ArrayList<Edge>> graph;

    private static class Edge{
        int to,cost;
        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static class Node implements Comparable<Node>{
        int to;
        long totalCost;
        int minOilCost;

        public Node(int to, long totalCost, int minOilCost){
            this.to = to;
            this.totalCost = totalCost;
            this.minOilCost = minOilCost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.totalCost, o.totalCost);
        }
    }

    public static void main(String[] args) throws IOException {
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        graph = new ArrayList<>();
        tokens = br.readLine().split(" ");
        oilCost = new int[N+1];

        for(int i=1;i<=N;i++){
            oilCost[i] = Integer.parseInt(tokens[i-1]);
        }

        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            int cost = Integer.parseInt(tokens[2]);

            graph.get(a).add(new Edge(b,cost));
            graph.get(b).add(new Edge(a,cost));
        }

        bw.write(dijkstra(1)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static long dijkstra(int startNode){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long [][] dist = new long[N+1][2501];
        for(long [] d : dist){
            Arrays.fill(d,MAX);
        }
        dist[startNode][oilCost[startNode]] = 0;
        pq.add(new Node(startNode,0,oilCost[startNode]));

        while(!pq.isEmpty()){
            Node curNode = pq.poll();

            if(curNode.to == N) return curNode.totalCost;

            for(Edge nextEdge : graph.get(curNode.to)){
                if(dist[nextEdge.to][curNode.minOilCost] > curNode.totalCost + (nextEdge.cost * curNode.minOilCost)){
                    dist[nextEdge.to][curNode.minOilCost] = curNode.totalCost + (nextEdge.cost * curNode.minOilCost);
                    pq.add(new Node(nextEdge.to,dist[nextEdge.to][curNode.minOilCost],Math.min(curNode.minOilCost,oilCost[nextEdge.to])));
                }
            }
        }

        return -1;
    }
}