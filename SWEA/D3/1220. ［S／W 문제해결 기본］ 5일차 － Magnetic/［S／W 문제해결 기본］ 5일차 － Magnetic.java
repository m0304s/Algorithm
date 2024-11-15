import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[][] map;
    static final int N = 1;
    static final int S = 2;

    public static void main(String[] args) throws IOException{
        for(int t=1;t<=10;t++){
            int mapLength = Integer.parseInt(br.readLine());
            map = new int[mapLength][mapLength];

            for(int i=0;i<mapLength;i++){
                String [] tokens = br.readLine().split(" ");
                for(int j=0;j<mapLength;j++){
                    map[i][j] = Integer.parseInt(tokens[j]);
                }
            }

            int deadLockCnt = findDeadLock();

            bw.write("#"+t+" "+deadLockCnt+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int findDeadLock(){
        int count = 0;

        for(int j=0;j<map.length;j++){
            boolean state = false;
            for(int i=0;i<map.length;i++){
                if(map[i][j] == N){
                    state = true;
                }

                if(state && map[i][j] == S){
                    count++;
                    state = false;
                }
            }
        }
        return count;
    }
}
