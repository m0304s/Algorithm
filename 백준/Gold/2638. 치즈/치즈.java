import java.io.*;
import java.util.*;

public class Main {
    private static final int AIR = 2; // 외부 공기
    private static final int CHEESE = 1; // 치즈
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N, M;
    private static int[][] map;
    private static ArrayList<Node> cheese;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        map = new int[N][M];
        cheese = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            tokens = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens[j]);
                if (map[i][j] == CHEESE)
                    cheese.add(new Node(i, j));
            }
        }

        map[0][0] = AIR;
        checkOutSide(new Node(0, 0)); // 초기 외부 공기 영역 탐색

        int time = 0;
        while (!cheese.isEmpty()) {
            melt();
            checkOutSide(new Node(0, 0)); // 외부 공기 갱신
            time++;
        }

        bw.write(time + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void melt() {
        Queue<Node> meltingCheese = new LinkedList<>();
        ArrayList<Node> remainingCheese = new ArrayList<>();

        for (Node node : cheese) {
            int contactWithAir = 0;

            for (int d = 0; d < 4; d++) {
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];

                if (!inRange(nx, ny)) continue;
                if (map[nx][ny] == AIR) contactWithAir++;
            }

            if (contactWithAir >= 2) {
                meltingCheese.add(node);
            } else {
                remainingCheese.add(node);
            }
        }

        while (!meltingCheese.isEmpty()) {
            Node meltedNode = meltingCheese.poll();
            map[meltedNode.x][meltedNode.y] = AIR;
//
//            // 새롭게 녹은 치즈의 좌표를 외부 공기로 탐색
//            checkOutSide(meltedNode);
        }

        cheese = remainingCheese;
    }

    private static void checkOutSide(Node node) {
        Queue<Node> airQueue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        airQueue.add(node);
        visited[node.x][node.y] = true;

        while (!airQueue.isEmpty()) {
            Node cur = airQueue.poll();
            if (map[cur.x][cur.y] == 0) map[cur.x][cur.y] = AIR;

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (!inRange(nx, ny) || visited[nx][ny] || map[nx][ny] == CHEESE) continue;

                visited[nx][ny] = true;
                airQueue.add(new Node(nx, ny));
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}