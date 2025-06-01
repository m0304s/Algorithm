import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final Character LAND = 'L', WATER = 'W';
    static int R,C;
    static Character[][] map;
    static int [] dx = {0,0,-1,1};
    static int [] dy = {-1,1,0,0};

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        R = Integer.parseInt(tokens[0]);
        C = Integer.parseInt(tokens[1]);
        map = new Character[R][C];

        for(int i=0;i<R;i++){
            String input = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = input.charAt(j);
            }
        }

        int maxTime = Integer.MIN_VALUE;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j] == LAND){
                    int time = bfs(i,j,new boolean[R][C]);
                    maxTime = Math.max(maxTime,time);
                }
            }
        }

        bw.write(maxTime+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static int bfs(int x,int y,boolean [][] visited){
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(x,y,0));
        visited[x][y] = true;

        int maxTime = 0;
        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            if(map[curNode.x][curNode.y] == LAND){
                maxTime = Math.max(maxTime, curNode.time);
            }

            for(int d=0;d<4;d++){
                int nx = curNode.x + dx[d];
                int ny = curNode.y + dy[d];

                if(!inRange(nx,ny) || map[nx][ny] != LAND || visited[nx][ny]) continue;

                queue.add(new Node(nx,ny, curNode.time+1));
                visited[nx][ny] = true;
            }
        }
        return maxTime;
    }

    static boolean inRange(int x,int y){
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    static class Node{
        int x,y;
        int time;

        public Node(int x,int y,int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
