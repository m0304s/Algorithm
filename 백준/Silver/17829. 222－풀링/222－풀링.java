import java.io.*;
import java.util.*;

public class Main{
    public static int [][] map;
    public static Queue<Integer> q = new LinkedList<>();
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((System.out)));
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0;i<N;i++){
            String[] tokens = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        solution(N);
        bw.write(Integer.toString(map[0][0])+"\n");
        bw.flush();
    }
    public static void solution(int n){
        if(n==1){  //1X1일 경우
            return;
        }else{
            for(int i=0;i<n;i=i+2){
                int [] max = new int[4];
                for(int j=0;j<n;j=j+2){
                    int idx = 0;
                    max[idx++] = map[i][j];
                    max[idx++] = map[i][j+1];
                    max[idx++] = map[i+1][j];
                    max[idx++] = map[i+1][j+1];

                    Arrays.sort(max);
                    q.add(max[2]);
                }  
            }
            n = n/2;   //새로운 배열의 크기
            map = new int[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    map[i][j] = q.poll();
                }
            }
            solution(n);
        }
    }
}