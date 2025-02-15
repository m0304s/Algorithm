import java.io.*;
import java.util.*;

class Main
{
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int N,M,D;
	private static int [][] map;
	private static int [] arrow;
	private static boolean [] visited;
	private static int BLANK = 0, ENEMY = 1, ARROW=3, CASTLE = 2;
	private static int maxCount;
	
	private static class Node{
		int x,y;
		
		public Node(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static class Arrow{
		int x,y;
		public Arrow(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String [] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		D = Integer.parseInt(tokens[2]);
		maxCount = Integer.MIN_VALUE;
		arrow = new int[3];
		visited = new boolean[M];
		
		map = new int[N+1][M];
		
		for(int i=0;i<N;i++) {
			tokens = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		
		for(int i=0;i<M;i++) {
			map[N][i] = CASTLE;
		}
		
		backtracking(0,0);
		bw.write(maxCount+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void backtracking(int start, int r) {
		if(r == 3) {
			simulation();
			return;
		}
		
		for(int i=start;i<M;i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			arrow[r] = i;
			backtracking(i+1,r+1);
			visited[i] = false;
		}
	}
	
	private static void simulation() {
	    int[][] cloneMap = cloneMap();
	    List<Arrow> arrowList = new ArrayList<>();

	    // 궁수 배치
	    for (int i : arrow) {
	        arrowList.add(new Arrow(N, i));
	    }

	    int countOfEnemy = 0;  // 공격한 적 수

	    while (true) {
	        if (!gameContinue(cloneMap)) break;

	        // 각 궁수별로 가장 가까운 적 탐색 및 공격 대상 수집
	        Set<Node> enemySet = new HashSet<>(findEnemy(cloneMap, arrowList));

	        // 적 제거 및 공격 카운트 증가
	        for (Node enemy : enemySet) {
	            if (cloneMap[enemy.x][enemy.y] == ENEMY) {
	                cloneMap[enemy.x][enemy.y] = BLANK;
	                countOfEnemy++;
	            }
	        }

	        // 적 이동
	        moveEnemies(cloneMap);
	    }

	    maxCount = Math.max(maxCount, countOfEnemy);
	}

	private static void moveEnemies(int [][] cloneMap) {
		for(int i=N-1;i>0;i--) {
			System.arraycopy(cloneMap[i-1], 0, cloneMap[i], 0, M);
		}
		Arrays.fill(cloneMap[0], BLANK);
	}


	
	private static List<Node> findEnemy(int [][] cloneMap, List<Arrow> arrowList) {
	    List<Node> enemiesToAttack = new ArrayList<>();
	    
	    // 각 궁수별로 가장 가까운 적을 탐색
	    for (Arrow arrow : arrowList) {
	        Node closestEnemy = findMinDistanceEnemy(arrow, cloneMap);
	        if (closestEnemy != null) {
	            enemiesToAttack.add(closestEnemy);
	        }
	    }

	    return enemiesToAttack;
	}

	private static Node findMinDistanceEnemy(Arrow arrow, int[][] cloneMap) {
	    Node closestEnemy = null;
	    int minDistance = Integer.MAX_VALUE;
	    int leftmostCol = M;

	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < M; j++) {
	            if (cloneMap[i][j] == ENEMY) {
	                int distance = calcDistance(arrow.x, arrow.y, i, j);
	                if (distance <= D) {
	                    if (distance < minDistance || 
	                       (distance == minDistance && j < leftmostCol)) {
	                        minDistance = distance;
	                        leftmostCol = j;
	                        closestEnemy = new Node(i, j);
	                    }
	                }
	            }
	        }
	    }

	    return closestEnemy;
	}


	
	private static int calcDistance(int r1,int c1, int r2, int c2) {
		return Math.abs(r1-r2) + Math.abs(c1-c2);
	}
	
	private static boolean gameContinue(int [][] cloneMap) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(cloneMap[i][j] == ENEMY) return true;
			}
		}
		return false;
	}
	
	private static int [][] cloneMap(){
		int [][] copy = new int[N+1][M];
		for(int i=0;i<N+1;i++) {
			for(int j=0;j<M;j++) {
				copy[i][j] = map[i][j];
			}
		}
		return copy;
	}
	
	private static void debug(int [][] copy) {
		for(int i=0;i<N+1;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(copy[i][j]+" ");
			}System.out.println();
		}
	}
}