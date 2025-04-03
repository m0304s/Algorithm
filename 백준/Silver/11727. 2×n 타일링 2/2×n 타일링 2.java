import java.util.*;
import java.io.*;
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException{
		int N = Integer.parseInt(br.readLine());
		int [] dp = new int[N+2];
		dp[1] = 1;
		dp[2] = 3;

		for(int i=3;i<=N;i++) {
			dp[i] = (dp[i-2]*2 + dp[i-1])%10007;
		}
		System.out.println(dp[N]);
	}
}
