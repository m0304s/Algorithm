import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static char [][] map;
	static int [][][] dist;	//dist[x][y][direction] = 설치거울개수
	static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};
    
    static boolean firstDoor;
    static final int INF = 1000000000;
    static int startX, startY, endX, endY;
    
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String [] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		dist = new int[N][N][4];
		firstDoor = true;
		
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = input.charAt(j);
				
				if(map[i][j] == '#') {
					if(firstDoor) {
						startX = i; startY = j;
						firstDoor = false;
					}else {
						endX = i; endY = j;
					}
				}
				Arrays.fill(dist[i][j],INF);
			}
		}
		
		bw.write(dijkstra()+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int d=0;d<4;d++) {
			pq.add(new Node(startX, startY, d, 0));
			dist[startX][startY][d] = 0;
		}
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if(curr.count > dist[curr.x][curr.y][curr.dir]) continue;
			
			if(curr.x == endX && curr.y == endY) {
				return curr.count;
			}
			
			int nx = curr.x + dx[curr.dir];
			int ny = curr.y + dy[curr.dir];
			
			if(!inRange(nx,ny) || map[nx][ny] == '*') continue;
			
			//거울을 설치하지 않거나 빈 공간이라서 통과하는 경우
			if(dist[nx][ny][curr.dir] > curr.count) {
				dist[nx][ny][curr.dir] = curr.count;
				pq.add(new Node(nx, ny, curr.dir, curr.count));
			}
			
			//거울을 설치할 수 있는 위치인 경우
			if(map[nx][ny] == '!') {
				int [] nextDirs = getNextDir(curr.dir);
				for(int nextDir : nextDirs) {
					if(dist[nx][ny][nextDir] > curr.count + 1) {
						dist[nx][ny][nextDir] = curr.count + 1;
						pq.add(new Node(nx, ny, nextDir, curr.count+1));
					}
				}
			}
		}
		return -1;
	}
	
	static boolean inRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	static int [] getNextDir(int dir) {
		if(dir == 0 || dir == 1)
			return new int[] {2,3};
		
		return new int[] {0,1};
	}
	
	static class Node implements Comparable<Node>{
		int x,y,dir,count;

		public Node(int x, int y, int dir, int count) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", dir=" + dir + ", count=" + count + "]";
		}
		
		public int compareTo(Node o) {
			return this.count - o.count;
		}
	}
}