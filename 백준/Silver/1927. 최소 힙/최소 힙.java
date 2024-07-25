import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i=0;i<n;i++){
            int inputNum = Integer.parseInt(br.readLine());
            if(inputNum == 0){
                if(priorityQueue.isEmpty()){
                    bw.write(0+"\n");
                }else{
                    bw.write(priorityQueue.poll()+"\n");
                }
            }else{
                priorityQueue.add(inputNum);
            }
        }
        bw.flush();
    }
}
