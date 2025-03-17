import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static final char WALL = '1';
	
	static int N;
	static char [][] map;
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		
		for(int i=0;i<N;i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		Train train = findTrain();
		
		int result = bfs(train);
		bw.write(result+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int bfs(Train train) {
		Queue<Train> queue = new ArrayDeque<>();
		HashSet<Train> visited = new HashSet<>();
		
		queue.add(train);
		visited.add(train);
		
		while(!queue.isEmpty()) {
			Train curTrain = queue.poll();
			if(isEnd(curTrain)) return curTrain.depth;
			for(int command=0;command<5;command++) {
				Result result = canMove(curTrain,command);
				if(!result.canMove) continue;
				
				int trainType = curTrain.type;
				if(command == 4) {
					trainType = (trainType == 0) ? 1 : 0;
				}
				Train newTrain = new Train(result.p1,result.p2,result.p3,curTrain.depth+1,trainType);
				if(visited.contains(newTrain)) continue;
				
				visited.add(newTrain);
				queue.add(newTrain);
			}
		}
		
		return 0;
	}

	private static Result canMove(Train train, int command) {
		Node p1 = train.p1;
		Node p2 = train.p2;
		Node p3 = train.p3;
		
		if(command == 0) {	//상
			Node np1 = new Node(p1.x-1, p1.y);
			Node np2 = new Node(p2.x-1, p2.y);
			Node np3 = new Node(p3.x-1, p3.y);
			
			boolean isInRange = inRange(np1) && inRange(np2) && inRange(np3);
			if(isInRange) {
				boolean isWall = isWall(np1) || isWall(np2) || isWall(np3);
				
				if(isWall) return new Result(false,np1,np2,np3);
				return new Result(true,np1,np2,np3);
			}else {
				return new Result(false,np1,np2,np3);
			}
		}else if(command == 1) {	//하
			Node np1 = new Node(p1.x+1, p1.y);
			Node np2 = new Node(p2.x+1, p2.y);
			Node np3 = new Node(p3.x+1, p3.y);
			boolean isInRange = inRange(np1) && inRange(np2) && inRange(np3);
			if(isInRange) {
				boolean isWall = isWall(np1) || isWall(np2) || isWall(np3);
				
				if(isWall) return new Result(false,np1,np2,np3);
				return new Result(true,np1,np2,np3);
			}else {
				return new Result(false,np1,np2,np3);
			}
		}else if(command == 2) {	//좌
			Node np1 = new Node(p1.x, p1.y-1);
			Node np2 = new Node(p2.x, p2.y-1);
			Node np3 = new Node(p3.x, p3.y-1);
			boolean isInRange = inRange(np1) && inRange(np2) && inRange(np3);
			if(isInRange) {
				boolean isWall = isWall(np1) || isWall(np2) || isWall(np3);
				
				if(isWall) return new Result(false,np1,np2,np3);
				return new Result(true,np1,np2,np3);
			}else {
				return new Result(false,np1,np2,np3);
			}
		}else if(command == 3) {	//우
			Node np1 = new Node(p1.x, p1.y+1);
			Node np2 = new Node(p2.x, p2.y+1);
			Node np3 = new Node(p3.x, p3.y+1);
			boolean isInRange = inRange(np1) && inRange(np2) && inRange(np3);
			if(isInRange) {
				boolean isWall = isWall(np1) || isWall(np2) || isWall(np3);
				
				if(isWall) return new Result(false,np1,np2,np3);
				return new Result(true,np1,np2,np3);
			}else {
				return new Result(false,np1,np2,np3);
			}
		}else if(command == 4) {	//회전
			Node np1 = new Node(p2.x-1,p2.y-1);
			Node np2 = new Node(p2.x-1,p2.y);
			Node np3 = new Node(p2.x-1,p2.y+1);
			Node np4 = new Node(p2.x,p2.y-1);
			Node np5 = new Node(p2.x,p2.y+1);
			Node np6 = new Node(p2.x+1,p2.y-1);
			Node np7 = new Node(p2.x+1,p2.y);
			Node np8 = new Node(p2.x+1,p2.y+1);
			
			boolean isInRange = inRange(np1) && inRange(np2) && inRange(np3) && inRange(np4) && inRange(np5) && inRange(np6) && inRange(np7) && inRange(np8);
			if(isInRange) {
				boolean isWall = isWall(np1) || isWall(np2) || isWall(np3) || isWall(np4) || isWall(np5) || isWall(np6) || isWall(np7) || isWall(np8);
				if(isWall) {
					return new Result(false,np1,np2,np3);
				}else {
					Node [] rotatedTrain = rotate(train);
					return new Result(true,rotatedTrain[0],rotatedTrain[1],rotatedTrain[2]);
				}
			}else {
				return new Result(false,null,null,null);
			}
		}
		return null;
	}
	
	
	private static boolean isEnd(Train curTrain) {
		Node p1 = curTrain.p1;
		Node p2 = curTrain.p2;
		Node p3 = curTrain.p3;
		
		if(map[p1.x][p1.y] == 'E' && map[p2.x][p2.y] == 'E' && map[p3.x][p3.y] == 'E') return true;
		return false;
	}

	static Train findTrain() {
		//가로 탐색
		for(int i=0;i<N;i++) {
			for(int j=0;j<N-2;j++) {
				if(map[i][j] == 'B' && map[i][j+1] == 'B' && map[i][j+2] == 'B') {
					return new Train(new Node(i,j), new Node(i,j+1), new Node(i,j+2),0,0);
				}
			}
		}
		
		//새로 탐색
		for(int j=0;j<N;j++) {
			for(int i=0;i<N-2;i++) {
				if(map[i][j] == 'B' && map[i+1][j] == 'B' && map[i+2][j] == 'B') {
					return new Train(new Node(i,j), new Node(i+1,j), new Node(i+2,j),0,1);
				}
			}
		}
		return null;
	}
	
	static Node[] rotate(Train train) {
		//세로일 경우
		if(train.p1.y == train.p2.y) {
			Node p1 = train.p1;
			Node p2 = train.p2;
			Node p3 = train.p3;
			
			Node np1 = new Node(p1.x+1,p1.y-1);
			Node np2 = p2;
			Node np3 = new Node(p3.x-1,p3.y+1);
			
			return new Node[] {np1,np2,np3};
		}else {
			//가로일 경우
			Node p1 = train.p1;
			Node p2 = train.p2;
			Node p3 = train.p3;
			
			Node np1 = new Node(p1.x-1,p1.y+1);
			Node np2 = p2;
			Node np3 = new Node(p3.x+1,p3.y-1);
			
			return new Node[] {np1,np2,np3};
		}
	}
	
	static boolean inRange(Node p) {
		return p.x >= 0 && p.x < N && p.y >= 0 && p.y < N;
	}
	
	static boolean isWall(Node p) {
		return map[p.x][p.y] == WALL;
	}

	static class Train{
		Node p1,p2,p3;
		int depth,type;
		public Train(Node p1, Node p2, Node p3,int depth,int type) {
			this.p1 = p1;
			this.p2 = p2;
			this.p3 = p3;
			this.depth = depth;
			this.type = type;
		}
		@Override
		public String toString() {
			return "Train [p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + ", depth=" + depth + ", type=" + type + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
			result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
			result = prime * result + ((p3 == null) ? 0 : p3.hashCode());
			result = prime * result + type;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Train other = (Train) obj;
			if (p1 == null) {
				if (other.p1 != null)
					return false;
			} else if (!p1.equals(other.p1))
				return false;
			if (p2 == null) {
				if (other.p2 != null)
					return false;
			} else if (!p2.equals(other.p2))
				return false;
			if (p3 == null) {
				if (other.p3 != null)
					return false;
			} else if (!p3.equals(other.p3))
				return false;
			if (type != other.type)
				return false;
			return true;
		}
	}
	
	static class Node{
		int x,y;
		public Node(int x,int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}
	
	static class Result{
		boolean canMove;
		Node p1,p2,p3;
		
		public Result(boolean canMove, Node p1,Node p2,Node p3) {
			this.canMove = canMove;
			this.p1 = p1;
			this.p2 = p2;
			this.p3 = p3;
		}
	}
}
