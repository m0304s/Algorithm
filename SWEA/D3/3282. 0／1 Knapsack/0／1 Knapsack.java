import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [] weight;
    static int [] cost;

    static int N,K;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            K = Integer.parseInt(tokens[1]);

            weight = new int[N+1];
            cost = new int[N+1];

            for(int i=1;i<=N;i++){
                tokens = br.readLine().split(" ");
                int v = Integer.parseInt(tokens[0]);
                int c = Integer.parseInt(tokens[1]);

                weight[i] = v;
                cost[i] = c;
            }

            int [][] dp = new int[N+1][K+1];

            for(int i=1;i<=N;i++){
                for(int j=1;j<=K;j++){
                    if(weight[i] > j) dp[i][j] = dp[i-1][j];
                    else{
                        dp[i][j] = Math.max(dp[i-1][j-weight[i]] + cost[i], dp[i-1][j]);
                    }
                }
            }

            bw.write("#"+t+" "+dp[N][K]+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
