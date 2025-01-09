import java.io.*;
import java.util.*;
class Main
{
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	//동,서,북,남
	private static int [] dx = {-1,1,0,0};
	private static int [] dy = {0,0,-1,1};
	
	/**
	 * 각 선생님들은 자신의 위치에서 상, 하, 좌, 우 4가지 방향으로 감시를 진행
	 * 복도에 장애물이 위치한 경우, 선생님은 장애물 뒤편에 숨어 있는 학생들은 볼 수 없다
	 * 아무리 멀리 있더라도 장애물로 막히기 전까지의 학생들은 모두 볼 수 있다
	 * 선생님 : T 학생 : S 장애물 : O
	 * 빈 칸 중에서 장애물을 설치할 위치를 골라, 정확히 3개의 장애물을 설치
	 * 3개의 장애물을 설치하여 모든 학생들을 감시로부터 피하도록 할 수 있는지 계산
	 * @param args
	 */
	
	private static String[][] map;
	private static int N;
	
	private static List<Node> teacherList;
	private static boolean answer;
	
	private static class Node{
		int x,y;
		public Node(int x,int y) {
			this.x = x;
			this.y =y;
		}
	}
	
	public static void main(String [] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		map = new String[N][N];
		teacherList = new ArrayList<Node>();
		
		for(int i=0;i<N;i++) {
			String [] tokens = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				map[i][j] = tokens[j];
				if(map[i][j].equals("T")) {
					teacherList.add(new Node(i,j));
				}
			}
		}
		backtracking(0,0,0);
		if(answer) {
			bw.write("YES\n");
		}else {
			bw.write("NO\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void backtracking(int i,int j,int depth) {
		if(answer) return;
		if(depth == 3) {	//3개의 장애물을 다 설치했을때
			if(canAvoidTeacher()) {
				answer = true;
			}
			return;
		}
		
		for(int x=i;x<N;x++) {
			for(int y=0;y<N;y++) {
				if(x==i&&y<j) continue;
				if(map[x][y].equals("X")) {
					map[x][y] = "O";
					backtracking(x,y+1,depth+1);
					map[x][y] = "X";
				}
			}
		}
	}
	
	private static boolean canAvoidTeacher() {
		for(Node teacher : teacherList) {
			int curX = teacher.x;
			int curY = teacher.y;
			for(int d=0;d<4;d++) {	//4방향 탐색
				if(check(d,curX,curY)) {	//선생님이 학생을 발견할 경우
					return false;
				}
			}
		}
		return true;
	}
	
	private static boolean check(int direction,int startX,int startY) {
		int nx = startX;
		int ny = startY;
		for(int i=0;i<N;i++) {
			nx += dx[direction];
			ny += dy[direction];
			
			if(!inRange(nx,ny)) continue;
			if(map[nx][ny].equals("O")) break;	//장애물을 만나면 뒷범위는 탐색할 필요가 없음
			if(map[nx][ny].equals("S")) return true;
		}
		return false;	//선생님이 학생을 발견하지 못함
	}
	
	private static boolean inRange(int x,int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}