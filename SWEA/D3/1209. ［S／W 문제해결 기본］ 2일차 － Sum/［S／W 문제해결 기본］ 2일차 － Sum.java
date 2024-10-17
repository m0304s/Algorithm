import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [][] map = new int[100][100];

    //행의 합, 열의 합, 각 대각선의 합
    static int [] dx = {0,1,1,1};
    static int [] dy = {1,0,1,-1};

    public static void main(String[] args) throws IOException {
        for(int t=1;t<=10;t++){
            int T = Integer.parseInt(br.readLine());
            for(int i=0;i<100;i++){
                String [] nums = br.readLine().split(" ");
                for(int j=0;j<100;j++){
                    map[i][j] = Integer.parseInt(nums[j]);
                }
            }
            int result = findMaxSum();
            bw.write("#"+t+" "+result+"\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
    static int findMaxSum(){
        int result = Integer.MIN_VALUE;

        //각 행, 열의 누적합 계산
        for(int i=0;i<map.length;i++){
            result = Math.max(result,getSum(i,0,0,0));
            result = Math.max(result,getSum(0,i,1,0));
        }
        // 각 대각선의 누적합 계산
        result = Math.max(result,getSum(0,0,2,0));
        result = Math.max(result,getSum(map.length-1,map.length-1,3,0));
        return result;
    }

    //각 방향에 맞는 누적합 계산하는 함수
    static int getSum(int x,int y,int direction, int sum){
        if(!inRange(x,y)) return sum;

        sum+=map[x][y];
        int nx = x + dx[direction];
        int ny = y + dy[direction];

        return getSum(nx,ny,direction,sum);
    }
    
    static boolean inRange(int x,int y){
        return x >= 0 && x < map.length && y >= 0 && y < map[0].length;
    }
}
