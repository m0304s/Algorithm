import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int H, W, N;
    static char[][] map;
    static String commands;
    static int tankX, tankY;
    static int tankDirection = 0;

    // 상 하 좌 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            String[] tokens = br.readLine().split(" ");
            H = Integer.parseInt(tokens[0]);
            W = Integer.parseInt(tokens[1]);

            map = new char[H][W];

            for (int i = 0; i < H; i++) {
                String input = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = input.charAt(j);
                    if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
                        tankX = i;
                        tankY = j;
                        tankDirection = getDirection(map[i][j]);
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

    static void move() {
        for (int i = 0; i < N; i++) {
            char command = commands.charAt(i);
            if (command == 'U' || command == 'D' || command == 'L' || command == 'R') {
                changeDirectionAndMove(command);
            } else if (command == 'S') {
                shoot();
            }
        }
    }

    static void changeDirectionAndMove(char command) {
        tankDirection = getDirection(command == 'U' ? '^' : command == 'D' ? 'v' : command == 'L' ? '<' : '>');
        int nx = tankX + dx[tankDirection];
        int ny = tankY + dy[tankDirection];

        if (inRange(nx, ny) && map[nx][ny] == '.') {
            map[tankX][tankY] = '.';
            tankX = nx;
            tankY = ny;
        }
        map[tankX][tankY] = command == 'U' ? '^' : command == 'D' ? 'v' : command == 'L' ? '<' : '>';
    }

    static void shoot() {
        int missileX = tankX;
        int missileY = tankY;
        while (true) {
            missileX += dx[tankDirection];
            missileY += dy[tankDirection];
            if (!inRange(missileX, missileY) || isWall(missileX, missileY)) {
                if (inRange(missileX, missileY) && map[missileX][missileY] == '*') {
                    map[missileX][missileY] = '.';
                }
                break;
            }
        }
    }

    static boolean isWall(int x, int y) {
        return map[x][y] == '*' || map[x][y] == '#';
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < H && y >= 0 && y < W;
    }

    static int getDirection(char direction) {
        switch (direction) {
            case '^':
                return 0;
            case 'v':
                return 1;
            case '<':
                return 2;
            case '>':
                return 3;
            default:
                return -1;
        }
    }
}
