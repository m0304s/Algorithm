import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N,M,K;
	static int [] arr;
	static long [] segTree;
	static int MOD = 1000000007;
	
	public static void main(String[] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		K = Integer.parseInt(tokens[2]);
		
		arr = new int[N+1];
		segTree = new long[4*N+1];
		
		for(int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		init(1, 1, N);
		
		for(int i=0;i<M+K;i++) {
			tokens = br.readLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			int c = Integer.parseInt(tokens[2]);
			
			if(a == 1) {
				//b번째 수를 c로 바꿈
				arr[b] = c;
				update(1, 1, N, b, c);
			}else {
				//b부터 c까지의 곱을 출력
				long answer = query(1, 1, N, b, c);
				bw.write(answer+"\n");
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	/**
	 * @param node : 트리의 번호
	 * @param start : 현재 노드가 관리하는 구간 시작점
	 * @param end : 현재 노드가 관리하는 구간 종료점
	 * @param left : 조회 시작 구간
	 * @param right : 조회 종료 구간
	 * @return
	 */
	private static long query(int node, int start, int end, int left, int right) {
		//완전히 구간을 벗어나는 경우
		if(end < left || right < start) return 1;
		
		//구간에 완전히 포함되는 경우
		if(left <= start && end <= right) return segTree[node];
		
		int mid = (start + end) / 2;
		return (query(node*2, start, mid, left, right) * query(node*2+1, mid+1, end, left, right)) % MOD;
	}
	
	/**
	 * @param node : 트리의 번호
	 * @param start : 현재 노드가 관리하는 구간 시작점
	 * @param end : 현재 노드가 관리하는 구간 종료점
	 * @return
	 */
	private static long init(int node, int start, int end) {
		if(start == end) {
			return segTree[node] = arr[start];
		}
		
		int mid = (start + end) / 2;
		return segTree[node] = (init(node*2, start, mid) * init(node*2+1, mid+1, end)) % MOD;
	}
	
	/**
	 * @param node : 트리의 번호
	 * @param start : 현재 노드가 관리하는 구간 시작점
	 * @param end : 현재 노드가 관리하는 구간 종료점
	 * @param idx : 값이 변하는 arr 인덱스
	 * @param diff : 값의 변화량
	 * @return
	 */
	private static long update(int node, int start, int end, int idx, int diff) {
		//만약 구간을 완전히 벗어나면
		if(idx < start || idx > end) return segTree[node];
		
		if(start == end) {
			return segTree[node] = diff;
		}
		
		int mid = (start + end) / 2;
		return segTree[node] = (update(node*2, start, mid, idx, diff) * update(node*2+1, mid+1, end, idx, diff)) % MOD;
	}
}