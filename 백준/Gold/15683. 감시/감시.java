import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,M,minBlindSpot;
    static int [][] originalMap;
    static int [][] cloneMap;

    static final int ROAD = 0;
    static final int WALL = 6;

    static class CCTV{
        int x;
        int y;
        int number;

        public CCTV(int x,int y, int number){
            this.x = x;
            this.y = y;
            this.number = number;
        }
    }

    static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌 시계방향 순서
    static int[] dy = {0, 1, 0, -1};

    static List<CCTV> cctvList;
    static int [] output;
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        minBlindSpot = Integer.MAX_VALUE;
        originalMap = new int[N][M];
        cctvList = new ArrayList<>();

        for(int i=0;i<N;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                originalMap[i][j] = Integer.parseInt(tokens[j]);
                if(originalMap[i][j] != ROAD && originalMap[i][j] != WALL){
                    cctvList.add(new CCTV(i,j,originalMap[i][j]));
                }
            }
        }
        output = new int[cctvList.size()];
        permutation(0);
        System.out.println(minBlindSpot);
    }
    private static void permutation(int depth){
        if(depth == cctvList.size()){
            cloneMap = getCloneMap();
            for(int i=0;i< output.length;i++){
                direction(cctvList.get(i),output[i]);
            }
            getBlindSpot();
            return;
        }

        for(int i=0;i<4;i++){
            output[depth] = i;
            permutation(depth+1);
        }
    }

    private static void getBlindSpot() {
        int blindSpot = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(cloneMap[i][j] == 0) blindSpot++;
            }
        }
        minBlindSpot = Math.min(minBlindSpot,blindSpot);
    }

    private static void direction(CCTV cctv, int direction) {
        if (cctv.number == 1){
            if(direction == 0) watch(cctv,0);
            else if(direction == 1) watch(cctv,1);
            else if(direction == 2) watch(cctv,2);
            else if(direction == 3) watch(cctv,3);
        }else if(cctv.number == 2){
            if(direction == 0 || direction == 2){
                watch(cctv,0);
                watch(cctv,2);
            }else if(direction == 1 || direction == 3){
                watch(cctv,1);
                watch(cctv,3);
            }
        }else if(cctv.number == 3){
            if(direction == 0){ //상우
                watch(cctv,0);
                watch(cctv,1);
            }else if(direction == 1) {   //우하
                watch(cctv, 1);
                watch(cctv, 2);
            }else if(direction == 2){   //하좌
                watch(cctv,2);
                watch(cctv,3);
            }else if(direction == 3){   //좌상
                watch(cctv,3);
                watch(cctv,0);
            }
        }else if(cctv.number == 4){
            if(direction == 0){
                watch(cctv,0);
                watch(cctv,1);
                watch(cctv,3);
            }else if (direction == 1){
                watch(cctv,0);
                watch(cctv,1);
                watch(cctv,2);
            }else if(direction == 2){
                watch(cctv,1);
                watch(cctv,2);
                watch(cctv,3);
            }else if(direction == 3){
                watch(cctv,0);
                watch(cctv,2);
                watch(cctv,3);
            }
        }else if(cctv.number == 5){
            watch(cctv,0);
            watch(cctv,1);
            watch(cctv,2);
            watch(cctv,3);
        }
    }

    private static void watch(CCTV cctv, int i) {
        Queue<CCTV> queue = new LinkedList<>();
        queue.add(new CCTV(cctv.x,cctv.y,cctv.number));

        while(!queue.isEmpty()){
            CCTV cur = queue.poll();

            int nx = cur.x + dx[i];
            int ny = cur.y + dy[i];

            if(!inRange(nx,ny)) break;
            if(cloneMap[nx][ny] == WALL) break;

            if(cloneMap[nx][ny] == 0){
                queue.add(new CCTV(nx,ny,cctv.number));
                cloneMap[nx][ny] = -1;
            }else{
                queue.add(new CCTV(nx,ny,cctv.number));
            }
        }
    }

    private static int [][] getCloneMap(){
        int [][] map = new int[N][M];
        for(int i=0;i<N;i++){
            map[i] = originalMap[i].clone();
        }
        return map;
    }

    private static boolean inRange(int x,int y){
        return x >= 0 && x < N && y >= 0 && y <M;
    }
}
