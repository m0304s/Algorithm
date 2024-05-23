import java.util.*;
import java.io.*;

public class Main {
    //위,아래,왼쪽,오른쪽,앞,뒤
    static int[] dx = {0,0,0,0,1,-1};
    static int[] dy = {0,0,1,-1,0,0};
    static int[] dz = {1,-1,0,0,0,0};
    static int N,M,H;
    static int[][][] map;

    static class Tomato{
        int x;
        int y;
        int z;
        int day;
        public Tomato(int z,int x,int y,int day){
            this.x = x;
            this.y = y;
            this.z = z;
            this.day = day;
        }
    }
    static Queue<Tomato> queue = new LinkedList<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String [] tokens = br.readLine().split(" ");
        M = Integer.parseInt(tokens[0]);
        N = Integer.parseInt(tokens[1]);
        H = Integer.parseInt(tokens[2]);

        map = new int[H][N][M]; //토마토를 저장할 3차원 배열

        for(int i=0;i<H;i++){
            for(int j=0;j<N;j++){
                tokens = br.readLine().split(" ");
                for(int k = 0;k<M;k++){
                    map[i][j][k] = Integer.parseInt(tokens[k]);
                    if(map[i][j][k]==1){
                        queue.add(new Tomato(i, j, k, 0));
                    }
                }
            }
        }
        bfs();
        bw.flush();
    }
    static void bfs() throws IOException{
        int day = 0;
        while(!queue.isEmpty()){
            Tomato t = queue.poll();
            day = t.day;
            for(int i=0;i<6;i++){
                int nextX = t.x+dx[i];
                int nextY = t.y+dy[i];
                int nextZ = t.z+dz[i];
                
                if(nextX>=0 && nextX<N && nextY>=0 && nextY<M && nextZ>=0 && nextZ<H){
                    if(map[nextZ][nextX][nextY]==0){
                        map[nextZ][nextX][nextY]=1;
                        queue.add(new Tomato(nextZ, nextX, nextY, day+1));
                    }
                }
            }
        }
        if(checkTomato()){
            bw.write(day+"\n");
        }else{
            bw.write("-1\n");
        }
    }
    static boolean checkTomato(){
        for(int i=0;i<H;i++){
            for(int j=0;j<N;j++){
                for(int k=0;k<M;k++){
                    if(map[i][j][k]==0){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
