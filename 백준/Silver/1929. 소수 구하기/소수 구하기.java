import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        String [] token = br.readLine().split(" ");
        int M = Integer.parseInt(token[0]);
        int N = Integer.parseInt(token[1]);

        Boolean[] isPrime = new Boolean[N+1];
        Arrays.fill(isPrime,true);
        isPrime[0]=false;
        isPrime[1]=false;   //0과 1은 소수가 아님

        for(int i=2;i*i<=N;i++){
            if(isPrime[i]){
                for(int j=i*i;j<=N;j+=i){
                    isPrime[j]=false;
                }
            }
        }
        for(int i=M;i<=N;i++){
            if(isPrime[i]){
                bw.write(i+"\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
