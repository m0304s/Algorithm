import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        long [] array = new long[N+1];
        for(int i=0;i<=N;i++){
            if(i == 0){
                array[i]=0;
            }else if(i==1){
                array[i] = 1;
            }else{
                array[i]=array[i-1]+array[i-2];
            }
        }
        bw.write(Long.toString(array[N])+"\n");
        bw.flush();
        bw.close();
    }
}
