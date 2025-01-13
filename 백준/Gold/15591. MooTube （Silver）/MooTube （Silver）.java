import java.io.*;
import java.util.*;

class Main
{
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int N,Q;
	private static class Node{
		int node,weight;
		
		public Node(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}
	
	public static void main(String args[]) throws Exception
	{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		Q = Integer.parseInt(tokens[1]);
		ArrayList<ArrayList<Node>> graph = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0;i<N-1;i++) {
			tokens = br.readLine().split(" ");	//유사도 입력
			int p = Integer.parseInt(tokens[0])-1;
			int q = Integer.parseInt(tokens[1])-1;
			int r = Integer.parseInt(tokens[2]);
			
			graph.get(p).add(new Node(q,r));
			graph.get(q).add(new Node(p,r));
		}
		
		for(int i=0;i<Q;i++) {
			tokens = br.readLine().split(" ");
			int k = Integer.parseInt(tokens[0]);
			int v = Integer.parseInt(tokens[1])-1;
			
			int answer = bfs(k,v,graph);
			bw.write(answer+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int bfs(int k,int v,ArrayList<ArrayList<Node>> graph) {
		boolean [] visited = new boolean[N];
		Queue<Integer> queue = new LinkedList<>();
		visited[v] = true;
		queue.add(v);
		
		int count=0;
		
		while(!queue.isEmpty()) {
			int curNode = queue.poll();
			for(int i=0;i<graph.get(curNode).size();i++) {
				Node node = graph.get(curNode).get(i);
				int n = node.node;
				int w = node.weight;
				
				if(w<k || visited[n]) continue;
				
				count++;
				queue.add(n);
				visited[n] = true;
			}
		}
		return count;
	}
}