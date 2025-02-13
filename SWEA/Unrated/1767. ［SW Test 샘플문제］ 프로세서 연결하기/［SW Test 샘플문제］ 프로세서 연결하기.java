import java.io.*;
import java.util.*;

public class Solution {
	private static final int BLANK = 0,CORE = 1, WIRE = 2;
	private static final int [] dx = {0,0,-1,1};
	private static final int [] dy = {-1,1,0,0};
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static ArrayList<Core> cores;
	private static int minLength,maxCore,N;
	private static int [][] map;
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
	
	private static class Node{
		int x,y;
		
		public Node(int x,int y) {
			this.x = x;
			this.y = y;
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
		map = new int[N][N];
		cores = new ArrayList<>();
		minLength = Integer.MAX_VALUE;
		maxCore = Integer.MIN_VALUE;
		for(int i=0;i<N;i++) {
			String [] tokens = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(tokens[j]);
				if(map[i][j] == CORE && i != 0 && j != 0 && i != N-1 && j != N-1) {
					cores.add(new Core(i,j));
				}
			}
		}
		backtracking(0,0,0);
	}
	
	private static void backtracking(int depth, int coreCnt,int totalLength) {
		if(depth == cores.size()) {
			if(maxCore < coreCnt) {
				minLength = totalLength;
				maxCore = coreCnt;
			}else if(maxCore == coreCnt) {
				minLength = Math.min(minLength, totalLength);
			}
			return;
		}
		
		backtracking(depth+1,coreCnt,totalLength);
		
		Core core = cores.get(depth);
		for(int d=0;d<4;d++) {
			int length = createLine(core,d,WIRE);
			if(length != -1) {
				backtracking(depth+1,coreCnt+1,totalLength+length);
				createLine(core,d,BLANK);
			}
		}
	}
	
	private static int createLine(Core core, int d, int value) {
		int length = 0;
		List<Node> path = new ArrayList<>();
		
		int x = core.x;
		int y = core.y;
		
		while(inRange(x + dx[d], y + dy[d])) {
			x += dx[d];
			y += dy[d];
			
			if(value == WIRE && map[x][y] != BLANK) {
				for(Node node : path) {
					map[node.x][node.y] = BLANK; 
				}
				return -1;
			}
			
			map[x][y] = value;
			path.add(new Node(x,y));
			length++;
		}
		return length;
	}
	
	private static boolean inRange(int x,int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}

