import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int [] dx = {0,0,-1,1};
	static int [] dy = {-1,1,0,0};
	static int [][] map;
	static int N,M,K;
	static final int WALL=1, BLANK=0;
	
	public static void main(String [] args) throws IOException{
		init();
		solution();
	}
	
	static void solution() throws IOException{
		//(1,1)에서 (N,M)위치까지 이동
		Queue<Node> queue = new ArrayDeque<>();
		boolean [][][] visited = new boolean[N][M][K+1];	// x, y, k(벽을 부순 횟수)
		
		Node startPoint = new Node(0,0,0,0);
		queue.add(startPoint);
		visited[0][0][0] = true;
		
		while(!queue.isEmpty()) {
			Node curNode = queue.poll();
			
			if(curNode.x == N-1 && curNode.y == M-1) {	//탈출구일 경우
				bw.write(curNode.distance+1+"\n");
				bw.flush();
				bw.close();
				br.close();
				
				return;
			}
			
			for(int d=0;d<4;d++) {
				int nx = curNode.x + dx[d];
				int ny = curNode.y + dy[d];
				
				if(!inRange(nx, ny)) continue;
				
				//만약 다음 칸이 빈칸이라면...
				if(map[nx][ny] == BLANK) {
					if(visited[nx][ny][curNode.k]) continue;
					
					queue.add(new Node(nx,ny,curNode.k,curNode.distance+1));
					visited[nx][ny][curNode.k] = true;
				}
				
				//벽을 부술 수 있고, 다음칸이 벽이라면..
				if(map[nx][ny] == WALL && curNode.k < K) {
					if(visited[nx][ny][curNode.k+1]) continue;
					
					queue.add(new Node(nx,ny,curNode.k+1,curNode.distance+1));
					visited[nx][ny][curNode.k+1] = true;
				}
			}
		}
		
		bw.write("-1\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static boolean inRange(int x,int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
	
	static void init() throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		K = Integer.parseInt(tokens[2]);
		
		map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = input.charAt(j)-'0';
			}
		}
	}
	
	static class Node{
		int x,y;	//좌표
		int k;		//벽을 부순 횟수
		int distance;
		public Node(int x, int y, int k, int distance) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
			this.distance = distance;
		}
	}
}
