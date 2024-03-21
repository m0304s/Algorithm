import java.io.*;
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        long A = Long.parseLong(tokens[0]);
        long B = Long.parseLong(tokens[1]);
        long C = Long.parseLong(tokens[2]);
        
        long result = multiple(A%C, B, C);
        bw.write(Long.toString(result)+"\n");
        bw.flush();
        bw.close();
    }
    public static long multiple(long a, long b, long c){
        if(b == 0){
            return 1%c;
        }else if(b%2 == 0){
            long temp = multiple(a, b/2, c);
            return (temp*temp)%c;
        }else{
            long temp = multiple(a, b-1, c);
            return (a*temp)%c;
        }
    }
}
