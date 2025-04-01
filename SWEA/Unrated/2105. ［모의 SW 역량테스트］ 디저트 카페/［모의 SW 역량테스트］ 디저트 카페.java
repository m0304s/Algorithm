import java.io.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N,maxCnt;
	static boolean [] desert;
	static boolean [][] visited;
	static int [][] arr;
	
	static int [] dx = {1,1,-1,-1};
	static int [] dy = {1,-1,-1,1};
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			int result= solution();
			bw.write("#"+t+" "+result+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int solution() throws IOException{
		N = Integer.parseInt(br.readLine());
		maxCnt = -1;
		arr = new int[N][N];
		for(int i=0;i<N;i++) {
			String [] tokens = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				desert = new boolean[101];
				visited = new boolean[N][N];
				desert[arr[i][j]] = true;
				visited[i][j] = true;
				dfs(i,j,i,j,0,1);
			}
		}
		return maxCnt;
	}
	
	static void dfs(int x,int y,int startX, int startY, int dir, int count) {
		for(int d=dir;d<=dir+1;d++) {
			if(d == 4) break;
			
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(!inRange(nx,ny)) continue;
			if(nx == startX && ny == startY) {
				maxCnt = Math.max(maxCnt, count);
				return;
			}
			
			if(desert[arr[nx][ny]]) continue;
			if(visited[nx][ny]) continue;
			
			visited[nx][ny] = true;
			desert[arr[nx][ny]] = true;
			dfs(nx,ny,startX,startY,d,count+1);
			desert[arr[nx][ny]] = false;
			visited[nx][ny] = false;
		}
	}
	
	static boolean inRange(int x,int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
