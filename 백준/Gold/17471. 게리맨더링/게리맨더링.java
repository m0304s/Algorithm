import java.io.*;
import java.util.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int N;
	private static int [] peopleOfCity;
	private static ArrayList<ArrayList<Integer>> graph;
	private static HashMap<Integer, HashSet<Integer>> cityMap;
	private static int answer = Integer.MAX_VALUE;
	
	public static void main(String [] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		peopleOfCity = new int[N+1];
		cityMap = new HashMap<>();
		graph = new ArrayList<>();
		
		for(int i=0;i<=N;i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=1;i<=N;i++) {
			cityMap.put(i, new HashSet<>());
		}
		
		String [] tokens = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			peopleOfCity[i+1] = Integer.parseInt(tokens[i]);
		}
		
		for(int i=0;i<N;i++) {
			tokens = br.readLine().split(" ");
			for(int j=1;j<tokens.length;j++) {
				cityMap.get(i+1).add(Integer.parseInt(tokens[j]));
			}
		}
		
		for(int key : cityMap.keySet()) {
			HashSet<Integer> adjustCity = cityMap.get(key);
			for(int node : adjustCity) {
				graph.get(key).add(node);
			}
		}
		backtracking(1,new boolean[N+1]);
		
		if(answer == Integer.MAX_VALUE) {
			bw.write("-1\n");
		}else {
			bw.write(answer+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void backtracking(int depth, boolean [] zipA) {
		if(depth == N+1) {
			simulation(zipA);
			return;
		}
		zipA[depth] = true;
		backtracking(depth+1,zipA);
		zipA[depth] = false;
		backtracking(depth+1,zipA);
	}
	
	private static void simulation(boolean [] zip) {
		int cntA = 0;
		int cntB = 0;
		
		for(int i=1;i<zip.length;i++) {
			if(zip[i]) cntA++;
			else cntB++;
		}
		
		if(cntA == 0 || cntB == 0) return;
		
		boolean [] groupA = new boolean[N+1];
		boolean [] groupB = new boolean[N+1];
		
		for(int i=1;i<=N;i++) {
			if(zip[i]) groupA[i] = true;
			else groupB[i] = true;
		}
		
		ArrayList<Integer> connectA = bfs(groupA,cntA);
		ArrayList<Integer> connectB = bfs(groupB,cntB);
		
		int popA = 0;
		int popB = 0;
		
		if(connectA == null || connectB == null) return;
		for(int node : connectA) {
			popA+=peopleOfCity[node];
		}
		
		for(int node : connectB) {
			popB+=peopleOfCity[node];
		}
		
		answer = Math.min(answer, Math.abs(popA-popB));
	}
	
	private static ArrayList<Integer> bfs(boolean [] group, int cnt){
		Queue<Integer> queue = new ArrayDeque<>();
		boolean [] visited = new boolean[N+1];
		ArrayList<Integer> nodeList = new ArrayList<>();
		
		int initialIdx = -1;
		for(int i=1;i<group.length;i++) {
			if(group[i]) {
				initialIdx = i;
				break;
			}
		}
		
		if(initialIdx == -1) return null;
		
		queue.add(initialIdx);
		visited[initialIdx] = true;
		
		while(!queue.isEmpty()) {
			int curNode = queue.poll();
			nodeList.add(curNode);
			
			for(int nextNode : graph.get(curNode)) {
				if(visited[nextNode] || !group[nextNode]) continue;
				
				visited[nextNode] = true;
				queue.add(nextNode);
			}
		}
		
		if(nodeList.size() == cnt) {
			return nodeList;
		}
		return null;
	}
}
