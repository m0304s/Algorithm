import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static long [] arr;
	static long [] segTree;
	static int N,M,K;
	
	public static void main(String [] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		K = Integer.parseInt(tokens[2]);
		
		arr = new long[N+1];
		segTree = new long[4*N+1];
		
		for(int i=1;i<=N;i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		init(1, 1, N);
		
		for(int i=0;i<M+K;i++) {
			tokens = br.readLine().split(" ");
			int trigger = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			long c = Long.parseLong(tokens[2]);

			if(trigger == 1) {
				long diff = c - arr[b];
				arr[b] = c;
				update(1, 1, N, b, diff);
			}else {
				bw.write(query(1, 1, N, b, c)+"\n");
				bw.flush();
			}
		}
		bw.close();
		br.close();
	}
	
	/**
	 * node : 현재 노드의 번호
	 * start : 담당 구간의 시작
	 * end : 담당 구간의 종료
	 */
	private static long init(int node, int start, int end) {
		if(start == end) return segTree[node] = arr[start];
		
		int mid = (start + end) / 2;
			
		return segTree[node] = init(node * 2, start, mid) + init(node*2 + 1, mid+1, end);
	}
	
	/**
	 * node : 현재 노드의 번호
	 * start : 담당 구간의 시작
	 * end : 담당 구간의 종료
	 * idx : 원본 배열의 인덱스
	 * diff : 변화량
	 */
	private static void update(int node, int start, int end, int idx, long diff) {
		//조회 구간이 전혀 영향이 없을 때
		if(idx < start || idx > end) return;
		
		//구간 내에 포함될 경우 값을 업데이트
		segTree[node] += diff;
		
		if(start != end) {
			int mid = (start + end) / 2;
			update(node * 2, start, mid, idx, diff);
			update(node * 2 + 1, mid+1, end, idx, diff);	
		}
	}
	
	/**
	 * node : 현재 노드의 번호
	 * start : 담당 구간의 시작
	 * end : 담당 구간의 종료
	 * left : 조회 구간의 시작
	 * right : 조회 구간의 종료
	 */
	private static long query(int node, int start, int end, int left, long right) {
		if(right < start || end < left) return 0;
		
		if(left <= start && end <= right) return segTree[node];
		
		int mid = (start + end) / 2;
		return query(node*2, start, mid, left, right) + query(node*2 + 1, mid+1, end, left, right);
	}
}