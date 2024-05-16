import java.io.*;
import java.util.*;

public class Main{
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());    //전체 사람의 수
        String [] tokens = br.readLine().split(" ");
        int n1 = Integer.parseInt(tokens[0]);   //첫번째 사람의 번호
        int n2 = Integer.parseInt(tokens[1]);   //두번째 사람의 번호

        int [][] wires = new int[N+1][N+1];
        boolean [] visited = new boolean[N+1];

        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            tokens = br.readLine().split(" ");
            int v1 = Integer.parseInt(tokens[0]);
            int v2 = Integer.parseInt(tokens[1]);

            wires[v1][v2] = wires[v2][v1] = 1;
        }
        int count = bfs(n1,n2, wires, visited);
        System.out.println(count);
    }
    public static int bfs(int v1,int v2, int[][] wires, boolean[] visited){
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{v1, 0});
        visited[v1] = true;
        int count = 0;

        while(!q.isEmpty()){
            int[] temp = q.poll();
            if(temp[0]==v2){
                count = temp[1];   //친척 관계가 있는 경우
                break;
            }
            for(int i=0;i<wires.length;i++){
                if(wires[temp[0]][i]==1 && !visited[i]){
                    q.add(new int[]{i, temp[1] + 1});
                    visited[i] = true;
                }
            }
        }
        if(count == 0){
            return -1;
        }
        return count;
    }
}