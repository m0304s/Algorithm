import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int WALL = 0;
    static final int BLANK = 1;
    static final int END = 2;
    static final int SIZE = 100;

    static int [][] map;

    //좌,우, 상
    static int [] dx = {0,0,-1};
    static int [] dy = {-1,1,0};

    public static void main(String[] args) throws IOException{
        for(int t=1;t<=10;t++){
            int testCase = Integer.parseInt(br.readLine());
            map = new int[SIZE][SIZE];

            int startY = 0;
            for(int i=0;i<SIZE;i++){
                String[] input = br.readLine().split(" ");
                for(int j=0;j<SIZE;j++){
                    map[i][j] = Integer.parseInt(input[j]);
                    if(i == SIZE-1 && map[i][j] == END){
                        startY = j;
                    }
                }
            }

            int answer = findStartPoint(SIZE-1, startY);
            bw.write("#"+t+" "+answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static int findStartPoint(int x,int y){
        int answer = 0;

        while(true){
            if(x==0){
                answer = y;
                break;
            }
            for(int i=0;i<2;i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!inRange(nx,ny)) continue;
                if(map[nx][ny] != BLANK) continue;

                while(inRange(nx,ny) && map[nx][ny] == BLANK){
                    y = ny;
                    ny+=dy[i];
                }
                break;
            }
            x+=dx[2];   //좌우 먼저 탐색 후 위칸으로 이동
        }
        return answer;
    }
    static boolean inRange(int x,int y){
        return x>=0 && x<SIZE && y>=0 && y<SIZE;
    }
}
