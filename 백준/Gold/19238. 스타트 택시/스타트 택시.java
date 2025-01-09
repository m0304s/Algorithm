import java.io.*;
import java.util.*;

public class Main{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int WALL = 1;
	
	private static int N,M;
	private static int [][] map;
	private static Taxi taxi;
	private static List<Passenger> passengerList;

	private static class Passenger{
	    Node start,end;
	    
	    public Passenger(int number,int startX,int startY, int endX, int endY) {
	    	this.start = new Node(startX,startY);
	    	this.end = new Node(endX,endY);
	    }
	    
	    public String toString() {
	        return " StartX =  " + this.start.x + " StartY = " + this.start.y + " EndX = " + this.end.x + " EndY = " + this.end.y;
	    }
	}

	private static class Taxi{
	    int x,y,fuel;
	    
	    public Taxi(int x,int y,int fuel) {
	        this.x = x;
	        this.y = y;
	        this.fuel = fuel;
	    }
	    
	    public String toString() {
	        return "X = " + this.x + " Y = " + this.y + " Fuel = " + this.fuel;
	    }
	}
	
	private static class Node{
	    int x,y;
	    
	    public Node(int x,int y) {
	        this.x = x;
	        this.y = y;
	    }
	}

	public static void main(String [] args) throws IOException{
	    String[] tokens = br.readLine().split(" ");
	    N = Integer.parseInt(tokens[0]);
	    M = Integer.parseInt(tokens[1]);
	    int fuel = Integer.parseInt(tokens[2]);
	    passengerList = new ArrayList<>();
	    
	    
	    map = new int[N][N];
	    
	    createMap();
	    readTaxiInfo(fuel);
	    readPassengerInfo();
	    simulation();
	}
	
	private static void simulation() throws IOException{
		for(int i=0;i<M;i++) {
			Passenger personToRide = findNearestPassengerToTaxi();
			
			if(personToRide == null) {
				taxi.fuel = -1;
				break;
			}
			
			int toStart = findShortestDistance(new Node(taxi.x, taxi.y), personToRide.start);
			int toEnd = findShortestDistance(personToRide.start, personToRide.end);
			
			if(toStart == -1 || toEnd == -1 || taxi.fuel < toStart + toEnd) {
				taxi.fuel = -1;
				break;
			}
			
			taxi.x = personToRide.end.x;
			taxi.y = personToRide.end.y;
			
			taxi.fuel -= (toStart + toEnd);
			taxi.fuel += toEnd * 2;
			
			passengerList.remove(personToRide);
		}
		
		bw.write(taxi.fuel+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static Passenger findNearestPassengerToTaxi() {
		Queue<Node> queue = new LinkedList<>();
		boolean [][] visited = new boolean[N][N];
		List<Passenger> candidates = new ArrayList<>();
		int [] dx = {-1,1,0,0};
		int [] dy = {0,0,-1,1};
		
		queue.add(new Node(taxi.x,taxi.y));
		visited[taxi.x][taxi.y] = true;
		
		int distance = 0;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i=0;i<size;i++) {
				Node cur = queue.poll();
				
				for(Passenger passenger : passengerList) {
					if(cur.x == passenger.start.x && cur.y == passenger.start.y) {
						candidates.add(passenger);
					}
				}
				
				for(int d=0;d<4;d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					
					if(!inRange(nx,ny) || visited[nx][ny] || map[nx][ny] == WALL) continue;
					
					visited[nx][ny] = true;
					queue.add(new Node(nx,ny));
				}
			}
			
			if(!candidates.isEmpty()) {
				candidates.sort((p1,p2) -> {
					if(p1.start.x != p2.start.x) {
						return Integer.compare(p1.start.x, p2.start.x);
					}
					return Integer.compare(p1.start.y, p2.start.y);
				});
				
				return candidates.get(0);
			}
			distance++;
		}
		return null;
	}
	
	private static int findShortestDistance(Node start, Node end) {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int [] dx = {-1,1,0,0};
		int [] dy = {0,0,-1,1};
		
		queue.add(start);
		visited[start.x][start.y] = true;
		
		int distance = 0;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i=0;i<size;i++) {
				Node cur = queue.poll();
				if(cur.x == end.x && cur.y == end.y)
					return distance;
				
				for(int d=0;d<4;d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					
					if(!inRange(nx,ny) || visited[nx][ny] || map[nx][ny] == WALL) continue;
					
					visited[nx][ny] = true;
					queue.add(new Node(nx,ny));
				}
			}
			distance++;
		}
		return -1;
	}
	
	private static boolean inRange(int x,int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static void createMap() throws IOException{
	    map = new int[N][N];
	    for(int i=0;i<N;i++) {
	        String [] tokens = br.readLine().split(" ");
	        for(int j=0;j<N;j++) {
	            map[i][j] = Integer.parseInt(tokens[j]);
	        }
	    }
	}
	
	private static void readTaxiInfo(int fuel)throws IOException{
	    String [] tokens = br.readLine().split(" ");
	    int x = Integer.parseInt(tokens[0])-1;
	    int y = Integer.parseInt(tokens[1])-1;
	    
	    taxi = new Taxi(x,y,fuel);
	}
	
	private static void readPassengerInfo() throws IOException{
	    for(int i=0;i<M;i++) {
	        String [] tokens = br.readLine().split(" ");
            int startX = Integer.parseInt(tokens[0])-1;
            int startY = Integer.parseInt(tokens[1])-1;
            int endX = Integer.parseInt(tokens[2])-1;
            int endY = Integer.parseInt(tokens[3])-1;
	            
            passengerList.add(new Passenger(i,startX,startY,endX,endY));
	    }
	}
}
