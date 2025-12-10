import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int R, C;
    static char[][] map;
    static boolean[] visitedAlpha = new boolean[26]; // 알파벳 중복 체크 (A~Z)
    static int maxCount = 0; // 최대 칸 수 저장
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		inputData();
		visitedAlpha[map[0][0] - 'A'] = true;
		
		findMaxCount(0,0,1);
		
		bw.write(maxCount+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	/**
	 * 좌측 상단에서 시작해서 말이 최대한 몇 칸 지날 수 있는지 구하는 함수
	 */
	static void findMaxCount(int x,int y,int count) {
		maxCount = Math.max(count, maxCount);
		
		for(int d=0;d<4;d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(!inRange(nx,ny)) continue;
			
			int alphaIdx = map[nx][ny] - 'A';
			
			//만약 아직 방문하지 않은 지점이라면..
			if(!visitedAlpha[alphaIdx]) {
				visitedAlpha[alphaIdx] = true;
				findMaxCount(nx, ny, count+1);
				visitedAlpha[alphaIdx] = false;
			}
		}
	}
	
	/**
	 * 좌표가 보드의 범위를 벗어나는지 벗어나지 않는지 체크하는 함수
	 */
	static boolean inRange(int x,int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}
	
	/**
	 * 보드 초기 정보와 각 칸의 정보를 입력받는 함수
	 * @throws IOException
	 */
	static void inputData() throws IOException {
        String[] tokens = br.readLine().split(" ");
        R = Integer.parseInt(tokens[0]);
        C = Integer.parseInt(tokens[1]);
        
        map = new char[R][C];
        
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
            }
        }
    }
}