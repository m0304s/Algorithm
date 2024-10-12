import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] firstLine = br.readLine().split(" ");
            String [] secondLine = br.readLine().split(" ");

            int N = Integer.parseInt(firstLine[0]);
            int K = Integer.parseInt(firstLine[1]);

            List<Integer> candyList = new ArrayList<>();
            for(int i=0;i<N;i++){
                candyList.add(Integer.parseInt(secondLine[i]));
            }

            Collections.sort(candyList);
            int minDiff = Integer.MAX_VALUE;
            for(int i=0;i<N-K+1;i++){
                int diff = candyList.get(i+K-1) - candyList.get(i);
                minDiff = Math.min(diff,minDiff);
            }
            bw.write("#"+t+" "+(minDiff)+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
