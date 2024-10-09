import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            int answer = 0;

            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            int totalSum = 0;
            for(int i=0;i<N;i++){
                String input = br.readLine();
                for(int j=0;j<N;j++){
                    map[i][j] = input.charAt(j) - '0';
                    totalSum+=map[i][j];
                }
            }
            if(N == 1){
                answer = map[0][0];
            }else{
                int length = map.length/2;
                for(int i=0;i<N/2;i++){
                    //좌측 빼기
                    for(int j=0;j<length;j++){
                        int minusNum = map[i][j];
                        totalSum-=minusNum;
                    }
                    //우측 빼기
                    for(int j=N-1;j>=N-length;j--){
                        int minusNum = map[i][j];
                        totalSum-=minusNum;
                    }
                    length--;
                }
                length++;
                for(int i=N/2+1;i<N;i++){
                    //좌측 빼기
                    for(int j=0;j<length;j++){
                        int minusNum = map[i][j];
                        totalSum-=minusNum;
                    }
                    //우측 빼기
                    for(int j=N-1;j>=N-length;j--){
                        int minusNum = map[i][j];
                        totalSum-=minusNum;
                    }
                    length++;
                }
            }
            bw.write("#"+t+" "+totalSum+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
