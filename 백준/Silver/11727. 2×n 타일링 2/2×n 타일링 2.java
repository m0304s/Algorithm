import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        if (N <= 1) {
            bw.write("1\n");
            bw.flush();
            bw.close();
            br.close();
            return;
        }
        int [] answer = new int[N+1];
        answer[0] = 1;
        answer[1] = 1;
        for(int i=2;i<=N;i++){
            answer[i] = (answer[i-1] + 2 * answer[i-2])%10007;
        }

        bw.write(answer[N]+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
