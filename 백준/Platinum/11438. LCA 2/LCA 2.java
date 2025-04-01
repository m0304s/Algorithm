import java.io.*;
import java.util.*;

public class Main {
    static int N, LOG;
    static List<Integer>[] tree;
    static int[][] parent;
    static int[] depth;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static void main(String[] args) throws IOException {
    	N = Integer.parseInt(br.readLine());
    	LOG = (int) (Math.log(N) / Math.log(2)) + 1;
    	
    	tree = new ArrayList[N+1];
    	for(int i=0;i<=N;i++) {
    		tree[i] = new ArrayList<>();
    	}
    	
    	for(int i=0;i<N-1;i++) {
    		String [] tokens = br.readLine().split(" ");
    		int u = Integer.parseInt(tokens[0]);
    		int v = Integer.parseInt(tokens[1]);
    		
    		tree[u].add(v);
    		tree[v].add(u);
    	}
    	
    	preprocess(1);	//1번 노드를 루트로 하여 전처리
    	
    	int Q = Integer.parseInt(br.readLine());
    	for(int q=0;q<Q;q++) {
    		String [] tokens = br.readLine().split(" ");
    		int u = Integer.parseInt(tokens[0]);
    		int v = Integer.parseInt(tokens[1]);
    		
    		int result = query(u,v);
    		bw.write(result+"\n");
    	}
    	bw.flush();
    	bw.close();
    	br.close();
    }
    
    static int query(int u,int v) {
    	//u의 깊이를 항상 v보다 깊이 유지
    	if(depth[u] < depth[v]) {
    		int temp = u;
    		u = v;
    		v = temp;
    	}
    	
    	//깊이 차이를 맞춤
    	for(int i=LOG-1; i>=0; i--) {
    		if(depth[u] - (1<<i) >= depth[v]) u = parent[u][i];
    	}
    	
    	//같은 노드라면 LCA 반환
    	if(u == v) return u;
    	
    	//공통 조상을 찾기 위해 이진 리프팅 수행
    	for(int i=LOG-1; i>=0 ; i--) {
    		if(parent[u][i] != parent[v][i]) {
    			u = parent[u][i];
    			v = parent[v][i];
    		}
    	}
    	
    	return parent[u][0];
    }
    
    static void preprocess(int root) {
    	parent = new int[N+1][LOG];	//parent[i][j] : i번 노드의 2^j번째 부모 저장
    	depth = new int[N+1];		//각 노드의 깊이 저장
    	Arrays.fill(depth, -1);		//방문하지 않은 노드는 -1로 설정
    	
    	dfs(root,-1);				//DFS 수행하여 depth 및 parent[0] 초기화
    	
    	//희소 테이블 초기화
    	for(int j=1;j<LOG;j++) {
    		for(int i=1;i<=N;i++) {
    			if(parent[i][j-1] != -1) {
    				parent[i][j] = parent[parent[i][j-1]][j-1];
    			}
    		}
    	}
    }
    
    static void dfs(int node, int par) {	//dfs를 이용해 깊이와 첫번째 부모 설정
    	parent[node][0] = par;	//바로 위 부모 저장
    	depth[node] = (par == -1) ? 0 : depth[par] + 1;	//부모의 깊이 + 1
    	
    	for(int child : tree[node]) {
    		if(child != par) {	//부모 노드로 돌아가지 않는 경우
    			dfs(child,node);
    		}
    	}
    }
    
    
}
