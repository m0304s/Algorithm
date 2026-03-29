import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int INF = 1000000000;
    static int N,M,X,Y;
    static List<List<Edge>> graph;

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);    //집의 개수
        M = Integer.parseInt(tokens[1]);    //도로의 개수(양방향)
        X = Integer.parseInt(tokens[2]);    //하루에 최대 걸을 수 있는 양
        Y = Integer.parseInt(tokens[3]);    //성현이의 집

        graph = new ArrayList<>();
        for(int i=0;i<N;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            int c = Integer.parseInt(tokens[2]);

            graph.get(a).add(new Edge(b,c));
            graph.get(b).add(new Edge(a,c));
        }

        int [] distance = dijkstra();   //Y 도시부터 각 도시까지 이동 가능한 모든 거리
        Arrays.sort(distance);  //낮은값부터...

        //짧은 도시부터 방문하면서 최소 날짜 계산
        int idx = 1;
        int day = 1;
        int walkToday = 0;  //오늘 걸음 수

        boolean canReach = true;
        while(idx < N){
            if(distance[idx] == INF){   //도달할 수 없는 도시
                canReach = false;
                break;
            }
            int remainWalk = X - walkToday; //오늘 남은 걸음 수
            int needWalk = (distance[idx] - distance[0]) * 2;   //해당 도시를 왕복하기 위해 필요한 걸음 수

            if(needWalk > X){
                canReach = false;
                break;
            }
            if(remainWalk < needWalk){  //내일 걸어야 함.
                day++;
                walkToday = 0;
            }else{
                //오늘 걸음
                idx++;
                walkToday += needWalk;
            }
        }

        if(canReach){
            bw.write(day+"\n");
        }else{
            bw.write("-1\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    //Y번 도시에서 다른 도시로 이동할 수 있는 최소값 계산
    static int [] dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int [] dist = new int[N];
        Arrays.fill(dist,INF);
        dist[Y] = 0;
        pq.add(new Node(Y,0));

        while(!pq.isEmpty()){
            Node curr = pq.poll();
            if(dist[curr.city] < curr.totalCost) continue;

            for(Edge edge : graph.get(curr.city)){
                int nextCity = edge.to;
                int nextTotalCost = curr.totalCost + edge.cost;

                if(dist[nextCity] > nextTotalCost){
                    dist[nextCity] = nextTotalCost;
                    pq.add(new Node(nextCity, dist[nextCity]));
                }
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node>{
        int city;
        int totalCost;

        @Override
        public String toString() {
            return "Node{" +
                "city=" + city +
                ", totalCost=" + totalCost +
                '}';
        }

        @Override
        public int compareTo(Node o) {
            return this.totalCost - o.totalCost;
        }

        public Node(int city, int totalCost) {
            this.city = city;
            this.totalCost = totalCost;
        }
    }

    static class Edge{
        int to;
        int cost;

        @Override
        public String toString() {
            return "Edge{" +
                "to=" + to +
                ", cost=" + cost +
                '}';
        }

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}