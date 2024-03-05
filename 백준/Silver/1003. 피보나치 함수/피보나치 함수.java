import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static HashMap<Integer, long[]> map = new HashMap<>();
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            long [] array = fibonacci(Integer.parseInt(br.readLine()));
            bw.write(Long.toString(array[0])+" "+Long.toString(array[1])+"\n");
        }
        bw.flush();
        bw.close();
    }
    public static long[] fibonacci(int N){
        if(N==0){
            long [] input = new long[2];
            input[0]=1;
            input[1]=0;
            return input;
        }else if(N==1){
            long [] input = new long[2];
            input[0]=0;
            input[1]=1;
            return input;
        }else{
            if(map.containsKey(N)){
                return map.get(N);
            }else{
                long [] input = new long[2];
                long [] a = fibonacci(N-1);
                long [] b = fibonacci(N-2);
                
                input[0] = a[0]+b[0];
                input[1]= a[1]+b[1];

                map.put(N,input);

                return input;
            }
        }
    }
}
