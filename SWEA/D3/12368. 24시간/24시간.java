import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] tokens = br.readLine().split(" ");
            int s = Integer.parseInt(tokens[0]);
            int e = Integer.parseInt(tokens[1]);

            int answer = (s+e) % 24;
            bw.write("#"+t+" "+answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
