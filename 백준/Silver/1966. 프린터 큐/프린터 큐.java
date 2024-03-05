import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Deque<Integer> deque = new LinkedList<>();
    public static Deque<Integer> deque2 = new LinkedList<>();
    public static void main(String[] args) throws Exception{
        int T = Integer.parseInt(br.readLine());    //테스트 개수
        for(int i=0;i<T;i++){
            String[] firstLine = br.readLine().split(" ");
            int N = Integer.parseInt(firstLine[0]);   //문서의 개수
            int M = Integer.parseInt(firstLine[1]);     //큐에서 몇번쨰 놓여있는지
            String [] secondLine = br.readLine().split(" ");
            int count=1;    //다른 큐에 들어갈 변수
            for(int j=0;j<secondLine.length;j++){
                deque2.add(count++);
                deque.add(Integer.parseInt(secondLine[j]));
            }

            int result= solution(M);
            bw.write(Integer.toString(result)+"\n");
            deque.clear();
            deque2.clear();
        }
        bw.flush();
        bw.close();
    }
    public static int solution(int M){  //찾고싶어하는 문서
        int answer=0;
        while(!deque.isEmpty()){
            int maxValue = findMaxValue();  //지금 현재 deque의 최대값 찾기
            if(deque.getFirst()<maxValue){
                deque.addLast(deque.removeFirst());
                deque2.addLast(deque2.removeFirst());
            }else{
                int outNum = deque2.removeFirst();
                deque.removeFirst();
                if(M+1==outNum){
                    break;
                }
                answer++;
            }
        }
        return answer+1;
    }
    public static int findMaxValue(){
        int max=Integer.MIN_VALUE;
        if(deque.isEmpty()){
            return 0;
        }else{
            for (int num : deque) {
                if (num > max) {
                    max = num;
                }
            }
        }
        return max;
    }
}
