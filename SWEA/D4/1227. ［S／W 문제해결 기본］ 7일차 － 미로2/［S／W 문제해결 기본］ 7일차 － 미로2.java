import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static final int MAPSIZE = 100;
    static final int END = 3;
    static final int START = 2;
    static final int WALL = 1;
    static final int ROAD = 0;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[][] map;
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};
    static boolean[][] visited;

    static class Node{
        int x,y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    static int startX,startY,endX,endY;
    public static void main(String[] args) throws IOException {
        int T = 10;
        for(int t=1;t<=T;t++){
            int testCase = Integer.parseInt(br.readLine());
            map = new int[MAPSIZE][MAPSIZE];
            visited = new boolean[MAPSIZE][MAPSIZE];
            startX = 0;
            startY = 0;
            endX = 0;
            endY = 0;
            for(int i=0;i<MAPSIZE;i++){
                String input = br.readLine();
                for(int j=0;j<MAPSIZE;j++){
                    map[i][j] = input.charAt(j) - '0';
                    if(map[i][j] == START){
                        startX = i;
                        startY = j;
                    }else if(map[i][j] == END){
                        endX = i;
                        endY = j;
                    }
                }
            }
            bw.write("#"+testCase+" ");
            boolean result = bfs(startX, startY);
            if(result){
                bw.write("1\n");
            }else{
                bw.write("0\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static boolean bfs(int x,int y){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x,y));
        visited[x][y] = true;

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur.x == endX && cur.y == endY){
                return true;
            }

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(!inRange(nx,ny)) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] == WALL) continue;

                visited[nx][ny] = true;
                queue.add(new Node(nx,ny));
            }
        }

        return false;
    }
    private static boolean inRange(int x,int y){
        return x >= 0 && x < MAPSIZE && y >= 0 && y < MAPSIZE;
    }
}
