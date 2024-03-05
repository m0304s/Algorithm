import java.util.*;
import java.io.*;

public class Main{
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static ArrayList<Integer> array = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            array.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(array);
        for(int i=0;i<array.size();i++){
            bw.write(Integer.toString(array.get(i))+"\n");
        }
        bw.flush();
        bw.close();
    }
}