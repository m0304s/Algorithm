import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] nums;
    static int N, K;
    static List<Integer> array = new ArrayList<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            String[] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            K = Integer.parseInt(tokens[1]);
            nums = new int[N];

            tokens = br.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(tokens[i]);
            }

            answer = 0;
            backtracking(0, 0);
            bw.write("#" + t + " " + answer + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void backtracking(int index, int sum) {
        if (sum == K) {
            answer++;
        }

        for (int i = index; i < nums.length; i++) {
            array.add(nums[i]);
            // 다음 탐색은 i + 1부터 시작
            backtracking(i + 1, sum + nums[i]);
            array.remove(array.size() - 1); // 마지막 요소 제거 (백트래킹)
        }
    }
}
