import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int RED = 1;
    static final int BLUE = 2;
    static final int GREEN = 3;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

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
    static boolean[][] visited;
    static boolean[][] colorVisited;

    static int answer = 0;
    static int n;
    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        colorVisited = new boolean[n][n];

        for(int i=0;i<n;i++){   //세로, Y
            String line = br.readLine();
            for(int j=0;j<n;j++){   //가로, X
                if(line.subSequence(j, j+1).equals("R")){
                    map[i][j] = RED;
                }else if(line.subSequence(j, j+1).equals("G")){
                    map[i][j] = GREEN;
                }else{
                    map[i][j] = BLUE;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j]){
                    bfs(j,i);
                    answer++;
                }
            }
        }
        bw.write(answer+" ");
        answer = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!colorVisited[i][j]){
                    coloredBfs(j,i);
                    answer++;
                }
            }
        }
        bw.write(answer+"\n");
        bw.flush();
    }

    public static void bfs(int x,int y){
        Queue<Node> queue = new LinkedList<>();
        visited[y][x] = true;
        queue.add(new Node(x, y));
        while(!queue.isEmpty()){
            Node now = queue.poll();
            for(int i=0;i<4;i++){
                int nextX = now.getX()+dx[i];
                int nextY = now.getY()+dy[i];

                // Check new Node can move
                if(nextX>=0 && nextX < n && nextY >=0 && nextY < n && !visited[nextY][nextX] && map[nextY][nextX] == map[y][x]){
                    queue.add(new Node(nextX, nextY));
                    visited[nextY][nextX] = true;
                }
            }
        }
    }
    public static void coloredBfs(int x,int y){
        Queue<Node> queue = new LinkedList<>();
        visited[y][x] = true;
        queue.add(new Node(x, y));
        while(!queue.isEmpty()){
            Node now = queue.poll();
            for(int i=0;i<4;i++){
                int nextX = now.getX()+dx[i];
                int nextY = now.getY()+dy[i];

                if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n ){
                    continue;
                }
                boolean ifColoredCanMove = false;
                int initialColor = map[y][x];
                if(initialColor == 1){
                    if(map[nextY][nextX] == 3 || map[nextY][nextX] == 1){
                        ifColoredCanMove = true;
                    }
                }else if(initialColor == 2){
                    if(map[nextY][nextX] == 2){
                        ifColoredCanMove = true;
                    }
                }else{
                    if(map[nextY][nextX] == 3 || map[nextY][nextX] == 1){
                        ifColoredCanMove = true;
                    }
                }
                // Check new Node can move
                if(!colorVisited[nextY][nextX] && ifColoredCanMove){
                    queue.add(new Node(nextX, nextY));
                    colorVisited[nextY][nextX] = true;
                }
            }
        }
    }
}
