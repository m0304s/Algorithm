import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N,M;
    static int [][] arr;
    static int [][] dp;
    static int [][] temp;
    
    public static void main(String [] args) throws IOException{
    	String [] tokens = br.readLine().split(" ");
    	N = Integer.parseInt(tokens[0]);
    	M = Integer.parseInt(tokens[1]);
    	
    	arr = new int[N][M];
    	dp = new int[N][M];
    	/**
    	 * [j][0] : 위 -> 아래, 왼쪽 -> 오른쪽 중 최대
    	 * [j][1] : 위 -> 아래, 오른쪽 -> 왼쪽 중 최대
    	 */
    	temp = new int[M][2];
    	
    	for(int i=0;i<N;i++) {
    		tokens = br.readLine().split(" ");
    		for(int j=0;j<M;j++) {
    			arr[i][j] = Integer.parseInt(tokens[j]);
    		}
    	}
    	
    	// 초기값 설정
        dp[0][0] = arr[0][0];
        for (int j = 1; j < M; j++) {
            dp[0][j] = dp[0][j - 1] + arr[0][j];
        }

        for (int i = 1; i < N; i++) {
            // temp 배열 초기화
            temp[0][0] = dp[i - 1][0] + arr[i][0];
            for (int j = 1; j < M; j++) {
                temp[j][0] = Math.max(temp[j - 1][0], dp[i - 1][j]) + arr[i][j];
            }

            temp[M - 1][1] = dp[i - 1][M - 1] + arr[i][M - 1];
            for (int j = M - 2; j >= 0; j--) {
                temp[j][1] = Math.max(temp[j + 1][1], dp[i - 1][j]) + arr[i][j];
            }

            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(temp[j][0], temp[j][1]);
            }
        }

    	bw.write(dp[N-1][M-1]+"\n");
    	bw.flush();
    	bw.close();
    	br.close();
    }
}
