import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, C;
    static int[] house;

    public static void main(String[] args) throws IOException {
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]); // 집의 개수
        C = Integer.parseInt(tokens[1]); // 설치할 공유기의 개수

        house = new int[N];

        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        int left = 1;
        int right = house[N - 1] - house[0];
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int installCnt = search(mid);

            if (installCnt < C) {
                right = mid - 1;
            } else {
                result = mid;
                left = mid + 1;
            }
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int search(int length) {
        int count = 1; // 첫 번째 집에는 무조건 공유기 설치
        int lastInstalled = house[0]; // 마지막으로 공유기를 설치한 위치

        for (int i = 1; i < N; i++) {
            if (house[i] - lastInstalled >= length) {
                count++;
                lastInstalled = house[i]; // 공유기 설치
            }
        }

        return count;
    }
}
