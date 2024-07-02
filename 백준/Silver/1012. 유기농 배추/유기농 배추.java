import java.io.*;
import java.util.*;

public class Main{
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int M;
    static int N;
    static int T;
    static int K;
    static int [][] map;
    static boolean [][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static class Node{
        int x;
        int y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        T = Integer.parseInt(br.readLine());
        for(int t = 0; t<T ; t++){
            //각 케이스 별 map 입력
            String [] tokens = br.readLine().split(" ");
            M = Integer.parseInt(tokens[0]);    //세로, map[0].length
            N = Integer.parseInt(tokens[1]);    //가로, map.length
            K = Integer.parseInt(tokens[2]);

            map = new int[N][M];
            visited = new boolean[N][M];

            for(int q = 0; q < K; q++){
                tokens = br.readLine().split(" ");
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                map[y][x] = 1;
            }
            int count = 0;
            for(int i=0;i<map.length;i++){  //세로
                for(int j=0;j<map[0].length;j++){   //가로
                    if(map[i][j]==1 && !visited[i][j]){
                        count++;
                        bfs(j,i);
                    }
                }
            }
            bw.write(count+"\n");
        }
        bw.flush();
    }
    static void bfs(int x,int y){
        queue.add(new Node(x, y));
        visited[y][x] = true;

        while(!queue.isEmpty()){
            Node now = queue.poll();
            for(int i=0;i<4;i++){
                int new_x = now.x+dx[i];
                int new_y = now.y+dy[i];
                
                //Check new Node
                if(new_x >=0 && new_x < M && new_y>=0 && new_y< N && !visited[new_y][new_x] && map[new_y][new_x] == 1){
                    queue.add(new Node(new_x, new_y));
                    visited[new_y][new_x] = true;
                }
            }
        }
    }
}