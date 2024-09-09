import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [] nums;
    static int [] arr;
    static boolean [] visited;
    static LinkedHashMap<Integer,ArrayList<Integer>> linkedHashMap = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);

        arr = new int[M];
        nums = new int[N];
        visited = new boolean[N];

        tokens = br.readLine().split(" ");
        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(tokens[i]);
        }
        Arrays.sort(nums);
        backTracking(N, M, 0,0);
        for (ArrayList<Integer> list : linkedHashMap.values()) {
            for (int num : list) {
                bw.write(num+" ");
            }bw.write("\n");
        }
        bw.flush();
    }
    public static void backTracking(int N,int M,int depth,int lastvisited){
        if(depth == M){
            ArrayList<Integer> list = new ArrayList<>();
            for(int i=0;i<arr.length;i++){
                list.add(arr[i]);
            }
            if(isNonDescending(list)){   //비내림차순일 경우
                int hashCode = list.hashCode();
                linkedHashMap.put(hashCode, list);
            }
            lastvisited++;
            return;
        }
        for(int i=lastvisited;i<N;i++){
            arr[depth] = nums[i];
            backTracking(N, M, depth+1,lastvisited);
        }
    }
    private static boolean isNonDescending(ArrayList<Integer> list) {
        for(int i=1;i<list.size();i++){
            if(list.get(i) < list.get(i-1)){
                return false;
            }
        }return true;
    }
}
