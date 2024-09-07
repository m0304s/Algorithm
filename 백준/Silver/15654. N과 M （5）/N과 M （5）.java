import java.io.*;
import java.util.Arrays;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [] arr;
    static boolean [] visited;

    static int [] nums;
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);

        arr = new int[M];
        visited = new boolean[N];
        nums = new int[N];  //가능한 숫자
        tokens = br.readLine().split(" ");
        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(tokens[i]);
        }
        Arrays.sort(nums);
        backtracking(N, M, 0);
        bw.flush();
    }
    public static void backtracking(int N,int M,int depth) throws IOException{
        if(depth == M){
            for(int i=0;i<arr.length;i++){
                bw.write(arr[i] + " ");
            }bw.write("\n");
            return;
        }
        for(int i=0;i<N;i++){
            if(!visited[i]){
                visited[i] = true;
                arr[depth] = nums[i];
                backtracking(N,M,depth+1);
                visited[i] = false;
            }
        }
    }
}
