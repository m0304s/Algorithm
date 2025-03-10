import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N,M,K;
	static long [] arr;
	static class SegmentTree{
		long [] tree;
		int treeSize;
		
		public SegmentTree(int arrSize) {
			int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
			
			this.treeSize = 1 << (h + 1);
			this.tree = new long[treeSize];
		}
		
		/**
		 * @param arr : 원소 배열
		 * @param node : 현재 노드
		 * @param start : 탐색 구간 배열 시작
		 * @param end : 탐색 구간 배열 끝
		 * @return
		 */
		public void init(long[] arr, int node, int start, int end) {
		    if (start == end) {
		        tree[node] = arr[start];
		    } else {
		        int mid = (start + end) / 2;
		        init(arr, node * 2, start, mid);
		        init(arr, node * 2 + 1, mid + 1, end);
		        tree[node] = tree[node * 2] + tree[node * 2 + 1];
		    }
		}

		
		/**
		 * 
		 * @param node 현재 노드
		 * @param start 배열의 시작
		 * @param end 배열의 끝
		 * @param idx 변경된 데이터의 idx
		 * @param diff 변경된 데이터 변화량
		 */
		public void update(int node, int start, int end, int idx, long diff) {
			if(idx < start || idx > end) return;
			
			tree[node] += diff;
			
			//리프노드가 아니라면 아래 자식들도 변화
			if(start != end) {
				int mid = (start + end) /2;
				update(node*2,start,mid,idx,diff);
				update(node*2+1,mid+1,end,idx,diff);
			}
		}
		
		public long sum(int node, int start, int end, int left, int right) {
			//범위를 전부 벗어나게 되는 경우
			if(left > end || right < start) {
				return 0;
			}
			
			//범위 내 완전히 포함되는 경우
			if(left <= start && end <= right) {
				return tree[node];
			}
			
			int mid = (start + end)/2;
			return sum(node*2,start,mid,left,right) +
					sum(node*2+1,mid+1,end,left,right);
		}
	}
	
	public static void main(String[] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);	//수의 개수
		M = Integer.parseInt(tokens[1]);	//변경이 일어나는 횟수
		K = Integer.parseInt(tokens[2]);	//구간의 합을 구하는 횟수
		
		arr = new long[N];
		for(int i=0;i<N;i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		SegmentTree st = new SegmentTree(N);
		st.init(arr, 1, 0, N-1);
		for(int i=0;i<M+K;i++) {
			tokens = br.readLine().split(" ");
			int type = Integer.parseInt(tokens[0]);
			if(type == 1) {
				int idx = Integer.parseInt(tokens[1])-1;
				long value = Long.parseLong(tokens[2]);
				long diff = value - arr[idx];
				st.update(1, 0, N-1, idx, diff);
				arr[idx] = value; // arr 배열을 갱신

			}else {
				int left = Integer.parseInt(tokens[1])-1;
				int right = Integer.parseInt(tokens[2])-1;
				
				long result = st.sum(1, 0, N-1, left, right);
				bw.write(result+"\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
