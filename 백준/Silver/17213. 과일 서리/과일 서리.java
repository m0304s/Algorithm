import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int result = combination(M-1,N-1);
        System.out.println(result);
    }
    private static int combination(int n,int r){
        if(n == r || r == 0){
            return 1;
        }
        return combination(n-1,r-1) + combination(n-1,r);
    }
}
