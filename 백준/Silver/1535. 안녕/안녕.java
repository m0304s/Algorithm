import java.io.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int [] health;
	private static int [] joy;
	
	private static int [][] dp;
	private static int N;
	
	public static void main(String [] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		health = new int[N+1];
		joy = new int[N+1];
		
		String [] tokens = br.readLine().split(" ");
		for(int i=1;i<=N;i++) {
			health[i] = Integer.parseInt(tokens[i-1]);
		}
		tokens = br.readLine().split(" ");
		for(int i=1;i<=N;i++) {
			joy[i] = Integer.parseInt(tokens[i-1]);
		}
		
		dp = new int[N+1][101];
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=100;j++) {
				if(j>health[i]) dp[i][j] = Math.max(dp[i-1][j], joy[i] + dp[i-1][j-health[i]]);
				else dp[i][j] = dp[i-1][j];
			}
		}
		
		bw.write(dp[N][100]+"\n");
        bw.flush();
        bw.close();
        br.close();
	}
}
