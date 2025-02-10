import java.io.*;
import java.util.*;

class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int N;
	
	private static class Node{
		int num,x,y;
		
		public Node(int num, int x,int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}
		
		public String toString() {
			return "Num : " + this.num + " X : " + this.x + " Y : " + this.y;
		}
	}
	private static int [] dx = {0,0,-1,1};
	private static int [] dy = {-1,1,0,0};
	private static Map<Integer,HashSet<Integer>> studentMap;
	private static ArrayList<Integer> order;
	private static Node[][] map;
	private static Map<Integer, Integer> score;
	
	private static class Point{
		int x,y,blankSpace, likeStudent;
		public Point(int x,int y,int blankSpace,int likeStudent) {
			this.x = x;
			this.y = y;
			this.blankSpace = blankSpace;
			this.likeStudent = likeStudent;
		}
	}
	
	public static void main(String [] args) throws IOException{
		studentMap = new HashMap<>();
		order = new ArrayList<>();
		N = Integer.parseInt(br.readLine());
		map = new Node[N][N];
		score = new HashMap<>();
		score.put(0, 0);
		score.put(1, 1);
		score.put(2, 10);
		score.put(3, 100);
		score.put(4, 1000);
		for(int i=0;i<N*N;i++) {
			String [] tokens = br.readLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			order.add(a);
			for(int j=1;j<=4;j++) {
				int studentNum = Integer.parseInt(tokens[j]);
				if(!studentMap.containsKey(a)) {
					studentMap.put(a, new HashSet<>());
					studentMap.get(a).add(studentNum);
				}else {
					studentMap.get(a).add(studentNum);
				}
			}
		}
		
		for(int i=0;i<order.size();i++) {
			int targetStudent = order.get(i);	//배정하는 학생 번호
			HashSet<Integer> goodStudentList = studentMap.get(targetStudent);
			
			Node targetStudentPlace;	//배정하는 학생이 앉을 자리
			int maxCount = Integer.MIN_VALUE;	//좋아하는 학생이 있는 수
			ArrayList<Point> possible = new ArrayList<>();
			
			for(int x=0;x<N;x++) {
				for(int y=0;y<N;y++) {
					if(map[x][y] != null) continue;
					
					int count = 0;	//현재 자리에서 좋아하는 학생 있는 수
					int blankSpace = 0;	//비어있는 자리 수
					
					for(int d=0;d<4;d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						
						if(!inRange(nx,ny)) continue;
						
						if(map[nx][ny] != null) {
							int number = map[nx][ny].num;
							if(goodStudentList.contains(number)) count++;
						}else if(map[nx][ny] == null) {
							blankSpace++;
						}
					}
					
					if(maxCount == count) {
						possible.add(new Point(x,y,blankSpace,count));
					}else if(maxCount < count) {
						maxCount = count;
						possible.clear();
						possible.add(new Point(x,y,blankSpace,count));
					}
				}
			}
			
			Collections.sort(possible,new Comparator<Point>() {
				public int compare(Point o1, Point o2) {
					if(o1.likeStudent == o2.likeStudent) {
						if(o1.blankSpace == o2.blankSpace) {
							if(o1.x == o2.x) {
								return o1.y - o2.y;
							}else {
								return o1.x - o2.x;
							}
						}else {
							return o2.blankSpace - o1.blankSpace;
						}
					}else {
						return o2.likeStudent - o1.likeStudent;
					}
				}
			});
			
			Point p = possible.get(0);
			
			targetStudentPlace = new Node(targetStudent,p.x,p.y);
			map[p.x][p.y] = targetStudentPlace; 
		}
		
		//만족도 조사
		int answer = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int student = map[i][j].num;
				int count = 0;
				HashSet<Integer> goodStudentList = studentMap.get(student);
				for(int d=0;d<4;d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if(!inRange(nx,ny)) continue;
					
					if(goodStudentList.contains(map[nx][ny].num)) count++;
				}
				answer+=score.get(count);
			}
		}
		bw.write(answer+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static boolean inRange(int x,int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
