import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int N = 1;
    static final int S = 2;
    static final int BLANK = 0;

    static int [][] map = new int[100][100];


    public static void main(String[] args) throws IOException {
        for(int t=1;t<=10;t++){
            int N = Integer.parseInt(br.readLine());    //정사각형의 한변의 길이
            for(int i=0;i<100;i++){
                String [] nums = br.readLine().split(" ");
                for(int j=0;j<100;j++){
                    map[i][j] = Integer.parseInt(nums[j]);
                }
            }
            int count = findDeadLock();
            bw.write("#"+t+" "+count+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static int findDeadLock(){
        int count = 0;

        // 세로부터 시작
        for(int i=0;i<map[0].length;i++){
            boolean state = false;
            for(int j=0;j<map.length;j++){
                if(map[j][i] == N) state = true;

                //만약 이전에 N극이 있던 상태에서, S극을 만나면 count +1 후 state 초기화
                if(state && map[j][i] == S){
                    count++;
                    state = false;
                }
            }
        }
        return count;
    }
}
