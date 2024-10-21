import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [][] maze;
    static boolean [][] visited;

    static final int BLANK = 0;
    static final int WALL = 1;
    static final int START = 2;
    static final int END = 3;

    static int startX,startY;
    static int endX,endY;

    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};

    static class Node{
        int x,y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        for(int t=1;t<=10;t++){
            int testCase = Integer.parseInt(br.readLine());
            visited = new boolean[16][16];
            maze = new int[16][16];
            startX = 0;
            startY = 0;
            endX = 0;
            endY = 0;
            for(int i=0;i<16;i++){
                String input = br.readLine();
                for(int j=0;j<16;j++){
                    maze[i][j] = input.charAt(j)-'0';
                    if(maze[i][j] == START){
                        startX = i;
                        startY = j;
                    }else if(maze[i][j] == END){
                        endX = i;
                        endY = j;
                    }
                }
            }
            int result = bfs(startX,startY);
            bw.write("#"+testCase+" "+result+"\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
    static int bfs(int x,int y){
        Queue<Node> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new Node(x,y));

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.x == endX && cur.y == endY){
                return 1;
            }

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(!inRange(nx,ny)) continue;
                if(visited[nx][ny] || maze[nx][ny] == WALL) continue;

                visited[nx][ny] = true;
                q.add(new Node(nx,ny));
            }
        }
        return 0;
    }
    static boolean inRange(int x,int y){
        return x>=0 && x<16 && y>=0 && y<16;
    }
}
