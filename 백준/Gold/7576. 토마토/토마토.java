import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static int [][] map;

    static int [] dx = {0,0,-1,1};
    static int [] dy = {1,-1,0,0};
    
    static class Tomato{
        int x;
        int y;
        int day;
        public Tomato(int x,int y,int day){
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }
    static Queue<Tomato> queue = new LinkedList<>();

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens  = br.readLine().split(" ");

        M = Integer.parseInt(tokens[0]);
        N = Integer.parseInt(tokens[1]);

        map = new int[N][M];

        for(int i=0;i<N;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(tokens[j]);
                if(map[i][j] == 1){
                    queue.add(new Tomato(i, j, 0));
                }
            }
        }
        bfs();
        bw.flush();
    }
    static void bfs()throws IOException{
        int day = 0;
        while(!queue.isEmpty()){
            Tomato t = queue.poll();
            day = t.day;
            for(int i=0;i<4;i++){
                int nextX = t.x+dx[i];
                int nextY = t.y+dy[i];
                if(nextX>=0 && nextX<N && nextY>=0 && nextY<M){
                    if(map[nextX][nextY]==0){
                        map[nextX][nextY]=1;
                        queue.add(new Tomato(nextX, nextY, day+1));
                    }
                }
            }
        }
        if(checkStatus()){
            bw.write(day+"\n");
        }else{
            bw.write(-1+"\n");
        }
    }
    static boolean checkStatus() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0)
                    return false;
                // 덜 익은 토마토가 있다면
            }
        }
        return true;
    }
}
