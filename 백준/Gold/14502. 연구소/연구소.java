import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [][] map;
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};

    static class Node{
        int x,y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    static int N,M,maxSafeZone;

    static final int ROAD = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        maxSafeZone = 0;
        map = new int[N][M];

        for(int i=0;i<N;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        dfs(0);
        System.out.println(maxSafeZone);
    }
    private static void dfs(int depth){
        if(depth == 3){
            bfs();
            return;
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] == ROAD){
                    map[i][j] = WALL;
                    dfs(depth+1);
                    map[i][j] = ROAD;
                }
            }
        }
    }

    private static void bfs(){
        Queue<Node> queue = new LinkedList<>();
        int [][] copyMap = cloneMap();
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

                if(!inRange(nx,ny)) continue;
                if(copyMap[nx][ny] != ROAD) continue;

                copyMap[nx][ny] = VIRUS;
                queue.add(new Node(nx,ny));
            }
        }

        maxSafeZone = Math.max(maxSafeZone,countSafeZone(copyMap));
    }

    private static int countSafeZone(int [][] map) {
        int count = 0;
        for (int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] == ROAD) count++;
            }
        }
        return count;
    }

    private static boolean inRange(int x,int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }
    private static int [][] cloneMap(){
        int [][] copyMap = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                copyMap[i][j] = map[i][j];
            }
        }
        return copyMap;
    }
}
