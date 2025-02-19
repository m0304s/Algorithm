import java.io.*;
import java.util.*;

public class Solution {

	/**
	 * 어느 사다리를 고르면 X표시에 도착하게 되는지..체크
	 * 문제 전체 설계 : BFS 기법 사용
	 * -> 도착 지점으로부터 역으로 BFS 사용
	 * -> 기본적으로 가로선은 왼쪽과 오른쪽이 동시에 올 수 없음
	 * 
	 * [도착지X좌표][도착지Y좌표] 지점부터 탐색 시작
	 * IF 좌우 방향 체크하였을 때 사다리가 존재?
	 * 		갈 수 있는 만큼 좌표 이동
	 * ELSE
	 * 		한칸 위로 이동
	 * 
	 * 만약 X좌표가 0이 되면, 그때의 Y좌표를 출력
	 * @param args
	 */
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int [][] map;
	private static final int SIZE = 100,WALL = 0,LADDER = 1;
	
	private static int [] dx = {0,0};
	private static int [] dy = {-1,1};
	
	private static class Node{
		int x,y;
		public Node(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException{
		int T = 10;
		for(int t=1;t<=T;t++) {
			br.readLine();
			int answer = solution();
			bw.write("#"+t+" "+answer+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int solution() throws IOException{
		Node endPoint = createMap();
		int x = endPoint.x;
		int y = endPoint.y;
        int answer = 0;

        while(true){
            if(x==0){
                answer = y;
                break;
            }
            for(int i=0;i<2;i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!inRange(nx,ny)) continue;
                if(map[nx][ny] != LADDER) continue;

                while(inRange(nx,ny) && map[nx][ny] == LADDER){
                    y = ny;
                    ny+=dy[i];
                }
                break;
            }
            x+=-1;   //좌우 먼저 탐색 후 위칸으로 이동
        }
        return answer;
	}
	
	private static Node createMap() throws IOException{
		map = new int[SIZE][SIZE];
		Node endPoint = null;
		for(int i=0;i<SIZE;i++) {
			String [] tokens = br.readLine().split(" ");
			for(int j=0;j<SIZE;j++) {
				map[i][j] = Integer.parseInt(tokens[j]);
				if(map[i][j] == 2 && i== SIZE-1) {
					endPoint = new Node(i,j);
				}
			}
		}
		return endPoint;
	}
	
	private static boolean inRange(int x,int y) {
		return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
	}

}