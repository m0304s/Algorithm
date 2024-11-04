import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static final int NEEDTOCLEAN = 0;
    static final int WALL = 1;
    static final int CLEAN = 2;

    static int[][] map;

    // 북, 동, 남, 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);

        map = new int[N][M];

        tokens = br.readLine().split(" ");
        int startX = Integer.parseInt(tokens[0]);
        int startY = Integer.parseInt(tokens[1]);
        int startDirection = Integer.parseInt(tokens[2]);

        // 현재 방 상태 입력
        for (int i = 0; i < N; i++) {
            tokens = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        int result = simulate(startX, startY, startDirection);
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static int simulate(int x, int y, int dir) {
        int count = 0;

        while (true) {
            // 1. 현재 칸을 청소한다.
            if (map[x][y] == NEEDTOCLEAN) {
                count++;
                map[x][y] = CLEAN;
            }

            boolean found = false;

            // 2. 반시계 방향으로 90도 회전하며 청소할 수 있는 칸을 찾는다.
            for (int i = 0; i < 4; i++) {
                dir = changeDirection(dir); // 방향 변경
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (inRange(nx, ny) && map[nx][ny] == NEEDTOCLEAN) {
                    // 청소할 수 있는 칸이 있다면 이동
                    x = nx;
                    y = ny;
                    found = true;
                    break; // 다음 루프에서 현재 위치로 돌아감
                }
            }

            if (!found) {
                // 3. 후진할 수 있는지 체크
                int backDir = (dir + 2) % 4; // 후진 방향
                int bx = x + dx[backDir];
                int by = y + dy[backDir];

                if (inRange(bx, by) && map[bx][by] != WALL) {
                    x = bx;
                    y = by;
                } else {
                    break;
                }
            }
        }
        return count;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static int changeDirection(int dir) {
        return (dir + 3) % 4; // 반시계 방향으로 90도 회전
    }
}
