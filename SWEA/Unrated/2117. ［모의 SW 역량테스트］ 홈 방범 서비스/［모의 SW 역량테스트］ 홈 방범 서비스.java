import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            bw.write("#" + t + " " + solution() + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    static int solution() throws IOException {
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]); // 도시 크기
        M = Integer.parseInt(tokens[1]); // 지불 비용
        int maxHouse = 0;
        map = new int[N][N];

        // 지도 입력
        for (int i = 0; i < N; i++) {
            tokens = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        
        for (int k = 1; k <= N+1; k++) { 
            int cost = k * k + (k - 1) * (k - 1);
            // 만약 그리드 전체의 최대 매출이 cost보다 작다면 더 이상 의미가 없음
            if (N * N * M < cost) continue;
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // BFS를 이용하여 (i,j)를 중심으로 마름모 범위 내 집 개수 계산
                    int houseCnt = calcHouseInRangeBFS(i, j, k);
                    int profit = houseCnt * M - cost;
                    if (profit >= 0) {
                        maxHouse = Math.max(maxHouse, houseCnt);
                    }
                }
            }
        }

        return maxHouse;
    }

    // BFS로 (x,y)를 중심으로 맨해튼 거리 k-1 이하인 영역(마름모)의 집 개수를 세는 메서드
    private static int calcHouseInRangeBFS(int x, int y, int k) {
        int houseCnt = 0;
        boolean[][] visited = new boolean[N][N];
        Queue<Point> queue = new LinkedList<>();
        
        // 시작점: (x, y)에서 거리 0부터 시작
        queue.add(new Point(x, y, 0));
        visited[x][y] = true;
        
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            // 현재 위치가 집이면 카운트 증가
            if (map[cur.x][cur.y] == 1) {
                houseCnt++;
            }
            // 거리가 k-1에 도달하면 더 확장하지 않음
            if (cur.dist == k - 1) continue;
            // 4방 탐색 (상, 하, 좌, 우)
            for (int[] d : new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}}) {
                int nx = cur.x + d[0];
                int ny = cur.y + d[1];
                if (inRange(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny, cur.dist + 1));
                }
            }
        }
        return houseCnt;
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    // BFS 탐색을 위한 좌표와 거리를 저장하는 내부 클래스
    private static class Point {
        int x, y, dist;
        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
