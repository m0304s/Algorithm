import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,M;
    static int [] parent;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            M = Integer.parseInt(tokens[1]);
            parent = new int[N+1];

            for(int i=1;i<=N;i++){
                parent[i] = i;
            }

            for(int i=0;i<M;i++){
                tokens = br.readLine().split(" ");
                int from = Integer.parseInt(tokens[0]);
                int to = Integer.parseInt(tokens[1]);
                union(from,to);
            }
            int groupCount = 0;
            for(int i=1;i<parent.length;i++){
                if(find(i) == i){
                    groupCount++;
                }
            }
            bw.write("#"+t+" "+groupCount+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void union(int a,int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA!=rootB){
            parent[rootB] = rootA;
        }
    }
    static int find(int a){
        if(parent[a] == a){
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}
