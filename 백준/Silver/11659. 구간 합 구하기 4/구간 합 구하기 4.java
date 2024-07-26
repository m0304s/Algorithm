import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);
        tokens = br.readLine().split(" ");
        ArrayList<Long> sum = new ArrayList<>();
        sum.add(0L);
        for(int i=1;i<=N;i++){
            sum.add(sum.get(i-1)+Long.parseLong(tokens[i-1]));
        }
        for(int i=0;i<M;i++){
            tokens = br.readLine().split(" ");
            int start = Integer.parseInt(tokens[0]);
            int end = Integer.parseInt(tokens[1]);
            Long answer = sum.get(end)-sum.get(start-1);
            bw.write(answer+"\n");
        }bw.flush();
    }
}
