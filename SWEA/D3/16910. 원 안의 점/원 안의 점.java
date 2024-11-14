import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            int count = findCounts();
            bw.write("#"+t+" "+count+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static int findCounts(){
        int count = 0;
        for(int i=-N;i<=N;i++){
            for(int j=-N;j<=N;j++){
                int multipleX = i*i;
                int multipleY = j*j;
                int multipleN = N*N;
                if(multipleX + multipleY <= multipleN) count++;
            }
        }
        return count;
    }
}
