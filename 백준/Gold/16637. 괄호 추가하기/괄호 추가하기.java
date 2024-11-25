import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static char[] input;
    static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        input = br.readLine().toCharArray();

        solve(2,input[0]-'0');
        bw.write(maxValue+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
    private static void solve(int i,int sum){
        if(i >= N){
            maxValue = Math.max(maxValue,sum);
            return;
        }

        solve(i+2,calc(sum,input[i]-'0',input[i-1]));
        if(i+2<N){
            int right = calc(input[i]-'0',input[i+2]-'0',input[i+1]);
            int left = calc(sum, right,input[i-1]);
            solve(i+4,left);
        }
    }

    private static int calc(int i,int j,char op){
        switch (op){
            case '+' : return i + j;
            case '-' : return i - j;
            case '*' : return i * j;
            default: throw new IllegalArgumentException();
        }
    }
}
