import java.io.*;
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int k = Integer.parseInt(tokens[1]);

        int [][]dp = new int[31][31];
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;

        for(int i=3;i<n+1;i++){
            for(int j=1;j<i+1;j++){
                if(j==1 || j == i){
                    dp[i][j] = 1;
                }else{
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }
            }
        }
        // 조합 계산
        bw.write(Integer.toString(dp[n][k])+"\n");
        bw.flush();
        bw.close();
    }
}
