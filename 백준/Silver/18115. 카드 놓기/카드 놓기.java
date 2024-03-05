import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Deque<Integer> deque = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int [] array = new int[N];  //A 수열
        String secondline = br.readLine();
        String [] split = secondline.split(" ");
        for(int i=0;i<N;i++){
            array[i]=Integer.parseInt(split[i]);
        }
        solution(array,N);
        for(int num : deque){
            bw.write(Integer.toString(num)+" ");
        }
        bw.flush();
        bw.close();
    }
    public static void solution(int[] array,int N){   //array => command 배열
        for(int i=array.length-1;i>=0;i--){
            if(array[i]==1){
                deque.addFirst(N-i);
            }else if(array[i]==2){
                int removeFirst = deque.removeFirst();
                deque.addFirst(N-i);
                deque.addFirst(removeFirst);
            }else{
                deque.addLast(N-i);
            }
        }
    }
}
