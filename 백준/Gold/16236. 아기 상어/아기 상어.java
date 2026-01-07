import java.io.*;
import java.util.*;

public class Main {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	/**
	 * N * N 크기의 공간
	 * M 마리의 물고기
	 * 1 마리의 아기상어
	 * 
	 * 아기 상어와 물고기는 모두 크기를 가지고 있음.
	 * 최초 아기 상어의 크기는 2이고, 아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동함
	 * 
	 * 아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없음.
	 * 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있음.
	 * 크기가 같은 물고기는 먹을 수 없지만, 물고기가 있는 칸은 지나갈 수 있음
	 * 
	 * 아기상어가 어디로 이동할지 결정하는 방법은 아래와 같음.
	 * 1. 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청
	 * 2. 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 감
	 * 3. 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 감.
	 * 
	 * 거리 : 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값
	 * 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹음.
	 * 
	 * 아기 상어의 이동은 1초가 걸리고, 물고기를 먹는데 걸리는 시간은 없음.
	 * 상어가 물고기를 먹으면 그 칸은 빈 칸이 된다.
	 * 
	 * 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다.
	 * 
	 * 공간의 상태가 주어졌을 때, 아기 상어가 몇 초 동안 엄마상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 프로그램을 작성해라.
	 * 
	 * 0 : 빈 칸
	 * 1, 2, 3, 4, 5, 6 : 칸에 있는 물고기의 크기
	 * 9 : 아기 상어의 위치
	 */
	
	static int N;
	static int [][] map;
	
	//상 좌 하 우
	static int [] dx = {-1,0,1,0};
	static int [] dy = {0,-1,0,1};
	static Shark shark;
	
	public static void main(String[] args) throws IOException{
		inputBaseData();
		simulation();
	}
	
	static void simulation() throws IOException{
		int time = 0;
		while(true) {	//공간에 남아있는 물고기의 개수
			Node target = findNearestFish();
			
			if(target == null) {
				break;
			}
			
			time+=target.distance;
			map[target.x][target.y] = 0;
			shark.eatCount++;
			shark.x = target.x;
			shark.y = target.y;
			
			if(shark.eatCount == shark.size) {
				shark.size++;
				shark.eatCount = 0;
			}
		}
		
		bw.write(time+"\n");
		bw.flush();
		bw.close();
	}
	
	/**
	 * 아기 상어와 가장 가까운 위치에 있는 물고기를 찾는 함수
	 */
	private static Node findNearestFish() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
			if(o1.distance != o2.distance) return Integer.compare(o1.distance, o2.distance);
			if(o1.x != o2.x) return Integer.compare(o1.x, o2.x);
			
			return Integer.compare(o1.y, o2.y);
		});
		
		boolean [][] visited = new boolean[N][N];
		
		pq.add(new Node(shark.x, shark.y, 0));
		visited[shark.x][shark.y] = true;
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			
			if(map[curNode.x][curNode.y] != 0 && map[curNode.x][curNode.y] < shark.size) {
				return curNode;
			}
			
			for(int d=0;d<4;d++) {
				int nx = curNode.x + dx[d];
				int ny = curNode.y + dy[d];
				
				if(!inRange(nx,ny) || visited[nx][ny]) continue;
				
				if(map[nx][ny] <= shark.size) {
					pq.add(new Node(nx,ny,curNode.distance + 1));
					visited[nx][ny] = true;
				}
			}
		}
		
		return null;
	}

	static boolean inRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	static void inputBaseData() throws IOException{
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			String [] tokens = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				int size = Integer.parseInt(tokens[j]);
				
				if(size == 9) {
					shark = new Shark(i,j,2,0);
					map[i][j] = 0;
				}else {
					map[i][j] = size;
				}
			}
		}
	}
	
	static class Shark{
		int x,y,size,eatCount;

		public Shark(int x, int y, int size, int eatCount) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.eatCount = eatCount;
		}

		@Override
		public String toString() {
			return "Shark [x=" + x + ", y=" + y + ", size=" + size + ", eatCount=" + eatCount + "]";
		}
	}
	
	static class Node{
		int x,y,distance;

		public Node(int x, int y, int distance) {
			super();
			this.x = x;
			this.y = y;
			this.distance = distance;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", distance=" + distance + "]";
		}
	}
}