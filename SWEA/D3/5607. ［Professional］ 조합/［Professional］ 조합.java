import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final long MOD = 1234567891L;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] tokens = br.readLine().split(" ");
            int N = Integer.parseInt(tokens[0]);
            int R = Integer.parseInt(tokens[1]);

            //페르마의 소정리 이용
            long number = factorial(N);
            long denom = factorial(R) * factorial(N-R) % MOD;
            long answer = number * pow(denom,MOD-2) % MOD;
            bw.write("#"+t+" "+answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static long factorial(long N){
        long fac = 1L;
        while(N>1){
            fac = (fac * N) % MOD;
            N--;
        }
        return fac;
    }
    static long pow(long base, long expo) {
        // 지수가 1일 경우 base^1 이므로 base % P를 리턴
        if(expo == 1) {
            return base % MOD;
        }

        // 지수의 절반에 해당하는 base^(expo / 2) 을 구한다.
        long temp = pow(base, expo / 2);

        /*
         * 현재 지수가 홀 수 였다면
         * base^(expo / 2) * base^(expo / 2) * base 이므로
         * base를 한 번 더 곱해주어야 한다.
         *
         * ex) A^9 = A^4 * A^4 * A
         */
        if(expo % 2 == 1) {
            return (temp * temp % MOD) * base % MOD;
        }
        return temp * temp % MOD;

    }
}
