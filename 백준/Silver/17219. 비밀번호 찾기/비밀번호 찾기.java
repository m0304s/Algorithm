import java.io.*;
import java.util.HashMap;

public class Main{
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);

        HashMap<String, String> pwMap = new HashMap<>();
        for(int i=0;i<n;i++){
            tokens = br.readLine().split(" ");
            pwMap.put(tokens[0], tokens[1]);
        }

        for(int i=0;i<m;i++){
            bw.write(pwMap.get(br.readLine())+"\n");
        }bw.flush();
    }
}