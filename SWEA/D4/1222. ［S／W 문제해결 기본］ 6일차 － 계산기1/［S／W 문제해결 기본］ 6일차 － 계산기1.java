import java.io.*;
import java.util.*;

class Solution
{
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String [] args) throws IOException{
		int T = 10;
		for(int t=1;t<=T;t++) {
			int lengthOfString = Integer.parseInt(br.readLine());
			String input = br.readLine();
			int answer = solution(lengthOfString, input);
			bw.write("#"+t+" "+answer+"\n");
		}
		br.close();
		bw.flush();
		bw.close();
	}
	private static int solution(int lengthOfString, String input) {
		Stack<Integer> stack = new Stack<>();
		
		int sum = 0;
		for(int i=0;i<lengthOfString;i++) {
			if(input.charAt(i) == '+') {
				stack.push(sum);
			}else {
				stack.push(input.charAt(i)-'0');	
			}
		}
		
		while(!stack.isEmpty()) {
			sum+=stack.pop();
		}
		return sum;
	}
}
