import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int WHITE = 2;
    static final int BLACK = 1;
    static final int BLANK = 0;

    static int [][] board;

    // 상, 하, 좌, 우, 대각선
    static int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int[] dy = {0, 0, -1, 1, -1, -1, 1, 1};

    static int N,M;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            M = Integer.parseInt(tokens[1]);

            board = new int[N+1][N+1];

            initBoard();
            for(int i=0;i<M;i++){
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

    static int [] countStones(){
        int black = 0;
        int white = 0;
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(board[i][j] == BLACK) black++;
                if(board[i][j] == WHITE) white++;
            }
        }
        return new int[]{black,white};
    }

    // 돌을 놓고 상대 돌을 뒤집는 로직
    static void placeStone(int x,int y,int color){
        board[x][y] = color;

        //8방향 탐색
        for(int d=0;d<8;d++){
            int nx = x+dx[d];
            int ny = y+dy[d];

            // 해당 방향으로 상대 돌이 있는지 확인
            if(inRange(nx,ny) && board[nx][ny] == getOpponent(color)){
                if(canFlip(nx,ny,d,color)){
                    flipStones(x,y,d,color);
                }
            }
        }
    }

    // 주어진 방향으로 돌을 뒤집을 수 있는지..
    static boolean canFlip(int x,int y,int d,int color){
        int nx = x + dx[d];
        int ny = y + dy[d];

        while(inRange(nx,ny)){
            if(board[nx][ny] == BLANK) return false;
            if(board[nx][ny] == color) return true;

            nx+=dx[d];
            ny+=dy[d];
        }
        return false;
    }

    static void flipStones(int x,int y,int d,int color){
        int nx = x+dx[d];
        int ny = y+dy[d];

        while(board[nx][ny] == getOpponent(color)){
            board[nx][ny] = color;
            nx+=dx[d];
            ny+=dy[d];
        }
    }

    static int getOpponent(int color){
        return color == BLACK ? WHITE : BLACK;
    }

    static boolean inRange(int x,int y){
        return x>=1 && x<=N && y>=1 && y<=N;
    }

    static void initBoard(){ // 보드 초기화
        // 4x4, 6x6, 8x8
        int middle = N/2;

        board[middle][middle] = board[middle+1][middle+1] = WHITE;
        board[middle][middle+1] = board[middle+1][middle] = BLACK;
    }

    static void debug(){
        System.out.println("==============================================");
        for(int i=1;i<board.length;i++){
            for(int j=1;j<board[0].length;j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("==============================================");
    }
}
