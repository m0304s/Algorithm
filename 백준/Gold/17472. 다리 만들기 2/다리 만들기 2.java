import java.io.*;
import java.util.*;

class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // 좌표를 나타내는 Node 클래스
    private static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node other = (Node)obj;
            return this.x == other.x && this.y == other.y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        @Override
        public String toString() {
            return "X : " + this.x + " Y : " + this.y;
        }
    }

    // 간선을 나타내는 클래스 (크루스칼 알고리즘에서 사용)
    private static class Graph implements Comparable<Graph> {
        int from, to, cost;
        public Graph(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Graph o) {
            return this.cost - o.cost;
        }
    }

    private static int N, M;
    private static int[][] map;
    // 부모 배열은 섬의 개수(섬 번호 1부터 사용)를 기준으로 할당합니다.
    private static int[] parent; 
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
    // 각 섬의 번호(1부터) → 해당 섬의 좌표들을 저장
    private static HashMap<Integer, HashSet<Node>> nodeMap;
    private static boolean[][] visited;
    // 각 섬간 다리 정보를 저장할 그래프 (섬 번호를 인덱스로 사용)
    private static ArrayList<ArrayList<Graph>> graph;

    public static void main(String[] args) throws IOException {
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        map = new int[N][M];
        visited = new boolean[N][M];
        nodeMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            tokens = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        // BFS를 이용해 섬을 구분(섬 번호는 1부터 부여)
        int islandCnt = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    nodeMap.put(islandCnt, bfs(islandCnt, i, j));
                    islandCnt++;
                }
            }
        }
        int numIslands = islandCnt - 1; // 실제 섬의 개수

        // 섬 번호를 인덱스로 사용하므로, dist 배열의 크기를 islandCnt x islandCnt로 할당
        int[][] dist = new int[islandCnt][islandCnt];
        for (int i = 0; i < islandCnt; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // 각 섬의 모든 점에서 4방향으로 다리 놓기를 시도 (다리 길이는 2 이상이어야 함)
        for (int i = 1; i < islandCnt; i++) {
            HashSet<Node> points = nodeMap.get(i);
            for (Node point : points) {
                // 4방향(좌, 우, 상, 하)
                for (int d = 0; d < 4; d++) {
                    int curX = point.x;
                    int curY = point.y;
                    int curLength = 0;

                    while (true) {
                        int nx = curX + dx[d];
                        int ny = curY + dy[d];
                        // 범위를 벗어나거나, 같은 섬의 내부면 중단
                        if (!inRange(nx, ny) || points.contains(new Node(nx, ny))) break;
                        // 다른 섬에 도달하기 전 물이면 계속 진행
                        if (map[nx][ny] > 0) break;
                        curLength++;
                        curX = nx;
                        curY = ny;
                    }
                    if (curLength < 2) continue;

                    int nx = curX + dx[d];
                    int ny = curY + dy[d];
                    if (inRange(nx, ny) && map[nx][ny] > 0) {
                        int islandA = map[point.x][point.y];
                        int islandB = map[nx][ny];
                        if (islandA == islandB) continue;
                        dist[islandA][islandB] = Math.min(dist[islandA][islandB], curLength);
                        dist[islandB][islandA] = dist[islandA][islandB];
                    }
                }
            }
        }

        // 그래프 리스트는 섬 번호(0~islandCnt-1)를 인덱스로 사용하므로 크기를 islandCnt로 할당
        graph = new ArrayList<>();
        for (int i = 0; i < islandCnt; i++) {
            graph.add(new ArrayList<>());
        }
        // dist 배열을 바탕으로 간선(다리) 리스트 구성 (섬 번호는 1부터 사용하므로 0번은 비워둠)
        for (int i = 1; i < islandCnt; i++) {
            for (int j = i + 1; j < islandCnt; j++) {
                if (dist[i][j] != Integer.MAX_VALUE) {
                    graph.get(i).add(new Graph(i, j, dist[i][j]));
                    graph.get(j).add(new Graph(j, i, dist[i][j])); // 무방향 그래프이므로 양쪽에 추가
                }
            }
        }

        // 간선 리스트로 옮기기 (MST용)
        ArrayList<Graph> edges = new ArrayList<>();
        for (int i = 1; i < islandCnt; i++) {
            edges.addAll(graph.get(i));
        }
        Collections.sort(edges);

        // 부모 배열도 섬 개수(인덱스 0~islandCnt-1)를 기준으로 할당합니다.
        parent = new int[islandCnt];
        for (int i = 0; i < islandCnt; i++) {
            parent[i] = i;
        }

        int minCost = 0;
        int edgeCount = 0;
        // 크루스칼 알고리즘으로 MST 구성 (필요한 간선의 개수는 numIslands - 1)
        for (Graph edge : edges) {
            int rootA = find(edge.from);
            int rootB = find(edge.to);
            if (rootA != rootB) {
                union(rootA, rootB);
                minCost += edge.cost;
                edgeCount++;
            }
            if (edgeCount == numIslands - 1) break;
        }
        if (edgeCount != numIslands - 1) {
            bw.write("-1\n");
        } else {
            bw.write(minCost + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA > rootB)
            parent[rootA] = rootB;
        else
            parent[rootB] = rootA;
    }

    // BFS를 통해 한 섬에 속한 모든 좌표를 찾고 섬 번호로 라벨링
    private static HashSet<Node> bfs(int islandNum, int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> pointList = new HashSet<>();
        visited[x][y] = true;
        queue.add(new Node(x, y));
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            pointList.add(curNode);
            map[curNode.x][curNode.y] = islandNum;
            for (int d = 0; d < 4; d++) {
                int nx = curNode.x + dx[d];
                int ny = curNode.y + dy[d];
                if (!inRange(nx, ny) || visited[nx][ny] || map[nx][ny] == 0)
                    continue;
                visited[nx][ny] = true;
                queue.add(new Node(nx, ny));
            }
        }
        return pointList;
    }

    private static boolean inRange(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M);
    }
}
