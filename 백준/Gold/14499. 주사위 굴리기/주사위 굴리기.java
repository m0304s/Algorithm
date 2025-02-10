import java.io.*;
import java.util.*;

class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int [] dx = {0,0,0,-1,1};
	private static int [] dy = {0,1,-1,0,0};
	private static int N,M,K,x,y;
	private static int [][] map;
	private static int [] dice;
	
	public static void main(String [] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		map = new int[N][M];
		dice = new int[7];
		
		x = Integer.parseInt(tokens[2]);	//현재 밑바닥 위치
		y = Integer.parseInt(tokens[3]);	//현재 밑바닥 위치
		
		K = Integer.parseInt(tokens[4]);
		
		for(int i=0;i<N;i++) {
			tokens = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		
		tokens = br.readLine().split(" ");
		for(int i=0;i<tokens.length;i++) {
			move(tokens[i]);
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void move(String command) throws IOException{
	    int nx, ny;

	    // 동쪽으로 굴림
	    if (command.equals("1")) { 
	        nx = x + dx[1];
	        ny = y + dy[1];

	        if (!inRange(nx, ny)) return;

	        // 동쪽으로 회전
	        int temp = dice[3];
	        dice[3] = dice[1];
	        dice[1] = dice[4];
	        dice[4] = dice[6];
	        dice[6] = temp;

	        if (map[nx][ny] == 0) {
	            map[nx][ny] = dice[6];
	        } else {
	            dice[6] = map[nx][ny];
	            map[nx][ny] = 0;
	        }
	        x = nx;
	        y = ny;
	    }

	    // 서쪽으로 굴림
	    else if (command.equals("2")) { 
	        nx = x + dx[2];
	        ny = y + dy[2];

	        if (!inRange(nx, ny)) return;

	     // 서쪽으로 굴림 (수정된 코드)
	        int temp = dice[3];
	        dice[3] = dice[6];
	        dice[6] = dice[4];
	        dice[4] = dice[1];
	        dice[1] = temp;


	        if (map[nx][ny] == 0) {
	            map[nx][ny] = dice[6];
	        } else {
	            dice[6] = map[nx][ny];
	            map[nx][ny] = 0;
	        }

	        x = nx;
	        y = ny;
	    }

	    // 북쪽으로 굴림
	    else if (command.equals("3")) { 
	        nx = x + dx[3];
	        ny = y + dy[3];

	        if (!inRange(nx, ny)) return;

	        // 북쪽으로 회전
	        int temp = dice[1];
	        dice[1] = dice[5];
	        dice[5] = dice[6];
	        dice[6] = dice[2];
	        dice[2] = temp;

	        if (map[nx][ny] == 0) {
	            map[nx][ny] = dice[6];
	        } else {
	            dice[6] = map[nx][ny];
	            map[nx][ny] = 0;
	        }

	        x = nx;
	        y = ny;
	    }

	    // 남쪽으로 굴림
	    else if (command.equals("4")) { 
	        nx = x + dx[4];
	        ny = y + dy[4];

	        if (!inRange(nx, ny)) return;

	        // 남쪽으로 회전
	        int temp = dice[2];
	        dice[2] = dice[6];
	        dice[6] = dice[5];
	        dice[5] = dice[1];
	        dice[1] = temp;

	        if (map[nx][ny] == 0) {
	            map[nx][ny] = dice[6];
	        } else {
	            dice[6] = map[nx][ny];
	            map[nx][ny] = 0;
	        }

	        x = nx;
	        y = ny;
	    }
	    
	    bw.write(dice[1]+"\n");
	}


	
	private static void debug() {
		for(int i=1;i<7;i++) {
			System.out.print(dice[i]+ " ");
		}System.out.println();
	}
	
	private static boolean inRange(int x,int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
