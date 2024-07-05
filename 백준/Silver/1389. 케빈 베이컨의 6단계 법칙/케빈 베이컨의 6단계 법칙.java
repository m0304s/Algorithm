import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static boolean[][] connections;
    static boolean[] visited;
    static int N;
    static int M;
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
            for(int j=1;j<=N;j++){
                if(i==j) continue;
                visited = new boolean[N+1];
                visited[i] = true;
                count+=bfs(i,j);
            }
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
    public static int bfs(int from, int to){
        Queue<Integer> queue = new LinkedList<>();
        for(int j=1;j<=N;j++){
            if(connections[from][j]){
                visited[j] = true;
                queue.add(j);
            }
        }
        int cnt=1;
		while(!queue.isEmpty()) {
			int qSize=queue.size();
			while(qSize-->0) {
				int num=queue.poll();
				if(num==to) {
					return cnt;
				}
				for(int j=1; j<=N; j++) {
					if(visited[j]==true || !connections[num][j]) continue;
					visited[j]=true;
					queue.offer(j);
				}
			}
			cnt++;
		}
        return 0;
    }
}
