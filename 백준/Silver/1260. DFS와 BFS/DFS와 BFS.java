import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);    //정점의 개수
        int M = Integer.parseInt(tokens[1]);    //간선의 개수
        int V = Integer.parseInt(tokens[2]);    //탐색을 시작할 정점의 번호

        int [][] wires = new int[N+1][N+1];
        boolean [] visited = new boolean[N+1];
        int [][] wires2 = new int[N+1][N+1];
        boolean [] visited2 = new boolean[N+1];
        for(int i=0;i<M;i++){
            tokens = br.readLine().split(" ");
            int v1 = Integer.parseInt(tokens[0]);
            int v2 = Integer.parseInt(tokens[1]);

            wires[v1][v2] = 1;
            wires[v2][v1] = 1;
            wires2[v1][v2] = 1;
            wires2[v2][v1] = 1;
        }
        dfs(V, wires, visited);
        bw.write("\n");
        bfs(V, wires2, visited2);
        bw.flush();
    }
    public static void bfs(int v, int[][] wires, boolean[] visited) throws IOException{
        Queue<Integer> q = new LinkedList<>();

        q.add(v);
        visited[v] = true;

        while(!q.isEmpty()){
            v = q.poll();
            bw.write(v+" ");
            for(int i=0;i<wires.length;i++){
                if(wires[v][i] == 1 && !visited[i]){
                    q.add(i);
                    visited[i] = true;
                }
            }
        }bw.write("\n");
    }

    public static void dfs(int v, int[][] wires, boolean[] visited) throws IOException{
        if(visited[v]){
            return;
        }
        bw.write(v+" ");
        visited[v] = true;
        for(int i=0;i<visited.length;i++){
            if(wires[v][i]==1 && !visited[i]){
                dfs(i, wires, visited);
            }
        }
    }
}
