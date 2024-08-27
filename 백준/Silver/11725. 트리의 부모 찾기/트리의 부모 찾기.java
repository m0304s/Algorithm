import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

        for(int i=0;i<n;i++){
            tree.add(new ArrayList<>());
        }
        for(int i=0;i<n-1;i++){
            String [] tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);

            tree.get(a-1).add(b-1);
            tree.get(b-1).add(a-1);
        }

        boolean[] visited = new boolean[n];
        int [] parentNode = new int[n];

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while(!q.isEmpty()){
            int v = q.remove();
            for (int node : tree.get(v)) {
                if(!visited[node]){
                    visited[node] = true;
                    q.add(node);
                    parentNode[node] = v;
                }
            }
        }
        for(int i=1;i<n;i++){
            bw.write((parentNode[i]+1)+"\n");
        }
        bw.flush();
    }
}
