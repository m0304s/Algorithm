import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,M;
    static int [][] originalMap;
    static int [][] copyMap;
    static int [] output;
    static int answer = 0;
    static ArrayList<CCTV> cctvList = new ArrayList<>();

    //상 우 하 좌
    static int [] dx = {-1,0,1,0};
    static int [] dy = {0,1,0,-1};

    static class CCTV{
        int num,x,y;
        public CCTV(int num,int x,int y){
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);

        originalMap = new int[N][M];
        answer = Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                originalMap[i][j] = Integer.parseInt(tokens[j]);
                if(originalMap[i][j] != 0 && originalMap[i][j] != 6){
                    cctvList.add(new CCTV(originalMap[i][j],i,j));
                }
            }
        }
        output = new int[cctvList.size()];
        permutation(0,cctvList.size());
        System.out.println(answer);
    }
    static void permutation(int depth, int r){
        if(depth == r){
            copyMap = cloneMap();

            for(int i=0;i<cctvList.size();i++){
                direction(cctvList.get(i),output[i]);
            }
            getSafeZone();
            return;
        }

        for(int i=0;i<4;i++){
            output[depth] = i;
            permutation(depth+1,r);
        }
    }

    private static void getSafeZone() {
        int count = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(copyMap[i][j] == 0) count++;
            }
        }
        answer = Math.min(answer,count);
    }

    static void direction(CCTV cctv, int d){
        int cctvNum = cctv.num;
        if(cctvNum == 1){
            if(d == 0) watch(cctv,0);
            if(d == 1) watch(cctv,1);
            if(d == 2) watch(cctv,2);
            if(d == 3) watch(cctv,3);
        }else if(cctvNum == 2) {
            if(d == 0 || d == 2){
                watch(cctv,0);
                watch(cctv,2);
            }else if(d == 1 || d == 3){
                watch(cctv,1);
                watch(cctv,3);
            }
        }else if(cctvNum == 3) {
            if(d == 0){
                watch(cctv,0);
                watch(cctv,1);
            }else if(d == 1){
                watch(cctv,1);
                watch(cctv,2);
            }else if(d == 2){
                watch(cctv,2);
                watch(cctv,3);
            }else if(d == 3){
                watch(cctv,3);
                watch(cctv,0);
            }
        }else if(cctvNum == 4) {
            if(d == 0){
                watch(cctv,0);
                watch(cctv,1);
                watch(cctv,3);
            }else if(d == 1){
                watch(cctv,1);
                watch(cctv,0);
                watch(cctv,2);
            }else if(d == 2){
                watch(cctv,2);
                watch(cctv,1);
                watch(cctv,3);
            }else if(d == 3){
                watch(cctv,0);
                watch(cctv,2);
                watch(cctv,3);
            }
        }else if(cctvNum == 5) {
            watch(cctv,0);
            watch(cctv,1);
            watch(cctv,2);
            watch(cctv,3);
        }
    }

    private static void watch(CCTV cctv, int d) {
        Queue<CCTV> queue = new LinkedList<>();
        boolean [][] visited = new boolean[N][M];
        queue.add(cctv);
        visited[cctv.x][cctv.y] = true;

        while(!queue.isEmpty()){
            CCTV cur = queue.poll();

            int nx = cur.x + dx[d];
            int ny = cur.y + dy[d];

            if(!inRange(nx,ny)) break;
            if(copyMap[nx][ny] == 6) break;

            if(copyMap[nx][ny] == 0){
                copyMap[nx][ny] = -1;
                queue.add(new CCTV(cctv.num,nx,ny));
            }else{
                queue.add(new CCTV(cctv.num,nx,ny));
            }
        }
    }
    static boolean inRange(int x,int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static int [][] cloneMap(){
        int[][] map = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                map[i][j] = originalMap[i][j];
            }
        }
        return map;
    }
}
