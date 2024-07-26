import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;
    static int m;

    static int [][] map;
    static boolean [] visited;
    static int count = 0;
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        
        map = new int[n+1][n+1];
        visited = new boolean[n+1];
        for(int i=1;i<=m;i++){
            tokens = br.readLine().split(" ");
            int firstNode = Integer.parseInt(tokens[0]);
            int secondNode = Integer.parseInt(tokens[1]);
            
            map[firstNode][secondNode] = 1;
            map[secondNode][firstNode] = 1;
        }

        for(int i=1;i<=n;i++){
            if(!visited[i]){
                dfs(i);
                count++;
            }
        }
        bw.write(count+"\n");
        bw.flush();
    }
    static void dfs(int v){
        visited[v] = true;
        for(int i=1;i<=n;i++){
            if(!visited[i] && map[v][i] == 1){
                dfs(i);
            }
        }
    }
}
