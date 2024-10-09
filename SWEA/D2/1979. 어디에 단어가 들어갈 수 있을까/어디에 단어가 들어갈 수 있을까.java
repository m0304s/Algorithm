import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int BLACK = 0;
    static final int WHITE = 1;

    static int [][] board;

    static int count = 0;
    static int N;   //단어 퍼즐의 크기
    static int K;   //단어의 길이

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            K = Integer.parseInt(tokens[1]);
            board = new int[N][N];
            for(int i=0;i<N;i++){
                tokens = br.readLine().split(" ");
                for(int j=0;j<N;j++){
                    board[i][j] = Integer.parseInt(tokens[j]);
                }
            }

            //가로 방향 탐색
            for(int i=0;i<N;i++){
                int length = 0;
                for(int j=0;j<N;j++){
                    if(board[i][j] == WHITE){
                        length++;
                    }else{
                        if(length == K) count++;
                        length = 0;
                    }
                }
                //행의 마지막까지 탐색 후 길이가 k일 경우 count+1
                if(length == K) count++;
            }

            for(int j=0;j<N;j++){
                int length = 0;
                for(int i=0;i<N;i++){
                    if(board[i][j] == WHITE){
                        length++;
                    }else{
                        if(length == K) count++;
                        length = 0;
                    }
                }
                //열의 마지막까지 탐색 후 길이가 k일 경우 count+1
                if(length == K) count++;
            }

            bw.write("#"+t+" "+count+"\n");
            count = 0;
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
