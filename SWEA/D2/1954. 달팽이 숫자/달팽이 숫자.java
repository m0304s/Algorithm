import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // 우, 하, 좌, 상
    static int [] dx = {0,1,0,-1};
    static int [] dy = {1,0,-1,0};

    static boolean [][] visited;
    static int [][] map;
    static int N;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            drawMap();
            bw.write("#"+t+"\n");
            printMap();
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void printMap() throws IOException{
        for(int i=0;i<N;i++){
            for(int j=0;j<N-1;j++){
                bw.write(map[i][j]+" ");
            }bw.write(map[i][N-1]+"\n");
        }
    }
    static void drawMap(){
        int direction = 0;
        int num = 1;

        int x = 0;
        int y = 0;
        map[x][y] = 1;
        visited[x][y] = true;
        while(!checkAllVisited()){

            int nx = x + dx[direction];
            int ny = y + dy[direction];
            if(!inRange(nx,ny) || visited[nx][ny]){
                direction++;
                if(direction == 4) direction = 0;
                continue;
            }

            if(inRange(nx,ny) && !visited[nx][ny]){
                map[nx][ny] = num+1;
                x = nx;
                y = ny;
                visited[nx][ny] = true;
                num++;
            }
        }
    }
    static boolean inRange(int x,int y){
        if(x>=0 && x<N && y>=0 && y<N) return true;
        return false;
    }
    static boolean checkAllVisited(){
        for(int i=0;i<visited.length;i++){
            for(int j=0;j<visited[0].length;j++){
                if(!visited[i][j]) return false;
            }
        }
        return true;
    }
}
