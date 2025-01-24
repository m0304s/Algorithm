import java.io.*;
import java.util.*;

class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static void main(String [] tokens) throws IOException{
    	int N = Integer.parseInt(br.readLine());
    	Stack<Integer> stack = new Stack<>();
    	int [] array = new int[N];
    	int inputNum = 1;
    	List<String> answer = new ArrayList<>();
    	for(int i=0;i<N;i++) {
    		array[i] = Integer.parseInt(br.readLine());
    	}
    	
    	boolean flag = true;
    	for(int i=0;i<N;i++) {
    		if(!stack.isEmpty()) {
    			if(stack.peek() > array[i]) {
    				flag = false;
    				break;
    			}
    		}
    		while(array[i] >= inputNum) {
    			stack.push(inputNum++);
    			answer.add("+\n");
    		}
    		if(array[i] == stack.peek()) {
    			stack.pop();
    			answer.add("-\n");
    		}
    	}
    	
    	if(flag) {
    		for(String string : answer) {
    			bw.write(string);
    		}
    	}else {
    		answer.clear();
    		bw.write("NO\n");
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
}
