import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] inputString = br.readLine().split(" ");
            int N = Integer.parseInt(inputString[0]);
            int M = Integer.parseInt(inputString[1]);
            int K = Integer.parseInt(inputString[2]);

            int [] peopleVisit = new int[N];
            inputString = br.readLine().split(" ");
            for(int i=0;i<N;i++){
                peopleVisit[i] = Integer.parseInt(inputString[i]);
            }
            boolean result = canAllPeopleEat(N,M,K,peopleVisit);
            if(result) bw.write("#"+t+" "+"Possible\n");
            else bw.write("#"+t+" "+"Impossible\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
    static boolean canAllPeopleEat(int N,int M,int K, int [] peopleVisit){
        Arrays.sort(peopleVisit);
        int lastPersonVisitSecond = peopleVisit[peopleVisit.length-1]+1;

        // 시간에 따라 생산 가능한 붕어빵 양
        int [] amountLeft = new int[lastPersonVisitSecond];

        for(int i=0;i<amountLeft.length;i++){
            amountLeft[i] = (i/M)*K;
        }

        //손님이 방문한 시간 이후의 붕어빵 개수 -1 처리
        for(int i=0;i<peopleVisit.length;i++){
            for(int j=peopleVisit[i];j<amountLeft.length;j++){
                amountLeft[j]--;
            }
        }

        for (int i : amountLeft) {
            if(i<0) return false;
        }
        return true;
    }
}
