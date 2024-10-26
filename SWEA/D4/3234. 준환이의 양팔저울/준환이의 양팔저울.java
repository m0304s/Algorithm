import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int [] weight;
    static boolean[] visited;
    static int [] output;

    static int answer;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            weight = new int[N];
            visited = new boolean[N];
            output = new int[N];
            answer = 0;
            String [] tokens = br.readLine().split(" ");
            for(int i=0;i<N;i++){
                weight[i] = Integer.parseInt(tokens[i]);
            }

            permutation(0);
            bw.write("#"+t+" "+answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void permutation(int depth){
        if(depth == N){
            findCounts(0,0,0);
        }
        for(int i=0;i<N;i++){
            if(visited[i]) continue;
            
            visited[i] = true;
            output[depth] = weight[i];
            permutation(depth+1);
            visited[i] = false;
        }
    }
    static void findCounts(int depth, int left, int right){
        if(left < right) return;
        if(depth == N){
            answer++;
            return;
        }
        findCounts(depth+1,left+output[depth],right);
        findCounts(depth+1,left,right+output[depth]);
    }
}
