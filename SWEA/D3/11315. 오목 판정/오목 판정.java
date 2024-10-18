import java.io.*;

public class Solution {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static char[][] board;

    // 상, 하, 좌, 우, 대각선 8방향
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            board = new char[N][N];

            for(int i=0;i<N;i++){
                String input = br.readLine();
                for(int j=0;j<N;j++){
                    board[i][j] = input.charAt(j);
                }
            }

            boolean answer = checkFiveStone();
            if(answer) bw.write("#"+t+" "+"YES\n");
            else bw.write("#"+t+" "+"NO\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean checkFiveStone() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(board[i][j] == 'o'){
                    int cnt = 1;
                    //8방향 탐색
                    for(int d=0;d<8;d++){
                        boolean result = checkEqualChar(i,j,d,cnt);
                        if(result) return true;
                    }
                }
            }
        }
        return false;
    }
    static boolean checkEqualChar(int x,int y,int direction,int count){
        int nx = x + dx[direction];
        int ny = y + dy[direction];

        if(count == 5) return true;

        if(inRange(nx,ny) && board[nx][ny] == 'o') return checkEqualChar(nx,ny,direction,count+1);

        return false;
    }

    static boolean inRange(int x,int y){
        return x>=0 && x<N && y>=0 && y<N;
    }
}