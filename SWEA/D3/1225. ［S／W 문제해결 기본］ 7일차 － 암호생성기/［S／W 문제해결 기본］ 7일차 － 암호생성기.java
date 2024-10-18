import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Deque<Integer> deque = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        for(int t=1;t<=10;t++){
            int testCase = Integer.parseInt(br.readLine());
            String [] tokens = br.readLine().split(" ");
            deque.clear();
            for(int i=0;i<8;i++){
                deque.add(Integer.parseInt(tokens[i]));
            }
            Deque<Integer> answer = getPassword(deque);
            printAnswer(t,answer);
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static Deque<Integer> getPassword(Deque<Integer> deque){
        int minusCnt = 1;
        while(true){
            int fitstNum = deque.pollFirst();
            int afterMoveFirst = fitstNum - minusCnt;
            if(afterMoveFirst<=0){
                afterMoveFirst = 0;
                deque.addLast(afterMoveFirst);
                return deque;
            }
            minusCnt++;
            if(minusCnt == 6) minusCnt = 1;
            deque.addLast(afterMoveFirst);
        }
    }
    static void printAnswer(int T, Deque<Integer> answer) throws IOException {
        bw.write("#" + T + " ");
        while (!answer.isEmpty()) {
            bw.write(answer.pollFirst() + " ");
        }
        bw.write("\n");
    }

}
