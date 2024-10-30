import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int WALL = 1;
    static final int ROAD = 0;
    static final int START = 2;
    static final int END = 3;
    static final int TEST_CASE = 10;
    static final int MAP_SIZE = 100;

    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};

    static int [][] map;
    static boolean [][] visited;

    static int startX,startY;
    static int endX,endY;

    static class Node{
        int x,y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        for(int t=1;t<=TEST_CASE;t++){
            map = new int[MAP_SIZE][MAP_SIZE];
            visited = new boolean[MAP_SIZE][MAP_SIZE];
            int testCase = Integer.parseInt(br.readLine());
            for(int i=0;i<map.length;i++){
                String input = br.readLine();
                for(int j=0;j<input.length();j++){
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

            boolean possible = bfs(startX,startY);
            if(possible){
                bw.write("#"+t+" " + "1\n");
            }else{
                bw.write("#"+t+" " + "0\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static boolean bfs(int startX,int startY){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startX,startY));
        visited[startX][startY] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();
            if(map[cur.x][cur.y] == END) return true;

            visited[cur.x][cur.y] = true;
            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(!inRange(nx,ny) || visited[nx][ny]) continue;
                if(map[nx][ny] == WALL) continue;
                
                visited[nx][ny] = true;
                q.add(new Node(nx,ny));
            }
        }

        return false;
    }
    static boolean inRange(int x,int y){
        return x >= 0 && x < MAP_SIZE && y >= 0 && y < MAP_SIZE;
    }
}
