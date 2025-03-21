import java.io.*;
import java.util.*;

public class Solution {
	private static final int [] dx = {0,0,-1,1};
	private static final int [] dy = {-1,1,0,0};
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int N;
	private static int [][] map;
	private static int [][] dist;
	
	private static class Node implements Comparable<Node>{
		int x,y,cost;
		
		public Node(int x,int y,int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	public static void main(String [] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			int answer = solution();
			bw.write("#"+t+" "+answer+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int solution() throws IOException{
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dist = new int[N][N];
		
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = input.charAt(j)-'0';
			}
		}
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0,0,0));
		
		for(int i=0;i<N;i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		dist[0][0] = 0;
		while(!pq.isEmpty()) {
			Node curNode = pq.poll(); 
			
			if(curNode.cost > dist[curNode.x][curNode.y]) continue;
			for(int d=0;d<4;d++) {
				int nx = curNode.x + dx[d];
				int ny = curNode.y + dy[d];
				
				if(!inRange(nx,ny)) continue;
				
				if(dist[nx][ny] > curNode.cost + map[nx][ny]) {
					dist[nx][ny] = curNode.cost + map[nx][ny];
					pq.add(new Node(nx,ny,dist[nx][ny]));
				}
			}
		}
		return dist[N-1][N-1];
	}
	
	private static boolean inRange(int x,int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}

