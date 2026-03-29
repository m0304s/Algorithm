import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N + 2];
        int[] P = new int[N + 2];
        int[] dp = new int[N + 2];

        for(int i=1;i<=N;i++){
            String [] tokens = br.readLine().split(" ");
            int day = Integer.parseInt(tokens[0]);
            int profit = Integer.parseInt(tokens[1]);

            T[i] = day;
            P[i] = profit;
        }

        int max = 0;    //현재까지 얻은 최대 수익
        for(int i=1;i<=N+1;i++){
            // i일 시점에서의 최대 수익
            max = Math.max(max, dp[i]);

            if(i <= N){
                int nextDay = i + T[i];
                if(nextDay <= N+1){
                    dp[nextDay] = Math.max(dp[nextDay], max + P[i]);
                }
            }
        }

        bw.write(max+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}