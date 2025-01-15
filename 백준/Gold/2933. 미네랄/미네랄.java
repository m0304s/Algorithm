import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    
    private static int R, C, N;
    private static int[] heightArr;
    private static char[][] mineralMap;
    
    private static class Node {
        int x, y;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    
    public static void main(String[] args) throws IOException {
        init();
        simulation();
        print();
    }
    
    private static void init() throws IOException {
        String[] tokens = br.readLine().split(" ");
        R = Integer.parseInt(tokens[0]);
        C = Integer.parseInt(tokens[1]);
        
        mineralMap = new char[R][C];
        
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                mineralMap[i][j] = input.charAt(j);
            }
        }
        
        N = Integer.parseInt(br.readLine());
        heightArr = new int[N];
        tokens = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            heightArr[i] = Integer.parseInt(tokens[i]) - 1;
        }
    }
    
    private static void simulation() {
        boolean[][] cluster = new boolean[R][C];
        
        for (int i = 0; i < heightArr.length; i++) {
            int firstCollideIndex = findBreakMineralIndex(heightArr[i], isLeftToRight(i));
            if (firstCollideIndex == -1) continue;
            
            int breakX = R - 1 - heightArr[i];
            int breakY = firstCollideIndex;
            mineralMap[breakX][breakY] = '.';
            
            // 깨진 지점 주변 4방향만 체크
            for (int d = 0; d < 4; d++) {
                int nx = breakX + dx[d];
                int ny = breakY + dy[d];
                if (!inRange(nx, ny) || mineralMap[nx][ny] == '.') continue;
                
                // 클러스터 초기화
                for (int x = 0; x < R; x++) {
                    Arrays.fill(cluster[x], false);
                }
                
                // 바닥에 붙어있는 클러스터 찾기
                findGroundCluster(cluster);
                
                // 공중에 떠있는 클러스터 처리
                if (!cluster[nx][ny]) {
                    List<Node> floating = new ArrayList<>();
                    getFloatingCluster(nx, ny, cluster, floating);
                    if (!floating.isEmpty()) {
                        applyGravity(floating);
                    }
                }
            }
        }
    }
    
    private static void findGroundCluster(boolean[][] cluster) {
        Queue<int[]> queue = new LinkedList<>();
        
        // 바닥에 있는 미네랄을 시작점으로 추가
        for (int j = 0; j < C; j++) {
            if (mineralMap[R-1][j] == 'x') {
                cluster[R-1][j] = true;
                queue.offer(new int[]{R-1, j});
            }
        }
        
        // BFS로 바닥과 연결된 클러스터 찾기
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                
                if (!inRange(nx, ny) || cluster[nx][ny] || mineralMap[nx][ny] == '.') continue;
                
                cluster[nx][ny] = true;
                queue.offer(new int[]{nx, ny});
            }
        }
    }
    
    private static void getFloatingCluster(int x, int y, boolean[][] cluster, List<Node> floating) {
        if (!inRange(x, y) || cluster[x][y] || mineralMap[x][y] == '.') return;
        
        cluster[x][y] = true;
        floating.add(new Node(x, y));
        
        for (int d = 0; d < 4; d++) {
            getFloatingCluster(x + dx[d], y + dy[d], cluster, floating);
        }
    }
    
    private static void applyGravity(List<Node> floating) {
        int dropDistance = getDropDistance(floating);
        if (dropDistance == 0) return;
        
        // 아래쪽 좌표부터 처리
        Collections.sort(floating, (a, b) -> b.x - a.x);
        
        for (Node node : floating) {
            mineralMap[node.x][node.y] = '.';
        }
        for (Node node : floating) {
            mineralMap[node.x + dropDistance][node.y] = 'x';
        }
    }
    
    private static int getDropDistance(List<Node> floating) {
        int distance = 1;
        while (true) {
            for (Node node : floating) {
                int nx = node.x + distance;
                if (nx >= R || (mineralMap[nx][node.y] == 'x' && 
                    !floating.contains(new Node(nx, node.y)))) {
                    return distance - 1;
                }
            }
            distance++;
        }
    }
    
    private static int findBreakMineralIndex(int height, boolean direction) {
        if (direction) {    //좌측에서 던질 경우
            for (int i = 0; i < C; i++) {
                if (mineralMap[R - 1 - height][i] == 'x') {
                    return i;
                }
            }
        } else {            //우측에서 던질 경우
            for (int i = C - 1; i >= 0; i--) {
                if (mineralMap[R - 1 - height][i] == 'x') {
                    return i;
                }
            }
        }
        return -1;
    }
    
    private static boolean isLeftToRight(int idx) {
        return idx % 2 == 0;
    }
    
    private static boolean inRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
    
    private static void print() throws IOException {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                bw.write(mineralMap[i][j]);
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}