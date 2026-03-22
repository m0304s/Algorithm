import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N,M,K;
	
	//면접장까지의 거리 => 그 도시에서 도달 가능한 가장 가까운 면접장까지의 최단 거리
	//면접장까지의 거리 중 가장 먼 도시에서 오는 면접자에게 교통비를 줘야 함.
	//면접장까지 거리가 가장 먼 도시의 번호를 출력
	
	static List<List<Edge>> graph;
	static boolean[] interviewCity;
	
	public static void main(String [] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		K = Integer.parseInt(tokens[2]);
		
		interviewCity = new boolean[N+1];
		
		graph = new ArrayList<>();
		for(int i=0;i<=N;i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0;i<M;i++) {
			tokens = br.readLine().split(" ");
			int u = Integer.parseInt(tokens[0]);
			int v = Integer.parseInt(tokens[1]);
			int k = Integer.parseInt(tokens[2]);
			
			//역방향 다익스트라
			graph.get(v).add(new Edge(v, u, k));
		}
		
		//면접장이 배치된 도시의 번호
		tokens = br.readLine().split(" ");
		for(int i=0;i<K;i++) {
			interviewCity[Integer.parseInt(tokens[i])] = true;
		}
		
		dijkstra();
	}
	
	private static void dijkstra() throws IOException{
		long [] dist = new long[N+1];
		Arrays.fill(dist, Long.MAX_VALUE);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int i=1;i<=N;i++) {
			if(interviewCity[i]) {
				dist[i] = 0;
				Node node = new Node(i, 0);
				pq.add(node);
			}
		}
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if(dist[curr.nextCity] < curr.totalCost) continue;
			
			for(Edge edge : graph.get(curr.nextCity)) {
				long nextCost = curr.totalCost + edge.cost;
				int nextCity = edge.to;
				
				if(dist[nextCity] > nextCost) {
					dist[nextCity] = nextCost;
					Node next = new Node(nextCity, nextCost);
					pq.add(next);
				}
			}
		}
		
		long maxDist = -1;
		int cityNum = 0;
		
		for(int i=1;i<=N;i++) {
			if(dist[i] > maxDist) {
				maxDist = dist[i];
				cityNum = i;
			}
		}
		
		bw.write(cityNum+"\n" + maxDist+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static class Edge{
		int from;
		int to;
		int cost;
		
		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", cost=" + cost + "]";
		}
	}
	
	static class Node implements Comparable<Node>{
		int nextCity;
		long totalCost;
		public Node(int nextCity, long totalCost) {
			super();
			this.nextCity = nextCity;
			this.totalCost = totalCost;
		}
		
		public int compareTo(Node o) {
			return Long.compare(this.totalCost, o.totalCost);
		}
	}
}