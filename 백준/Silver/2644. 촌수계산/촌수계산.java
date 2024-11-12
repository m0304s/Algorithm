import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, START, TARGET;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];
        String[] tokens = br.readLine().split(" ");
        START = Integer.parseInt(tokens[0]);
        TARGET = Integer.parseInt(tokens[1]);

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            tokens = br.readLine().split(" ");
            int v1 = Integer.parseInt(tokens[0]);
            int v2 = Integer.parseInt(tokens[1]);

            graph[v1][v2] = 1;
            graph[v2][v1] = 1;
        }

        int answer = bfs();
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        visited[START] = true;
        queue.add(START);

        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size(); // Nodes at the current level

            for (int i = 0; i < size; i++) {
                int node = queue.poll();

                if (node == TARGET) {
                    return count;
                }

                for (int j = 0; j < graph.length; j++) {
                    if (graph[node][j] == 1 && !visited[j]) {
                        queue.add(j);
                        visited[j] = true;
                    }
                }
            }
            count++;
        }
        return -1;
    }
}
