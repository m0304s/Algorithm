import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static final int MAX = 1000000000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, K;

    public static void main(String[] args) throws IOException {
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        K = Integer.parseInt(tokens[1]);

        int answer = dijkstra();
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static int dijkstra() {
        int[] dist = new int[100001]; // 각 좌표까지 도달하는 최소 시간
        Arrays.fill(dist, MAX);

        // 우선순위 큐: {누적 시간, 좌표}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        pq.add(new int[]{0, N});
        dist[N] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curTime = cur[0];
            int curPoint = cur[1];

            // 목표 지점 도달 시 종료
            if (curPoint == K) return curTime;

            // 순간이동
            int newPoint = curPoint * 2;
            if (newPoint <= 100000 && dist[newPoint] > curTime) {
                dist[newPoint] = curTime;
                pq.add(new int[]{curTime, newPoint});
            }

            // 뒤로 걷기
            newPoint = curPoint - 1;
            if (newPoint >= 0 && dist[newPoint] > curTime + 1) {
                dist[newPoint] = curTime + 1;
                pq.add(new int[]{curTime + 1, newPoint});
            }

            // 앞으로 걷기
            newPoint = curPoint + 1;
            if (newPoint <= 100000 && dist[newPoint] > curTime + 1) {
                dist[newPoint] = curTime + 1;
                pq.add(new int[]{curTime + 1, newPoint});
            }
        }

        return dist[K];
    }
}
