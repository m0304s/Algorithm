import java.io.*;
import java.util.*;
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        list.add(0);    //크기 N+1

        // N =1 일때 경우의 수 : 3
        list.add(3);
        // N =2 일때 경우의 수 : 7
        list.add(7);
        // N = i+1 일때 -> dp[i] = 2* dp[i-1] + dp[i-2]
        for(int i=3;i<=N;i++){
            list.add((2*list.get(i-1)+list.get(i-2))%9901);
        }
        bw.write(Integer.toString(list.get(N))+"\n");
        bw.flush();
        bw.close();
    }
}
