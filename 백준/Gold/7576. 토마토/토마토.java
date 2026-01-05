import java.io.*;
import java.util.*;

public class Main {
	static final int RIPE = 1, UNRIPE = 0, BLANK = -1;
	
	static final int [] dx = {0,0,-1,1};
	static final int [] dy = {-1,1,0,0};
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N,M;
	static int [][] map;
	static Queue<Tomato> queue;
	
	public static void main(String[] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		M = Integer.parseInt(tokens[0]);
		N = Integer.parseInt(tokens[1]);
		
		map = new int[N][M];
		queue = new ArrayDeque<>();
		
		for(int i=0;i<N;i++) {
			tokens = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(tokens[j]);
				
				if(map[i][j] == RIPE) {
					queue.add(new Tomato(i,j,0));
				}
			}
		}
		
		bfs();
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static boolean inRange(int x,int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
	
	static void bfs() throws IOException{
		int day = 0;
		
		while(!queue.isEmpty()) {
			Tomato curNode = queue.poll();
			day = curNode.day;
			
			for(int d=0;d<4;d++) {
				int nx = curNode.x + dx[d];
				int ny = curNode.y + dy[d];
				
				if(!inRange(nx,ny)) continue;
				if(map[nx][ny] == UNRIPE) {
					map[nx][ny] = RIPE;
					queue.add(new Tomato(nx,ny,day+1));	
				}
			}
		}
		
		if(!checkStatus()) {	//토마토가 모두 익지 못하는 상황이라면
			bw.write("-1\n");
		}else {
			bw.write(day+"\n");
		}
	}
	
	static boolean checkStatus() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == UNRIPE) return false;
			}
		}
		return true;
	}
	
	static class Tomato{
		int x,y,day;

		public Tomato(int x, int y, int day) {
			super();
			this.x = x;
			this.y = y;
			this.day = day;
		}

		@Override
		public String toString() {
			return "Tomato [x=" + x + ", y=" + y + ", day=" + day + "]";
		}
	}
}