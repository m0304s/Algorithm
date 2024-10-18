import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int [] arrayT;
    static int [] arrayP;
    static boolean [] possibleDay;
    static int maxProfit = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arrayT = new int[N+1];
        arrayP = new int[N+1];
        possibleDay = new boolean[N+1];

        Arrays.fill(possibleDay,true);

        for(int i=1;i<=N;i++){
            String [] tokens = br.readLine().split(" ");
            int t = Integer.parseInt(tokens[0]);
            int p = Integer.parseInt(tokens[1]);
            arrayT[i] = t;
            arrayP[i] = p;
        }
        backTracking(1,0);
        bw.write(maxProfit+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void backTracking(int day,int sum){
        //퇴사일을 넘어갈 경우 종료
        if(day == N+1){
            maxProfit = Math.max(sum,maxProfit);
            return;
        }

        //그날 상담을 진행하는 경우(퇴사일이 상담하는 날을 지나지 않는 경우)
        if(day+arrayT[day]-1 <=N)
            backTracking(day+arrayT[day],sum+arrayP[day]);
        //그날 상담을 하지 않는 경우
        backTracking(day+1,sum);
    }
}
