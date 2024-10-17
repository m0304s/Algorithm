import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static boolean [] visited;
    static ArrayList<ArrayList<Integer>> nodeList = new ArrayList<>();
    static int N;
    static int M;
    static int answer;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            answer = 1;
            String [] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            M = Integer.parseInt(tokens[1]);

            visited = new boolean[N+1];
            nodeList = new ArrayList<>();
            for(int i=0;i<=N;i++){
                nodeList.add(new ArrayList<>());
            }

            for(int i=0;i<M;i++){
                tokens = br.readLine().split(" ");
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);

                nodeList.get(x).add(y);
                nodeList.get(y).add(x);
            }

            for(int i=1;i<=N;i++){
                dfs(i,0);
            }
            bw.write("#"+t+" "+answer+"\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
    static void dfs(int startNode, int depth){
        if(allChildNodeHasVisited(startNode)){
            answer = Math.max(answer,depth);
        }else{
            for (int now : nodeList.get(startNode)) {
                if(!visited[now]){
                    visited[now] = true;
                    dfs(now,depth+1);
                    visited[now] = false;
                }
            }
        }
    }
    static boolean allChildNodeHasVisited(int startNode){
        for (int i: nodeList.get(startNode)) {
            if(!visited[i]) return false;
        }
        return true;
    }

}
