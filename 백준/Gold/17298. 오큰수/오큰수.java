import java.io.*;
import java.util.*;

class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static class Node{
		int idx;
		int value;
		
		public Node(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}
	}
	
	public static void main(String [] args) throws IOException{
		int N = Integer.parseInt(br.readLine());
		Stack<Node> stack = new Stack<>();
		int [] arr = new int[N];
		String [] tokens = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			int num = Integer.parseInt(tokens[i]);
			while(!stack.isEmpty()) {
				if(stack.peek().value < num) {
					Node node = stack.pop();
					arr[node.idx] = num; 
				}else {
					break;
				}
			}
			stack.push(new Node(i,num));
		}
		for(int i=0;i<N;i++) {
			if(arr[i] == 0) bw.write("-1 ");
			else bw.write(arr[i]+" ");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}