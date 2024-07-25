import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int n;
    static int m;
    static int startX;
    static int startY;

    static int [] dx = {0,0,-1,1};
    static int [] dy = {-1,1,0,0};
    
    static class Node{
        int x;
        int y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    }
    
    static int [][] map;
    static int [][] result;
    static boolean [][] visited;
    
    public static void main(String[] args) throws IOException{
        String[] tokens = br.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);    //세로 크기
        m = Integer.parseInt(tokens[1]);    //가로 크기
        map = new int[n][m];
        result = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0;i<n;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<tokens.length;j++){
                map[i][j] = Integer.parseInt(tokens[j]);
                if(map[i][j] == 2){
                    startX = j;
                    startY = i;
                }else if(map[i][j] == 0){
                    visited[i][j] = true;
                }
            }
        }
        bfs(startX,startY);
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j]){
                    result[i][j] = -1;
                }
                bw.write(result[i][j] + " ");
            }bw.write("\n");
        }
        bw.flush();
    }
    public static void bfs(int x,int y){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));
        visited[y][x] = true;
        while(!queue.isEmpty()){
            Node now = queue.poll();
            for(int i=0;i<4;i++){
                int nextX = now.getX()+dx[i];
                int nextY = now.getY()+dy[i];
                // Check new node can move
                if(nextX>=0 && nextX < m && nextY >=0 && nextY < n){
                    if(!visited[nextY][nextX] && map[nextY][nextX] == 1){
                        visited[nextY][nextX] = true;
                        queue.add(new Node(nextX, nextY));
                        result[nextY][nextX] = result[now.getY()][now.getX()] + 1;
                    }
                }
            }
        }
    }
}