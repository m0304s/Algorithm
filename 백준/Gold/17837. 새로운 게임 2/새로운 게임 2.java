import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static class Chess {
        int x, y, direction;

        Chess(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    private static class MapCell {
        int color;
        List<Integer> chessStatus;

        MapCell(int color) {
            this.color = color;
            this.chessStatus = new ArrayList<>();
        }
    }

    private static MapCell[][] map;
    private static List<Chess> chessList;
    private static int N, K;

    private static final int WHITE = 0, RED = 1, BLUE = 2;
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        K = Integer.parseInt(tokens[1]);

        createMap();
        createChess();
        move();
    }

    private static void move() {
        int turn = 0;

        while (++turn <= 1000) {
            for (int i = 1; i <= K; i++) {
                Chess currentChess = chessList.get(i);
                int x = currentChess.x;
                int y = currentChess.y;
                int direction = currentChess.direction;

                List<Integer> currentStack = map[x][y].chessStatus;
                int startIndex = currentStack.indexOf(i);
                List<Integer> movingChess = new ArrayList<>(currentStack.subList(startIndex, currentStack.size()));

                // 이동할 위치 계산
                int nx = x + dx[direction];
                int ny = y + dy[direction];

                // 파란색 또는 범위 밖이면 방향 반전
                if (!inRange(nx, ny) || map[nx][ny].color == BLUE) {
                    currentChess.direction = reverseDirection(direction);
                    nx = x + dx[currentChess.direction];
                    ny = y + dy[currentChess.direction];

                    if (!inRange(nx, ny) || map[nx][ny].color == BLUE) continue;
                }

                // 이동 처리
                moveChess(x, y, nx, ny, movingChess, map[nx][ny].color);

                // 종료 조건 확인
                if (map[nx][ny].chessStatus.size() >= 4) {
                    System.out.println(turn);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    private static void moveChess(int x, int y, int nx, int ny, List<Integer> movingChess, int targetColor) {
        // 기존 위치에서 이동하는 체스 제거
        map[x][y].chessStatus.removeAll(movingChess);

        // 빨간색이면 순서 반전
        if (targetColor == RED) Collections.reverse(movingChess);

        // 새로운 위치에 추가
        map[nx][ny].chessStatus.addAll(movingChess);

        // 이동한 말들의 좌표 업데이트
        for (int chessId : movingChess) {
            Chess chess = chessList.get(chessId);
            chess.x = nx;
            chess.y = ny;
        }
    }

    private static int reverseDirection(int direction) {
        return direction % 2 == 0 ? direction + 1 : direction - 1;
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static void createMap() throws IOException {
        map = new MapCell[N][N];
        for (int i = 0; i < N; i++) {
            String[] tokens = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = new MapCell(Integer.parseInt(tokens[j]));
            }
        }
    }

    private static void createChess() throws IOException {
        chessList = new ArrayList<>();
        chessList.add(null); // 1-based indexing
        for (int i = 1; i <= K; i++) {
            String[] tokens = br.readLine().split(" ");
            int x = Integer.parseInt(tokens[0]) - 1;
            int y = Integer.parseInt(tokens[1]) - 1;
            int direction = Integer.parseInt(tokens[2]) - 1;
            chessList.add(new Chess(x, y, direction));
            map[x][y].chessStatus.add(i);
        }
    }
}