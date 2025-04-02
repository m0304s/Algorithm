import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static int[][] map;
    static int startX, startY; // 맨홀의 위치
    static int L;             // 탈출 후 소요된 시간

    // 상, 하, 좌, 우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    
    // 각 방향의 반대 방향
    static int[] opposite = { 1, 0, 3, 2 };
    
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            bw.write("#" + t + " " + solution() + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    
    static int solution() throws IOException {
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        startX = Integer.parseInt(tokens[2]);
        startY = Integer.parseInt(tokens[3]);
        L = Integer.parseInt(tokens[4]);
        map = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            tokens = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        
        Node startPoint = new Node(startX, startY, 1);
        return bfs(startPoint);
    }
    
    static int bfs(Node startPoint) {
        boolean[][] visited = new boolean[N][M];
        Queue<Node> queue = new ArrayDeque<>();
        visited[startPoint.x][startPoint.y] = true;
        queue.add(startPoint);
        
        int available = 0;  // 탈주범이 존재할 수 있는 위치의 수
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            if (curNode.time > L) continue;
            available++;
            if (curNode.time == L) continue;
            
            int curType = map[curNode.x][curNode.y];
            int[] curMovable = getMovablePosition(curType);
            if (curMovable == null) continue;
            
            for (int d : curMovable) {
                int nx = curNode.x + dx[d];
                int ny = curNode.y + dy[d];
                
                if (!inRange(nx, ny) || visited[nx][ny]) continue;
                if (map[nx][ny] == 0) continue;
                
                // 현재 방향 d에 대해, 이웃 터널이 반대 방향으로 열려있는지 확인
                int[] neighborMovable = getMovablePosition(map[nx][ny]);
                if (!canConnect(neighborMovable, opposite[d])) continue;
                
                visited[nx][ny] = true;
                queue.add(new Node(nx, ny, curNode.time + 1));
            }
        }
        
        return available;
    }
    
    // 해당 터널 타입에서 이동 가능한 방향 배열을 반환
    // 방향 인덱스: 0: up, 1: down, 2: left, 3: right
    static int[] getMovablePosition(int type) {
        switch (type) {
            case 1: return new int[] { 0, 1, 2, 3 };
            case 2: return new int[] { 0, 1 };
            case 3: return new int[] { 2, 3 };
            case 4: return new int[] { 0, 3 };
            case 5: return new int[] { 1, 3 };
            case 6: return new int[] { 1, 2 };
            case 7: return new int[] { 0, 2 };
            default: return null;
        }
    }
    
    // 이웃 터널의 이동 가능 방향 배열에 'dir'이 포함되어 있는지 확인
    static boolean canConnect(int[] neighborMovable, int dir) {
        if (neighborMovable == null) return false;
        for (int d : neighborMovable) {
            if (d == dir) return true;
        }
        return false;
    }
    
    static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
    
    static class Node {
        int x, y, time;
        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
