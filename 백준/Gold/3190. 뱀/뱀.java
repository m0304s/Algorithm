import java.io.*;
import java.util.*;

class Main {
	private static final int APPLE = 1,SNAKE=2;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static class Node{
		int x,y;
		public Node(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}

	//우 하 좌 상
	private static int [] dx = {0,1,0,-1};
	private static int [] dy = {1,0,-1,0};
	private static HashMap<Integer,String> commands;
	private static int N,K,L,direction;
	private static int [][] map;
	private static Deque<Node> snake;
	private static boolean flag = false;	//게임이 종료되는지 여부
	public static void main(String [] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new int[N][N];
		commands = new HashMap<>();
		snake = new LinkedList<>();
		for(int i=0;i<K;i++) {
			String [] tokens = br.readLine().split(" ");
			int x = Integer.parseInt(tokens[0])-1;
			int y = Integer.parseInt(tokens[1])-1;
			
			map[x][y] = APPLE;
		}
		
		L = Integer.parseInt(br.readLine());	//뱀의 방향 변환 정보
		for(int i=0;i<L;i++) {
			String [] tokens = br.readLine().split(" ");
			int time = Integer.parseInt(tokens[0]);
			commands.put(time,tokens[1]);
		}
		
		direction = 0;
		map[0][0] = SNAKE;
		snake.addFirst(new Node(0,0));
		
		int time = 0;
		while(!flag) {
			simulation();
			time++;
			if(flag) break;
			if(commands.containsKey(time)) {
				switchDirection(commands.get(time));
			}
		}
		bw.write(time+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	//우 하 좌 상
	private static void switchDirection(String switchDirection) {
		if (switchDirection.equals("L")) {
		    direction = (direction + 3) % 4; // 왼쪽 90도 회전
		} else if (switchDirection.equals("D")) {
		    direction = (direction + 1) % 4; // 오른쪽 90도 회전
		}
	}

	//1초간 진행되는 시뮬레이션
	private static void simulation() {
		Node snakeHead = snake.peekFirst();
		int nx = snakeHead.x + dx[direction];
		int ny = snakeHead.y + dy[direction];
		
		snake.addFirst(new Node(nx,ny));
		
		if(!inRange(nx,ny) || map[nx][ny] == SNAKE) {
			flag = true;
			return;
		}
		
		if(map[nx][ny] == APPLE) {
			map[nx][ny] = 0;
		}else {
			Node tail = snake.removeLast();
			map[tail.x][tail.y] = 0; 
		}
		
		map[nx][ny] = SNAKE;
	}
	
	private static boolean inRange(int x,int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
