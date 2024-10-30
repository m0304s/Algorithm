import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static final int ROAD = 1;
    static final int MAP_SIZE = 100;
    static final int TEST_CASE = 10;

    //좌 우 하
    static int [] dx = {0,0,1};
    static int [] dy = {-1,1,0};

    static int [][] map;

    public static void main(String[] args) throws IOException {
        for(int t=1;t<=TEST_CASE;t++){
            map = new int[MAP_SIZE][MAP_SIZE];
            int testCase = Integer.parseInt(br.readLine());
            for(int i=0;i<MAP_SIZE;i++){
                String [] tokens = br.readLine().split(" ");
                for(int j=0;j<MAP_SIZE;j++){
                    map[i][j] = Integer.parseInt(tokens[j]);
                }
            }

            int minLength = Integer.MAX_VALUE;
            int resultX = 0;
            for(int j=0;j<MAP_SIZE;j++){
                if(map[0][j] == ROAD){
                    int length = move(0,j);
                    if(minLength > length){
                        resultX = j;
                        minLength = length;
                    }
                }
            }
            bw.write("#"+t+" "+resultX+"\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    static int move(int x,int y){
        int length = 0;

        int nx = x;
        int ny = y;

        while (true){
            if(inRange(nx,ny) && map[nx][ny] == 1 && nx == MAP_SIZE-1){
                break;
            }
            for(int i=0;i<2;i++){
                if(inRange(nx+dx[i],ny+dy[i]) && map[nx+dx[i]][ny+dy[i]] == 1){
                    //벽에 도달할때까지 그 방향으로 이동
                    while(inRange(nx,ny) && map[nx][ny] == 1){
                        length++;
                        nx += dx[i];
                        ny += dy[i];
                    }
                    nx-=dx[i];
                    ny-=dy[i];
                    break;
                }
            }
            //아래 방향으로 이동
            nx+=dx[2];
            ny+=dy[2];
            length++;
        }
        return length;
    }

    static boolean inRange(int x,int y){
        return x >= 0 && x < MAP_SIZE && y >=0 && y < MAP_SIZE;
    }
}