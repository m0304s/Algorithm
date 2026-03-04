import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N;	//자르는 횟수가 담긴 목록의 길이
	static int M;	//자를 수 있는 지점의 개수
	static int L;	//롤 케이크의 길이
	
	static int [] cuttingPoints;
	
	public static void main(String[] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		L = Integer.parseInt(tokens[2]);
		
		cuttingPoints = new int[M+1];
		
		for(int i=0;i<M;i++) {
			cuttingPoints[i] = Integer.parseInt(br.readLine());
		}

		cuttingPoints[M] = L;
		
		for(int i=0;i<N;i++) {
			int cuttingCount = Integer.parseInt(br.readLine());
			bw.write(binarySearch(cuttingCount)+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int binarySearch(int targetCount) {
		int left = 1;
		int right = L;
		int answer = 0;
		
		while(left <= right) {
			int mid = (left + right) / 2;	//가장 작은 조각의 최대 길이
			
			if(canCut(mid, targetCount)) {
				answer = mid;
				left = mid + 1;
			}else {
				right = mid - 1;
			}
		}
		return answer;
	}
	
	static boolean canCut(int minLength, int requiredCount) {
		int count = 0;
		int prev = 0;
		
		for(int i=0;i<=M;i++) {
			if(cuttingPoints[i] - prev >= minLength) {
				count++;
				prev = cuttingPoints[i];
			}
		}
		
		return count > requiredCount;
	}
}