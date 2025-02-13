import java.io.*;
import java.util.*;

public class Solution {
	private static final int [] dx = {0,0,-1,1};
	private static final int [] dy = {-1,1,0,0};
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static ArrayList<Core> cores;
	private static int [][] map;
	private static int N;
	private static int maxCores,minLength;
	private static class Node{
		int x,y;
		
		public Node(int x,int y) {
			this.x = x;
			this.y =y;
		}
	}
	
	private static class Core{
		int x,y;
		
		public Core(int x,int y) {
			this.x = x;
			this.y = y;
		}
		
		public String toString() {
			return "X : " + this.x + " Y : " + this.y;
 		}
	}
	
	public static void main(String [] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			solution();
			bw.write("#"+t+" "+minLength+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void solution() throws IOException{
		N = Integer.parseInt(br.readLine());
		maxCores = Integer.MIN_VALUE;
		minLength = Integer.MAX_VALUE;
		
		map = new int[N][N];
		cores = new ArrayList<>();
		for(int i=0;i<N;i++) {
			String [] tokens = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(tokens[j]);
				if(map[i][j] == 1 && i != 0 && j != 0 && i != N-1 && j != N-1) {
					cores.add(new Core(i,j));
				}
			}
		}
		
		backtracking(0,0,0);
	}
	
	private static void backtracking(int depth, int coreCnt, int totalLength) {
		if(depth == cores.size()) {
			//전체 탐색할 수 있는 코어 탐색한 경우..
			if(maxCores < coreCnt) {
				maxCores = coreCnt;
				minLength = totalLength;
			}else if(maxCores == coreCnt) {
				minLength = Math.min(minLength, totalLength);
			}
			return;
		}
		
		Core currentCore = cores.get(depth);
		
		backtracking(depth+1,coreCnt,totalLength);
		
		for(int d=0;d<4;d++) {
			int wireLength = createLine(currentCore,d,2);
			
			if(wireLength != -1) {
				backtracking(depth+1,coreCnt+1,totalLength + wireLength);
				createLine(currentCore,d,0);
			}
		}
	}
	
	private static int createLine(Core core, int dir, int value) {
		int wireLength = 0;
		
		int x = core.x;
		int y = core.y;
		
		List<Node> path = new ArrayList<>();
		
		while(inRange(x + dx[dir],y+dy[dir])) {
			x += dx[dir];
			y += dy[dir];
			
			if(value == 2 && map[x][y] != 0) {
				for(Node node : path) {
					map[node.x][node.y] = 0; 
				}
				return -1;
			}
			
			map[x][y] = value;
			path.add(new Node(x,y));
			wireLength++;
		}
		return wireLength;
	}
	
	private static boolean inRange(int x,int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
