import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int AIRCLEANER = -1;

    static int [][] map;

    //우상좌하
    static int [] dx = {0,-1,0,1};
    static int [] dy = {1,0,-1,0};

    static int N,M,T;

    static class AirCleaner{
        int x,y;
        public AirCleaner(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    static ArrayList<AirCleaner> airCleaners = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        T = Integer.parseInt(tokens[2]);

        map = new int[N][M];

        for(int i=0;i<N;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(tokens[j]);
                if(map[i][j] == AIRCLEANER){
                    airCleaners.add(new AirCleaner(i,j));
                }
            }
        }

        for(int i=0;i<T;i++){
            diffusionDust();
            removeDust();
        }
        int totalDust = getTotalDust();
        bw.write(totalDust+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static void removeDust() {
        AirCleaner topAirCleaner = airCleaners.get(0);
        AirCleaner bottomAirCleaner = airCleaners.get(1);

        // 상부 공기청정기 바람 (반시계 방향)
        int top = topAirCleaner.x;
        for (int i = top - 1; i > 0; i--)
            map[i][0] = map[i - 1][0];
        for (int i = 0; i < M - 1; i++)
            map[0][i] = map[0][i + 1];
        for (int i = 0; i < top; i++)
            map[i][M - 1] = map[i + 1][M - 1];
        for (int i = M - 1; i > 1; i--)
            map[top][i] = map[top][i - 1];
        map[top][1] = 0;

        // 하부 공기청정기 바람 (시계 방향)
        int bottom = bottomAirCleaner.x;
        for (int i = bottom + 1; i < N - 1; i++)
            map[i][0] = map[i + 1][0];
        for (int i = 0; i < M - 1; i++)
            map[N - 1][i] = map[N - 1][i + 1];
        for (int i = N - 1; i > bottom; i--)
            map[i][M - 1] = map[i - 1][M - 1];
        for (int i = M - 1; i > 1; i--)
            map[bottom][i] = map[bottom][i - 1];
        map[bottom][1] = 0;
    }

    private static void diffusionDust(){
        int [][] tempMap = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] != 0 && map[i][j] != AIRCLEANER){
                    int diffusionAmount = map[i][j] / 5;    //확산되는 양
                    int diffusionCount = 0;
                    for(int d=0;d<4;d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if(inRange(nx,ny) && map[nx][ny] != AIRCLEANER){
                            diffusionCount++;
                            tempMap[nx][ny] += diffusionAmount;
                        }
                    }
                    tempMap[i][j] += map[i][j] - diffusionAmount * diffusionCount;
                }
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                map[i][j] = tempMap[i][j];
            }
        }
        addAircleanerToMap();
    }

    private static void addAircleanerToMap(){
        for (AirCleaner airCleaner : airCleaners) {
            map[airCleaner.x][airCleaner.y] = AIRCLEANER;
        }
    }
    private static boolean inRange(int x,int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private static void debug(){
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }
    }

    private static int getTotalDust(){
        int totalDust = 0;
        for (int[] ints : map) {
            for (int anInt : ints) {
                if(anInt == AIRCLEANER) continue;
                totalDust+=anInt;
            }
        }
        return totalDust;
    }
}
