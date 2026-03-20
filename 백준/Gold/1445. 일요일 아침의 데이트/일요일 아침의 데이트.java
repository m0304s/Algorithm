import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N, M;
	static char [][] map;
	static boolean [][] visited;
	static boolean [][] isNearGarbage;
	
	static int [] dx = {-1,1,0,0};
	static int [] dy = {0,0,-1,1};
	
	static Node start, end;
	
	public static void main(String [] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		
		map = new char[N][M];
		visited = new boolean[N][M];
		isNearGarbage = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = input.charAt(j);
				
				if(map[i][j] == 'S') {
					start = new Node(i,j,0,0);
				}else if(map[i][j] == 'F') {
					end = new Node(i,j,0,0);
				}
			}
		}
		
		checkIsNearGarbage();
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(start);
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if(visited[curr.x][curr.y]) continue;
			visited[curr.x][curr.y] = true;
			
			if(map[curr.x][curr.y] == 'F') {
				bw.write(curr.garbageCount + " " + curr.nearGarbageCount+"\n");
				break;
			}
			
			for(int d=0;d<4;d++) {
				int nx = curr.x + dx[d];
				int ny = curr.y + dy[d];
				
				if(!inRange(nx, ny) || visited[nx][ny]) continue;
				
				int nextG = curr.garbageCount;
				int nextNG = curr.nearGarbageCount;
				
				if(map[nx][ny] == 'g') {
					nextG++;
				}else if(map[nx][ny] == '.') {
					if(isNearGarbage(nx, ny)) {
						nextNG++;
					}
				}
				
				pq.add(new Node(nx, ny, nextG, nextNG));
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void checkIsNearGarbage() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 'g') {
					for(int d=0;d<4;d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						
						if(!inRange(nx, ny)) continue;
						
						isNearGarbage[nx][ny] = true;
					}
				}
			}
		}
	}
	
	static boolean isNearGarbage(int x, int y) {
		return isNearGarbage[x][y];
	}
	
	static boolean inRange(int x,int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
	
	static class Node implements Comparable<Node>{
		int x,y;
		int garbageCount;
		int nearGarbageCount;
		
		public int compareTo(Node o) {
			if(this.garbageCount == o.garbageCount) {
				return this.nearGarbageCount - o.nearGarbageCount;
			}
			
			return this.garbageCount - o.garbageCount;
		}
		
		public Node(int x, int y, int garbageCount, int nearGarbageCount) {
			super();
			this.x = x;
			this.y = y;
			this.garbageCount = garbageCount;
			this.nearGarbageCount = nearGarbageCount;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", garbageCount=" + garbageCount + ", nearGarbageCount="
					+ nearGarbageCount + "]";
		}
	}
}