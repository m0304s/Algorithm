import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int H,W,N;
    static char [][] map;
    static String commands;
    static int tankX,tankY;
    static int tankDirection = 0;

    //상 하 좌 우
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] tokens = br.readLine().split(" ");
            H = Integer.parseInt(tokens[0]);
            W = Integer.parseInt(tokens[1]);

            map = new char[H][W];

            //격자판 입력
            for(int i=0;i<H;i++){
                String input = br.readLine();
                for(int j=0;j<W;j++){
                    map[i][j] = input.charAt(j);
                    if(map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>'){
                        tankX = i;
                        tankY = j;
                        if(map[i][j] == '^') tankDirection = 0;
                        if(map[i][j] == 'v') tankDirection = 1;
                        if(map[i][j] == '<') tankDirection = 2;
                        if(map[i][j] == '>') tankDirection = 3;
                    }
                }
            }
            N = Integer.parseInt(br.readLine());
            commands = br.readLine();

            move();
            // 최종 결과 출력
            bw.write("#" + t + " ");
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    bw.write(map[i][j]);
                }
                bw.write("\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void move(){
        /**
         * 문자	의미
         * .	평지(전차가 들어갈 수 있다.)
         * *	벽돌로 만들어진 벽
         * #	강철로 만들어진 벽
         * -	물(전차는 들어갈 수 없다.)
         * ^	위쪽을 바라보는 전차(아래는 평지이다.)
         * v	아래쪽을 바라보는 전차(아래는 평지이다.)
         * <	왼쪽을 바라보는 전차(아래는 평지이다.)
         * >	오른쪽을 바라보는 전차(아래는 평지이다.)
         */

        /**
         *  문자	동작
         U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
         D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
         L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
         R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
         S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
         */
        for (int i=0;i<N;i++){
            char command = commands.charAt(i);
            if(command == 'U'){
                tankDirection = getDirection('^');
                int nx = tankX + dx[tankDirection];
                int ny = tankY + dy[tankDirection];

                if(inRange(nx,ny) && map[nx][ny] == '.'){
                    map[tankX][tankY] = '.';
                    tankX = nx;
                    tankY = ny;
                }
                map[tankX][tankY] = '^';
            }else if(command == 'D'){
                tankDirection = getDirection('v');
                int nx = tankX + dx[tankDirection];
                int ny = tankY + dy[tankDirection];

                if(inRange(nx,ny) && map[nx][ny] == '.'){
                    map[tankX][tankY] = '.';
                    tankX = nx;
                    tankY = ny;
                }
                map[tankX][tankY] = 'v';
            }else if(command == 'L'){
                tankDirection = getDirection('<');
                int nx = tankX + dx[tankDirection];
                int ny = tankY + dy[tankDirection];

                if(inRange(nx,ny) && map[nx][ny] == '.'){
                    map[tankX][tankY] = '.';
                    tankX = nx;
                    tankY = ny;
                }
                map[tankX][tankY] = '<';
            }else if(command == 'R'){
                tankDirection = getDirection('>');
                int nx = tankX + dx[tankDirection];
                int ny = tankY + dy[tankDirection];

                if(inRange(nx,ny) && map[nx][ny] == '.'){
                    map[tankX][tankY] = '.';
                    tankX = nx;
                    tankY = ny;
                }
                map[tankX][tankY] = '>';
            }else if(command == 'S'){
                //포탄 발사
                /**
                 * 전차가 포탄을 발사하면, 포탄은 벽돌로 만들어진 벽 또는 강철로 만들어진 벽에 충돌하거나 게임 맵 밖으로 나갈 때까지 직진한다.
                 * 만약 포탄이 벽에 부딪히면 포탄은 소멸하고, 부딪힌 벽이 벽돌로 만들어진 벽이라면 이 벽은 파괴되어 칸은 평지가 된다.
                 * 강철로 만들어진 벽에 포탄이 부딪히면 아무 일도 일어나지 않는다.
                 * 게임 맵 밖으로 포탄이 나가면 아무런 일도 일어나지 않는다.
                 */
                int missileX = tankX;
                int missileY = tankY;
                while (true){
                    //새로운 포탄의 X,Y좌표 계산
                    missileX += dx[tankDirection];
                    missileY += dy[tankDirection];

                    if(!inRange(missileX,missileY) || isWall(missileX,missileY)){
                        if(inRange(missileX,missileY) && map[missileX][missileY] == '*'){
                            map[missileX][missileY] = '.';
                        }
                        break;
                    }
                }
            }
        }
    }
    static boolean isWall(int x,int y){
        return map[x][y] == '*' || map[x][y] == '#';
    }
    static boolean inRange(int x,int y){
        return x>=0 && x<H && y>=0 && y<W;
    }
    static int getDirection(char direction){
        //상 하 좌 우
        switch (direction){
            case '^' : return 0;
            case 'v' : return 1;
            case '<' : return 2;
            case '>' : return 3;
            default: return -1;
        }
    }
}
