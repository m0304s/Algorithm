import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static long [] tree;
	static long [] arr;
	static int N,M,K;
	static int MOD = 1000000007;
	
	public static void main(String [] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		
		N = Integer.parseInt(tokens[0]);	//수의 개수
		M = Integer.parseInt(tokens[1]);	//수의 변경이 일어나는 횟수
		K = Integer.parseInt(tokens[2]);	//구간의 곱을 구하는 횟수
		
		tree = new long[4*N];
		arr = new long[N+1];	//배열 인덱스는 1부터 시작
		
		for(int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		init(1, 1, N);
		
		for(int i=0;i<M+K;i++) {
			tokens = br.readLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			long c = Long.parseLong(tokens[2]);
			
			if(a == 1) {
				//b번쨰 수를 c로 변경
				update(1, 1, N, b, c);
				arr[b] = c;
			}else {
				bw.write(query(1, 1, N, b, (int)c) +"\n");
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static long query(int node, int start, int end, int left, int right) {
		if(left > end || right < start) {
			return 1;
		}
		
		if(left <= start && end <= right) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		
		return query(node * 2, start, mid, left, right) * query(node * 2 + 1, mid + 1, end, left, right) % MOD;
	}
	
	static long init(int node, int start, int end) {
		if(start == end) {
			return tree[node] = arr[start];
		}
		
		int mid = (start + end) / 2;
		
		return tree[node] = init(node * 2, start, mid) * init(node * 2 + 1, mid + 1, end) % MOD;
	}
	
	static long update(int node, int start, int end, int index, long val) {
		if(index < start || index > end) {
			return tree[node];
		}
		
		if(start == end) {
			return tree[node] = val;
		}
		
		int mid = (start + end) / 2;
		return tree[node] = update(node * 2, start, mid , index, val) * update(node * 2 + 1, mid + 1, end, index, val) % MOD;
	}
	
	
}