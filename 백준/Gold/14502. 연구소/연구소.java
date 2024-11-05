import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int ROAD = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;

    static int N,M;
    static int [][] originMap;

    static int [] dx = {0,0,-1,1};
    static int [] dy = {1,-1,0,0};
    static int maxSafeZoneCnt = Integer.MIN_VALUE;

    static class Node{
        int x,y;

        public Node(int x,int y){
            this.x =x;
            this.y =y;
        }
    }
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);

        originMap = new int[N][M];

        for(int i=0;i<N;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                originMap[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        dfs(0);
        bw.write(maxSafeZoneCnt+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
    static void dfs(int wallCount){
        if(wallCount == 3){
            bfs();
            return;
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(originMap[i][j] == ROAD){
                    originMap[i][j] = WALL;
                    dfs(wallCount+1);
                    originMap[i][j] = ROAD;
                }
            }
        }
    }

    static void bfs(){
        Queue<Node> queue = new LinkedList<>();
        int [][] copyMap = copyMap();

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(copyMap[i][j] == VIRUS){
                    queue.add(new Node(i,j));
                }
            }
        }

        while (!queue.isEmpty()){
            Node cur = queue.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(inRange(nx,ny) && copyMap[nx][ny] == ROAD){
                    queue.add(new Node(nx,ny));
                    copyMap[nx][ny] = VIRUS;
                }
            }
        }

        countSafeZone(copyMap);
    }
    static void countSafeZone(int [][] copyMap){
        int safeZoneCnt = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(copyMap[i][j] == ROAD){
                    safeZoneCnt++;
                }
            }
        }

        maxSafeZoneCnt = Math.max(safeZoneCnt,maxSafeZoneCnt);
    }

    static boolean inRange(int x,int y){
        return x >=0 && x < N && y >= 0 && y < M;
    }

    static int [][] copyMap(){
        int [][] map = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                map[i][j] = originMap[i][j];
            }
        }

        return map;
    }
}
