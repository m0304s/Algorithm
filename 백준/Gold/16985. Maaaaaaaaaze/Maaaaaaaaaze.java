import java.io.*;
import java.util.*;

public class Main {
    private static final int SIZE = 5;
    private static int[][][] maze = new int[SIZE][SIZE][SIZE];
    private static int[][][][] rotatedMazes = new int[SIZE][4][SIZE][SIZE];  // 회전 저장
    private static boolean[] used = new boolean[SIZE];
    private static int[] order = new int[SIZE];
    private static int[] rotations = new int[SIZE];

    private static final int[] dx = {0, 0, 0, 0, -1, 1};
    private static final int[] dy = {0, 0, -1, 1, 0, 0};
    private static final int[] dz = {-1, 1, 0, 0, 0, 0};

    private static int minMoves = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                String[] line = br.readLine().split(" ");
                for (int k = 0; k < SIZE; k++) {
                    maze[i][j][k] = Integer.parseInt(line[k]);
                }
            }
        }

        precomputeRotations();
        permuteLayers(0);
        System.out.println(minMoves == Integer.MAX_VALUE ? -1 : minMoves);
    }

    private static void precomputeRotations() {
        for (int i = 0; i < SIZE; i++) {
            rotatedMazes[i][0] = maze[i];
            for (int r = 1; r < 4; r++) {
                rotatedMazes[i][r] = rotate(rotatedMazes[i][r - 1]);
            }
        }
    }

    private static int[][] rotate(int[][] arr) {
        int[][] rotated = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                rotated[j][SIZE - 1 - i] = arr[i][j];
            }
        }
        return rotated;
    }

    private static void permuteLayers(int depth) {
        if (depth == SIZE) {
            permuteRotations(0);  // 회전 경우의 수 생성
            return;
        }

        for (int i = 0; i < SIZE; i++) {
            if (!used[i]) {
                used[i] = true;
                order[depth] = i;
                permuteLayers(depth + 1);
                used[i] = false;
            }
        }
    }

    private static void permuteRotations(int depth) {
        if (depth == SIZE) {
            evaluateMaze();
            return;
        }

        for (int r = 0; r < 4; r++) {
            rotations[depth] = r;
            permuteRotations(depth + 1);
        }
    }

    private static void evaluateMaze() {
        int[][][] builtMaze = new int[SIZE][SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            builtMaze[i] = rotatedMazes[order[i]][rotations[i]];
        }

        // 출발점이 막혀 있으면 스킵
        if (builtMaze[0][0][0] == 0 || builtMaze[SIZE - 1][SIZE - 1][SIZE - 1] == 0) {
            return;
        }

        int moves = bfs(builtMaze);
        if (moves != -1) {
            minMoves = Math.min(minMoves, moves);
        }
    }

    /** BFS로 최단 거리 계산 */
    private static int bfs(int[][][] maze) {
        boolean[][][] visited = new boolean[SIZE][SIZE][SIZE];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0, 0});
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], z = cur[2], depth = cur[3];

            if (x == SIZE - 1 && y == SIZE - 1 && z == SIZE - 1) {
                return depth;
            }

            if (depth >= minMoves) {
                return -1;
            }

            for (int d = 0; d < 6; d++) {
                int nx = x + dx[d], ny = y + dy[d], nz = z + dz[d];

                if (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE && nz >= 0 && nz < SIZE &&
                        !visited[nx][ny][nz] && maze[nx][ny][nz] == 1) {
                    visited[nx][ny][nz] = true;
                    queue.add(new int[]{nx, ny, nz, depth + 1});
                }
            }
        }

        return -1;  // 도착 불가능
    }
}