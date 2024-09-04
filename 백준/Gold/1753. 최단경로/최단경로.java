import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static class Node{
        int idx;
        int cost;
        public Node(int idx,int cost){
            this.idx = idx;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int v = Integer.parseInt(tokens[0]);    //정점의 개수
        int e = Integer.parseInt(tokens[1]);    //간선의 개수
        int start = Integer.parseInt(br.readLine());    //시작 정점의 번호

        // 1.인접리스트를 이용한 그래프 초기화
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        // 노드의 번호가 1부터 시작하므로, 0번 인덱스 부분을 임의로 만들어 놓기만 하면 된다.
        for(int i=0;i<v+1;i++){
            graph.add(new ArrayList<>());
        }

        // 그래프에 값을 넣는다.
        for(int i=0;i<e;i++){
            // a -> b 로 가는 비용은 cost이다.
            tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            int cost = Integer.parseInt(tokens[2]);

            graph.get(a).add(new Node(b, cost));
        }
        // 2.방문 여부를 확인할 boolean 배열, start 노드부터 end 노드까지 최소 거리를 저장할 배열을 만든다.
        boolean [] visited = new boolean[v+1];
        int [] dist = new int[v+1];

        // 3.최소 거리 정보를 담을 배열을 초기화 한다.
        for(int i=0;i<v+1;i++){
            dist[i] = Integer.MAX_VALUE;
        }
        // 출발 지점의 비용은 0으로 시작한다.
        dist[start] = 0;

        // 4.다익스트라 알고리즘을 시작한다.
        // 모든 노드를 방문하면 종료하기 때문에 노드의 개수만큼만 반복을 한다.
        for(int i=0;i<v;i++){
            // 4 - 1. 현재 거리 비용 중 최소인 지점을 선택한다.
			// 해당 노드가 가지고 있는 현재 비용.
            int nodeVaue = Integer.MAX_VALUE;
            int nodeIdx = 0;
            for(int j=1;j<v+1;j++){
                // 해당 노드를 방문하지 않았고, 현재 모든 거리비용 중 최솟값을 찾는다.
                if(!visited[j] && dist[j] < nodeVaue){
                    nodeIdx = j;
                    nodeVaue = dist[j];
                }
            }
            visited[nodeIdx] = true;

            // 4 - 2. 해당 지점을 기준으로 인접 노드의 최소 거리 값을 갱신한다.
            for(int j=0;j<graph.get(nodeIdx).size();j++){
                Node adxNode = graph.get(nodeIdx).get(j);
                // 인접 노드가 현재 가지는 최소 비용과
				// 현재 선택된 노드의 값 + 현재 노드에서 인접 노드로 가는 값을 비교하여 더 작은 값으로 갱신한다.
                if(dist[adxNode.idx]> dist[nodeIdx] + adxNode.cost){
                    dist[adxNode.idx] = dist[nodeIdx] + adxNode.cost;
                }
            }
        }
        for(int i=1;i<v+1;i++){
            if(dist[i] == Integer.MAX_VALUE) bw.write("INF\n");
            else bw.write(dist[i]+"\n");
        }
        bw.flush();
    }
}
