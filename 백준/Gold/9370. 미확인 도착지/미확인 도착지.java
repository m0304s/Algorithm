import java.io.*;
import java.util.*;

public class Main {
	
	private static class Node implements Comparable<Node>{
		int idx;
		int cost;
		
		public Node(int idx,int cost) {
			this.idx = idx;
			this.cost = cost;
		}
		
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	private static class Road{
		int s,d;
		
		public Road(int s,int d) {
			this.s = s;
			this.d = d;
		}
	}
	
	private static class Result{
		int [] dist;
		int [] parent;
		
		public Result(int [] dist, int [] parent) {
			this.dist = dist;
			this.parent = parent;
		}
	}
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int N,M,T;
	private static int S,G,H;
	private static int MAX = 1000000000;

	
	private static ArrayList<ArrayList<Node>> graph;
	private static ArrayList<Integer> possibleTargets;
	
	public static void main(String[] args) throws IOException{
		int CASE = Integer.parseInt(br.readLine());
		
		for(int x=1;x<=CASE;x++) {
			String [] tokens = br.readLine().split(" ");
			N = Integer.parseInt(tokens[0]);	//교차로 수  
			M = Integer.parseInt(tokens[1]);	//도로 수
			T = Integer.parseInt(tokens[2]);	//목적지 후보의 수
			
			tokens = br.readLine().split(" ");
			S = Integer.parseInt(tokens[0]);	//출발지
			G = Integer.parseInt(tokens[1]);	//교차로 시작점
			H = Integer.parseInt(tokens[2]);	//교차로 종료점
			
			graph = new ArrayList<>();
			possibleTargets = new ArrayList<>();
			
			for(int i=0;i<=N;i++) {
				graph.add(new ArrayList<>());
			}
			
			for(int i=0;i<M;i++) {
				tokens = br.readLine().split(" ");
				int a = Integer.parseInt(tokens[0]);
				int b = Integer.parseInt(tokens[1]);
				int d = Integer.parseInt(tokens[2]);
				
				graph.get(a).add(new Node(b,d));
				graph.get(b).add(new Node(a,d));
			}
			
			for(int i=0;i<T;i++) {
				int t = Integer.parseInt(br.readLine());
				possibleTargets.add(t);
			}
			List<Integer> answer = new ArrayList<>();
			//가능한 목적지 후보들 각각 다익스트라를 통해 최단경로 구함 -> 최단 경로에 G->H가 있는지 체크
			
			int [] distFromS = dijkstra(S);
			int [] distFromG = dijkstra(G);
			int [] distFromH = dijkstra(H);
			
			for(int i=0;i<possibleTargets.size();i++) {
				int target = possibleTargets.get(i);
				
				int finalDistance = Math.min(distFromS[G] + distFromG[H] + distFromH[target],  distFromS[H] + distFromH[G] + distFromG[target]);
				
				if(finalDistance == distFromS[target]) answer.add(target);
			}
			Collections.sort(answer);
			
			for(int i=0;i<answer.size();i++) {
				bw.write(answer.get(i)+" ");
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int [] dijkstra(int start) {
		int [] dist = new int[N+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(dist, MAX);
		
		dist[start] = 0;
		pq.add(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			
			if(curNode.cost > dist[curNode.idx]) continue;
			
			for(int i=0;i<graph.get(curNode.idx).size();i++) {
				Node nextNode = graph.get(curNode.idx).get(i);
				
				if(dist[nextNode.idx] > curNode.cost + nextNode.cost) {
					dist[nextNode.idx] = curNode.cost + nextNode.cost;
					pq.add(new Node(nextNode.idx,dist[nextNode.idx]));
				}
			}
		}
		return dist;
	}
}


