import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            int inputNum = Integer.parseInt(br.readLine());
            if(inputNum == 0){
                if(maxHeap.isEmpty()){
                    bw.write(0+"\n");
                }else{
                    bw.write(maxHeap.poll()+"\n");
                }
            }else{
                maxHeap.add(inputNum);
            }
        }
        bw.flush();
    }
}