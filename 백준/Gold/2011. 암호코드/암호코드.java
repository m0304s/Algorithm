import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private final static int MOD = 1000000;
	
	public static void main(String [] args) throws IOException{
		String code = br.readLine();
		int n = code.length();
		int [] dp = new int[n+1];
		
		if(code.charAt(0) == '0') {
			bw.write("0\n");
			bw.flush();
			br.close();
			bw.close();
			return;
		}
		
		dp[0] = 1;
		dp[1] = 1;
		
		for(int i=2;i<n+1;i++) {
			int currentDigit = code.charAt(i-1) - '0';
			int prevDigit = code.charAt(i-2) - '0';
			
			if(currentDigit >= 1 && currentDigit <= 9) {
				dp[i] = (dp[i] + dp[i-1]) % MOD;
			}
			if(prevDigit != 0) {
				int twoDigitNum = prevDigit * 10 + currentDigit;
				if(twoDigitNum >= 10 && twoDigitNum <= 26) {
					dp[i] = (dp[i] + dp[i-2]) % MOD;
				}
			}
		}
		
		bw.write(dp[n]+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
