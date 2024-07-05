import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static boolean[][] connections;
    static boolean[] visited;
    static int N;
    static int M;
    static class Node{
        int target;
        int dept;
        public Node(int target,int dept){
            this.target = target;
            this.dept = dept;
        }
    }
    public static void main(String[] args) throws IOException{
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);

        connections = new boolean[N+1][N+1];
        visited = new boolean[N+1];
    
        //Make connections
        for(int i=0;i<M;i++){
            tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            connections[a][b] = true;
            connections[b][a] = true;
        }
        
        ArrayList<Integer> result = new ArrayList<>();  //케빈 베이컨의 수를 저장하는 배열

        for(int i=1;i<=N;i++){
            int count = 0;
            visited = new boolean[N+1];
            visited[i] = true;
            count+=bfs(i);
            result.add(count);
        }
        int min = result.get(0);
        int ans = 0;
        for(int i=1;i<result.size();i++){
            if(min>result.get(i)){
                ans = i;
                min = result.get(i);
            }
        }
        bw.write(ans+1+"\n");
        bw.flush();
    }
    public static int bfs(int from){
        int total = 0;
        Queue<Node> queue = new LinkedList<>();
        for(int i=1;i<=N;i++){
            if(connections[from][i]){
                Node node = new Node(i, 1);
                queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if(visited[now.target]){
                continue;
            }
            visited[now.target] = true;
            total+=now.dept;
            
            for(int i=1;i<=N;i++){
                if(connections[now.target][i]){
                    Node new_node = new Node(i, now.dept+1);
                    queue.add(new_node);
                }
            }
        }
        return total;
    }
}
