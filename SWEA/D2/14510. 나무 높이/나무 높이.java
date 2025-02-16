import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            int answer = solution();
            bw.write("#"+t+" "+answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution() throws IOException{
        int size = Integer.parseInt(br.readLine());
        int [] treeHeight = new int[size];

        String [] tokens = br.readLine().split(" ");

        int maxHeight = Integer.MIN_VALUE;
        for(int i=0;i<tokens.length;i++){
            treeHeight[i] = Integer.parseInt(tokens[i]);
            maxHeight = Math.max(maxHeight,treeHeight[i]);
        }

        int odd = 0;
        int even = 0;

        for(int i=0;i<tokens.length;i++){
            int diff = maxHeight - treeHeight[i];

            if(diff == 0) continue;

            even += diff / 2;
            odd += diff % 2;
        }

        if(even > odd){
            while (Math.abs(even - odd) > 1){
                even--;
                odd+=2;
            }
        }

        int result = 0;
        if(odd > even) result = odd * 2 -1;
        else if(even > odd) result = even * 2;
        else result = odd + even;

        return result;
    }
}