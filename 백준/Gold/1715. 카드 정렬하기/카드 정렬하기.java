import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            pq.add(Integer.parseInt(br.readLine()));
        }

        int result = 0;

        while (pq.size() != 1){
            int x = pq.poll();
            int y = pq.poll();

            int sum = x + y;
            pq.add(sum);
            result+=sum;
        }
        bw.write(result+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
