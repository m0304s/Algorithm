import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [] board = new int[101];
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);

        for(int i=0;i<board.length;i++){
            board[i] = i;
        }

        for(int i=0;i<N;i++){
            tokens = br.readLine().split(" ");
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);

            board[x] = y;
        }

        for(int i=0;i<M;i++){
            tokens = br.readLine().split(" ");
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);

            board[x] = y;
        }

        bw.write(bfs(1)+"\n");
        bw.flush();
    }
    public static int bfs(int startNode){
        int [] check = new int[101];
        Queue<Integer> q = new LinkedList<>();
        q.offer(startNode);
        check[startNode] = 0;

        while(!q.isEmpty()){
            int nowNode = q.poll();
            for(int i=1;i<7;i++){
                int newNode = nowNode + i;

                if(newNode > 100){
                    continue;
                }

                if(check[board[newNode]] == 0){
                    q.add(board[newNode]);
                    check[board[newNode]] = check[nowNode] + 1;
                }

                if(newNode == 100){
                    return check[100];
                }
            }
        }
        return 0;
    }
}
