import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int S = Integer.parseInt(br.readLine());

        // 각 인덱스를 순회하며 해당 위치에 올 수 있는 가장 큰 값을 찾기
        for (int i = 0; i < N && S > 0; i++) {
            
            int maxVal = -1;
            int maxIdx = -1;

            //i 부터 (i+S) 까지 닿을 수 있는 범위만큼 가장 큰 숫자 탐색
            for (int j = i; j < N && j <= i + S; j++) {
                if (arr[j] > maxVal) {
                    maxVal = arr[j];
                    maxIdx = j;
                }
            }
            
            // 찾은 가장 큰 값을 i 위치로 이동
            // maxIdx부터 i+1까지, 현재 원소를 왼쪽 원소와 교환 반복
            for (int j = maxIdx; j > i; j--) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }

            // 사용한 교환 횟수 차감
            S -= (maxIdx - i);
        }

        for(int i : arr) {
        	bw.write(i+" ");
        }
        bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
    }
}