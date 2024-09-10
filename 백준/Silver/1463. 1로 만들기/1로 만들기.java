import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int X = Integer.parseInt(br.readLine());
        int [] arr = new int[X+1];

        arr[0] = arr[1] = 0;
        for(int i=2;i<=X;i++){
            arr[i] = arr[i-1] + 1;
            if( i%2 == 0 ){
                arr[i] = Math.min(arr[i], arr[i/2] + 1);
            }
            if( i%3 == 0 ){
                arr[i] = Math.min(arr[i], arr[i/3] + 1);
            }
        }

        bw.write(arr[X] + "\n");
        bw.flush();
    }
}
