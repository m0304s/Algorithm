import java.io.*;

public class Main{
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [] arr;
    static boolean [] visited;

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);
        arr = new int[M];
        visited = new boolean[N];
        backtracking(N, M, 0);
        bw.flush();
    }
    public static void backtracking(int N, int M, int depth) throws IOException{
        if(depth == M){
            for (int i : arr) {
                bw.write(i+" ");
            }bw.write("\n");
            return;
        }
        for(int i=0;i<N;i++){
            arr[depth] = i+1;
            backtracking(N, M, depth+1);
        }
        
    }
}