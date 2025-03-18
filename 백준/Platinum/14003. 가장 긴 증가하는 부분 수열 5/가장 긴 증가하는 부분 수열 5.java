import java.io.*;
import java.util.Stack;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N;
    static int[] arr;
    static int[] dp;
    static int[] lis;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        
        lis = new int[N+1];
        arr = new int[N];
        dp = new int[N];
        
        String[] tokens = br.readLine().split(" ");
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }
        
        lis[0] = -1000000001;
        int len = 0;
        int idx = 0;
        for(int i=0;i<N;i++) {
        	if(arr[i] > lis[len]) {
        		dp[i] = ++len;
        		lis[len] = arr[i];
        	}else {
        		idx = binarySearch(0, len, arr[i]);
        		lis[idx] = arr[i];
        		dp[i] = idx;
        	}
        }
        
        Stack<Integer> stack = new Stack<>();
        for(int i=N-1;i>=0;i--) {
        	if(dp[i] == len) {
        		stack.push(arr[i]);
        		len--;
        	}
        }
        bw.write(stack.size()+"\n");
        while(!stack.isEmpty()) {
        	bw.write(stack.pop()+" ");
        }
        bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
    }
    
    // dp 배열의 [left, right] 범위 내에서 key 이상의 값이 처음 나타나는 인덱스를 반환
    static int binarySearch(int left, int right, int key) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (lis[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
