import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2){
                int abs1 = Math.abs(o1);
                int abs2 = Math.abs(o2);

                if(abs1 == abs2){
                    return o1 - o2;
                }else{
                    return abs1 - abs2;
                }
            }
        });

        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            int inputNum = Integer.parseInt(br.readLine()); //입력받은 수
            if(inputNum != 0){
                pq.add(inputNum);
            }else{
                if(pq.isEmpty()){
                    bw.write(0+"\n");
                }else{
                    bw.write(pq.remove()+"\n");
                }
            }
        }
        bw.flush();
    }
}
