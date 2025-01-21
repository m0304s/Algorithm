import java.io.*;
import java.util.*;

class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String [] args) throws IOException{
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=1;i<=N;i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(minHeap.size() == maxHeap.size()) {
				maxHeap.add(num);
			}else {
				minHeap.add(num);
			}
			if(!minHeap.isEmpty() && !maxHeap.isEmpty()) {
				if(minHeap.peek() < maxHeap.peek()) {
					int a = minHeap.poll();
					int b = maxHeap.poll();
					
					minHeap.add(b);
					maxHeap.add(a);
				}	
			}
			
			bw.write(maxHeap.peek()+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}