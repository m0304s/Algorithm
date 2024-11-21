import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int R, C, T;
    static int[][] dustMap;

    static class Aircleaner {
        int x1, y1, x2, y2;

        public Aircleaner(int x, int y) {
            this.x1 = x;
            this.y1 = y;
            this.x2 = x1 + 1;
            this.y2 = y;
        }
    }

    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    static Aircleaner aircleaner;

    public static void main(String[] args) throws IOException {
        // 입력 처리
        String[] tokens = br.readLine().split(" ");
        R = Integer.parseInt(tokens[0]);
        C = Integer.parseInt(tokens[1]);
        T = Integer.parseInt(tokens[2]);
        dustMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            tokens = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                dustMap[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        findAircleaner();

        // T초 동안 확산과 공기청정기 작동 반복
        for (int t = 0; t < T; t++) {
            diffusion();
            cleanAir();
        }

        // 남은 미세먼지 총합 계산 및 출력
        int answer = getDustTotal();
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getDustTotal() {
        int total = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (dustMap[i][j] > 0) { // 공기청정기(-1) 제외
                    total += dustMap[i][j];
                }
            }
        }
        return total;
    }

    private static void cleanAir() {
        cleanUpperPart(aircleaner.x1);
        cleanLowerPart(aircleaner.x2);
    }

    private static void cleanUpperPart(int top) {
        // 위쪽 순환 (반시계 방향)
        for (int i = top - 1; i > 0; i--) dustMap[i][0] = dustMap[i - 1][0];
        for (int i = 0; i < C - 1; i++) dustMap[0][i] = dustMap[0][i + 1];
        for (int i = 0; i < top; i++) dustMap[i][C - 1] = dustMap[i + 1][C - 1];
        for (int i = C - 1; i > 1; i--) dustMap[top][i] = dustMap[top][i - 1];
        dustMap[top][1] = 0; // 공기청정기 바로 옆은 0
    }

    private static void cleanLowerPart(int bottom) {
        // 아래쪽 순환 (시계 방향)
        for (int i = bottom + 1; i < R - 1; i++) dustMap[i][0] = dustMap[i + 1][0];
        for (int i = 0; i < C - 1; i++) dustMap[R - 1][i] = dustMap[R - 1][i + 1];
        for (int i = R - 1; i > bottom; i--) dustMap[i][C - 1] = dustMap[i - 1][C - 1];
        for (int i = C - 1; i > 1; i--) dustMap[bottom][i] = dustMap[bottom][i - 1];
        dustMap[bottom][1] = 0; // 공기청정기 바로 옆은 0
    }

    private static void diffusion() {
        int[][] tempMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (dustMap[i][j] > 0) { // 미세먼지가 있는 경우 확산
                    int diffusionAmount = dustMap[i][j] / 5;
                    int diffusionCount = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (!inRange(nx, ny) || dustMap[nx][ny] == -1) continue; // 범위 밖 또는 공기청정기 제외

                        diffusionCount++;
                        tempMap[nx][ny] += diffusionAmount;
                    }
                    // 남은 먼지 계산
                    tempMap[i][j] += dustMap[i][j] - (diffusionCount * diffusionAmount);
                }
            }
        }

        // 확산 결과를 dustMap에 반영
        for (int i = 0; i < R; i++) {
            dustMap[i] = tempMap[i].clone();
        }
        dustMap[aircleaner.x1][aircleaner.y1] = -1;
        dustMap[aircleaner.x2][aircleaner.y2] = -1;
    }

    private static void findAircleaner() {
        for (int i = 0; i < R; i++) {
            if (dustMap[i][0] == -1) {
                aircleaner = new Aircleaner(i, 0);
                return;
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}