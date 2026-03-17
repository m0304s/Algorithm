import java.io.*;
import java.util.ArrayDeque;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int [] dx = {-1,1,0,0};
	static int [] dy = {0,0,-1,1};
	
	static int N,M;
	static Node junan;
	static Node choco;
	static int [][] map;
	static boolean [][] visited;
	
	public static void main(String[] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		
		String[] coords = br.readLine().split(" ");
	    int x1 = Integer.parseInt(coords[0]) - 1;
	    int y1 = Integer.parseInt(coords[1]) - 1;
	    int x2 = Integer.parseInt(coords[2]) - 1;
	    int y2 = Integer.parseInt(coords[3]) - 1;
	    
	    junan = new Node(x1, y1,0);
	    choco = new Node(x2, y2,0);

	    map = new int[N][M];
	    visited = new boolean[N][M];
	    
	    for (int i = 0; i < N; i++) {
	        String input = br.readLine();
	        for (int j = 0; j < M; j++) {
	            char c = input.charAt(j);
	            if (c == '*') {
	                map[i][j] = 0; // 주난이 자리는 빈 공간과 같음
	            } else if (c == '#') {
	                map[i][j] = 7;
	            } else {
	                map[i][j] = c - '0';
	            }
	        }
	    }
	    
	    bw.write(bfs()+"\n");
	    bw.flush();
	    bw.close();
	    br.close();
	}
	
	private static int bfs() {
		ArrayDeque<Node> dq = new ArrayDeque<Node>();
		dq.add(new Node(junan.x, junan.y, 0));
		visited[junan.x][junan.y] = true;
		
		while(!dq.isEmpty()) {
			Node curr = dq.poll();
			
			if(curr.x == choco.x && curr.y == choco.y) {
				return curr.count;
			}
			
			for(int d=0;d<4;d++) {
				int nx = curr.x + dx[d];
				int ny = curr.y + dy[d];
				
				if(!inRange(nx,ny) || visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				
				if(map[nx][ny] == 0) {
					dq.addFirst(new Node(nx, ny, curr.count));
				}else {
					dq.addLast(new Node(nx,ny,curr.count+1));
				}
			}
		}
		
		return -1;
	}
	
	static boolean inRange(int x,int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
	
	static class Node{
		int x,y;
		int count;
		
		public Node(int x,int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
}