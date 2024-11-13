import java.io.*;
import java.util.Arrays;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int [] studyDays;
    static int minDays;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            studyDays = new int[7];
            minDays = Integer.MAX_VALUE;
            String [] tokens = br.readLine().split(" ");
            for(int i=0;i<7;i++){
                studyDays[i] = Integer.parseInt(tokens[i]);
            }

            calMinDays();
            bw.write("#"+t+" "+minDays+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void calMinDays(){
        for(int i=0;i<7;i++){
            int day = findDays(i);
            minDays = Math.min(day,minDays);
        }
    }

    private static int findDays(int startDay){
        int day = 0;
        int classes = 0;
        int i = startDay;

        while(classes < N){
            if(studyDays[i%7] == 1) classes++;
            day++;
            i++;
        }
        return day;
    }
}
