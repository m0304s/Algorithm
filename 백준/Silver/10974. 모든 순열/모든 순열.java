import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static List<Integer> numList = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        backTracking(0);
    }
    static void backTracking(int depth){
        if(depth == N){
            for (Integer i : numList) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
        for(int i=1;i<=N;i++){
            if(!visited[i]){
                visited[i] = true;
                numList.add(i);
                backTracking(depth+1);
                numList.remove(numList.size()-1);
                visited[i] = false;
            }
        }
    }
}
