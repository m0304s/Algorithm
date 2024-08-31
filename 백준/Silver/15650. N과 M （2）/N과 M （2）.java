import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int [] arr;
    public static boolean [] visited;
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);

        arr = new int[m];
        visited = new boolean[n];

        dfs(n, m, 0,0);
    }
    public static void dfs(int n,int m, int depth, int lastVisited){
        if(depth == m){
            for (int i : arr) {
                System.out.print(i + " ");
            }System.out.println();
            return;
        }
        for(int i=lastVisited;i<n;i++){
            if(!visited[i]){
                visited[i] = true;
                arr[depth] = i+1;
                dfs(n, m, depth+1,i);
                visited[i] = false;
            }
        }
    }
}
