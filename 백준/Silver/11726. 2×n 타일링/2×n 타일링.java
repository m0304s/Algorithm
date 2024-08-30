import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(br.readLine());
        Long [] arr = new Long[n+1];
        arr[0] = 1L;
        arr[1] = 1L;
        for(int i=2;i<=n;i++){
            arr[i] = (arr[i-2] + arr[i-1])%10007; 
        }
        bw.write(arr[n]+"\n");
        bw.flush();
    }
}
