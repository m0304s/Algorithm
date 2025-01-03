import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int M = Integer.parseInt(br.readLine());
            int[] arr = readArray(M);

            List<Integer> result = findMedians(arr, M);
            printResult(result);
        }
        bw.flush();
        bw.close();
    }

    private static int[] readArray(int M) throws IOException {
        int[] arr = new int[M];
        int idx = 0;

        while (idx < M) {
            String[] tokens = br.readLine().split(" ");
            for (String token : tokens) {
                arr[idx++] = Integer.parseInt(token);
            }
        }
        return arr;
    }

    private static List<Integer> findMedians(int[] arr, int M) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 중앙값 이하 (최대 힙)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 중앙값 이상 (최소 힙)
        List<Integer> medians = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            if (maxHeap.isEmpty() || arr[i] <= maxHeap.peek()) {
                maxHeap.add(arr[i]);
            } else {
                minHeap.add(arr[i]);
            }

            // 균형 유지
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }

            // 중앙값 저장
            if (i % 2 == 0) { // i가 홀수일 때 중앙값을 저장
                medians.add(maxHeap.peek());
            }
        }
        return medians;
    }

    private static void printResult(List<Integer> result) throws IOException {
        bw.write(result.size() + "\n");
        for (int i = 0; i < result.size(); i++) {
            if (i > 0 && i % 10 == 0) bw.write("\n");
            bw.write(result.get(i) + " ");
        }
        bw.write("\n");
    }
}