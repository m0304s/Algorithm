import java.io.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N,M,C;
	static int [][] map;
	static int maxHoney;
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			int answer = solution();
			bw.write("#"+t+" "+answer+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int solution() throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		C = Integer.parseInt(tokens[2]);
		maxHoney = Integer.MIN_VALUE;
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			tokens = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		int [] selected = new int[2];
		
		backtracking(0,0,selected);
		return maxHoney;
	}
	
	static void backtracking(int depth, int index, int [] selected) {
		if(depth == 2) {
			//2개의 벌통을 선택한 경우
			int r1 = selected[0] / N;
			int c1 = selected[0] % N;
			int r2 = selected[1] / N;
			int c2 = selected[1] % N;
			
            if(c1 + M > N || c2 + M > N) return;
            if(r1 == r2 && c1 + M > c2) return;
            
			int honey = getMaxHoney(selected[0], selected[1]);
			maxHoney = Math.max(maxHoney, honey);
			return;
		}
		
		for(int i=index;i<N*N;i++) {
			selected[depth] = i;
			backtracking(depth+1,i+1,selected);
		}
	}

	private static int getMaxHoney(int i, int j) {
		//채집할 수 있는 최대의 꿀 반환
		int sumA = getSum(0,i,0);
		int sumB = getSum(0,j,0);
		
		return sumA + sumB;
	}
	
	private static int getSum(int depth, int index, int dummy) {
	    int r = index / N;
	    int c = index % N;
	    int[] seg = new int[M];
	    for (int k = 0; k < M; k++) {
	        seg[k] = map[r][c + k];
	    }
	    return getSubsetMax(seg, 0, 0, 0);
	}

	private static int getSubsetMax(int[] seg, int pos, int currentSum, int currentProfit) {
	    if (pos == seg.length) {
	        return currentProfit;
	    }
	    int ret = currentProfit;
	    // 현재 칸을 선택하지 않는 경우
	    ret = Math.max(ret, getSubsetMax(seg, pos + 1, currentSum, currentProfit));
	    // 현재 칸을 선택하는 경우
	    if (currentSum + seg[pos] <= C) {
	        ret = Math.max(ret, getSubsetMax(seg, pos + 1, currentSum + seg[pos], currentProfit + seg[pos] * seg[pos]));
	    }
	    return ret;
	}

}
