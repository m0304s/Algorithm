import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

	/**
	 * 2차원 배열의 누적합 사용
	 * 1. 2차원 원본 배열 입력
	 * 2. 2차원 누적합 배열 생성
	 * 3. MxM 윈도우 크기 생성 후 잡을 수 있는 파리 개수의 최대값 계산
	 * @param args
	 */
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    private static int N, M, maxCount;
    private static int[][] arr;
    private static int[][] sum;
    
	public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            String[] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            M = Integer.parseInt(tokens[1]);

            maxCount = Integer.MIN_VALUE;
            arr = new int[N][N];
            sum = new int[N][N];

            // 원본 배열 입력
            for (int i = 0; i < N; i++) {
                tokens = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(tokens[j]);
                }
            }

            // 누적 합 배열 초기화
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sum[i][j] = arr[i][j];
                    if (i > 0) sum[i][j] += sum[i - 1][j]; // 위쪽 누적 합 더하기
                    if (j > 0) sum[i][j] += sum[i][j - 1]; // 왼쪽 누적 합 더하기
                    if (i > 0 && j > 0) sum[i][j] -= sum[i - 1][j - 1]; // 중복 제거
                }
            }

            // MxM 영역에서 최대 파리 개수 계산
            for (int i = 0; i <= N - M; i++) {
                for (int j = 0; j <= N - M; j++) {
                    int x1 = i, y1 = j;
                    int x2 = i + M - 1, y2 = j + M - 1;

                    int count = sum[x2][y2];
                    if (x1 > 0) count -= sum[x1 - 1][y2]; // 위쪽 제거
                    if (y1 > 0) count -= sum[x2][y1 - 1]; // 왼쪽 제거
                    if (x1 > 0 && y1 > 0) count += sum[x1 - 1][y1 - 1]; // 중복 제거된 값 추가

                    maxCount = Math.max(maxCount, count);
                }
            }

            // 결과 출력
            bw.write("#" + t + " " + maxCount + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}