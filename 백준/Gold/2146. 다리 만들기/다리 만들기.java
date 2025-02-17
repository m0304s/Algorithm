import java.io.*;
import java.util.*;

public class Main {
	static final int LAND = 1, WATER = 0;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int [] dx = {0,0,-1,1};
    static int [] dy = {-1,1,0,0};
    
    private static int N;
    private static int [][] map;
    private static boolean [][] visited;
    
    private static class Node{
    	int x,y;
    	
    	public Node(int x,int y) {
    		this.x = x;
    		this.y = y;
    	}

		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return Objects.hash(x,y);
		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return this.x == ((Node) obj).x && this.y == ((Node) obj).y; 
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
    }
    
    public static void main(String [] args) throws IOException{
    	N = Integer.parseInt(br.readLine());
    	map = new int[N][N];
    	visited = new boolean[N][N];
    	
    	for(int i=0;i<N;i++) {
    		String [] tokens = br.readLine().split(" ");
    		for(int j=0;j<N;j++) {
    			map[i][j] = Integer.parseInt(tokens[j]);
    		}
    	}
    	
    	int islandCnt = 1;
    	for(int i=0;i<N;i++) {
    		for(int j=0;j<N;j++) {
    			if(map[i][j] == LAND && !visited[i][j]) {
    				bfs(i,j, islandCnt++);
    			}
    		}
    	}
    	
    	int minLength = Integer.MAX_VALUE;
    	for(int i=0;i<N;i++) {
    		for(int j=0;j<N;j++) {
    			if(map[i][j] == 0) continue;
    			int length = build(i,j);
    			if(length == 1) continue;
    			
    			minLength = Math.min(minLength, length);
    		}
    	}
    	bw.write(minLength-1+"\n");
    	bw.flush();
    	bw.close();
    	br.close();
    }
    
    private static int build(int x, int y) {
        int startIsland = map[x][y];
        Queue<Node> queue = new LinkedList<>();
        boolean [][] visited2 = new boolean[N][N];
        visited2[x][y] = true;
        queue.add(new Node(x, y));

        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curNode = queue.poll();

                // 다른 섬에 도달했다면 현재까지의 거리 반환
                if (map[curNode.x][curNode.y] != 0 && map[curNode.x][curNode.y] != startIsland) {
                    return distance;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = curNode.x + dx[d];
                    int ny = curNode.y + dy[d];

                    if (!inRange(nx, ny) || visited2[nx][ny] || map[nx][ny] == startIsland) continue;
                    visited2[nx][ny] = true;
                    queue.add(new Node(nx, ny));
                }
            }
            distance++; // 한 레벨(거리) 처리 후 증가
        }
        return distance;
    }

    
    private static HashSet<Node> bfs(int x,int y, int islandCnt){
    	HashSet<Node> points = new HashSet<>();
    	
    	Queue<Node> queue = new LinkedList<>();
    	visited[x][y] = true;
    	queue.add(new Node(x,y));
    	
    	while(!queue.isEmpty()) {
    		Node curNode = queue.poll();
    		points.add(curNode);
    		map[curNode.x][curNode.y] = islandCnt; 
    		for(int i=0;i<4;i++) {
    			int nx = curNode.x + dx[i];
    			int ny = curNode.y + dy[i];
    			
    			if(!inRange(nx,ny) || visited[nx][ny] || map[nx][ny] == WATER) continue;
    			
    			queue.add(new Node(nx,ny));
    			visited[nx][ny] = true;
    		}
    	}
    	return points;
    }
    
    private static boolean inRange(int x,int y) {
    	return x >= 0 && x < N && y >= 0 && y < N;
    }
}