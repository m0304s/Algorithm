import java.io.*;
import java.util.*;

/**
 * kb, ms
 */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N,M;
	static ArrayList<ArrayList<Weight>> list;
	static int start, end;
	
	public static void main(String [] args) throws IOException{
		init();
		solution();
	}
	
	static void solution() throws IOException{
		long left = 1;
		long right = 1000000000;
		long result = 0;
		
		while(left <= right) {
			int mid = (int)(left + right) / 2;
			
			if(bfs(mid)) {	//mid 중량으로 두 공장을 오갈 수 있는가 여부
				//증량 늘려야함
				result = mid;
				left = mid + 1;
			}else {
				//중량 낮춰야함
				right = mid - 1;
			}
		}
		
		bw.write(result+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static boolean bfs(int limit) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean [] visited = new boolean[N+1];
		
		visited[start] = true;
		queue.add(start);
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			if(current == end) return true;
			
			for(Weight bridge : list.get(current)) {
				if(visited[bridge.to]) continue;
				
				if(bridge.cost >= limit) {
					visited[bridge.to] = true;
					queue.add(bridge.to);
				}
			}
		}
		
		return false;
	}
	
	static void init() throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		list = new ArrayList<>();
		for(int i=0;i<=N;i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0;i<M;i++) {
			tokens = br.readLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			int c = Integer.parseInt(tokens[2]);
			
			list.get(a).add(new Weight(b, c));
			list.get(b).add(new Weight(a, c));
		}
		
		tokens = br.readLine().split(" ");
		start = Integer.parseInt(tokens[0]);
		end = Integer.parseInt(tokens[1]);
	}
	
	
	static class Weight{
		int to;
		int cost;
		public Weight(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "Weight [to=" + to + ", cost=" + cost + "]";
		}
		
	}
}
