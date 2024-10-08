import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [] map;
    public static void main(String[] args) throws IOException{
        for(int t=1;t<=10;t++){
            int N = Integer.parseInt(br.readLine());
            String [] tokens = br.readLine().split(" ");
            map = new int[N];
            for(int i=0;i<N;i++){
                map[i] = Integer.parseInt(tokens[i]);
            }

            Long count = 0L;
            for(int i=2;i<map.length-2;i++){
                //조망권 체크
                int firstLeft = map[i-2];   //좌측 기준 2칸 떨어진 건물 높이
                int secondLeft = map[i-1];  //좌측 기준 1칸 떨어진 건물 높이
                int height = map[i];    //측정 건물 높이
                int secondRight = map[i+1]; //우측 기준 1칸 떨어진 건물 높이
                int firstRight = map[i+2];  //우측 기준 2칸 떨어진 건물 높이

                int leftMinHeight = Math.max(firstLeft, secondLeft);
                int rightMinHeight = Math.max(firstRight, secondRight);

                int minHeight = Math.max(leftMinHeight, rightMinHeight);
                int answer = height - minHeight;
                
                if(answer<0) answer=0;
                count+=answer;
            }
            bw.write("#"+t+" ");
            bw.write(count+"\n");
        }
        bw.flush();
    }
}
