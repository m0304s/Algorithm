import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,B;
    static int [] height;
    static int minDifference;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            B = Integer.parseInt(tokens[1]);
            height = new int[N];
            minDifference = Integer.MAX_VALUE;
            tokens = br.readLine().split(" ");
            for(int i=0;i<N;i++){
                height[i] = Integer.parseInt(tokens[i]);
            }
            backtracking(0,0,0);
            bw.write("#"+t+" "+minDifference+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void backtracking(int index, int depth, int sum){
        if(depth == N){
            //높이가 B 이상인 탑 중에서 높이가 가장 낮은 탑
            if(sum>=B){
                minDifference = Math.min(minDifference,Math.abs(sum-B));
            }
            return;
        }

        //직원이 탑에 올라갈 경우
        backtracking(index+1,depth+1,sum+height[index]);
        //직원이 탑에 올라가지 않을 경우
        backtracking(index+1,depth+1,sum);
    }
}
