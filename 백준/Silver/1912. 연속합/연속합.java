import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        String [] token = br.readLine().split(" ");
        int [] list = new int[N];
        int [] d = new int [N];
        for(int i=0;i<N;i++){
            list[i]=Integer.parseInt(token[i]);
        }
        int max = 0;
        d[0] = max = list[0];

        for(int i=1;i<N;i++){
            d[i] = Math.max(d[i-1]+list[i],list[i]);
        }
        Arrays.sort(d);
        bw.write(Integer.toString(d[d.length-1])+"\n");
        bw.flush();
        bw.close();
    }
}
