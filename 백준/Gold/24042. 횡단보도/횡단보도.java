import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N,M;
	static final long INF = Long.MAX_VALUE;
	static List<List<Edge>> graph;
	
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
			int u = Integer.parseInt(tokens[0]);
			int v = Integer.parseInt(tokens[1]);
			
			graph.get(u).add(new Edge(v, i));
			graph.get(v).add(new Edge(u, i));
		}
		
		long ans = dijkstra();
		
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static long dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		long [] dist = new long[N+1];
		Arrays.fill(dist, INF);
		dist[1] = 0;
		
		pq.add(new Node(1, 0));
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if(dist[curr.to] < curr.totalTime) continue;
			if(curr.to == N) return dist[curr.to];
			
			for(Edge next : graph.get(curr.to)) {
				long waitTime = (next.timeIdx - (curr.totalTime % M) + M) % M;
				long nextTime = curr.totalTime + waitTime + 1;
				
				if(dist[next.to] > nextTime) {
					dist[next.to] = nextTime;
					pq.add(new Node(next.to, nextTime));
				}
			}
		}
		
		return dist[N];
	}
	
	static class Edge{
		int to;
		int timeIdx;	//이 횡단보도가 켜지는 순서
		
		public Edge(int to, int timeIdx) {
			super();
			this.to = to;
			this.timeIdx = timeIdx;
		}
		
		@Override
		public String toString() {
			return "Edge [to=" + to + ", timeIdx=" + timeIdx + "]";
		}
	}
	
	static class Node implements Comparable<Node>{
		int to;
		long totalTime;	//현재까지 걸린 시간
		public Node(int to, long totalTime) {
			super();
			this.to = to;
			this.totalTime = totalTime;
		}
		
		@Override
		public String toString() {
			return "Node [to=" + to + ", totalTime=" + totalTime + "]";
		}
		
		@Override
		public int compareTo(Node o) {
			return Long.compare(this.totalTime, o.totalTime);
		}
	}
}