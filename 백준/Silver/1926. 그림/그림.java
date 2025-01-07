import java.io.*;
import java.util.*;

class Main
{
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int N,M,countOfPaint,sumOfPaint;
	private static int [][] map;
	private static boolean [][] visited;
	private static int [] dx = {-1,1,0,0};
	private static int [] dy = {0,0,-1,1};
	
	private static class Node{
		int x,y;
		
		public Node(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String [] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			tokens = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		
		countOfPaint = 0;
		sumOfPaint = Integer.MIN_VALUE;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					countOfPaint++;
					sumOfPaint = Math.max(sumOfPaint, bfs(i,j));
				}
			}
		}
		if(countOfPaint == 0) sumOfPaint = 0;
		bw.write(countOfPaint+"\n"+sumOfPaint+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int bfs(int i,int j) {
		Queue<Node> queue = new LinkedList<>();
		visited[i][j] = true;
		queue.add(new Node(i,j));
		int size = 0;
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			size++;
			for(int d=0;d<4;d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if(!inRange(nx,ny)) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny] == 0) continue;
				
				visited[nx][ny] = true;
				queue.add(new Node(nx,ny));
			}
		}
		return size;
	}
	
	private static boolean inRange(int x,int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
