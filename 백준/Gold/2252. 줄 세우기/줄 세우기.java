import java.io.*;
import java.util.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int N,M;
	private static ArrayList<ArrayList<Integer>> graph;
	private static int [] arr;	//연결되는 노드 개수를 저장하는 배열
	public static void main(String [] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		graph = new ArrayList<>();
		arr = new int[N+1];
		
		for(int i=0;i<=N;i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0;i<M;i++) {
			tokens = br.readLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			
			graph.get(a).add(b);
			arr[b]++;
		}
		
		Queue<Integer> answer = new ArrayDeque<>();
		for(int i=1;i<arr.length;i++) {
			if(arr[i] == 0) answer.add(i);
		}
		
		while(!answer.isEmpty()) {
			int num = answer.poll();
			bw.write(num+" ");
			
			//연결된 노트 삭제
			List<Integer> nodes = graph.get(num);
			for(int i : nodes) {
				arr[i]--;
				if(arr[i] == 0) answer.add(i);
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
