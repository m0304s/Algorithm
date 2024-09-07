import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [] arr;
    static boolean [] visited;
    static int [] nums;
    static LinkedHashMap<Integer,ArrayList<Integer>> finalMap = new LinkedHashMap<>();
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);

        arr = new int[M];
        visited = new boolean[N];
        nums = new int[N];

        tokens = br.readLine().split(" ");
        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(tokens[i]);
        }
        Arrays.sort(nums);
        backtracking(N, M, 0);
        for (ArrayList<Integer> list : finalMap.values()) {
            for (int num : list) {
                bw.write(num + " ");
            }
            bw.write("\n");
        }
        bw.flush();
    }
    static void backtracking(int N, int M, int depth){
        if(depth == M){
            ArrayList<Integer> list = new ArrayList<>();
            for(int i=0;i<arr.length;i++){
                list.add(arr[i]);
            }
            int hashCode = list.hashCode();
            if(!finalMap.containsKey(hashCode)){
                finalMap.put(hashCode, list);
            }
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
