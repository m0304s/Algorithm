import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M, K;
    static int minSum = Integer.MAX_VALUE;
    static int[][] originalMap;
    static Rotate[] command;
    
    static class Rotate {
        int r, c, s;
        public Rotate(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
    
    public static void main(String[] args) throws IOException {
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        K = Integer.parseInt(tokens[2]);
        
        originalMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            tokens = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                originalMap[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        
        command = new Rotate[K];
        for (int i = 0; i < K; i++) {
            tokens = br.readLine().split(" ");
            int r = Integer.parseInt(tokens[0]);
            int c = Integer.parseInt(tokens[1]);
            int s = Integer.parseInt(tokens[2]);
            command[i] = new Rotate(r, c, s);
        }
        
        backtracking(0, new int[K], new boolean[K]);
        
        bw.write(minSum + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static void backtracking(int depth, int[] order, boolean[] visited) {
        if (depth == K) {
            int[][] copiedMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                copiedMap[i] = Arrays.copyOf(originalMap[i], M);
            }
            for (int i = 0; i < K; i++) {
                Rotate cmd = command[order[i]];
                applyRotation(copiedMap, cmd);
            }

            for (int i = 0; i < N; i++) {
                int sum = 0;
                for (int j = 0; j < M; j++) {
                    sum += copiedMap[i][j];
                }
                minSum = Math.min(minSum, sum);
            }
            return;
        }
        
        for (int i = 0; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[depth] = i;
                backtracking(depth + 1, order, visited);
                visited[i] = false;
            }
        }
    }
    private static void applyRotation(int[][] map, Rotate rotate) {
        int r = rotate.r, c = rotate.c, s = rotate.s;
        int x1 = r - s - 1;
        int y1 = c - s - 1;
        int x2 = r + s - 1;
        int y2 = c + s - 1;
        
        int height = x2 - x1 + 1;
        int width = y2 - y1 + 1;

        int[][] sub = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sub[i][j] = map[x1 + i][y1 + j];
            }
        }
        
        int layers = Math.min(height, width) / 2;
        for (int layer = 0; layer < layers; layer++) {
            int top = layer, left = layer;
            int bottom = height - layer - 1, right = width - layer - 1;
            
            int temp = sub[top][left];
            
            // 1. 왼쪽 열 위로 당기기
            for (int i = top; i < bottom; i++) {
                sub[i][left] = sub[i + 1][left];
            }
            
            // 2. 아래쪽 행 왼쪽으로 당기기
            for (int j = left; j < right; j++) {
                sub[bottom][j] = sub[bottom][j + 1];
            }
            
            // 3. 오른쪽 열 아래로 당기기
            for (int i = bottom; i > top; i--) {
                sub[i][right] = sub[i - 1][right];
            }
            
            // 4. 위쪽 행 오른쪽으로 당기기
            for (int j = right; j > left + 1; j--) {
                sub[top][j] = sub[top][j - 1];
            }
            
            // 저장해둔 값 복원
            sub[top][left + 1] = temp;
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[x1 + i][y1 + j] = sub[i][j];
            }
        }
    }
}