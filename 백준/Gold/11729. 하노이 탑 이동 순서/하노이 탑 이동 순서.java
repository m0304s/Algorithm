import java.io.*;
import java.util.*;

public class Main{
    public static int count=0;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        ArrayList<int[]> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        br.close();
        count=(int) (Math.pow(2, N)-1);
        bw.write(String.valueOf(count));
        bw.newLine();
        hanoi(N,1,3,2,list);
        bw.flush();
        bw.close();
    }
    public static void hanoi(int N, int start, int end, int via,ArrayList<int[]> answer) throws IOException{
        if(N==1){
            // System.out.println(start+" "+end);
            bw.write(String.valueOf(start));
            bw.write(" ");
            bw.write(String.valueOf(end));
            bw.newLine();
        }else{
            hanoi(N-1,start,via,end,answer);
            bw.write(String.valueOf(start));
            bw.write(" ");
            bw.write(String.valueOf(end));
            bw.newLine();
            hanoi(N-1,via,end,start,answer);
        }
    }
}