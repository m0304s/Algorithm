import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[] arr;
    public static boolean[] visited;

    public static ArrayList<Integer> list = new ArrayList<>();
    public static ArrayList<Integer> fin = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        String [] tokens = br.readLine().split(" ");
        int [] nums = new int[N];
        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(tokens[i]);
        }
        arr = new int[N];
        visited = new boolean[N];
        dfs(N,nums,0);
        Collections.sort(fin,Collections.reverseOrder());
        bw.write(Integer.toString(fin.get(0))+"\n");
        bw.flush();
    }
    public static void dfs(int N,int[] nums, int depth){
        if(depth == N){
            for (int i : arr) {
                list.add(nums[i]);
            }
            int result = 0;
            for(int i=0;i<N-1;i++){
                result+=(Math.abs(list.get(i)-list.get(i+1)));
            }
            fin.add(result);
            list.clear();
            return;
        }
        for(int i=0;i<N;i++){
            if(!visited[i]){
                visited[i] = true;
                arr[i] = depth;
                dfs(N,nums,depth+1);
                visited[i] = false;
            }
        }
        return;
    }
}
