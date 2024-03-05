import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Deque<Integer> deque = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int answer = solution(N);
        bw.write(Integer.toString(answer)+"\n");
        bw.flush();
        bw.close();
    }
    public static int solution(int N){
        for(int i=1;i<=N;i++){
            deque.add(i);
        }
        while(deque.size()!=1){
            deque.removeFirst();    //제일 위에 있는 것 버림
            int second = deque.removeFirst();  //제일 위에 있는 숫자 빼서 제일 아래로
            deque.addLast(second);
        }
        return deque.getFirst();
    }
}
