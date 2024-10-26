import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Solution {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int [] cnt;
    static int N;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            cnt = new int[200];
            for(int i=0;i<N;i++){
                String [] tokens = br.readLine().split(" ");
                int n1 = Integer.parseInt(tokens[0]);
                int n2 = Integer.parseInt(tokens[1]);

                int start = Math.min(n1,n2);
                int end = Math.max(n1,n2);

                start = (start % 2 == 0) ? start/2-1 : start/2;
                end = (end % 2 == 0) ? end/2-1 : end/2;

                for(int j=start;j<=end;j++){
                    cnt[j]++;
                }
            }
            bw.write("#"+t+" "+Arrays.stream(cnt).max().getAsInt()+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
