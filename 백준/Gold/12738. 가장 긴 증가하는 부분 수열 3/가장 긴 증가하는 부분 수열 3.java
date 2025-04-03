import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N;
	static int [] arr;
	static int [] dp;
	static int [] x;
	
	public static void main(String [] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N];
		x = new int[N];
		String [] tokens = br.readLine().split(" "); 
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(tokens[i]);
		}
		
		dp[0] = 1;
		x[0] = arr[0];
		int length = 1;
		for(int i=1;i<N;i++) {
			int insertNum = arr[i];
			if(x[length-1] < insertNum) {
				dp[length] = dp[length-1]+1;
				x[length++] = insertNum;
			}else {
				//이진 탐색을 통해 lower bound 구함
				int insertIdx = binarySearch(insertNum, 0, length-1);
				x[insertIdx] = insertNum;
			}
		}
		bw.write(length+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	//x 배열에서 insertNum이 들어갈 수 있는 lower Bound 구함
	static int binarySearch(int target, int left, int right) {
		while(left < right) {
			int mid = (left + right) / 2;
			if(x[mid] < target) {
				left = mid + 1;
			}else {
				right = mid;
			}
		}
		
		return right;
	}
}