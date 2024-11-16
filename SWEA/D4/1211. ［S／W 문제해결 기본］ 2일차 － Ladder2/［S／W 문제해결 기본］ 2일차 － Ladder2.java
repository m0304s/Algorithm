import java.io.*;

public class Solution {
    //좌 우 하
    static int [] dx = {0,0,1};
    static int [] dy = {-1,1,0};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int TESTCASE = 10;
    static final int BOARDSIZE = 100;
    static final int WALL = 0;
    static final int ROAD = 1;

    static int [][] board;
    static int [][] map;
    static int minLength;
    static int answer;

    public static void main(String[] args) throws IOException {
        for(int t=1;t<=TESTCASE;t++){
            br.readLine();
            minLength = Integer.MAX_VALUE;
            answer = 0;
            board = new int[BOARDSIZE][BOARDSIZE];
            makeBoard();
            cloneBoard();
            for(int x=0;x<BOARDSIZE;x++){
                if(map[0][x] == ROAD){
                    simulate(0,x);
                }
            }
            bw.write("#"+t+" "+answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static void simulate(int startX,int startY){
        int count = 0;
        int x = startX;
        int y = startY;

        while(inRange(x,y) && map[x][y] == ROAD){
            if(x == BOARDSIZE-1) break;
            count++;
            //좌측 또는 우측으로 갈 수 있으면 끝까지 이동
            for(int i=0;i<2;i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!inRange(nx,ny)) continue;
                if(map[nx][ny] == WALL) continue;

                while(inRange(nx,ny) && map[nx][ny] == ROAD){
                    nx += dx[i];
                    ny += dy[i];
                    count++;
                }
                x = (nx-dx[i]);
                y = (ny-dy[i]);
                break;
            }
            //아래로 이동 -> 좌측 또는 우측이 1이 나올때까지 중지
            x+=dx[2];
            y+=dy[2];
            while(inRange(x,y) && map[x][y] == ROAD){
                if(checkLeftRightBlock(x,y)){
                    break;
                }else{
                    x += dx[2];
                    y += dy[2];
                    count++;
                }
            }
        }
        if(minLength >= count){
            minLength = Math.min(minLength,count);
            answer = Math.max(answer,startY);
        }
    }
    private static boolean checkLeftRightBlock(int x, int y){
        for(int i=0;i<2;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(inRange(nx,ny) && map[nx][ny] == ROAD) return true;
        }
        return false;
    }
    private static boolean inRange(int x,int y){
        return x >= 0 && x < BOARDSIZE && y >= 0 && y < BOARDSIZE;
    }

    private static void cloneBoard() {
        map = new int[BOARDSIZE][BOARDSIZE];
        for (int i=0; i<BOARDSIZE;i++) {
            map[i] = board[i].clone();
        }
    }

    private static void makeBoard() throws IOException{
        for(int i=0;i<BOARDSIZE;i++){
            String [] tokens = br.readLine().split(" ");
            for(int j=0;j<BOARDSIZE;j++){
                board[i][j] = Integer.parseInt(tokens[j]);
            }
        }
    }
}
