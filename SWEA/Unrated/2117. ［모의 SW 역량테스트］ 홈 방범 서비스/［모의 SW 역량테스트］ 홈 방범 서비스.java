import java.io.*;

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
        
        for (int k = 1; k <= 2 * N; k++) { 
            int cost = k * k + (k - 1) * (k - 1);
            // 만약 최대 매출(모든 집이 있는 경우)이 cost보다 작다면, 이후 k는 이익 조건을 만족할 수 없음.
            if (N * N * M < cost) continue;
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int houseCnt = calcHouseInRange(i, j, k); // (i,j)를 중심으로 k 범위 내 집 개수
                    int profit = houseCnt * M - cost;
                    if (profit >= 0) {
                        maxHouse = Math.max(maxHouse, houseCnt);
                    }
                }
            }
        }

        return maxHouse;
    }

    private static int calcHouseInRange(int x, int y, int k) {
        int houseCnt = 0;
        for (int dx = -k + 1; dx < k; dx++) {
            for (int dy = - (k - 1 - Math.abs(dx)); dy <= (k - 1 - Math.abs(dx)); dy++) {
                int nx = x + dx;
                int ny = y + dy; 
                if (inRange(nx, ny) && map[nx][ny] == 1) {
                    houseCnt++;
                }
            }
        }
        return houseCnt;
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
