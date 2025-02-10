import java.io.*;
import java.util.*;

class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static class Node implements Comparable<Node>{
		int idx,cost;
		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	private static int N,M,K;
	private static ArrayList<ArrayList<Node>> graph;
	private static PriorityQueue<Integer>[] dist;
    public static void main(String [] args) throws IOException{
    	String [] tokens = br.readLine().split(" ");
    	N = Integer.parseInt(tokens[0]);
    	M = Integer.parseInt(tokens[1]);
    	K = Integer.parseInt(tokens[2]);
    	dist = new PriorityQueue[N+1];
    	graph = new ArrayList<>();
    	
    	for(int i=0;i<=N;i++) {
    		graph.add(new ArrayList<>());
    		dist[i] = new PriorityQueue<>(Collections.reverseOrder());
    	}
    	
    	for(int i=0;i<M;i++) {
    		tokens = br.readLine().split(" ");
    		int a = Integer.parseInt(tokens[0]);
    		int b = Integer.parseInt(tokens[1]);
    		int c = Integer.parseInt(tokens[2]);
    		
    		graph.get(a).add(new Node(b,c));
    	}
    	
    	dijkstra(1);
    	
    	for(int i=1;i<=N;i++) {
    		if(dist[i].size()<K) bw.write("-1\n");
    		else bw.write(dist[i].peek()+"\n");
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    
    private static void dijkstra(int start) {
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	dist[start].add(0);
    	pq.add(new Node(start,0));
    	
    	while(!pq.isEmpty()) {
    		Node curNode = pq.poll();
    		
    		for(Node nextNode : graph.get(curNode.idx)) {
    			int newCost = curNode.cost + nextNode.cost;
    			
    			if(dist[nextNode.idx].size() < K) {
    				dist[nextNode.idx].add(newCost);
    				pq.add(new Node(nextNode.idx,newCost));
    			}else if (dist[nextNode.idx].peek() > newCost) {
                    dist[nextNode.idx].poll();
                    dist[nextNode.idx].add(newCost);
                    pq.add(new Node(nextNode.idx, newCost));
                }
    		}
    	}
    }
}
