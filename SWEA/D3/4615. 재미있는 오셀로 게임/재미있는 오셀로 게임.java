import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int WHITE = 2;
    static final int BLACK = 1;
    static final int BLANK = 0;

    static int [][] board;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    static int N,M;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            M = Integer.parseInt(tokens[1]);

            board = new int[N+1][N+1];
            initBoard();
            for(int m=0;m<M;m++){
                tokens = br.readLine().split(" ");
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                int color = Integer.parseInt(tokens[2]);

                placeStone(x,y,color);
            }
            int [] result = countStones();
            bw.write("#"+t+" "+result[0]+" "+result[1]+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void placeStone(int x,int y,int color){
        board[x][y] = color;

        //8방향 탐색
        for(int d=0;d<8;d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(inRange(nx,ny) && board[nx][ny] == getOpponent(color)){
                //주어진 방향으로 일직선을 그었을때 자신의 색이 등장해야함
                if(canFlip(nx,ny,d,color)){
                    flipStone(x,y,d,color);
                }
            }
        }
    }

    static boolean canFlip(int x, int y, int d, int color){
        int nx = x + dx[d];
        int ny = y + dy[d];

        while(inRange(nx,ny)){
            if(board[nx][ny] == BLANK) return false;
            if(board[nx][ny] == color) return true;

            nx += dx[d];
            ny += dy[d];
        }
        return false;
    }

    static void flipStone(int x,int y,int d,int color){
        int nx = x + dx[d];
        int ny = y + dy[d];

        while(inRange(nx,ny) && board[nx][ny] == getOpponent(color)){
            board[nx][ny] = color;

            nx+=dx[d];
            ny+=dy[d];
        }
    }

    // 보드 초기화 (정가운데 돌 배치)
    static void initBoard() {
        int mid = N / 2;
        board[mid][mid] = WHITE;
        board[mid + 1][mid + 1] = WHITE;
        board[mid][mid + 1] = BLACK;
        board[mid + 1][mid] = BLACK;
    }

    static int [] countStones(){
        int blackCnt = 0;
        int whiteCnt = 0;

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(board[i][j] == BLACK) blackCnt++;
                if(board[i][j] == WHITE) whiteCnt++;
            }
        }
        return new int[]{blackCnt,whiteCnt};
    }
    static boolean inRange(int x, int y){
        return x>=1 && x<=N && y>=1 && y<=N;
    }
    static int getOpponent(int color){
        return color == BLACK ? WHITE : BLACK;
    }
}
