import java.io.*;
import java.util.Arrays;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,Q;
    static int [] numbers;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            Q = Integer.parseInt(tokens[1]);

            numbers = new int[N+1];
            for(int q=1;q<=Q;q++){
                tokens = br.readLine().split(" ");
                int L = Integer.parseInt(tokens[0]);
                int R = Integer.parseInt(tokens[1]);

                Arrays.fill(numbers,L,R+1,q);
            }
            printNumbers(t);
        }
    }
    private static void printNumbers(int q){
        System.out.print("#"+q+" ");
        for(int i=1;i< numbers.length;i++){
            System.out.print(numbers[i]+" ");
        }
        System.out.println();
    }
}
