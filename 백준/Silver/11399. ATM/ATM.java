import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        String [] token = br.readLine().split(" ");
        for(int i=0;i<N;i++){
            list.add(Integer.parseInt(token[i]));
        }
        Collections.sort(list);
        int sum=0;
        int index=0;
        for(int i=0;i<list.size();i++){
            for(int j=0;j<=index;j++){
                sum+=list.get(j);
            }index++;
        }
        bw.write(Integer.toString(sum)+"\n");
        bw.flush();
        bw.close();
    }
}
/**
 * 3 1 4 3 2
 * 
 * 2 5 1 4 3
 */
