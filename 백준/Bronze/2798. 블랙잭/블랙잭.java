import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	/**
	 * 문제 설계
	 * 뽑는 카드 조합 계산
	 * 재귀함수의 매개변수에 뽑은 카드 숫자를 포함
	 * 
	 */
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int N,M, maxSum = Integer.MIN_VALUE;
	private static int [] cards;
	public static void main(String[] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		cards = new int[N];
		tokens = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			cards[i] = Integer.parseInt(tokens[i]);
		}
		backtracking(0, 0, 0);
		bw.write(maxSum+"\n");
		bw.flush();
		br.close();
		bw.close();
	}
	private static void backtracking(int depth, int totalSum, int selectedCnt) {
		if(totalSum > M) return;
		
		if(selectedCnt == 3) {
			maxSum = Math.max(maxSum, totalSum);
			return;
		}
		
		if(depth >= N) return;
		
		backtracking(depth+1, totalSum+cards[depth], selectedCnt+1);
		backtracking(depth+1, totalSum, selectedCnt);
	}
}