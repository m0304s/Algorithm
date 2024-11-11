import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [] numbers;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            int testCase = Integer.parseInt(br.readLine());
            numbers = new int[101];
            String [] tokens = br.readLine().split(" ");
            for(int i=0;i<tokens.length;i++){
                numbers[Integer.parseInt(tokens[i])]++;
            }

            int result = findAnswer();
            bw.write("#"+t+" "+result+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static int findAnswer(){
        int maxCount = Integer.MIN_VALUE;
        int index = 0;
        for(int i=0;i<numbers.length;i++){
            if(numbers[i] > maxCount) {
                maxCount = numbers[i];
                index = i;
            }else if(numbers[i] == maxCount){
                index = Math.max(i,index);
            }
        }
        return index;
    }
}
