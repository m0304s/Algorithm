import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static double maxPossibility;

    static int [][] possibility;
    static double [] output;
    static boolean [] visited;
    static int N;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            output = new double[N];
            visited = new boolean[N];
            maxPossibility = Double.MIN_VALUE;
            possibility = new int[N][N];
            for(int i=0;i<N;i++){
                String [] tokens = br.readLine().split(" ");
                for(int j=0;j<N;j++){
                    possibility[i][j] = Integer.parseInt(tokens[j]);
                }
            }
            dfs(0,1.0);
//            bw.write("#"+t+" "+String.format("%.6f",maxPossibility*100));
            System.out.println("#"+t+" "+String.format("%.6f",maxPossibility*100));
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static void dfs(int depth,double currentPossibility){
        if(depth == N){
            maxPossibility = Math.max(currentPossibility,maxPossibility);
            return;
        }
        if (currentPossibility <= maxPossibility) {
            return;
        }
        for(int i=0;i<N;i++){
            if(visited[i]) continue;
            visited[i] = true;
            dfs(depth+1,currentPossibility*toDouble(possibility[depth][i]));
            visited[i] = false;
        }
    }
    private static double toDouble(int num){
        return num/100.0;
    }
}
