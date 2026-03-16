import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N,M;
	static final int INF = 1000000000;
	static int [] dist;
	static ArrayList<ArrayList<Edge>> graph;
	
	public static void main(String[] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		
		graph = new ArrayList<>();
		
		for(int i=0;i<=N;i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0;i<M;i++) {
			tokens = br.readLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			int c = Integer.parseInt(tokens[2]);
			
			graph.get(a).add(new Edge(a, b, c));
			graph.get(b).add(new Edge(b, a, c));
		}
		
		dijkstra();
		
		bw.write(dist[N]+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void dijkstra() {
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		dist[1] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if(curr.city == N) return;
			if(dist[curr.city] < curr.totalCost) continue;
			
			for(Edge edge : graph.get(curr.city)) {
				int nextCost = curr.totalCost + edge.cost;
				
				if(nextCost < dist[edge.to]) {
					dist[edge.to] = nextCost;
					pq.add(new Node(edge.to, nextCost));
				}
			}
		}
	}
	
	static class Node implements Comparable<Node>{
		int city;
		int totalCost;
		
		public int compareTo(Node o) {
			return this.totalCost - o.totalCost;
		}
		
		public Node(int city, int totalCost) {
			super();
			this.city = city;
			this.totalCost = totalCost;
		}

		@Override
		public String toString() {
			return "Node [city=" + city + ", totalCost=" + totalCost + "]";
		}
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
}