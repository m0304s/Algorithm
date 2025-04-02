import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N,M;
    static ArrayList<ArrayList<Integer>> lower;
    static ArrayList<ArrayList<Integer>> higher;
    
    public static void main(String [] args) throws IOException{
    	String [] tokens = br.readLine().split(" ");
    	N = Integer.parseInt(tokens[0]);
    	M = Integer.parseInt(tokens[1]);
    	lower = new ArrayList<>();
    	higher = new ArrayList<>();
    	
    	for(int i=0;i<=N;i++) {
    		lower.add(new ArrayList<>());
    		higher.add(new ArrayList<>());
    	}
    	
    	for(int i=0;i<M;i++) {
    		tokens = br.readLine().split(" ");
    		int low = Integer.parseInt(tokens[0]);
    		int high = Integer.parseInt(tokens[1]);
    		
    		//자신보다 키가 작은 학생 저장
    		lower.get(high).add(low);
    		//자신보다 키가 큰 학생 저장
    		higher.get(low).add(high);
    	}
    	
    	int answer = 0;
    	
    	for(int i=1;i<=N;i++) {
    		/**
    		 * 각각의 학생마다
    		 * 자신보다 키가 작은 학생 수 계산
    		 * 자신보다 키가 큰 학생 수 계산
    		 * 자신보다 키가 작은 학생 수 + 자신 + 자신보다 키가 큰 학생 == 전체 학생 수 : 키순서 계산 가능
    		 */
    		int lowCnt = bfs(i,lower);
    		int highCnt = bfs(i,higher);
    		
    		if(lowCnt + 1 + highCnt == N) {
    			answer++;
    		}
    	}
    	bw.write(answer+"\n");
    	bw.flush();
    	bw.close();
    	br.close();
    }
    
    static int bfs(int startIndex, ArrayList<ArrayList<Integer>> graph) {
    	int cnt = 0;
    	boolean [] visited = new boolean[N+1];
    	Queue<Integer> queue = new ArrayDeque<>();
    	queue.add(startIndex);
    	visited[startIndex] = true;
    	
    	while(!queue.isEmpty()) {
    		int curNode = queue.poll();
    		
    		for(int child : graph.get(curNode)) {
    			if(visited[child]) continue;
    			
    			visited[child] = true;
    			queue.add(child);
    			cnt++;
    		}
    	}
    	return cnt;
    }
}
