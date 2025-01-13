import java.io.*;
import java.util.*;

class Main {
	static int n, q;
	static ArrayList <Node> al[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String [] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		al = new ArrayList[n];
		
		for(int i = 0; i < n; i++) {
			al[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			int usado = Integer.parseInt(st.nextToken());
			
			al[start-1].add(new Node(next-1, usado));
			al[next-1].add(new Node(start-1, usado));
		}
		
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			sb.append(findUsado(k,v-1) + "\n");
		}
		
		System.out.print(sb);
	}
	
	private static int findUsado (int k, int idx) {
		int min = 0;
		int cnt = 0;
		
		boolean visited[] = new boolean[n];
		visited[idx] = true;
		
		Queue<Node> q = new LinkedList<>();
		
		q.add(new Node(idx, Integer.MAX_VALUE));
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			for(Node next : al[node.now]) {
				if(!visited[next.now]) {
					visited[next.now] = true;
					int newUsado = Math.min(next.usado, node.usado);
					if(newUsado >= k) {
						q.add(new Node(next.now, newUsado));
						cnt++;
					}
				}
			}
		}
		
		return cnt;
	}
	
	
	
	static class Node{
		int now;
		int usado;
		Node(int now, int usado){
			this.now = now;
			this.usado = usado;
		}
	}
}
