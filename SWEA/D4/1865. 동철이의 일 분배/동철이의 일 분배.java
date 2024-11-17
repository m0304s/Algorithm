import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int [][] probability;
    static double maxProbability;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            probability = new int[N][N];
            visited = new boolean[N];
            maxProbability = Double.MIN_VALUE;
            for(int i=0;i<N;i++){
                String [] tokens = br.readLine().split(" ");
                for(int j=0;j<N;j++){
                    probability[i][j] = Integer.parseInt(tokens[j]);
                }
            }

            permutation(0,1.0);
            bw.write("#"+t+" "+String.format("%.6f",maxProbability*100)+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static void permutation(int depth, double currentProbability){
        if(depth == N){
            maxProbability = Math.max(maxProbability,currentProbability);
            return;
        }
        if(currentProbability<maxProbability) return;
        for(int i=0;i<N;i++){
            if(visited[i]) continue;
            visited[i] = true;
            permutation(depth+1,currentProbability*toDouble(probability[depth][i]));
            visited[i] = false;
        }
    }
    private static double toDouble(int i){
        return i/100.0;
    }
}
