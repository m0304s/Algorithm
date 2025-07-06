import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static List<Node> pointList;
	static List<Integer> xList;	//선분 출발 좌표 목록
	static List<Integer> yList;	//선분 도착 좌표 목록
	
	public static void main(String[] args) throws IOException{
		init();
		int length = solution();
		bw.write(length+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int solution() {
		xList = new ArrayList<>();
		yList = new ArrayList<>();
		
		for(int i=0;i<pointList.size();i++) {
			Node point = pointList.get(i);
			if(xList.isEmpty()) {
				xList.add(point.x);
			}
			
			if(yList.isEmpty()) {
				yList.add(point.y);
				continue;
			}
			
			//새로운 선분이 추가되는 경우
			if(point.x > yList.get(yList.size()-1)) {
				xList.add(point.x);
				yList.add(point.y);
			}else {
			//기존의 선분이 연장되는 경우
				int originalMaxY = yList.remove(yList.size()-1);
				yList.add(Math.max(originalMaxY, point.y));
			}
		}
		
		int totalLength = 0;
		for(int i=0;i<xList.size();i++) {
			int start = xList.get(i);
			int end = yList.get(i);
			
			totalLength+=(end - start);
		}
		
		return totalLength;
	}
	
	static void init() throws IOException{
		pointList = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			String [] tokens = br.readLine().split(" ");
			int x = Integer.parseInt(tokens[0]);
			int y = Integer.parseInt(tokens[1]);
			
			int finalX = Math.min(x, y);
			int finalY = Math.max(x, y);
			
			pointList.add(new Node(finalX,finalY));
		}
		
		Collections.sort(pointList, (o1,o2)->{
			if(o1.x == o2.x) return o1.y - o2.y; 
			return o1.x - o2.x;
		});
	}
	
	static class Node{
		int x,y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
	}
}
