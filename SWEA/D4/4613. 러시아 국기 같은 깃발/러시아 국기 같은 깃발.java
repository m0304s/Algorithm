import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,M;
    static char [][] board;

    static int min;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            min = Integer.MAX_VALUE;
            String [] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            M = Integer.parseInt(tokens[1]);

            board = new char[N][M];

            //깃발 입력
            for(int i=0;i<N;i++){
                board[i] = br.readLine().toCharArray();
            }

            for(int whiteEnd = 0;whiteEnd<N-2;whiteEnd++){
                int toWhite = 0;
                for(int i=0;i<=whiteEnd;i++){
                    toWhite+=toColor('W',i);
                }

                for(int blueEnd= whiteEnd+1;blueEnd<N-1;blueEnd++){
                    int toBlue = 0;
                    for(int j=whiteEnd+1;j<=blueEnd;j++){
                        toBlue+=toColor('B',j);
                    }

                    int toRed = 0;
                    for(int redEnd = blueEnd+1;redEnd<N;redEnd++){
                        toRed+=toColor('R',redEnd);
                    }

                    min = Math.min(min,toWhite+toBlue+toRed);
                }
            }
            bw.write("#"+t+" "+min+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int toColor(char color, int index){
        int count = 0;

        for(int i=0;i<board[index].length;i++){
            if(board[index][i] != color) count++;
        }
        return count;
    }
}
