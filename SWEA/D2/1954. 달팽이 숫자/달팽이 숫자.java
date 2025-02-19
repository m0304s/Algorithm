import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

	/**
	 * 배열에 저장되어야 하는 값은 1~N^2까지
	 * 출발지점은 (0,0)
	 * 새로운 좌표를 계산할때, 방향 전환은 오른쪽 방향, 아랫방향, 왼쪽 방향, 윗쪽 방향으로 변함
	 * delta 좌표 변환을 이용해서
	 * 
	 * 만약 지금까지 작성한 숫자가 N^2을 넘지 않는 다면
	 * 	숫자를 작성할 새로운 좌표 X,Y 계산
	 *	만약 새로운 좌표 X,Y가 배열 바깥이거나 이미 배열에 숫자가 작성된 경우 방향 전환
	 *	그렇지 않다면 배열에 숫자 작성
	 *
	 * 모든 숫자가 작성된 경우 배열 출력
	 * @param args
	 */
	
	private static int [] dx = {0,1,0,-1};
	private static int [] dy = {1,0,-1,0};
	private static int [][] map;
	private static boolean [][] visited;
	private static int N;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			bw.write("#"+t+"\n");
			solution();
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void solution() throws IOException{
		visited[0][0] = true;
		int x = 0;
		int y = 0;
		int totalNum = N * N;
		int dir = 0;
		int num = 1;
		map[x][y] = num;
		
		while(num < totalNum) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			if(!inRange(nx,ny) || visited[nx][ny]) {
				dir = (dir+1)%4;
				continue;
			}
			
			visited[nx][ny] = true;
			map[nx][ny] = ++num;
			x = nx;
			y = ny;
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				bw.write(map[i][j]+" ");
			}bw.write("\n");
		}
	}
	
	private static boolean inRange(int x,int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}