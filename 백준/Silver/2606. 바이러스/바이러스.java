import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());    //컴퓨터의 수
        int M = Integer.parseInt(br.readLine());    //간선의 수

        int [][] wires = new int[N+1][N+1];
        boolean [] visited = new boolean[N+1];

        for(int i=0;i<M;i++){
            String[] tokens = br.readLine().split(" ");
            int v1 = Integer.parseInt(tokens[0]);
            int v2 = Integer.parseInt(tokens[1]);

            wires[v1][v2] = 1;
            wires[v2][v1] = 1;
        }
        int answer = bfs(1,wires,visited);
        bw.write(answer+"\n");
        bw.flush();
    }
    public static int bfs(int V, int[][] wires, boolean[] visited){
        Queue<Integer> q = new LinkedList<>();

        int count = 0;
        q.add(V);
        visited[V] = true;

        while(!q.isEmpty()){
            V = q.poll();
            for(int i=0;i<wires.length;i++){
                if(wires[V][i] == 1 && !visited[i]){
                    q.add(i);
                    visited[i] = true;
                    count++;
                }
            }
        }
        return count;
    }
}
