import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N;
	static List<Point> allPoints;
	static List<Point> canons;
	static Point start, end;
	
	public static void main(String[] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		double startX = Double.parseDouble(tokens[0]);
		double startY = Double.parseDouble(tokens[1]);
		
		start = new Point(startX, startY);
		
		tokens = br.readLine().split(" ");
		double endX = Double.parseDouble(tokens[0]);
		double endY = Double.parseDouble(tokens[1]);
		
		end = new Point(endX, endY);
		
		N = Integer.parseInt(br.readLine());
		
		canons = new ArrayList<>();
	
		for(int i=0;i<N;i++) {
			tokens = br.readLine().split(" ");
			double x = Double.parseDouble(tokens[0]);
			double y = Double.parseDouble(tokens[1]);
			
			canons.add(new Point(x, y));
		}
		
		allPoints = new ArrayList<>();
		allPoints.add(start);
		allPoints.addAll(canons);
		allPoints.add(end);
		
		int totalNodes = allPoints.size();
		double [] minTime = new double[totalNodes];
		Arrays.fill(minTime, Double.MAX_VALUE);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.add(new Node(0,start,0));
		minTime[0] = 0.0;
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			if(minTime[curr.index] < curr.totalTime) continue;
			
			for(int nextIdx = 0; nextIdx < totalNodes; nextIdx++) {
				if(curr.index == nextIdx) continue;
				
				double dist = getDistance(allPoints.get(curr.index), allPoints.get(nextIdx));
				double travelTime;
				
				if(curr.index >= 1 && curr.index <= N) {	//현재 위치가 대포인 경우
					travelTime = Math.min(dist / 5.0, 2.0 + Math.abs(dist - 50.0) / 5.0);
				}else {	//현재 위치가 시작점인 경우
					travelTime = dist / 5.0;
				}
				
				if (minTime[nextIdx] > minTime[curr.index] + travelTime) {
                    minTime[nextIdx] = minTime[curr.index] + travelTime;
                    pq.add(new Node(nextIdx, allPoints.get(nextIdx), minTime[nextIdx]));
                }
			}
		}
		
		bw.write(String.format("%.6f\n", minTime[totalNodes - 1]));
		bw.flush();
		bw.close();
		br.close();
	}
	
	static double getDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
	
	static class Point{
		double x,y;
		
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}
	
	static class Node implements Comparable<Node>{
		int index;
		Point curPoint;
		double totalTime;
		
		public Node(int index, Point curPoint, double totalTime) {
			super();
			this.index = index;
			this.curPoint = curPoint;
			this.totalTime = totalTime;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(this.totalTime, o.totalTime);
		}
	}
}