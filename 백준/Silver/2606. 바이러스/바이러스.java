import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [][] graph;
    static int computers;

    public static void main(String[] args) throws IOException {
        computers = Integer.parseInt(br.readLine());
        graph = new int[computers+1][computers+1];

        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            String [] tokens = br.readLine().split(" ");
            int v1 = Integer.parseInt(tokens[0]);
            int v2 = Integer.parseInt(tokens[1]);

            graph[v1][v2] = 1;
            graph[v2][v1] = 1;
        }

        int answer = bfs()-1;
        bw.write(answer+"\n");
        bw.flush();
        bw.close();
    }
    private static int bfs(){
        int virusCount = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[computers+1];
        visited[1] = true;
        q.add(1);

        while(!q.isEmpty()){
            int node = q.poll();
            virusCount++;

            for(int i=0;i<computers+1;i++){
                if(graph[node][i] == 1 && !visited[i]){
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
        return virusCount;
    }
}
