import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            int N = Integer.parseInt(br.readLine());
            int [][] stickers = new int[2][N+1];
            int [][] dp = new int[2][N+1];
            for(int i=0;i<2;i++){
                String [] tokens = br.readLine().split(" ");
                for(int n=1;n<=N;n++){
                    stickers[i][n] = Integer.parseInt(tokens[n-1]);
                }
            }
            dp[0][1] = stickers[0][1];
            dp[1][1] = stickers[1][1];

            for(int i=2;i<=N;i++){
                dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + stickers[0][i];
                dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + stickers[1][i];
            }
            System.out.println(Math.max(dp[0][dp[0].length-1],dp[1][dp[0].length-1]));
        }
    }
}
