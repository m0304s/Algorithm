import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};

    static int [][] mapBackup;
    static int [][] map;
    static boolean [][] visited;
    static int N;
    static int maxHeight = 0;

    static class Node{
        int x,y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        mapBackup = new int[N][N];
        visited = new boolean[N][N];
        for(int i=0;i<N;i++){
            String [] tokens = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                mapBackup[i][j] = Integer.parseInt(tokens[j]);
                maxHeight = Math.max(maxHeight,mapBackup[i][j]);
            }
        }
        int maxCount = findMaxSafeZone();
        System.out.println(maxCount);
    }
    static int findMaxSafeZone(){
        int maxSafeZoneCnt = 0;
        for(int h=0;h<=maxHeight;h++){
            copyMap();
            checkFloodPoint(h);
            int safeZoneCnt = 0;
            visited = new boolean[N][N];
            for(int i=0;i<map.length;i++){
                for(int j=0;j<map.length;j++){
                    if(map[i][j] != 0 && !visited[i][j]){
                        bfs(i,j);
                        safeZoneCnt++;
                    }
                }
            }
            maxSafeZoneCnt = Math.max(safeZoneCnt, maxSafeZoneCnt);
        }
        return maxSafeZoneCnt;
    }
    static void bfs(int x,int y){
        Queue<Node> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.add(new Node(x,y));

        while(!queue.isEmpty()){
            Node now = queue.poll();
            for(int i=0;i<4;i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(inRange(nx,ny) && !visited[nx][ny] && map[nx][ny] != 0){
                    visited[nx][ny] = true;
                    queue.add(new Node(nx,ny));
                }
            }
        }
    }

    static void checkFloodPoint(int h){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map.length;j++){
                if(map[i][j]<=h){
                    map[i][j] = 0;
                }
            }
        }
    }

    static void copyMap(){
        map = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] = mapBackup[i][j];
            }
        }
    }
    static boolean inRange(int x,int y){
        return x>=0 && x<N && y>=0 && y<N;
    }
    static void debug(){
        for (int i=0;i<N;i++) {
            for(int j=0;j<N;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
